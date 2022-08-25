package oliviaproject;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oliviaproject.ui.dashboard.OliviaFrame;

public class OliviaProjectChessBoard {
    private static final Logger log = LoggerFactory.getLogger(OliviaProjectChessBoard.class);

	private static final String TEST = "Olivia est super bete";
	private static final String QUESTION = "Olivia: Quel age as-tu?";
	private static final String QUESTIONREPONSE = "Tu as ";

	public static void main(String[] args) {
		CommandLine commandLine= new CommandLine(QUESTION,QUESTIONREPONSE);
	
		System.out.println(TEST);
		System.out.println(commandLine.getQuestion());
		commandLine.setReponse(commandLine.getReponse()+System.console().readLine());
		System.out.println(commandLine.getReponse());
		
	     SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                try {
						new OliviaFrame().init();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });

	}

}
