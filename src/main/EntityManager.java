package main;

import annotations.Column;
import annotations.NotNull;
import annotations.Table;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager {

    public void getAll(Class clazz) throws Exception {
        Table table = EntityUtils.getTableAnnotation(clazz);
        System.out.println("SELECT * FROM " + table.name());
    }

    public void get(Class clazz, Object id) throws Exception, java.lang.IllegalAccessException {
        Table table = EntityUtils.getTableAnnotation(clazz);

        Field idField = EntityUtils.getIdField(clazz);

        Class idFieldClass = idField.getType();
        if (idFieldClass.isPrimitive()) {
            idFieldClass = EntityUtils.primitiveClassMap.get(idFieldClass);
        }

        if (idFieldClass != id.getClass()) {
            throw new Exception("Wrong id class");
        }

        Column column = idField.getAnnotation(Column.class);
        if (column == null) {
            throw new Exception("Id is not a column");
        }

        System.out.println("SELECT * FROM " + table.name() + " WHERE " + column.name() + "=" + id.toString());
    }

    public void insert(Class clazz, Object value) throws Exception {
        Table table = EntityUtils.getTableAnnotation(clazz);

        if (value.getClass() != clazz) {
            throw new Exception("Cannot insert " + value.getClass() + " as " + clazz);
        }
        List<Field> columns = EntityUtils.getColumnFields(clazz);

        for (Field c : columns) {
            c.setAccessible(true);
            if (c.getAnnotation(NotNull.class) != null && c.get(value) == null) {
                throw new Exception(c.getName() + " cannot be null");
            }
        }

        String columnNames = columns
                .stream()
                .map(c -> c.getAnnotation(Column.class).name())
                .collect(Collectors.joining(", "));

        String valueNames = "";

        valueNames = columns
                .stream()
                .map(c -> {
                    try {
                        Object o = c.get(value);
                        if (o.getClass() == String.class) {
                            return "\"" + o + "\"";
                        }
                        return o.toString();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return "";
                })
                .collect(Collectors.joining(", "));

        System.out.printf("INSERT INTO %s (%s) VALUES (%s)\n", table.name(), columnNames, valueNames);
    }

}
