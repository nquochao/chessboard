package oliviaproject.ui.demo;

/*
 * @(#)ClipImage.java 1.2 98/07/09
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Starry extends JApplet {
  public void init() {
    Image starry = new ImageIcon("yourFile.gif").getImage();
    StarPanel starPanel = new StarPanel(starry);
    getContentPane().add(starPanel, BorderLayout.CENTER);
  }

  protected URL getURL(String filename) {
    URL codeBase = this.getCodeBase();
    URL url = null;

    try {
      url = new URL(codeBase, filename);
    } catch (java.net.MalformedURLException e) {
      System.out.println("Couldn't create image: " + "badly specified URL");
      return null;
    }

    return url;
  }

  public static void main(String[] args) {
    Image starry = new ImageIcon("yourFile.gif").getImage();

    StarPanel starPanel = new StarPanel(starry);

    JFrame f = new JFrame("Starry");
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    f.getContentPane().add(starPanel, BorderLayout.CENTER);
    f.setSize(new Dimension(550, 200));
    f.setVisible(true);
  }
}

class StarPanel extends JPanel {

  Image img;

  int w, h;

  public StarPanel(Image img) {
    this.img = img;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.white);
    w = getSize().width;
    h = getSize().height;
    Graphics2D g2;
    g2 = (Graphics2D) g;
    URL url;
    BufferedImage originalImage = null;
	try {
		url = new URL("http://i.stack.imgur.com/Nqf3H.jpg");
		 originalImage = ImageIO.read(url);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    FontRenderContext frc = g2.getFontRenderContext();
    Font f = new Font("Helvetica", 1, w / 10);
    String s = new String("The Starry Night");
    TextLayout textTl = new TextLayout(s, f, frc);
    AffineTransform transform = new AffineTransform();
    Shape outline = textTl.getOutline(null);
    Rectangle r = outline.getBounds();
    transform = g2.getTransform();
    transform.translate(w / 2 - (r.width / 2), h / 2 + (r.height / 2));
    g2.transform(transform);
    g2.setColor(Color.PINK);
    g2.draw(outline);
    g2.setClip(outline);
    img=originalImage;
    g2.drawImage(img, r.x, r.y, r.width, r.height, this);

  }
}