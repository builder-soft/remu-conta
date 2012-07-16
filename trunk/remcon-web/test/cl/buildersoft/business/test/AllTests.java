package cl.buildersoft.business.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cl.buildersoft.business.test.period.TestPeriod;

@RunWith(Suite.class)
@SuiteClasses({ TestSaveBookForEmployee.class, TestSaveOvertime.class, TestPeriod.class })
public class AllTests {

}
