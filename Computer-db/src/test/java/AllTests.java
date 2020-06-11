package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Company_testing.class, CompanyDAOTesting.class, ServiceComputer_Testing.class })
public class AllTests {

}
