package edu.qc.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MyMainTest {
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Four score and seven years ago our fathers brought forth on this continent a new nation,\n " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n +" +
                " Now we are engaged in a great civil war, testing whether that nation or any nation \n +" +
                " so conceived and so dedicated, can long endure.");

        fileWriter.close();
        return file1;
    }

    private File createInputFile5() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party shall have been duly convicted,\n +" +
                " shall exist within the United States, or any place subject to their jurisdiction.");

        fileWriter.close();
        return file1;
    }

    private File createInputFile6() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("No state shall make or enforce any law \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " nor shall any state deprive any person of life, liberty, or property, \n" +
                "without due process of law; nor deny to any person within its jurisdiction the equal protection of the laws.");

        fileWriter.close();
        return file1;
    }

    private File createInputFile7() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("The right of citizens of the United States to vote shall not \n" +
                "be denied or abridged by the United States or by any State \n" +
                "on account of race, color, or previous condition of servitude.");

        fileWriter.close();
        return file1;
    }


    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /*
        Test frame: 01 MyMainTest01 Test Case 33
        Test frame: 02 MyMainTest02 Test Case 37
        Test frame: 03 MyMainTest03 Test Case 23
        Test frame: 04 MyMainTest07 Test Case 67
        Test frame: 05 MyMainTest11 Test Case 13
        Test frame: 06 MyMainTest13 Test Case 34
        Test frame: 07 MyMainTest14 Test Case 26
        Test frame: 08 MyMainTest15 Test Case 35
        Test frame: 09 MyMainTest24 Test Case 36
        Test frame: 10 MyMainTest26 Test Case 25
     */


    @Test
    public void MyMainTest01() throws Exception {
        // Test frame 01

        //   Test Case 33 		(Key = 2.3.1.3.4.2.2.2.2.2.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  One
        //   Position of the string in the file                    :  Any line
        //   number of command line arguments and options          :  -f
        //   Replace to                                            :  ReplaceFrom
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  1

        File inputFile = createInputFile1();

        String args[] = {"-f", "Bill", "Jerry", "--",  inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Jerry,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest02 () throws Exception {
        //Test frame 02

        // Test Case 37 		(Key = 2.3.1.3.6.4.2.2.2.2.)
        //        Size                                                  :  Not Empty
        //        Number Of the occurencences of the string in the file :  Many
        //        Number of occurences in one line                      :  One
        //        Position of the string in the file                    :  Any line
        //        number of command line arguments and options          :  -i
        //        Replace to                                            :  Insensitive
        //        length of string                                      :  One or More
        //        File Size                                             :  >1
        //        File Name                                             :  Present
        //        Number of Files                                       :  1

        File inputFile = createInputFile1();

        String args[] = {"-i", "Bill", "Jerry", "--",  inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Jerry,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy Jerry\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }


    @Test
    public void MyMainTest03 () throws Exception {
        // Test frame 03
        //        Test Case 23 		(Key = 2.3.1.2.4.2.2.2.2.2.)
        //        Size                                                  :  Not Empty
        //        Number Of the occurencences of the string in the file :  Many
        //        Number of occurences in one line                      :  One
        //        Position of the string in the file                    :  Last line
        //        number of command line arguments and options          :  -f
        //        Replace to                                            :  ReplaceFrom
        //        length of string                                      :  One or More
        //        File Size                                             :  >1
        //        File Name                                             :  Present
        //        Number of Files                                       :  1

        File inputFile = createInputFile1();

        String args[] = {"-f", "!", "...", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again...";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest04 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f and -l parameters work when used together)

        File inputFile = createInputFile1();

        String args[] = {"-f", "-l", "s", "ss", "--", inputFile.getPath()};
        Main.main(args);

        String expected =  "Howdy Bill,\n" +
                "Thiss is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's ssay \"howdy bill\" again!";


        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest05 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f and -l parameters work when used together)

        File inputFile = createInputFile2();

        String args[] = {"-f", "-l", "Item", "item", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) item 1\n" +
                "-b) item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest06 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f parameters working alone

        File inputFile = createInputFile2();

        String args[] = {"-f", "twice", "once", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" once";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest07 () throws Exception {
        // Test frame 04
        // Test Case 67 		(Key = 2.3.2.3.6.4.2.2.2.2.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  Many
        //   Position of the string in the file                    :  Any line
        //   number of command line arguments and options          :  -i
        //   Replace to                                            :  Insensitive
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  1


        File inputFile = createInputFile2();

        String args[] = {"-i", "howdy", "how", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "how Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"how Bill\" twice";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }


    @Test
    public void MyMainTest08 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile3();

        String args[] = {"-i","123", "456", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 456?\n" +
                "It is important to know your abc and 456," +
                "so you should study it\n" +
                "and then repeat with me: abc and 456";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest09 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile3();

        String args[] = {"-i","study","not study", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should not study it\n" +
                "and then repeat with me: abc and 123";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest10 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f parameters working alone

        File inputFile = createInputFile3();

        String args[] = {"-f","have","had", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, had you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest11 () throws Exception {
        // Test frame 05
        // Test Case 13 		(Key = 2.3.1.1.4.2.2.2.2.2.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  One
        //   Position of the string in the file                    :  First line
        //   number of command line arguments and options          :  -f
        //   Replace to                                            :  ReplaceFrom
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  1

        File inputFile = createInputFile4();

        String args[] = {"-f","years","years,", "--", inputFile.getPath()};
        Main.main(args);

        String expected =  "Four score and seven years, ago our fathers brought forth on this continent a new nation,\n " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n +" +
                " Now we are engaged in a great civil war, testing whether that nation or any nation \n +" +
                " so conceived and so dedicated, can long endure.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest12 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile4();

        String args[] = {"-i","nation","NATION", "--", inputFile.getPath()};
        Main.main(args);

        String expected =  "Four score and seven years ago our fathers brought forth on this continent a new NATION,\n " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n +" +
                " Now we are engaged in a great civil war, testing whether that NATION or any NATION \n +" +
                " so conceived and so dedicated, can long endure.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest13() throws Exception {
        // Test frame 06
        // Test Case 34 		(Key = 2.3.1.3.4.2.2.2.2.3.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  One
        //   Position of the string in the file                    :  Any line
        //   number of command line arguments and options          :  -f
        //   Replace to                                            :  ReplaceFrom
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  >1

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile4();


        String args[] = {"-b","-f", "we", "WE", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that WE can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 =  "Four score and seven years ago our fathers brought forth on this continent a new nation,\n " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n +" +
                " Now WE are engaged in a great civil war, testing whether that nation or any nation \n +" +
                " so conceived and so dedicated, can long endure.";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest14() throws Exception {
        // Test frame 07
        // Test Case 26 		(Key = 2.3.1.2.5.3.2.2.2.3.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  One
        //   Position of the string in the file                    :  Last line
        //   number of command line arguments and options          :  -l
        //   Replace to                                            :  ReplaceTo
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  >1

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile4();


        String args[] = {"-b","-l","And","AND", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "AND let's say \"howdy bill\" again!";

        String expected2 = "Four score and seven years ago our fathers brought forth on this continent a new nation,\n " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n +" +
                " Now we are engaged in a great civil war, testing whether that nation or any nation \n +" +
                " so conceived and so dedicated, can long endure.";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest15 () throws Exception {
        // Test frame 08
        // Test Case 35 		(Key = 2.3.1.3.5.3.2.2.2.2.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  One
        //   Position of the string in the file                    :  Any line
        //   number of command line arguments and options          :  -l
        //   Replace to                                            :  ReplaceTo
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  1

        File inputFile = createInputFile3();

        String args[] = {"-f","Bill","Andy", "--", inputFile.getPath()};
        Main.main(args);

        String args2[] = {"-l", "abc","ABC", "--", inputFile.getPath()};
        Main.main(args2);

        String expected = "Howdy Andy, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: ABC and 123";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest16 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f parameters working alone
        File inputFile = createInputFile4();

        String args[] = {"-f","great","GREAT", "--", inputFile.getPath()};
        Main.main(args);

        String expected =  "Four score and seven years ago our fathers brought forth on this continent a new nation,\n " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n +" +
                " Now we are engaged in a GREAT civil war, testing whether that nation or any nation \n +" +
                " so conceived and so dedicated, can long endure.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest17 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f parameters working alone

        File inputFile = createInputFile2();

        String args[] = {"-f","list","List", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a List:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";;

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest18 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile2();

        String args[] = {"-i","Item","List", "--", inputFile.getPath()};
        Main.main(args);

        String expected =  "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) List 1\n" +
                "-b) List 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest19 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile1();

        String args[] = {"-i","test","TEST", "--", inputFile.getPath()};
        Main.main(args);

        String expected =  "Howdy Bill,\n" +
                "This is a TEST file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting TEST cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest20 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f and -l parameters work when used together

        File inputFile = createInputFile5();

        String args[] = {"-f", "-l", "shall", "Shall", "--",  inputFile.getPath()};
        Main.main(args);

        String expected = "Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party Shall have been duly convicted,\n +" +
                " Shall exist within the United States, or any place subject to their jurisdiction.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }


    @Test
    public void MyMainTest21() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile1();
        String args[] = {"-i", "bill", "James", "--", inputFile.getPath()};
        Main.main(args);

        String expected =  "Howdy James,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy James\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest22() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -i parameters work when used together

        File inputFile = createInputFile2();
        String args[] = {"-b","-i", "bill", "James", "--", inputFile.getPath()};
        Main.main(args);

        String expected =   "Howdy James,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy James\" twice";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest23() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -l parameters work when used together

        File inputFile = createInputFile5();
        String args[] = {"-b","-l", "any", "ANY", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party shall have been duly convicted,\n +" +
                " shall exist within the United States, or ANY place subject to their jurisdiction.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest24() throws Exception {
        // Test frame 09
        // Test Case 36 		(Key = 2.3.1.3.5.3.2.2.2.3.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  One
        //   Position of the string in the file                    :  Any line
        //   number of command line arguments and options          :  -l
        //   Replace to                                            :  ReplaceTo
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  >1


        File inputFile1 = createInputFile3();
        File inputFile2 = createInputFile5();
        File inputFile3 = createInputFile2();

        String args[] = {"-b", "-l", "-f", "Bill", "Nick", "--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        String args1[] = {"123","321", "--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        Main.main(args);
        Main.main((args1));

        String expected1 = "Howdy Nick, have you learned your abc and 321?\n" +
                "It is important to know your abc and 321," +
                "so you should study it\n" +
                "and then repeat with me: abc and 321";

        String expected2 = "Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party shall have been duly convicted,\n +" +
                " shall exist within the United States, or any place subject to their jurisdiction.";

        String expected3 = "Howdy Nick,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Nick\" twice";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("The files differ!", expected3, actual3);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest25() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -i parameters work when used together

        File inputFile = createInputFile1();
        String args[] = {"-b","-i", "le", "LE", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test fiLE for the replace utility\n" +
                "LEt's make sure it has at LEast a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And LEt's say \"howdy bill\" again!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest26() throws Exception {
        // Test frame 10
        // Test Case 25 		(Key = 2.3.1.2.5.3.2.2.2.2.)
        //   Size                                                  :  Not Empty
        //   Number Of the occurencences of the string in the file :  Many
        //   Number of occurences in one line                      :  One
        //   Position of the string in the file                    :  Last line
        //   number of command line arguments and options          :  -l
        //   Replace to                                            :  ReplaceTo
        //   length of string                                      :  One or More
        //   File Size                                             :  >1
        //   File Name                                             :  Present
        //   Number of Files                                       :  1

        File inputFile = createInputFile6();
        String args[] = {"-l", "equal", "EQUAL", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "No state shall make or enforce any law \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " nor shall any state deprive any person of life, liberty, or property, \n" +
                "without due process of law; nor deny to any person within its jurisdiction the EQUAL protection of the laws.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest27() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -f parameters work when used together

        File inputFile1 = createInputFile6();
        File inputFile2 = createInputFile4();


        String args[] = {"-b","-f","or","OR", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);

        String expected1 = 	"No state shall make OR enforce any law \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " nor shall any state deprive any person of life, liberty, or property, \n" +
                "without due process of law; nor deny to any person within its jurisdiction the equal protection of the laws.";

        String expected2 =  "Four scORe and seven years ago our fathers brought forth on this continent a new nation,\n " +
                "conceived in Liberty, and dedicated to the proposition that all men are created equal.\n +" +
                " Now we are engaged in a great civil war, testing whether that nation or any nation \n +" +
                " so conceived and so dedicated, can long endure.";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest28() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f parameters working alone

        File inputFile = createInputFile7();
        String args[] = {"-f", "right", "RIGHT", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "The RIGHT of citizens of the United States to vote shall not \n" +
                "be denied or abridged by the United States or by any State \n" +
                "on account of race, color, or previous condition of servitude.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest29() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile7();
        String args[] = {"-i","race", "RACE","color","COLOR", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "The right of citizens of the United States to vote shall not \n" +
                "be denied or abridged by the United States or by any State \n" +
                "on account of RACE, COLOR, or previous condition of servitude.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest30() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -i parameters work when used together

        File inputFile = createInputFile7();
        String args[] = {"-b","-i","any","ANY", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "The right of citizens of the United States to vote shall not \n" +
                "be denied or abridged by the United States or by ANY State \n" +
                "on account of race, color, or previous condition of servitude.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest31() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b,-i and -f parameters work when used together

        File inputFile1 = createInputFile5();
        File inputFile2 = createInputFile6();
        File inputFile3 = createInputFile7();

        String args[] = {"-b","-i","any","ANY", "--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        Main.main(args);
        String args1[] = {"-f","shall","Shall", "--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        Main.main(args1);

        String expected1 = 	"Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party Shall have been duly convicted,\n +" +
                " shall exist within the United States, or ANY place subject to their jurisdiction.";

        String expected2 =  "No state Shall make or enforce ANY law \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " nor shall ANY state deprive ANY person of life, liberty, or property, \n" +
                "without due process of law; nor deny to ANY person within its jurisdiction the equal protection of the laws.";

        String expected3 = "The right of citizens of the United States to vote Shall not \n" +
                "be denied or abridged by the United States or by ANY State \n" +
                "on account of race, color, or previous condition of servitude.";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("The files differ!", expected3, actual3);


        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest32() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -f parameters working alone

        File inputFile1 = createInputFile5();
        File inputFile2 = createInputFile6();
        File inputFile3 = createInputFile7();

        String args[] = {"-f","crime","CRIME", "--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        Main.main(args);

        String expected1 = 	"Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for CRIME whereof the party shall have been duly convicted,\n +" +
                " shall exist within the United States, or any place subject to their jurisdiction.";

        String expected2 =  "No state shall make or enforce any law \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " nor shall any state deprive any person of life, liberty, or property, \n" +
                "without due process of law; nor deny to any person within its jurisdiction the equal protection of the laws.";

        String expected3 = "The right of citizens of the United States to vote shall not \n" +
                "be denied or abridged by the United States or by any State \n" +
                "on account of race, color, or previous condition of servitude.";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("The files differ!", expected3, actual3);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest33() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -f parameters work when used together

        File inputFile1 = createInputFile1();
        File inputFile2 = createInputFile3();
        File inputFile3 = createInputFile6();


        String args[] = {"-b","-f","replace","REPLACE", "--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        Main.main(args);

        String expected1 = 	"Howdy Bill,\n" +
                "This is a test file for the REPLACE utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String expected2 =  "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String expected3 = "No state shall make or enforce any law \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " nor shall any state deprive any person of life, liberty, or property, \n" +
                "without due process of law; nor deny to any person within its jurisdiction the equal protection of the laws.";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("The files differ!", expected3, actual3);


        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest34 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b parameters working alone

        File inputFile = createInputFile5();

        String args[] = {"-b", "--",  inputFile.getPath()};
        Main.main(args);

        String expected = "Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party shall have been duly convicted,\n +" +
                " shall exist within the United States, or any place subject to their jurisdiction.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest35 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -i parameters working alone

        File inputFile = createInputFile5();

        String args[] = {"-i", "shall","SHALL", "--",  inputFile.getPath()};
        Main.main(args);

        String expected = "Neither slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party SHALL have been duly convicted,\n +" +
                " SHALL exist within the United States, or any place subject to their jurisdiction.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest36 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -l parameters work when used together

        File inputFile = createInputFile2();

        String args[] = {"-b","-l", "twice","ONCE", "--",  inputFile.getPath()};
        Main.main(args);

        String expected =  "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" ONCE";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest37 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -l and -i parameters work when used together

        File inputFile1 = createInputFile2();
        File inputFile2 = createInputFile3();

        String args[] = {"-l", "twice","ONCE", "--",  inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args);
        String args1[] = {"-i","Bill","Andy","--",inputFile1.getPath(),inputFile2.getPath()};
        Main.main(args1);

        String expected1 =  "Howdy Andy,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Andy\" ONCE";

        String expected2 = "Howdy Andy, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);

        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest38 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -l parameters work when used together

        File inputFile = createInputFile5();

        String args[] = {"-b","-l", "Neither","NEITHER", "--",  inputFile.getPath()};
        Main.main(args);

        String expected =  "NEITHER slavery nor involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party shall have been duly convicted,\n +" +
                " shall exist within the United States, or any place subject to their jurisdiction.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest39 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -l parameters work when used together

        File inputFile = createInputFile6();

        String args[] = {"-b","-l", "laws","LAWS", "--",  inputFile.getPath()};
        Main.main(args);

        String expected =  "No state shall make or enforce any law \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " nor shall any state deprive any person of life, liberty, or property, \n" +
                "without due process of law; nor deny to any person within its jurisdiction the equal protection of the LAWS.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest40 () throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b and -f parameters work when used together

        File inputFile = createInputFile7();

        String args[] = {"-b","-f", "right","RIGHT", "--",  inputFile.getPath()};
        Main.main(args);
        String args1[] = {"vote","VOTE","--",inputFile.getPath()};
        Main.main(args1);

        String expected =  "The RIGHT of citizens of the United States to VOTE shall not \n" +
                "be denied or abridged by the United States or by any State \n" +
                "on account of race, color, or previous condition of servitude.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }

    @Test
    public void MyMainTest41() throws Exception {
        // Newly created test case.
        // Purpose: Testing that the -b,-f and -i parameters work when used together

        File inputFile1 = createInputFile5();
        File inputFile2 = createInputFile6();
        File inputFile3 = createInputFile7();


        String args[] = {"-b","-f","nor","NOR", "--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        Main.main(args);
        String args1[] = {"-i","law","LAW", "liberty","LIBERTY","--", inputFile1.getPath(), inputFile2.getPath(),inputFile3.getPath()};
        Main.main(args1);


        String expected1 = 	"Neither slavery NOR involuntary servitude,\n +" +
                " except as a punishment for crime whereof the party shall have been duly convicted,\n +" +
                " shall exist within the United States, or any place subject to their jurisdiction.";

        String expected2 =  "No state shall make or enforce any LAW \n" +
                "which shall abridge the privileges or immunities of citizens of the United States;\n" +
                " NOR shall any state deprive any person of life, LIBERTY, or property, \n" +
                "without due process of LAW; nor deny to any person within its jurisdiction the equal protection of the LAWs.";

        String expected3 = "The right of citizens of the United States to vote shall not \n" +
                "be denied or abridged by the United States or by any State \n" +
                "on account of race, color, or previous condition of servitude.";


        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());
        String actual3 = getFileContent(inputFile3.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("The files differ!", expected3, actual3);


        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }
}