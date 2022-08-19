package oliviaproject.ui.dashboard;

import java.awt.Menu;

import org.springframework.stereotype.Component;

import oliviaproject.ui.selection.tile.color.demo.DemoMenuItem;


public class OliviaFrameDemo extends OliviaFrame {
public OliviaFrameDemo() {
	this.pane=new OliviaPanelDemo();
}
	@Override
	protected void createDemoMenu(Menu menu, Long durationDashboard) {
		String[] demoLabels = DemoMenuItem.demoLabels;
		for (int i = 0; i < demoLabels.length; i++) {
			String key = demoLabels[i];
			demoMenus.put(key, new DemoMenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.DemoColor + " " + key, null, key,
					durationDashboard));
			DemoMenuItem menuItem = demoMenus.get(key);
			menu.add(menuItem);
		}
		DemoMenuItem item = new DemoMenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.DemoColor + " " + "Timer", null,
				"Timer", 100L);
		menu.add(item);
	}

}
