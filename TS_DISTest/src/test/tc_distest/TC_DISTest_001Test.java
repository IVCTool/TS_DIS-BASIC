package tc_distest;

import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import de.fraunhofer.iosb.tc_lib.IVCT_Verdict;

public class TC_DISTest_001Test extends TSTest {

    @Test
    @Override
    void test() {

        TC_DISTest_001 testCase = new TC_DISTest_001();
        setUp(testCase);
        IVCT_Verdict verdict = testCase.execute(tcParamJson, runLogger);
		runLogger.info("Test Case Verdict: {}", verdict);
		assertSame("Test Case shall pass", IVCT_Verdict.Verdict.PASSED, verdict.verdict);

    }
    
}
