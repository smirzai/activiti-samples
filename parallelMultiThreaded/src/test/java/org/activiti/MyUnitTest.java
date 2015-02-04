package org.activiti;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.variable.VariableType;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

public class MyUnitTest extends PluggableActivitiTestCase{
	
	public static long startTime = System.currentTimeMillis();
	public static Integer[] counters = new Integer[1000];
	
	
	
	
	@Deployment(resources = {"org/activiti/test/my-process.bpmn20.xml"})
	public void test() throws InterruptedException {
		int index = 0;
		Integer lock = new Integer(index);
		counters[index] = 3;
		
		
		Map vars = new HashMap();
		vars.put("lock", lock);
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", vars);
		System.out.println("processs returned");
		assertNotNull(processInstance);

		waitForJobExecutorToProcessAllJobs(5000, 100);
		System.out.println("jobs finished");
		try {
			synchronized (lock) {
				lock.wait();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
