package main;

import annotations.Column;
import annotations.Id;
import annotations.PostInsert;
import annotations.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class EntityUtils {

    public final static Map<Class, Class> primitiveClassMap = new HashMap<Class, Class>();
    static {
        primitiveClassMap.put(boolean.class, Boolean.class);
        primitiveClassMap.put(byte.class, Byte.class);
        primitiveClassMap.put(short.class, Short.class);
        primitiveClassMap.put(char.class, Character.class);
        primitiveClassMap.put(int.class, Integer.class);
        primitiveClassMap.put(long.class, Long.class);
        primitiveClassMap.put(float.class, Float.class);
        primitiveClassMap.put(double.class, Double.class);
    }

    public static Table getTableAnnotation(Class clazz) throws Exception {
        Table table = (Table)clazz.getAnnotation(Table.class);

        if (table == null) {
            throw new Exception("Target class " + clazz + " is not a table");
        }
        return table;
    }

    public static Field getFieldWithAnnotation(Class clazz, Class annotation) {
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getAnnotation(annotation) != null) {
                return f;
            }
        }
        return null;
    }

    public static List<Field> getFieldsWithAnnotation(Class clazz, Class annotation) {
        List<Field> fields = new ArrayList<Field>();
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getAnnotation(annotation) != null) {
                fields.add(f);
            }
        }
        return fields;
    }

    public static List<Field> getColumnFields(Class clazz) {
//        List<Field> fields = getFieldsWithAnnotation(clazz, Column.class);
//        List<Column> columns = new ArrayList<Column>();
//        for (Field f : fields) {
//            columns.add((Column)f);
//        }
        return getFieldsWithAnnotation(clazz, Column.class);
    }

    public static Field getIdField(Class clazz) throws Exception {
        Field idField = getFieldWithAnnotation(clazz, Id.class);

        if (idField == null) {
            throw new Exception(clazz + " has no id field");
        }
        return idField;
    }

    public static List<Method> getMethodsWithAnnotation(Class clazz, Class annotation) {
        List<Method> methods = new ArrayList<Method>();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getAnnotation(annotation) != null) {
                methods.add(m);
            }
        }
        return methods;
    }
}
