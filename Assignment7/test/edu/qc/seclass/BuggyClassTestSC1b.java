package edu.qc.seclass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC1b {
    //  (1) every test suite that achieves 100% statement coverage reveals the fault
    @Test
    public void testBuggyMethod1() {
        BuggyClass buggy = new BuggyClass();
        assertEquals(0, buggy.buggyMethod1(0, 3));
    }
}
