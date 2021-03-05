package com.xinhuan.wms.baselibrary.utils;

import java.lang.reflect.ParameterizedType;

public class ObjUtils {

    public static <T> T getObj(Object o, int i) {
        ParameterizedType type = null;
        Class<T> clz = (Class<T>) o.getClass();
        while (true) {
            if (clz != null && clz.getGenericSuperclass() instanceof ParameterizedType) {
                type = (ParameterizedType) clz.getGenericSuperclass();
                break;
            }
            clz = (Class<T>) clz.getSuperclass();
        }
        try {
            return ((Class<T>) type.getActualTypeArguments()[i]).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
