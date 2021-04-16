package edu.qc.seclass;

import java.util.HashMap;

public class MyCustomString implements MyCustomStringInterface {

    public String str;

    @Override
    public String getString() {
        return str;
    }

    @Override
    public void setString(String string) {
        str = string;
    }

    @Override
    public int countNumbers() {
        if (str == null) throw new NullPointerException("The string is empty.");

        int count = 0;
        boolean CheckDigit = false;

        // Check each position, if the position is a digit (0-9).
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {

                // Set CheckDigit to true, so we know it's a digit. And count + 1.
                // If the next position is also a digit, and our CheckDigit is still true.
                // We know the two digits are a number, so we skip the !CheckDigit test and don't do count + 1.
                if (!CheckDigit) {
                    CheckDigit = true;
                    count++;
                }
                // Otherwise, set it to false, so we know it's not a digit.
            } else {
                CheckDigit = false;
            }
        }
        return count;
    }

    @Override
    public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
        if (str == null) throw new NullPointerException("The string is empty.");
        if (n <= 0) throw new IllegalArgumentException("The value of n can't be negative.");

        StringBuilder current = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        int slength = str.length();

        if (slength < n) {
            return current.toString();
        }

        // StartFromEnd is true
        // We start from the end
        if (startFromEnd) {
            for (int i = slength - n; i >= 0; i -= n) {
                current.append(str.charAt(i));
            }

            // Reverse the string and store it in answer
            for (int j = current.length() - 1; j >= 0; j--) {
                answer.append(current.charAt(j));
            }
            return answer.toString();
        }

        // StartFromEnd is false
        else {
            for (int i = n - 1; i < str.length(); i += n) {
                current.append(str.charAt(i));
            }
            return current.toString();
        }
    }

    @Override
    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
        if (str == null)  throw new NullPointerException("The String is empty.");
        if (startPosition > endPosition) throw new IllegalArgumentException("Error!");
        if (startPosition < 1 || endPosition < 1 || endPosition > str.length())throw new MyIndexOutOfBoundsException("Index Out of Bound!.");

        HashMap<Integer,String> map = new HashMap<>();
        map.put(48,"Zero");
        map.put(49,"One");
        map.put(50,"Two");
        map.put(51,"Three");
        map.put(52,"Four");
        map.put(53,"Five");
        map.put(54,"Six");
        map.put(55,"Seven");
        map.put(56,"Eight");
        map.put(57,"Nine");

        int ActualIndex = startPosition - 1;
        StringBuilder ans = new StringBuilder();

        // outer loop go through the entire string
        for(int i = 0; i < str.length(); i++) {
            // if reach the value set by the user
            if(i == ActualIndex) {
                // startPosition to endPosition
                for(int j = ActualIndex; j < endPosition;j++) {
                    if (Character.isDigit(str.charAt(j))){
                        char c = str.charAt(j);
                        int converse = c;
                        ans.append(map.get(converse));
                    }
                    else ans.append(str.charAt(j));
                    i++;
                    }
                }
                ans.append(str.charAt(i));
        }
        str = ans.toString();
    }
}

