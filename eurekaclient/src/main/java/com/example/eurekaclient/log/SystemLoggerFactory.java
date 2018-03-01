package com.example.eurekaclient.log;

/**
 * 日志工厂
 */
public class SystemLoggerFactory {

    public static SystemLogger getLogger(Class<?> cla){
        return new SystemLoggerImpl(cla);
    }
}
