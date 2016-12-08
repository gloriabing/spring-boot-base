package org.gloria.config;

/**
 * Create on 2016/12/7 22:51.
 *
 * @author : gloria.
 */
public class DSContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static String getContext(){
        return contextHolder.get();
    }

    public static void setContext(String context){
        contextHolder.set(context);
    }
}
