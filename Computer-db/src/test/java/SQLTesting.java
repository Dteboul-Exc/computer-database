package test.java;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CompanyDAOTesting.class })
public class SQLTesting {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(CompanyDAOTesting.class);
	      for (Failure failure : result.getFailures()) {
	          System.out.println(failure.toString());
	       }
	 		
	       System.out.println(result.wasSuccessful());
	}

}