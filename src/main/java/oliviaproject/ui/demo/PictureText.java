package oliviaproject.ui.demo;

import java.awt.*;
import java.awt.font.*;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import java.net.URL;
import java.io.File;

class PictureText {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://i.stack.imgur.com/Nqf3H.jpg");
        BufferedImage originalImage = ImageIO.read(url);
        final BufferedImage textImage = new BufferedImage(
            originalImage.getWidth(),
            originalImage.getHeight(),
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = textImage.createGraphics();
        FontRenderContext frc = g.getFontRenderContext();
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 125);
        GlyphVector gv = font.createGlyphVector(frc, "Holidays");
        Rectangle2D box = gv.getVisualBounds();
        int xOff = 25+(int)-box.getX();
        int yOff = 80+(int)-box.getY();
        Shape shape = gv.getOutline(xOff,yOff);
        g.setClip(shape);
        g.drawImage(originalImage,0,0,null);
        g.setClip(null);
        g.setStroke(new BasicStroke(2f));
        g.setColor(Color.BLACK);
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.draw(shape);

        g.dispose();

        File file = new File("cat-text.png");
        ImageIO.write(textImage,"png",file);
        Desktop.getDesktop().open(file);
    }
}