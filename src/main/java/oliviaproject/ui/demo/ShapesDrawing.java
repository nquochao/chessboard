package oliviaproject.ui.demo;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ShapesDrawing {

    private JFrame frame;
    private JPanel pane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ShapesDrawing().createAndShowGui();
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
}