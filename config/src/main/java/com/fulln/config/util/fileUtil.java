package com.fulln.config.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;


public class fileUtil {

    private static Properties props;

    private static Logger logger = LoggerFactory.getLogger(fileUtil.class);

    static {
        loadProps("application.properties");
    }
    //读取配置文件
    synchronized static private void loadProps(String name) {
        props = new Properties();
        try (InputStream in =fileUtil.class.getClassLoader().getResourceAsStream(name)){
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("配置文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        }
    }

    //写入配置文件
    synchronized static public void writeProps(Properties ps,String path) {
        path=getLocalPath(path);
        try (OutputStream out =   new FileOutputStream(path)){
            ps.store(out,null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件输出流开启异常");
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
    //封装资源路径
    private static String getLocalPath(String path){
        path=fileUtil.class.getClassLoader().getResource(path).getPath();
        return path.replace("target/classes","src/main/resources");
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
