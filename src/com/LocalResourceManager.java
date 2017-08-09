package com;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocalResourceManager {
    private static Logger log = Logger.getLogger(LocalResourceManager.class);
    private static String fileName="localExtend.properties";

    private static Properties props;
    private static volatile boolean initialized = false;
    public static void init(String name) {
        if (initialized) {
            return;
        }
        synchronized (LocalResourceManager.class) {
            InputStream is = null;
            try {
                props = new Properties();

                is = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(name);
                props.load(is);

                initialized = true;
            } catch (Exception e) {
                log.fatal("Error loading properties", e);
                e.printStackTrace();
            } finally {
                if (null != is) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        log.error("close properties read strem fail !", e);
                    }
                }
            }
        }
    }

    public static Properties getMyProps() {
        init(fileName);
        return props;
    }

    public static boolean hasProperty(String key) {
        init(fileName);
        return props.containsKey(key);
    }

    public static String getProperty(String key) {
        init(fileName);
        return props.getProperty(key);
    }

    public static int getIntProperty(String key) {
        if (hasProperty(key)) {
            String value = getProperty(key);
            return Integer.parseInt(value);
        }

        return 0;
    }

    public static String getProperty(String key, String defaultValue) {
        if (hasProperty(key)) {
            return getProperty(key);
        }

        return defaultValue;
    }

    public static int getIntProperty(String key, int defaultValue) {
        if (hasProperty(key)) {
            String value = getProperty(key);
            return Integer.parseInt(value);
        }

        return defaultValue;
    }

    public static long getLongProperty(String key, long defaultValue) {
        if (hasProperty(key)) {
            String value = getProperty(key);
            return Long.parseLong(value);
        }

        return defaultValue;
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        if (hasProperty(key)) {
            String value = getProperty(key);
            return Boolean.parseBoolean(value);
        }

        return defaultValue;
    }

    public static float getFloatProperty(String key, float defaultValue) {
        if (hasProperty(key)) {
            String value = getProperty(key);
            return Float.parseFloat(value);
        }

        return defaultValue;
    }

}
