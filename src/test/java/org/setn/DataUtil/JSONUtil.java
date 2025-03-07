package org.setn.DataUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JSONUtil {

    public static final String jsonPath = "Data.json";

    public static String jsonConverter() {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(jsonPath);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
