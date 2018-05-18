package com.fulln.config.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


public class fileUtil {

    private static Properties props;

    private static Logger logger = LoggerFactory.getLogger(fileUtil.class);

    static {
        loadProps("application.properties");
    }

    synchronized static private void loadProps(String name) {
        props = new Properties();
        InputStream in = null;
        try {
            in =fileUtil.class.getClassLoader().getResourceAsStream(name);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("配置文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("配置文件流关闭出现异常");
            }
        }
    }

    //获取单个key
    public static String getProperty(String key) {
        if (null == props) {
            loadProps("application.properties");
        }
        if (key != null && !"".equals(key)) {
            return  props.getProperty(key);
        } else {
            return null;
        }
    }


    /**
     * 写入文件
     */
    public static void writeToFile(String FileName,StringBuffer sb){
        PrintStream ps =null;
        try {
        File f= new File(FileName);
             ps = new PrintStream(new FileOutputStream(f),true);
            ps.println(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(ps != null){
                ps.close();
            }
        }
    }

    //获取全部的键值对
    public static Properties getProps(String name) {
        loadProps(name);
        return props;
    }

}
