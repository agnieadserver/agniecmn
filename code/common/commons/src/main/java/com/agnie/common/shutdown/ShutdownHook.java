package com.agnie.common.shutdown;

/**
 * @author Pandurang Patil 15-Mar-2014
 * 
 */
public interface ShutdownHook {
	/**
	 * 
	 * @param sync represents call should be returned only after shutdown is done.
	 */
	void shutdown(boolean sync);
}
