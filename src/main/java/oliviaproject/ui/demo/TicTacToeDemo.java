package oliviaproject.ui.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TicTacToeDemo {

    public static void main(String[] args) {
        new TicTacToeDemo();
    }

    public TicTacToeDemo() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private List<Rectangle> quads;
        private Rectangle selected;

        public TestPane() {
            quads = new ArrayList<>(9);
            MouseAdapter ma = new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    selected = null;
                    for (Rectangle cell : quads) {
                        if (cell.contains(e.getPoint())) {
                            selected = cell;
                            break;
                        }
                    }
                    repaint();
                }
            };
            addMouseMotionListener(ma);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        public void invalidate() {
            // When ever the size of the container changes, we
            // need to revalidate the rectangles based on the new size
            // of the container
            super.invalidate();
            quads.clear();
            int width = getWidth();
            int height = getHeight();
            if (width != 0 && height != 0) {
                int vGap = getHeight() / 10;
                int hGap = getWidth() / 15;

                width -= hGap;
                height -= vGap;

                hGap /= 2;
                vGap /= 2;

                for (int xPos = 0; xPos < 3; xPos++) {
                    for (int yPos = 0; yPos < 3; yPos++) {
                        int x = hGap + (xPos * (width / 3));
                        int y = vGap + (yPos * (height / 3));
                        quads.add(new Rectangle(x, y, width / 3, height / 3));
                    }
                }
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            if (selected != null) {
                g2d.setColor(new Color(0, 0, 255, 128));
                g2d.fill(selected);
            }

            g2d.setColor(Color.black);
            Graphics2D g2 = (Graphics2D) g2d;
            g2.setStroke(new BasicStroke(10));
            //Vertical
            g2.drawLine(this.getWidth() / 3, this.getHeight() / 10, this.getWidth() / 3, this.getHeight() - this.getHeight() / 10);
            g2.drawLine(2 * (this.getWidth() / 3), this.getHeight() / 10, 2 * (this.getWidth() / 3), this.getHeight() - this.getHeight() / 10);
            //Horizontal
            g2.drawLine(this.getWidth() / 25, this.getHeight() / 3, this.getWidth() - this.getWidth() / 25, this.getHeight() / 3);
            g2.drawLine(this.getWidth() / 25, (this.getHeight() / 3) * 2, this.getWidth() - this.getWidth() / 25, (this.getHeight() / 3) * 2);
            g2d.dispose();
        }

    }
}