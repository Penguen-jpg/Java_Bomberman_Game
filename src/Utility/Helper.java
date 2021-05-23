package Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Helper {
    //將檔案讀成字串
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                builder.append(line + '\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    //將字串轉成整數
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
