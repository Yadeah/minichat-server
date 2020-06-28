package com.yadeah.minichat.common.utils;

import com.yadeah.minichat.common.constant.system.ConfigKey;
import com.yadeah.minichat.common.utils.log.LogUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

/***
 * 读取配置文件工具类
 */
public class ConfigUtils {

    private static Properties prop = null;

    static {
        try {
            Resource resource;
            resource = new FileSystemResource("config/application.properties");
            if (!resource.exists()) {
                resource = new ClassPathResource("application.properties");
            }
            prop = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException ex) {
            LogUtils.error(ex);
        }
    }

    public static String getProperty(ConfigKey key) {
        return prop.getProperty(key.getValue());
    }

    public static int getIntProperty(ConfigKey key) {
        int property = 0;
        try {
            String value = getProperty(key);
            if (StringUtils.isEmpty(key)) {
                LogUtils.error("配置项{}未找到", key);
            }
            property = Integer.parseInt(value);
        } catch (Exception e) {
            LogUtils.error(e);
        }

        return property;
    }
}
