package oliviaproject.ui.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class ChessBoard {

    static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
    static Random rnd = new Random();

    public static ArrayList<Shape> separateShapeIntoRegions(Shape shape) {
        ArrayList<Shape> regions = new ArrayList<Shape>();

        PathIterator pi = shape.getPathIterator(null);
        int ii = 0;
        GeneralPath gp = new GeneralPath();
        while (!pi.isDone()) {
            double[] coords = new double[6];
            int pathSegmentType = pi.currentSegment(coords);
            int windingRule = pi.getWindingRule();
            gp.setWindingRule(windingRule);
            if (pathSegmentType == PathIterator.SEG_MOVETO) {
                gp = new GeneralPath();
                gp.setWindingRule(windingRule);
                gp.moveTo(coords[0], coords[1]);
                System.out.println(ii++ + " \t" + coords[0] + "," + coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_LINETO) {
                gp.lineTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_QUADTO) {
                gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
            } else if (pathSegmentType == PathIterator.SEG_CUBICTO) {
                gp.curveTo(
                        coords[0], coords[1],
                        coords[2], coords[3],
                        coords[4], coords[5]);
            } else if (pathSegmentType == PathIterator.SEG_CLOSE) {
                gp.closePath();
                regions.add(new Area(gp));
            } else {
                System.err.println("Unexpected value! " + pathSegmentType);
            }

            pi.next();
        }

        return regions;
    }
    public static BufferedImage addColoredUnicodeCharToBufferedImage(
            String s, 
            Color bgColor, Color outlineColor, boolean blackSquare) {

        int sz = font.getSize();
        BufferedImage bi = new BufferedImage(
                sz, sz, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(
                RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(
                RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        FontRenderContext frc = g.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(frc, s);
        Rectangle2D box1 = gv.getVisualBounds();

        Shape shape1 = gv.getOutline();
        Rectangle r = shape1.getBounds();
        int spaceX = sz - r.width;
        int spaceY = sz - r.height;
        AffineTransform trans = AffineTransform.getTranslateInstance(
                -r.x + (spaceX / 2), -r.y + (spaceY / 2));

        Shape shapeCentered = trans.createTransformedShape(shape1);

        Shape imageShape = new Rectangle2D.Double(0, 0, sz, sz);
        Area imageShapeArea = new Area(imageShape);
        Area shapeArea = new Area(shapeCentered);
        imageShapeArea.subtract(shapeArea);
        ArrayList<Shape> regions = separateShapeIntoRegions(imageShapeArea);
        g.setStroke(new BasicStroke(1));
        for (Shape region : regions) {
            Rectangle r1 = region.getBounds();
            if (r1.getX() < 0.001 && r1.getY() < 0.001) {
            } else {
                g.setColor(bgColor);
                g.fill(region);
            }
        }
        g.setColor(outlineColor);
        g.fill(shapeArea);
        g.dispose();

        JLabel l = new JLabel(new ImageIcon(bi), JLabel.CENTER);
        Color bg = (blackSquare ? Color.BLACK : Color.WHITE);
        l.setBackground(bg);
        l.setOpaque(true);
        return bi;
    }

    public static void addColoredUnicodeCharToContainer(
            String s, Container c,
            Color bgColor, Color outlineColor, boolean blackSquare) {

        int sz = font.getSize();
        BufferedImage bi = new BufferedImage(
                sz, sz, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(
                RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(
                RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        FontRenderContext frc = g.getFontRenderContext();
        GlyphVector gv = font.createGlyphVector(frc, s);
        Rectangle2D box1 = gv.getVisualBounds();

        Shape shape1 = gv.getOutline();
        Rectangle r = shape1.getBounds();
        System.out.println("shape rect: " + r);
        int spaceX = sz - r.width;
        int spaceY = sz - r.height;
        AffineTransform trans = AffineTransform.getTranslateInstance(
                -r.x + (spaceX / 2), -r.y + (spaceY / 2));
        System.out.println("Box2D " + trans);

        Shape shapeCentered = trans.createTransformedShape(shape1);

        Shape imageShape = new Rectangle2D.Double(0, 0, sz, sz);
        Area imageShapeArea = new Area(imageShape);
        Area shapeArea = new Area(shapeCentered);
        imageShapeArea.subtract(shapeArea);
        ArrayList<Shape> regions = separateShapeIntoRegions(imageShapeArea);
        g.setStroke(new BasicStroke(1));
        for (Shape region : regions) {
            Rectangle r1 = region.getBounds();
            if (r1.getX() < 0.001 && r1.getY() < 0.001) {
            } else {
                g.setColor(bgColor);
                g.fill(region);
            }
        }
        g.setColor(outlineColor);
        g.fill(shapeArea);
        g.dispose();

        JLabel l = new JLabel(new ImageIcon(bi), JLabel.CENTER);
        Color bg = (blackSquare ? Color.BLACK : Color.WHITE);
        l.setBackground(bg);
        l.setOpaque(true);
        c.add(l);
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                JPanel gui = new JPanel(new GridLayout(0, 6, 4, 4));

                String[] pieces = {
                    "\u2654", "\u2655", "\u2656", "\u2657", "\u2658", "\u2659"
                };

                boolean blackSquare = false;
                for (String piece : pieces) {
                    addColoredUnicodeCharToContainer(
                            piece, gui,
                            new Color(203,203,197),
                            Color.DARK_GRAY,
                            blackSquare);
                            blackSquare = !blackSquare;
                }
                            blackSquare = !blackSquare;
                for (String piece : pieces) {
                    addColoredUnicodeCharToContainer(
                            piece, gui,
                            new Color(192,142,60),
                            Color.DARK_GRAY,
                            blackSquare);
                            blackSquare = !blackSquare;
                }

                JOptionPane.showMessageDialog(null, gui);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}