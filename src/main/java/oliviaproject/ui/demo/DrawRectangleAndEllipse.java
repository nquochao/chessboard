package oliviaproject.ui.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class DrawRectangleAndEllipse extends JApplet {
  final static BasicStroke stroke = new BasicStroke(2.0f);
BufferedImage img;
  public void init() {
  setBackground(Color.white);
  setForeground(Color.white);
  }
  public void paint(Graphics g) {
	  try {
		  URL url = new URL("http://i.stack.imgur.com/Nqf3H.jpg");
		img= ImageIO.read(url);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  Graphics2D g2 = (Graphics2D) g;
  g2.setPaint(Color.red);
  int x = 6;
  int y = 8;
  g2.setStroke(stroke);
  Double ellipse = new Ellipse2D.Double(100, 100, 150, 150);
  g2.setClip(ellipse);
  g2.drawImage(img,6,8,null);
  g2.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
  File file = new File("cat-text.png");
 
  }
  public static void main(String args[]) throws Exception {
	  boolean test=true;
	  if(test) {
		  drawpicture();
		  
	  }
  JFrame frame = new JFrame("Draw Ellipse inside Rectangle");
  JApplet applet = new DrawRectangleAndEllipse();
  frame.getContentPane().add("Center", applet);
  applet.init();
  frame.setSize(400, 400);
  frame.show();

  }
  public static void drawpicture() throws Exception {
      URL url = new URL("http://i.stack.imgur.com/Nqf3H.jpg");
      BufferedImage originalImage = ImageIO.read(url);
      final BufferedImage textImage = new BufferedImage(
          originalImage.getWidth(),
          originalImage.getHeight(),
          BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = textImage.createGraphics();
      Double ellipse = new Ellipse2D.Double(100, 100, 150, 150);
      g.setClip(ellipse);
      g.drawImage(originalImage,0,0,null);
      g.setClip(null);
      g.setStroke(new BasicStroke(2f));
      g.setColor(Color.BLACK);
      g.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);

      g.dispose();

      File file = new File("cat-text.png");
      ImageIO.write(textImage,"png",file);
      Desktop.getDesktop().open(file);
  }

}
