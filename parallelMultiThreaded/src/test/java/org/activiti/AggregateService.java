package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class AggregateService implements JavaDelegate {
	 static Logger log = Logger.getLogger(AggregateService.class);
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Integer lock = (Integer) execution.getVariable("lock");
		if (lock == null) {
			log.error("lock varaible is null in processId=" + execution.getProcessInstanceId());
			return;
		}
		log.info("All external ws calls returned. Try to unlock the lock. time = " +  (System.currentTimeMillis()- MyUnitTest.startTime));
		Boolean obj = MyUnitTest.processed[lock];
		
		synchronized (obj) {
			MyUnitTest.processed[lock] = true;
			obj.notify();
			
		}
		
		
		
		
		
	}

}
