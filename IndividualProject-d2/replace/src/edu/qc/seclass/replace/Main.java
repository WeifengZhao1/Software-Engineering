
package edu.qc.seclass.replace;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

public class Main {

     static boolean Backup,ReplaceFrom,ReplaceTo, Insensitive, Condition;
     static String replaceTo,replaceFrom;
     static ArrayList<java.io.File> File = new ArrayList<>();
     static ArrayList<String> From = new ArrayList<>();
     static ArrayList<String> To = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        File.clear();
        Backup = ReplaceFrom = ReplaceTo = Insensitive = Condition = false;
        File = new ArrayList<>();
        From = new ArrayList<>();
        To = new ArrayList<>();

        try {
            for(int i = 0; i < args.length; i++) {
                if(!Condition) {
                    switch(args[i]) {
                        case "-b":
                            Backup = true;
                            break;

                        case "-f":
                            ReplaceFrom = true;
                            break;

                        case "-l":
                            ReplaceTo = true;
                            break;

                        case "-i":
                            Insensitive = true;
                            break;

                        default:
                            Condition = true;
                    }
                }

                if(Condition == true) {

                    if(args[i].equals("--")) {
                        i++;
                    }

                    while(From.size() == 0 || !args[i].equals("--") ) {
                        From.add(args[i]);
                        i++;

                        To.add(args[i]);
                        i++;
                    }
                    i++;
                    while(i < args.length) {
                        File.add(new File(args[i]));
                        i++;
                    }
                }
            }
        }
        catch (Exception e) {
            usage();
        }

        for(File file : File) {
            int index;
            int curChar;

            for(int i = 0; i < From.size(); i++) {
                replaceTo = To.get(i);
                replaceFrom = From.get(i);


                try {
                    String text = "";
                    FileReader filereader = new FileReader(file);
                    BufferedReader bufferedreader = new BufferedReader(filereader);

                    while( (curChar = bufferedreader.read()) >= 0) {
                        if(true) {
                            text += (char)curChar;
                        }
                    }
                    bufferedreader.close();

                    if(Backup) {
                            File file2 = new File(file.getPath() + ".bck");
                            Files.copy(file.toPath(), file2.toPath());

                    }

                    if(!ReplaceFrom && !ReplaceTo) {
                        if(Insensitive) {
                            text = text.replaceAll("(?i)" + replaceFrom, replaceTo);
                        }
                        else {
                            text = text.replaceAll(replaceFrom, replaceTo);
                        }
                    }

                    if(ReplaceFrom) {
                        if(Insensitive) {
                            text = text.replaceFirst( "(?i)" + replaceFrom, replaceTo);
                        }
                        else {
                            text = text.replaceFirst(replaceFrom, replaceTo);
                        }
                    }

                    if(ReplaceTo) {
                        if(Insensitive) {
                            String revText = new StringBuilder(text).reverse().toString();
                            String revFrom = new StringBuilder(replaceFrom).reverse().toString();
                            String revTo = new StringBuilder(replaceTo).reverse().toString();
                            revText = revText.replaceFirst("(?i)" + revFrom, revTo);
                            text = new StringBuilder(revText).reverse().toString();
                        }

                        else {

                            index = text.lastIndexOf(replaceFrom);
                            if(index >= 0) {
                                text = text.substring(0, index) + replaceTo + text.substring(index + replaceFrom.length());;
                            }
                        }
                    }

                    FileWriter filewriter1 = new FileWriter(file);
                    BufferedWriter bufferedwriter1 = new BufferedWriter(filewriter1);
                    bufferedwriter1.write(text);
                    bufferedwriter1.close();

                }
                catch(FileNotFoundException e) {
                    System.err.println("File " + file.getName() + " not found");
                }
                catch(FileAlreadyExistsException e) {
                    System.err.println("File " + file.getName() + " already exists");
                }
            }
        }
    }

    private static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }

}