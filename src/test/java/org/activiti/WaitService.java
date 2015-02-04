package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class WaitService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Integer index= (Integer) execution.getVariable("lock");
		while (MyUnitTest.counters[index] > 0) {
			Thread.sleep(100);
		}
		
	}

}
