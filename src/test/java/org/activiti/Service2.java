package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class Service2 implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("in service 2, time = " + (System.currentTimeMillis() - MyUnitTest.startTime) / 1000);
		
		Thread.sleep(1000);
		Integer counter = (Integer) execution.getVariable("lock");
		Integer index= (Integer) execution.getVariable("lock");
		System.out.println("index = " +index + " value=" + (MyUnitTest.counters[index]--));
		
	}

}
