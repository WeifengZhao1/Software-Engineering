package edu.qc.seclass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC3 {
    //  it is possible to create a test suite that achieves 100% statement coverage, does not achieve 100% branch coverage, and reveals the fault.
    @Test
    public void testBuggyMethod3() {
        BuggyClass buggy = new BuggyClass();
        assertEquals(5, buggy.buggyMethod3(0,5));
        assertEquals(5, buggy.buggyMethod3(20, 4));
    }
}