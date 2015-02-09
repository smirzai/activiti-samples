package org.activiti;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class AggregateService implements JavaDelegate {
	 static Logger log = Logger.getLogger(AggregateService.class);
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Integer lockNo = (Integer) execution.getVariable("lock");
		if (lockNo == null) {
			log.error("lock varaible is null in processId=" + execution.getProcessInstanceId());
			return;
		}
		log.info("All external ws calls returned. Try to unlock the lock. time = " +  (System.currentTimeMillis()- MyUnitTest.startTime));
		Condition condition = MyUnitTest.conditions[lockNo];
		Lock myLock = MyUnitTest.locks[lockNo];
		myLock.lock();
		condition.signalAll();
		myLock.unlock();
		
		
		
		
		
		
	}

}
