package oliviaproject.ui.selection.tile.color.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActionDemo implements ActionListener {
	public ActionDemo(Runnable runnable, Long duration) {
		this.duration=duration;
		this.runnable=runnable;
	}
	Long duration;
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	Runnable runnable;
	public Runnable getRunnable() {
		return runnable;
	}
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
	public Boolean getStart() {
		return start;
	}
	public void setStart(Boolean start) {
		this.start = start;
	}
	Boolean start=true;

	ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	@Override
	public void actionPerformed(ActionEvent e) {
		triggeExecutorService(false);

			}
	void updateExecutorService() {
		triggeExecutorService(true);
		
	};
	void triggeExecutorService(Boolean restart) {
		if(start) {
			executorService.scheduleAtFixedRate(
					runnable,                                    
	                0 ,                                       
	                Duration.ofMillis( duration ).toMillis(),    
	                TimeUnit.MILLISECONDS ) ;
			}else {
				if(restart) {
					executorService.scheduleAtFixedRate(
							runnable,                                    
			                0 ,                                       
			                Duration.ofMillis( duration ).toMillis(),    
			                TimeUnit.MILLISECONDS ) ;
				}else {
					executorService.shutdownNow();
					
				}
			}
			start=!start;

	}
}
