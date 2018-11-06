package main;

import annotations.Id;
import annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityUtils {

//    private static Annotation getAnnotation(Class targetClass, Class annotation) {
//        for (Annotation a : targetClass.getAnnotations()) {
//            System.out.println(a.annotationType());
//            if (a.annotationType().equals(annotation)) {
//                return a;
//            }
//        }
//        return null;
//    }

    private static Field getIdField(Class targetClass) {
        for (Field f : targetClass.getFields()) {
            if (f.getAnnotation(Id.class) != null) {
                return f;
            }
        }
        return null;
    }

    public static List<Object> getAll(Class targetClass) {
        Table table = (Table)targetClass.getAnnotation(Table.class);

        System.out.println("SELECT * FROM " + table.name());
        // TODO
        return new ArrayList<>();
    }

    public static Object get(Class targetClass, Object id) {
        Field idField = getIdField(targetClass);
        return new Object();
        // TODO
    }
}
