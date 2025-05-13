package com.arkdev.z9tkvtu.util;

public class Convert {
    public static String getString(Object value) {
        return value != null ? value.toString() : null;
    }

    public static <E extends Enum<E>> E getEnum(Class<E> enumType, Object value) {
        return value != null ? Enum.valueOf(enumType, value.toString()) : null;
    }

    public static Integer getInt(Object value) {
        return value != null ? ((Number) value).intValue() : null;
    }
}
