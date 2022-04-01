package Mar29.SherlockAndTheValidString;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    public static String isValid(String s) {
        // Write your code here
        int[] vals = new int[26];

        for (int i = 0; i < s.length(); i++) {
            vals[s.charAt(i) - 'a']++;
        }

        if (check(vals)) return "YES";

        for (int i = 0; i < 26; i++) {
            if (vals[i] != 0) {
                vals[i]--;
                if (check(vals)) return "YES";
                vals[i]++;
            }
        }
        return "NO";
    }

    private static boolean check(int[] vals) {
        boolean works = true;
        for (int j = 1; j < 26; j++) {
            if (vals[j] != 0) {
                if (vals[j] != vals[0]) {
                    works = false;
                }
            }
        }
        return works;
    }
}
