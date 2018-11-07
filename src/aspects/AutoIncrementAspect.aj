package aspects;

import annotations.AutoIncrement;
import annotations.Table;
import main.EntityUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

public aspect AutoIncrementAspect {

    private static HashMap<Class, Integer> ids = new HashMap<Class, Integer>();

    pointcut increment() : initialization(public entities.*.new(..));

    after(): increment() {
        Object object = thisJoinPoint.getThis();
        Class clazz = object.getClass();

        if (clazz.getAnnotation(Table.class) == null) {
            return;
        }

        Field autoInceremtField = EntityUtils.getFieldWithAnnotation(clazz, AutoIncrement.class);

        if (autoInceremtField == null) {
            return;
        }

        if (!ids.containsKey(clazz)) {
            ids.put(clazz, 0);
        }

        autoInceremtField.setAccessible(true);
        try {
            autoInceremtField.set(object, ids.get(clazz));
        } catch (IllegalAccessException e) {
            System.out.println("Can't set id field");
        }

        System.out.println("Auto set new Student id to " + ids.get(clazz));

        ids.replace(clazz, ids.get(clazz) + 1);
    }
}
