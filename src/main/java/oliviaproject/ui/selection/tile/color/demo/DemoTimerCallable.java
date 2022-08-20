package oliviaproject.ui.selection.tile.color.demo;

import oliviaproject.io.AppContext;

public class DemoTimerCallable implements Runnable {
	public Long MIN_DURATION=100L, MAX_DURATION=2000L;
	DemoMenuItems demoMenuItems;
	@Override
	public void run() {
	  demoMenuItems = AppContext.getApplicationContext().getBean(DemoMenuItems.class);
		upgradeDuration();
	}
	Long interval;
	public void upgradeDuration() {
		for(String  key: demoMenuItems.keySet()) {
			DemoMenuItem item =demoMenuItems.get(key);
			ActionDemo demo =(ActionDemo)item.getListener();
			demo.setDuration(Math.max(demo.getDuration()+interval, 100));
			if(demo.getDuration()<=MIN_DURATION || demo.getDuration()>MIN_DURATION)interval=-interval;
			demo.updateExecutorService();
		}
	}
	}
