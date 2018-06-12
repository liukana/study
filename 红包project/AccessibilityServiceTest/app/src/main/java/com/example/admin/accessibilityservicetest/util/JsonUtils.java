package com.example.admin.accessibilityservicetest.util;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * json管理
 *
 * @author Tom
 */
public class JsonUtils {
    private static GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(String.class, new TypeAdapter<String>() {

        @Override
        public void write(JsonWriter out, String value) throws IOException {
            if (value == null) {
                // out.nullValue();
                out.value(""); // 序列化时将 null 转为 ""
            } else {
                out.value(value);
            }
        }

        @Override
        public String read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return "";
            }
            // return in.nextString();
            String str = in.nextString();
            if (str.equals("null")) { // 反序列化时将 "" 转为 null
                return "";
            } else {
                return str;
            }
        }

    });
    private static Gson gson = gsonBuilder.create();

    public static <T> T object(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            return null;
        }

    }


    public static <T> T object(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (Exception e) {
            return null;
        }

    }


    public static String toJson(Object param) {
        try {
            return gson.toJson(param);
        } catch (Exception e) {
            return "";
        }

    }

}