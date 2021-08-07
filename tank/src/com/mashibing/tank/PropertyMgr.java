package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key){
        if(props==null){
            return null;
        }
        return props.getProperty(key);
    }
    public static Integer getInt(String key){
        if(props==null){
            return null;
        }
        return Integer.parseInt(props.getProperty(key));
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.getString("initTankCount"));
    }

}
