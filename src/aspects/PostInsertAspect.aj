package aspects;

import annotations.PostInsert;
import main.EntityUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public aspect PostInsertAspect {
    pointcut insertion(Class clazz, Object value) : call(public void main.EntityManager.insert(Class, Object)) && args(clazz, value);

    after(Class clazz, Object value): insertion(clazz, value) {
        List<Method> methods = EntityUtils.getMethodsWithAnnotation(clazz, PostInsert.class);

        for (Method m : methods) {
            m.setAccessible(true);
            try {
                m.invoke(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
