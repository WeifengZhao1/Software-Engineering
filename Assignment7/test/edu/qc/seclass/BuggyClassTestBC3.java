package edu.qc.seclass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestBC3 {
    //   it is possible to create a test suite that achieves 100% branch coverage and does not reveal the fault.
    @Test
    public void testBuggyMethod3() {
        BuggyClass buggy = new BuggyClass();
        assertEquals(1, buggy.buggyMethod3(1,1));
        assertEquals(1, buggy.buggyMethod3(2,2));
    }
}