package selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   LoginCustomerTest.class,
   LoginSportCentreTest.class,
   LoginSportTrainerTest.class
})
public class JUnitTestSuite {

}
