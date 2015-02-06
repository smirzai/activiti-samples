package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class Service3 implements JavaDelegate{
	 static Logger log = Logger.getLogger(Service3.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("Service 3 started, time = " + (System.currentTimeMillis() - MyUnitTest.startTime) );
		Thread.sleep(500);
		log.info("Service 3 finished, time = " + (System.currentTimeMillis() - MyUnitTest.startTime) );

	}

}
