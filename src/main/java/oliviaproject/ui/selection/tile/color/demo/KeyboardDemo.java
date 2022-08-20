package oliviaproject.ui.selection.tile.color.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import oliviaproject.util.file.FileUtils;

public class KeyboardDemo {
	JPanel panel;
	Scanner scanner;
	Color[] colors;
	Map<String, MoveObject> movedObjects = new HashMap<>();

	public Map<String, MoveObject> getMovedObjects() {
		return movedObjects;
	}
	public void setMovedObjects(Map<String, MoveObject> movedObjects) {
		this.movedObjects = movedObjects;
	}
	public KeyboardDemo(JPanel panel, String filePath, Color[] colors) {
		this.panel=panel;
		InputStream inp = FileUtils.getFileFromResourceAsStream("/animation/animation.txt");
		scanner = new Scanner(inp);
		this.colors=colors;
	}
	public String readnextLine(Boolean reinit) {
		String line = null;
		if (scanner.hasNext() && !reinit) {
			line = scanner.nextLine();
		} else {
			scanner.close();
			InputStream inp = FileUtils.getFileFromResourceAsStream("/animation/animation.txt");
			scanner = new Scanner(inp);
		}
		return line;
	}

	private void reInitializeKeyboard() {
		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                "pressed");
panel.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"),
                "released");
	        Action pressedAction= new AbstractAction() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						createMovedObjects(readnextLine(true), colors);
					} catch (Exception ex) {
						
					}
				}};;

				panel.getActionMap().put("pressed",
				        pressedAction);

				panel.getInputMap().put(KeyStroke.getKeyStroke("F2"),
                        "doSomething");
Action anAction= new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				initializeSequencer();
				} catch (Exception ex) {
				
			}
		}};;
		panel.getActionMap().put("doSomething",
                anAction);
	}
	public void initializeKeyboard() {
	    final String enterKey = "VK_ENTER";

	       KeyStroke enter = KeyStroke.getKeyStroke("pressed ENTER");
	        panel.getInputMap().put(enter, enterKey);
	        Action enterAction= new AbstractAction() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						createMovedObjects(readnextLine(false), colors);
					} catch (Exception ex) {
						
					}
				}};;

				panel.getActionMap().put(enterKey, enterAction);
				reInitializeKeyboard();

	}
	
	public void initializeSequencer() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.scheduleAtFixedRate(
				new Runnable() {

					@Override
					public void run() {
						createMovedObjects(readnextLine(false), colors);
						
					}},                                    
                0 ,                                       
                Duration.ofSeconds( 3).toSeconds(),    
                TimeUnit.SECONDS ) ;
		initializeSequencerRepaint();

	}
	private void initializeSequencerRepaint() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		
		executorService.scheduleAtFixedRate(
				new Runnable() {

					@Override
					public void run() {
						panel.repaint();
					}},                                    
                0 ,                                       
                Duration.ofMillis( 10).toMillis(),    
                TimeUnit.MILLISECONDS ) ;

	}
	protected int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public Map<String, MoveObject> createMovedObjects(String title, Color[] colors) {
		Map<String, MoveObject> result=new HashMap<>();
		String[] mots = title.split(" ");
		for (int i = 0; i < mots.length; i++) {
			String mot = mots[i];
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, getRandomNumber(60, 200));
			int colorint = (i % colors.length);
			result.put(mot,
					new MoveObject(getRandomNumber(0, 300), getRandomNumber(0, 300), getRandomNumber(0, 20),
							getRandomNumber(0, 20), mot, colors[colorint], colors, 0, panel.getWidth(), 0,
							panel.getHeight(), font));

		}
		this.movedObjects=result;
		return result;
	}
}
