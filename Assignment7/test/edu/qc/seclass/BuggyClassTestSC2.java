package edu.qc.seclass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC2 {
    // (1) it is possible to create a test suite that achieves 100% statement coverage and does not reveal the fault.
    @Test
    public void testBuggyMethod2(){
        BuggyClass buggy = new BuggyClass();
        assertEquals(5, buggy.buggyMethod2(1, 5));
    }
}





