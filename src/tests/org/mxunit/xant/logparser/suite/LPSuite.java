package tests.org.mxunit.xant.logparser.suite;

import tests.org.mxunit.xant.logparser.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
  FusionReactorSQLGeneratorTest.class,
  FusionReactorLogParserTest.class,
  FusionReactorLogParserReportTest.class,
  XMLUtilTest.class
})
public class LPSuite {
    // the class remains completely empty, 
    // being used only as a holder for the above annotations
}