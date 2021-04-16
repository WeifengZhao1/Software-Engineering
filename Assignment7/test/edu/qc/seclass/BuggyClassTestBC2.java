package edu.qc.seclass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestBC2 {
    // every test suite that achieves more than 50% branch coverage reveals the fault.
    @Test
    public void testBuggyMethod2() {
        BuggyClass buggy = new BuggyClass();
        assertEquals(0, buggy.buggyMethod2(0,5));
    }
}

