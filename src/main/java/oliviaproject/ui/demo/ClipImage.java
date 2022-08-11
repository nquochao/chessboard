package oliviaproject.ui.demo;

/*
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
* Animated clipping of an image & shapes with alpha.
*/
public class ClipImage extends JApplet implements Runnable {

 private Image img;

 private final double OINC[] = { 5.0, 3.0 };

 private final double SINC[] = { 5.0, 5.0 };

 private double x, y;

 private double ix = OINC[0];

 private double iy = OINC[1];

 private double iw = SINC[0];

 private double ih = SINC[1];

 private double ew, eh; // ellipse width & height

 private GeneralPath p = new GeneralPath();

 private AffineTransform at = new AffineTransform();

 private BasicStroke bs = new BasicStroke(20.0f);

 private Arc2D arc = new Arc2D.Float();

 private Ellipse2D ellipse = new Ellipse2D.Float();

 private RoundRectangle2D roundRect = new RoundRectangle2D.Float();

 private Rectangle2D rect = new Rectangle2D.Float();

 private Color redBlend = new Color(255, 0, 0, 120);

 private Color greenBlend = new Color(0, 255, 0, 120);

 private Thread thread;

 private BufferedImage offImg;

 private int w, h;

 private boolean newBufferedImage;

 public void init() {
   setBackground(Color.white);
   img = new ImageIcon("yourFile.gif").getImage();
 }

 public void drawDemo(Graphics2D g2) {
   if (newBufferedImage) {
     x = Math.random() * w;
     y = Math.random() * h;
     ew = (Math.random() * w) / 2;
     eh = (Math.random() * h) / 2;
   }
   x += ix;
   y += iy;
   ew += iw;
   eh += ih;
   if (ew > w / 2) {
     ew = w / 2;
     iw = Math.random() * -w / 16 - 1;
   }
   if (ew < w / 8) {
     ew = w / 8;
     iw = Math.random() * w / 16 + 1;
   }
   if (eh > h / 2) {
     eh = h / 2;
     ih = Math.random() * -h / 16 - 1;
   }
   if (eh < h / 8) {
     eh = h / 8;
     ih = Math.random() * h / 16 + 1;
   }
   if ((x + ew) > w) {
     x = (w - ew) - 1;
     ix = Math.random() * -w / 32 - 1;
   }
   if (x < 0) {
     x = 2;
     ix = Math.random() * w / 32 + 1;
   }
   if ((y + eh) > h) {
     y = (h - eh) - 2;
     iy = Math.random() * -h / 32 - 1;
   }
   if (y < 0) {
     y = 2;
     iy = Math.random() * h / 32 + 1;
   }

   ellipse.setFrame(x, y, ew, eh);
   g2.setClip(ellipse);

   rect.setRect(x + 5, y + 5, ew - 10, eh - 10);
   
   /**
    * 
    * this is usage of intersection + image
    */
   g2.clip(rect);

   g2.drawImage(img, 0, 0, w, h, this);

   p.reset();
   p.moveTo(-w / 2.0f, -h / 8.0f);
   p.lineTo(+w / 2.0f, -h / 8.0f);
   p.lineTo(-w / 4.0f, +h / 2.0f);
   p.lineTo(+0.0f, -h / 2.0f);
   p.lineTo(+w / 4.0f, +h / 2.0f);
   p.closePath();

   at.setToIdentity();
   at.translate(w * .5f, h * .5f);
   g2.transform(at);
   g2.setStroke(bs);
   g2.setPaint(redBlend);
   g2.draw(p);

   at.setToIdentity();
   g2.setTransform(at);

   g2.setPaint(greenBlend);

   for (int yy = 0; yy < h; yy += 50) {
     for (int xx = 0, i = 0; xx < w; i++, xx += 50) {
       switch (i) {
       case 0:
         arc.setArc(xx, yy, 25, 25, 45, 270, Arc2D.PIE);
         g2.fill(arc);
         break;
       case 1:
         ellipse.setFrame(xx, yy, 25, 25);
         g2.fill(ellipse);
         break;
       case 2:
         roundRect.setRoundRect(xx, yy, 25, 25, 4, 4);
         g2.fill(roundRect);
         break;
       case 3:
         rect.setRect(xx, yy, 25, 25);
         g2.fill(rect);
         i = -1;
       }

     }
   }
 }

 public Graphics2D createDemoGraphics2D(Graphics g) {
   Graphics2D g2 = null;

   if (offImg == null || offImg.getWidth() != w || offImg.getHeight() != h) {
     offImg = (BufferedImage) createImage(w, h);
     newBufferedImage = true;
   }

   if (offImg != null) {
     g2 = offImg.createGraphics();
     g2.setBackground(getBackground());
   }

   // .. set attributes ..
   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

   // .. clear canvas ..
   g2.clearRect(0, 0, w, h);

   return g2;
 }

 public void paint(Graphics g) {

   w = getWidth();
   h = getHeight();

   if (w <= 0 || h <= 0)
     return;

   Graphics2D g2 = createDemoGraphics2D(g);
   drawDemo(g2);
   g2.dispose();

   if (offImg != null && isShowing()) {
     g.drawImage(offImg, 0, 0, this);
   }

   newBufferedImage = false;
 }

 public void start() {
   thread = new Thread(this);
   thread.start();
 }

 public synchronized void stop() {
   thread = null;
 }

 public void run() {

   Thread me = Thread.currentThread();

   while (thread == me && isShowing()) {
     Graphics g = getGraphics();
     paint(g);
     g.dispose();
     thread.yield();
   }
   thread = null;
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

 public static void main(String s[]) {
   final ClipImage demo = new ClipImage();
   WindowListener l = new WindowAdapter() {
     public void windowClosing(WindowEvent e) {
       System.exit(0);
     }

     public void windowDeiconified(WindowEvent e) {
       demo.start();
     }

     public void windowIconified(WindowEvent e) {
       demo.stop();
     }
   };
   JFrame f = new JFrame("Java 2D Demo - ClipImage");
   f.addWindowListener(l);
   f.getContentPane().add("Center", demo);
   f.setSize(new Dimension(400, 300));
   f.show();
   demo.start();
 }
}