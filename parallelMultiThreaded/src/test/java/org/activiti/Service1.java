package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class Service1 implements JavaDelegate{
	 static Logger log = Logger.getLogger(Service1.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("Service 1 started, time = " + (System.currentTimeMillis() - MyUnitTest.startTime) / 1000);
		Thread.sleep(1000);
		log.info("Service 2 started, time = " + (System.currentTimeMillis() - MyUnitTest.startTime) / 1000);
	}

}
