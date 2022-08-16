package oliviaproject.ui.selection.tile.color.demo;

import java.awt.MenuItem;
import java.awt.event.ActionListener;

public class DemoMenuItem extends MenuItem{
	public static final String[] demoLabels = new String[] { "Dashboard", "Piece", "Selection" };

	Long duration;
	ActionListener listener;
	public DemoMenuItem(String label, ActionListener listener, String key, Long duration ) {
        super(label);
        this.duration=duration;
	this.listener=listener!=null?listener: defaultAction(key, duration); 
	this.addActionListener(this.listener);
	}
	public ActionListener getListener() {
		return listener;
	}
	public void setListener(ActionListener listener) {
		this.listener = listener;
	}
	private ActionListener defaultAction(String key, Long duration) {
		ActionListener result=null;
		switch(key) {
		case "Dashboard":{
			result= new ActionDemo(new DemoDashBoardColorCallable(), duration);
			break;
		}
		case "Piece":{
			result=new ActionDemo(new DemoPieceColorCallable(), duration);
			break;
		}
		case "Selection":{
			result=new ActionDemo(new DemoSelectColorCallable(), duration);
			break;
		}
		case "Timer":{
			result=new ActionDemo(new DemoTimerCallable(), duration);
			break;
		}		}
		return result;
	}

}
