package com.davesherby.poison.shutdowner;

import java.time.ZonedDateTime;

public interface Shutdowner {
	
	public boolean isShutdownScheduled();
	
	public void cancelShutdown();
	
	public ZonedDateTime scheduleShutdown(ZonedDateTime dateOfShutdown);

	public ZonedDateTime getScheduledShutdownDate();

}
