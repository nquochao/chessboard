package oliviaproject.ui.promotion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import oliviaproject.event.ChessEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.event.Event;
import oliviaproject.eventbus.EventListener;
import oliviaproject.ui.dashboard.util.IChessboardPanel;
import oliviaproject.ui.dashboard.util.SelectorImageRectangle;
import oliviaproject.ui.position.Position;

public class PromotionPanel extends JPanel implements IChessboardPanel, EventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Rectangle selected;
	int xlength = TILE_X;
	int ylength = TILE_Y;
	int xinit = 0;
	int yinit = 0;
	Map<ChessPiecePromotion, SelectorImageRectangle> population = new HashMap<>();
	private Position targetPosition;

	public void initialize() {
		boolean blackSquare = false;
		for (ChessPiecePromotion ref : ChessPiecePromotion.values()) {
			BufferedImage bi = addColoredUnicodeCharToBufferedImage(ref.getUnicode(), new Color(203, 203, 197),
					COLOR_TILE_BLACK, blackSquare);

			Color bg = COLOR_TILE_WHITE;
			fillPopulation(bi, ref);

		}

		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				super.mouseMoved(e);
				selected = null;
				for (ChessPiecePromotion key : population.keySet()) {
					SelectorImageRectangle cell = population.get(key);
					if (cell.getRectangle().contains(e.getPoint())) {
						selected = cell.getRectangle();

						break;
					}
				}
				repaint();

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				super.mouseReleased(e);
				if (selected != null) {
					for (ChessPiecePromotion key : population.keySet()) {
						SelectorImageRectangle cell = population.get(key);
						if (cell.getRectangle().contains(e.getPoint())) {
							selected = cell.getRectangle();
							// we send the event with the reference selected
							ChessEvent event = new ChessEvent();
							event.setPromotion(key);
							event.setPosition(new Position(targetPosition.getX(),targetPosition.getY()));
							DefaultConnection.getEventBus().publish(event);
							exit() ;
							break;
						}
					}
				}
			}
		};
		addMouseMotionListener(ma);
		addMouseListener(ma);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);

		Graphics2D g2d = (Graphics2D) g;

		try {
			Color color = COLOR_TILE_WHITE;
			g2d.setColor(color);
			draw(g2d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (selected != null) {
			Color color = COLOR_TILE_SELECTED;
			g2d.setColor(color);
			g2d.fillRect(selected.x, selected.y, selected.width, selected.height);

		}
	}

	private void draw(Graphics2D g2d) throws IOException {
		if (population.size() == 0)
			return;
		for (ChessPiecePromotion ref : ChessPiecePromotion.values()) {

			SelectorImageRectangle rectselector = population.get(ref);
			Rectangle r = rectselector.getRectangle();
			if (r == null)
				return;
			BufferedImage img = rectselector.getImage();

			// Draw image inside square
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

			g2d.fillRect(r.x, r.y, r.width, r.height);

			g2d.drawImage(img, r.x, r.y, r.width, r.height, null);

		}

	}

	static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
	static Random rnd = new Random();

	private void fillPopulation(BufferedImage img, ChessPiecePromotion reference) {

		int y = yinit;
		int x = xinit + population.size() * xlength;
		population.put(reference,
				new SelectorImageRectangle(new Rectangle(x, y, xlength, ylength), reference.name(), img));

	}

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
				gp.curveTo(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
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

	public static BufferedImage addColoredUnicodeCharToBufferedImage(String s, Color bgColor, Color outlineColor,
			boolean blackSquare) {

		int sz = font.getSize();
		BufferedImage bi = new BufferedImage(sz, sz, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		FontRenderContext frc = g.getFontRenderContext();
		GlyphVector gv = font.createGlyphVector(frc, s);
		Rectangle2D box1 = gv.getVisualBounds();

		Shape shape1 = gv.getOutline();
		Rectangle r = shape1.getBounds();
		int spaceX = sz - r.width;
		int spaceY = sz - r.height;
		AffineTransform trans = AffineTransform.getTranslateInstance(-r.x + (spaceX / 2), -r.y + (spaceY / 2));

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

	public static void addColoredUnicodeCharToContainer(String s, Container c, Color bgColor, Color outlineColor,
			boolean blackSquare) {

		int sz = font.getSize();
		BufferedImage bi = new BufferedImage(sz, sz, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		FontRenderContext frc = g.getFontRenderContext();
		GlyphVector gv = font.createGlyphVector(frc, s);
		Rectangle2D box1 = gv.getVisualBounds();

		Shape shape1 = gv.getOutline();
		Rectangle r = shape1.getBounds();
		System.out.println("shape rect: " + r);
		int spaceX = sz - r.width;
		int spaceY = sz - r.height;
		AffineTransform trans = AffineTransform.getTranslateInstance(-r.x + (spaceX / 2), -r.y + (spaceY / 2));
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

	@Override
	public Dimension getPreferredSize() {
		int x=xinit;
			x=xinit+xlength*ChessPiecePromotion.values().length;
		return new Dimension(x, ylength);
	}

	public static void main(String[] args) {
		showFrame(null);
	}
	static JFrame frame;
	public static void showFrame(Position pTarget) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				PromotionPanel gui = null;
				gui = new PromotionPanel();
				gui.initialize();
				frame = new JFrame();
				gui.setPreferredSize(new Dimension(300, 300));
				gui.setTargetPosition(pTarget);
				frame.add(gui);
				frame.pack();

				frame.setVisible(true);
				// JOptionPane.showMessageDialog(null, gui);

			}
		};
		SwingUtilities.invokeLater(r);

	}

	protected void setTargetPosition(Position pTarget) {
		this.targetPosition = pTarget;
	}

	@Override
	public void onMyEvent(Event event) {

	}
	public void exit() {
		frame.dispose();
	}
}
