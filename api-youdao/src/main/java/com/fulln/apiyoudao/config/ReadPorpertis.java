package com.fulln.apiyoudao.config;

import com.fulln.apiyoudao.Exception.YDtranslateException;
import com.fulln.config.util.fileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @AUthor: fulln
 * @Description: 翻译文件读写类
 * @Date : Created in  13:43  2018/5/20.
 */
@Component
public class ReadPorpertis {

    private static Logger log = LoggerFactory.getLogger(ReadPorpertis.class);

    private static String readFile;

    private static String writeFile;

    /**
     * @Author: fulln
     * @Description: 调用threadutil进行文件写入
     * @params:
     * @return:
     * @Date: 2018/5/17 0017 17:16
     */
    @SuppressWarnings("unchecked")
    public void writeToFile() {
        Properties penp = fileUtil.getProps(writeFile);
        rmThreadPoolUtil rm = new rmThreadPoolUtil(); //调用线程池
        try {
            Map map = ReadFromFile();
            /**
             * 用线程池不要装逼用反射
             */
            Map ResultMap = rm.getStart(map);
            ResultMap.forEach((k, v) -> {
                penp.setProperty(k.toString(), v.toString());
                System.out.println("翻译的内容为:"+k+" 结果是"+v);
            });
            fileUtil.writeProps(penp, writeFile);
        } catch (YDtranslateException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 获取翻译前的文本map
     */
    private Map ReadFromFile() throws YDtranslateException {
        Properties penp = fileUtil.getProps(writeFile);
        Properties pznp = fileUtil.getProps(readFile);
        Map<Object, Object> map = new HashMap<>(); //要进行翻译的map
        if (0 != pznp.size()) {
            if (penp.size() != 0) {
                //遍历中文 文件中的键值对
                pznp.forEach((k, v) -> {
                    if (!penp.keySet().contains(k)) {
                        map.put(k, v);
                    }
                });
            } else {
                pznp.forEach(map::put);
            }
            if (map.size() > 0) {
                return map;
            }else{
                throw new YDtranslateException("文件中没有未翻译过的字段");
            }
        }
        throw new YDtranslateException("对应的中文文件中没有值");
    }

    @Value("${readFile.path}")
    public void setReadFile(String readFile) {
        ReadPorpertis.readFile = readFile;
    }

    @Value("${writeFile.path}")
    public void setWriteFile(String writeFile) {
        ReadPorpertis.writeFile = writeFile;
    }

}
