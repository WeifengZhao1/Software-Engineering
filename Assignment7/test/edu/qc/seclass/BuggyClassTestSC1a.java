package edu.qc.seclass;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuggyClassTestSC1a {
    // (1) it is possible to create a test suite that achieves 100% statement coverage and does not reveal the fault
    @Test
    public void testBuggyMethod1() {
        BuggyClass buggy = new BuggyClass();
        assertEquals(1, buggy.buggyMethod1(5, 5));
        assertEquals(1, buggy.buggyMethod1(-1,-1));
    }
}
