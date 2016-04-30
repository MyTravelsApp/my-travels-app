package com.github.mytravelsapp.persistence.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Utility class for working with Json.
 *
 * @author fjtorres
 */
public final class JsonHelper {

    private JsonHelper() {
        // Blank
    }

    /**
     * This method transforms the parameter to a JSon string and return it.
     *
     * @param obj Object to transform.
     * @param <T> Type of the parameter.
     * @return JSon string.
     */
    public static <T> String toJson(T obj) {
        final Gson gson = generateGson();
        return gson.toJson(obj);
    }

    /**
     * This method transforms a JSon string to an object.
     *
     * @param jsonStr JSon string.
     * @param type    Type for json transformation.
     * @param <T>     Type of the result.
     * @return Object from json string.
     * @see com.google.gson.reflect.TypeToken
     */
    public static <T> T fromJson(final String jsonStr, Type type) {
        final Gson gson = generateGson();
        return gson.fromJson(jsonStr, type);
    }

    private static Gson generateGson() {
        return new GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").create();
    }
}
