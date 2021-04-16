package edu.qc.seclass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC4 {
    //  it is possible to create a test suite that achieves 100% statement coverage, does not achieve 100% branch coverage, and reveals the fault.
    @Test
    public void testBuggyMethod4() {
        BuggyClass buggy = new BuggyClass();
        assertEquals(5, buggy.buggyMethod4(0,5));
    }
}
