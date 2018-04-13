package com.fulln.config.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;


public class fileUtil {

    private static Properties props;

    private static Logger logger = LoggerFactory.getLogger(fileUtil.class);

    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        props = new Properties();
        InputStream in = null;
        try {
            in =fileUtil.class.getClassLoader().getResourceAsStream("application.properties");
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

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
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

}
