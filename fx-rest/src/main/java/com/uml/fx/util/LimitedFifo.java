/*
 * Copyright (c) 2013 OutSmart Power Systems, Inc. -- All Rights Reserved.
 */
package com.uml.fx.util;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Provide a FIFO with a limited depth.
 *
 * @author uwe
 */
public class LimitedFifo<T> extends ConcurrentLinkedQueue<T> {

	private final int depth;

	/**
	 * Create a bounded FIFO.
	 *
	 * @param depth fifo depth
	 */
	public LimitedFifo(int depth) {
		if (depth <= 0) {
			throw new IllegalArgumentException("LimitedFifo: must have a depth > 0");
		}
		this.depth = depth;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(T e) {
		return this.offer(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean offer(T e) {
		synchronized (this) {
			while (super.size() > depth - 1) {
				// clean up all the old elements
				super.poll();
			}
			return super.offer(e);
		}
	}

	/**
	 * The poll operation is not allowed, because it would alter the queue
	 * depth. It will always throw an IllegalStateException.
	 *
	 * @throws IllegalStateExecption
	 */
	@Override
	public T poll() {
		throw new IllegalStateException("Cannot poll from a fixed fifo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T peek() {
		synchronized (this) {
			return super.peek();
		}
	}
}
