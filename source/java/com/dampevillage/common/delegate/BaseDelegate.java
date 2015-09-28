/*
 *================================================================
 * Copyright          : P&O Ferries Ltd
 *================================================================
 */

package com.dampevillage.common.delegate;



/**
 * Generic base class for Service Delegate.
 * 
 * Copyright (c) P&O Ferries, All Rights Reserved.<br>
 * <br>
 * 
 * @param <I>
 *            generic type.
 * @param <O>
 *            generic type.
 */
public abstract class BaseDelegate<I, O> {

	/**
	 * Used to implement the business logic for the delegate.
	 * 
	 * @param parameters
	 *            I input generic type.
	 * 
	 * @return O returning generic type.
	 * @throws BusinessException
	 *             could be thrown.
	 * @throws SystemException
	 *             could be thrown.
	 */
	public abstract O invoke(I parameters);

}
