package com.google.android.exoplayer2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Doraemon
 * Date: 16/5/20
 * Time: 11:30
 * Summary:
 */
public class QGSharedpreferences {

    public static final String defaultName = "qg";

    private static SharedPreferences sSharedPreferences;

    public static void init(Context context) {
        sSharedPreferences = new Builder().context(context).name(defaultName).build();
    }

    public static final class Builder {
        private Context context;
        private String name;


        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public SharedPreferences build() {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }


    /**
     * 将键值对设置到内存中
     *
     * @param key
     * @param value 支持String，Integer，Long，Float，Boolean
     * @return true:成功，false:失败
     * @author shk
     */
    public static boolean save(String key, Object value) {
        boolean bool = false;
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        try {
            if (key != null && value != null) {
                if (value instanceof String) {
                    editor.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                } else if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);
                } else if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                } else {
                    return bool;
                }
            }
            editor.apply();
            bool = true;
        } catch (Exception e) {
            Log.d("将键[" + key + "]值[" + value + "]保存到内存卡中出错！", "");
        }
        return bool;
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static <T> T get(String key, T defaultValue) {
        Object obj = null;
        if (defaultValue instanceof String) {
            obj = sSharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            obj = sSharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            obj = sSharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            obj = sSharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            obj = sSharedPreferences.getLong(key, (Long) defaultValue);
        }
        if (obj != null) {
            return (T) obj;
        }
        return (T) obj;
    }


    public static void remove(String key) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }


}
