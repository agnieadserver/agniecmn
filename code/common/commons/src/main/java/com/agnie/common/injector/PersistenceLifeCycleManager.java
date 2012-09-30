package com.agnie.common.injector;

/**
 * Defined a interface to start and stop persistence service also to begin and end unit of work. In multiple Persistent
 * unit environment.
 * 
 */
public interface PersistenceLifeCycleManager {

	public void startService();

	public void stopService();

	public void beginUnitOfWork();

	public void endUnitOfWork();
}
