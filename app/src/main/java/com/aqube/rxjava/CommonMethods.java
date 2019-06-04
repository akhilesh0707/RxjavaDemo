package com.aqube.rxjava;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommonMethods {

    private static final Map<Class<?>, Class<?>> CLASS_MAP = new HashMap<Class<?>, Class<?>>() {{
        put(Integer.class, int.class);
        put(Float.class, float.class);
        put(Double.class, double.class);
        put(Long.class, long.class);
        put(Boolean.class, boolean.class);
    }};

    public static Field setValue(Object mClassName, String fieldName, Object valueTobeSet) throws NoSuchFieldException, IllegalAccessException {
        Field field = mClassName.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(mClassName, valueTobeSet);
        return field;
    }

    public static Field setFieldValue(Object object, String fieldName, Object valueTobeSet) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(object.getClass(), fieldName);
        field.setAccessible(true);
        field.set(object, valueTobeSet);
        return field;
    }

    public static Field getField(Class mClass, String fieldName) throws NoSuchFieldException {
        try {
            return mClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = mClass.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    public static Object getPrivateFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(object.getClass(), fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    private static Method callPrivateMethod(Object object, String methodName, int paramCount, Object... params) throws NoSuchMethodException {
        Method privateMethod;
        if (paramCount > 0) {
            Class<?>[] classArray = new Class<?>[paramCount];
            for (int i = 0; i < paramCount; i++) {
                if (CLASS_MAP.containsKey(params[i].getClass())) {
                    classArray[i] = CLASS_MAP.get(params[i].getClass());
                } else {
                    classArray[i] = params[i].getClass();
                }
            }
            privateMethod = object.getClass().getDeclaredMethod(methodName, classArray);
        } else {
            privateMethod = object.getClass().getDeclaredMethod(methodName);
        }
        privateMethod.setAccessible(true);
        return privateMethod;
    }

    public static void callPrivateMethodWithoutReturn(Object object, String methodName, int paramCount, Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateMethod = callPrivateMethod(object, methodName, paramCount, params);
        if (paramCount > 0) {
            privateMethod.invoke(object, params);
            return;
        }
        privateMethod.invoke(object);
    }

    public static Object callPrivateMethodForReturn(Object object, String methodName, int paramCount, Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateMethod = callPrivateMethod(object, methodName, paramCount, params);
        if (paramCount > 0) {
            return privateMethod.invoke(object, params);
        }
        return privateMethod.invoke(object);
    }

    /*public static class TempContext extends MockContext {
        @Override
        public Resources getResources() {
            return new TempResource();
        }
    }

    public static class TempResource extends MockResources {

        @Override
        public boolean getBoolean(int id) throws Resources.NotFoundException {
            //return id == super.getBoolean(id);
            return true;
        }

        @Override
        public InputStream openRawResource(int id) {
            String fileContents = "line one\n" +
                    "line two\n" +
                    "line three";
            return new ByteArrayInputStream(fileContents.getBytes());
        }
    }

    public static class Test extends ContentResolver {
        public Test(Context context) {
            super(context);
        }
    }*/
}