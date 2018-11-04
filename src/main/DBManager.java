package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {
    public static List<Object> getAll(Class targetCalass) {
        return Arrays.asList(targetCalass.getAnnotations());
    }
}
