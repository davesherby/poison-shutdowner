package com.davesherby.poison.shutdowner;

import java.time.ZonedDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockShutdowner implements Shutdowner {

	private static final Logger logger = LoggerFactory.getLogger(MockShutdowner.class);

	private ZonedDateTime scheduledShutdownDate;

	public MockShutdowner() {
	}

	public boolean isShutdownScheduled() {
		logger.trace("isShutDownScheduled", this);
		boolean isShutdownScheduled = false;
		if (scheduledShutdownDate != null) {
			isShutdownScheduled = true;
		}
		return isShutdownScheduled;
	}

	public void cancelShutdown() {
		logger.trace("cancelShutdown");
		if (scheduledShutdownDate != null) {
			scheduledShutdownDate = null;
		} else {
			throw new ShutdownerException(ShutdownerException.NO_SHUTDOWN_SCHEDULED);
		}
	}

	public ZonedDateTime scheduleShutdown(ZonedDateTime dateOfShutdown) {
		logger.trace("scheduleShutdown", this, dateOfShutdown);
		checkIsAfterNow(dateOfShutdown);
		this.scheduledShutdownDate = dateOfShutdown;
		return this.scheduledShutdownDate;
	}

	private void checkIsAfterNow(ZonedDateTime dateOfShutdown) {
		Objects.nonNull(dateOfShutdown);
		ZonedDateTime now = ZonedDateTime.now();
		if (now.isAfter(dateOfShutdown)) {
			throw new ShutdownerException(ShutdownerException.INCORRECT_SHUTDOWN_DATE);
		};
	}

	public ZonedDateTime getScheduledShutdownDate() {
		return this.scheduledShutdownDate;
	}

}
