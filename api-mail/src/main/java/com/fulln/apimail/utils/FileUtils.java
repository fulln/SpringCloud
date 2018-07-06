package com.fulln.apimail.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

/**
 * @program: SpringCloud
 * @description: 文件读写类
 * @author: fulln
 * @create: 2018-07-06 14:21
 * @Version： 0.0.1
 **/
@Slf4j
public class FileUtils {

    private static Properties props;

    public FileUtils(String name){
        loadProps(name);
    }


    public static String readFromJSON(String path) throws IOException {
        BufferedReader buf = null;
        StringBuilder content= new StringBuilder();
        try(InputStreamReader reader = new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream(path),"utf-8")){
            buf =new BufferedReader(reader);
            String tempString="";
            while ((tempString = buf.readLine())!=null){
                content.append(tempString);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("[文件读写]读取中发生异常");
        }finally {
            if (buf != null) {
                buf.close();
            }
        }
        return content.length() != 0 ? content.toString() : null;
    }

    //读取配置文件
    synchronized static private void loadProps(String name) {
        props = new Properties();
        try (InputStream in =FileUtils.class.getClassLoader().getResourceAsStream(name)){
            props.load(in);
        } catch (FileNotFoundException e) {
            log.error("配置文件未找到");
        } catch (IOException e) {
            log.error("出现IOException");
        }
    }

    //获取单个key
    public String getProperty(String key) {

        if (null == props) {
            loadProps("application.properties");
        }
        if (key != null && !"".equals(key)) {
            return  props.getProperty(key);
        } else {
            return null;
        }
    }
}
