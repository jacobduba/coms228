package Mar29.SherlockAndTheValidString;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        System.out.println(Result.isValid(s));
    }
}