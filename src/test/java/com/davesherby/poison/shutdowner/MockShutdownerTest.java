package com.davesherby.poison.shutdowner;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;

public class MockShutdownerTest {

	private Shutdowner shutdowner;

	@Before
	public void setUp() {
		shutdowner = new MockShutdowner();
	}
	
	@Test
	public void testShutdownerState() {
		assertTrue(shutdowner.getScheduledShutdownDate() == null);
		assertTrue(!shutdowner.isShutdownScheduled());
	}

	@Test(expected=ShutdownerException.class)
	/**
	 * Must throw an exception :
	 */
	public void testCancelWhenNoShutdownWasScheduled() {
		shutdowner.cancelShutdown();
	}
	
	@Test
	public void testScheduling() {
		ZonedDateTime inThreeMinutes = ZonedDateTime.now().plusMinutes(3);
		shutdowner.scheduleShutdown(inThreeMinutes);
		assertTrue(shutdowner.isShutdownScheduled());
		assertTrue(shutdowner.getScheduledShutdownDate().equals(inThreeMinutes));
	}
	
	@Test(expected=NullPointerException.class)
	public void testBadShutdownDateNull() {
		shutdowner.scheduleShutdown(null);
	}
	
	@Test(expected=ShutdownerException.class)
	public void testBadShutdownDateBefore() {
		ZonedDateTime threeMinutesAgo = ZonedDateTime.now().minusMinutes(3);
		shutdowner.scheduleShutdown(threeMinutesAgo);
	}
	
	@Test
	public void testAbortScheduling() {
		ZonedDateTime inThreeMinutes = ZonedDateTime.now().plusMinutes(3);
		shutdowner.scheduleShutdown(inThreeMinutes);
		shutdowner.cancelShutdown();
		assertFalse(shutdowner.isShutdownScheduled());
		assertTrue(shutdowner.getScheduledShutdownDate() == null);
	}


}
