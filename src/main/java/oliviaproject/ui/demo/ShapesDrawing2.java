package oliviaproject.ui.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ShapesDrawing2 {

    private JFrame frame;
    private JPanel pane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShapesDrawing2().createAndShowGui();
            }
        });
    }

    public void createAndShowGui() {
        frame = new JFrame(getClass().getSimpleName());
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); //ALWAYS call this method first!
                g.drawRect(10, 10, 50, 50); //Draws square
                g.drawRect(10, 75, 100, 50); //Draws rectangle
                g.drawPolygon(new int[] {35, 10, 60}, new int[] {150, 200, 200}, 3); //Draws triangle
                Graphics2D g2d = (Graphics2D) g;
    			g2d.fillRect(10, 10, 50, 50); // Draws square
    			// Draw image inside square
    			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    			g2d.drawImage(loadImage(), 10, 10, 50, 50, null);
                g2d.drawImage(loadImage(),10, 75, 100, 50,null); //Draws rectangle
                boolean blackSquare = false;

                BufferedImage img=ChessBoard.addColoredUnicodeCharToBufferedImage(
                		"\u2654", 
                        new Color(203,203,197),
                        Color.DARK_GRAY,
                        blackSquare);
                        blackSquare = !blackSquare;
                        g2d.drawImage(img,10, 75, 50, 50,null); //Draws rectangle
                
                        g2d.dispose();
    			g.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 300);
            }
        };

        frame.add(pane);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public BufferedImage loadImage() {
        BufferedImage bi=null;
    	try {
    
    		bi = ImageIO.read(new File("resources/pieces/RoiB.png"));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        return bi;
    }
}