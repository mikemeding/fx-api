/*
 * Copyright (c) 1999 Meding Software Technik -- All Rights Reserved.
 */
package com.uml.fx.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Re-entrant Mutex
 */
public class Mutex {

	/** The Thread owning this lock status */
	protected Thread ownedBy = null;
	/** The name of this Mutex */
	protected String name = null;
	/** The logger for this Mutex */
	protected Logger log = null;
	/** The log level for this Mutex */
	protected Level logLevel = null;
	/**
	 * Debugging
	 */
	protected boolean logEnabled = true;

	/**
	 * Constructor
	 */
	public Mutex(String name, Logger log, Level level) {
		this.name = name;
		this.log = log;
		this.logLevel = level;
	}

	/**
	 * Log a message
	 */
	private void log(String message) {
		if (logEnabled) {
			if (null != log && null != logLevel) {
				log.log(logLevel, message);
			}
		}
	}

	/**
	 * Enable logging
	 */
	public void enableLogging() {
		logEnabled = true;
	}

	/**
	 * Disable logging
	 */
	public void disableLogging() {
		logEnabled = false;
	}

	/**
	 * Acquire this mutex, blocking indefinitely
	 */
	public boolean acquire() throws InterruptedException {

		log(Thread.currentThread().getName()
				+ " trying to acquire mutex " + name);

		if (Thread.interrupted()) {
			log(Thread.currentThread().getName()
					+ " interrupted trying to acquire mutex " + name);
			throw new InterruptedException();
		}
		synchronized (this) {
			if (ownedBy == Thread.currentThread()) {
				log(Thread.currentThread().getName() + " already owns mutex " + name);
				return true;
			}
			try {
				while (null != ownedBy) {
					this.wait();
				}
				log(Thread.currentThread().getName()
						+ " acquired mutex " + name);
				ownedBy = Thread.currentThread();
			} catch (InterruptedException ex) {
				log(Thread.currentThread().getName()
						+ " interrupted trying to acquire mutex "
						+ name + " after acquiring monitor");
				notify();
				throw ex;
			}
			return true;
		}
	}

	/**
	 * Release this mutex
	 * @throw	IllegalStateException	if calling thread does not own it.
	 */
	public synchronized void release() {
		Thread thread = Thread.currentThread();
		if (null == ownedBy) {
			log(thread.getName() + " tried to release unowned mutex " + name);
			return;
		}
		if (thread == ownedBy) {
			log(thread.getName() + " released mutex " + name);
			ownedBy = null;
			this.notify();
			return;
		} else {
			log(thread.getName() + " tried to released mutex "
					+ name + " without owning it");
		}
	}

	/**
	 * Attempt to acquire this mutex by waiting up to the given millisec
	 * @return	true if granted
	 */
	public boolean attempt(long msecs) throws InterruptedException {

		log(Thread.currentThread().getName()
				+ " will wait up to " + msecs
				+ "ms to acquire mutex " + name);

		if (Thread.interrupted()) {
			log(Thread.currentThread().getName()
					+ " interrupted trying to acquire mutex " + name);
			throw new InterruptedException();
		}
		synchronized (this) {

			if (null == ownedBy) {

				log(Thread.currentThread().getName()
						+ " acquired mutex " + name);
				ownedBy = Thread.currentThread();
				return true;

			} else if (ownedBy == Thread.currentThread()) {

				log(Thread.currentThread().getName()
						+ " already owns mutex " + name);
				return true;

			} else if (msecs <= 0) {

				log(Thread.currentThread().getName() + " did not get mutex "
						+ name + " since it is owned by " + ownedBy.getName()
						+ "and caller did not want to wait");
				return false;

			} else {

				log(Thread.currentThread().getName()
						+ " has to wait for mutex " + name
						+ " since it is owned by " + ownedBy.getName());
				long waitTime = msecs;
				long start = System.currentTimeMillis();
				try {
					for (;;) {
						this.wait(waitTime);
						if (null == ownedBy) {

							ownedBy = Thread.currentThread();
							log(Thread.currentThread().getName()
									+ " acquired mutex " + name + " after waiting");
							return true;

						} else {

							waitTime = msecs - (System.currentTimeMillis() - start);
							if (waitTime <= 0) {
								log(Thread.currentThread().getName()
										+ " failed to acquire mutex " + name + " after waiting"
										+ +msecs + "ms");
								return false;
							}
						}
					}
				} catch (InterruptedException ex) {
					log(Thread.currentThread().getName()
							+ " interrupted while trying to acquire mutex " + name);
					notify();
					throw ex;
				}
			}
		}
	}
}
