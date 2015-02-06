package org.activiti;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.asyncexecutor.DefaultAsyncJobExecutor;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;

public class MyUnitTest extends PluggableActivitiTestCase{
	
	public static long startTime ;
	public static Boolean[] processed = new Boolean[1000];
	
	 static Logger log = Logger.getLogger(MyUnitTest.class);

	
	
	@Deployment(resources = {"org/activiti/test/my-process.bpmn20.xml"})
	public void test() throws InterruptedException {
		DefaultAsyncJobExecutor je = (DefaultAsyncJobExecutor) processEngineConfiguration.getAsyncExecutor();
		je.setCorePoolSize(10);
		
		// specific to this process. Maybe could be changed with processInstanceId and an hashmap instead of array
		int index = 0;
		Integer lock = new Integer(index);
		processed[index] = false;
		
		
		Map vars = new HashMap();
		vars.put("lock", lock);
		
		
		startTime = System.currentTimeMillis();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", vars);
		log.info("Call to start process just returned. Starting wait loop");
		assertNotNull(processInstance);

		synchronized (processed[index]) {
			while (!processed[index]) { 
				processed[index].wait();
			}
		}
		
		log.info("wait loop exited. Program finished. total taime spent = " + (System.currentTimeMillis()- startTime));
		
		}
		
	

}
