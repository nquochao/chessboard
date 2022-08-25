package oliviaproject.ui.dashboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import oliviaproject.eventbus.EventListener;
import oliviaproject.ui.dashboard.util.IChessboardPanel;
import oliviaproject.ui.selection.tile.color.demo.DemoColorUtil;
import oliviaproject.ui.selection.tile.color.demo.KeyboardDemo;
import oliviaproject.ui.selection.tile.color.demo.MoveObject;

public class OliviaPanelDemo extends OliviaPanel implements IChessboardPanel, EventListener {
	/**
	 * 
	 */
	Color[] colors = new Color[] { Color.orange, Color.white, Color.red, DemoColorUtil.createPastelRandomColor(),
			DemoColorUtil.createPastelRandomColor(), DemoColorUtil.createPastelRandomColor(),
			DemoColorUtil.createPastelRandomColor(), DemoColorUtil.createPastelRandomColor() };
	KeyboardDemo kb;
	public void initialize() throws IOException {
		kb=new KeyboardDemo(this,  "/animation/animation.txt", colors);

		kb.createMovedObjects(kb.readnextLine(false), colors);
		kb.initializeKeyboard();
		kb.initializeSequencer();
		super.initialize();
	}


@Override
protected void paintComponent(Graphics g) {
	// TODO Auto-generated method stub
	super.paintComponent(g);


}
private void moveObject(Graphics2D g2d, MoveObject moveObject) {
    FontRenderContext frc = g2d.getFontRenderContext();
    if(moveObject==null)return;
    Font font = moveObject.getFont();
       GlyphVector gv = font.createGlyphVector( frc, moveObject.getLabel());
        Rectangle2D box = gv.getVisualBounds();
        moveObject.setMinx(0);	      
        moveObject.setMaxx(this.getWidth()- (int)box.getWidth());
        moveObject.setMiny( (int)box.getHeight());
        moveObject.setMaxy(this.getHeight()- (int)box.getHeight());
        int xOff = moveObject.getXMove();
        int yOff = moveObject.getYMove();
        Shape shape = gv.getOutline(xOff,yOff);
        g2d.setClip(shape);
       // g2d.setClip(null);
        g2d.setStroke(new BasicStroke(12f));
        g2d.setColor(moveObject.getColor());
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.draw(shape);
	
}         
@Override
protected void customPaint(Graphics2D g2d) {
	for(String key: kb.getMovedObjects().keySet()) {
		MoveObject m= kb.getMovedObjects().get(key);
		moveObject(g2d, m);
	}	
}
}
