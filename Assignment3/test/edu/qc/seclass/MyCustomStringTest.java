package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers2() {
       mycustomstring.setString("MSC11 77 koo0 t0 the ps5.");
        assertEquals(5, mycustomstring.countNumbers());
    }

    @Test (expected = NullPointerException.class)
    public void testCountNumbers3() {
        mycustomstring.setString(null);
        mycustomstring.countNumbers();
    }

    @Test
    public void testCountNumbers4() {
        mycustomstring.setString("The table's 123ab 222 to the cc2 and 12345ds.");
        assertEquals(4, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers5() {
        mycustomstring.setString("The sda 123 22Apple 19.21 3 /1  2202eq0appdmS!..");
        assertEquals(8, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers6() {
        mycustomstring.setString("MS9876---==124 sfja21xdsa e1 3 ifjiasjri2q");
        assertEquals(6, mycustomstring.countNumbers());
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    @Test(expected = NullPointerException.class )
    public void testGetEveryNthCharacterFromBeginningOrEnd3() throws Exception{
        mycustomstring.setString(null);
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true);
    }
    @Test(expected =IllegalArgumentException.class )
    public void testGetEveryNthCharacterFromBeginningOrEnd4() throws Exception{
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-1, true);
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(" tps 1ih5nrt", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'3rtm1s sr,g", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));}

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("br  t  ni", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, false));}

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("br  t  ni", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(5, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("3pm1 5,t", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(6, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'tt shrr", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(6, true));}

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("ttdi ,?", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(7, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("It 1n5 ", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(7, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("ts1hnt", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(8, false));}

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'rmss,", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(8, true));
    }

    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    @Test (expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("Throws NullPointerException", mycustomstring.getString());
    }

    @Test (expected =MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring3() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(9, 999999);
        assertEquals("Throws IndexOutOfBoundsException", mycustomstring.getString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring4() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(55, 5);
        assertEquals("Throws IllegalArgumentException", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring5() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(1, 45);
        assertEquals("I'd bThreettThreer put sZerome dOneSixOnets in this FivetrOnenSix, right?", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring6() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(20, 35);
        assertEquals("I'd b3tt3r put s0me dOneSixOnets in this 5tr1n6, right?", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring7() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(2, 18);
        assertEquals("I'd bThreettThreer put sZerome d161ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring8() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(8, 25);
        assertEquals("I'd b3ttThreer put sZerome dOneSixOnets in this 5tr1n6, right?", mycustomstring.getString());
    }
}
