package com.fulln.apiyoudao.service.Impl;

import com.fulln.apiyoudao.service.ITranslateService;
import com.fulln.config.entity.ThreadEntity;
import com.fulln.config.util.fileUtil;
import com.fulln.config.util.httpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.fulln.config.util.MD5Util.md5;

/**
 * @AUthor: fulln
 * @Description: 翻译api
 * @Date : Created in  16:11  2018/5/13.
 */
@Service
public class TranslateServiceImpl implements ITranslateService {

    private static Logger log = LoggerFactory.getLogger(TranslateServiceImpl.class);

    private static final String appkey = "36fbdeb54e581879"; //产品id

    private static final String key = "fPPpzhtuE05qRRFm1QaQxEd2FzBzHnsz"; //产品密钥

    private static final String uri = "http://openapi.youdao.com/api"; //api地址

    /**
     * 调取接口进行翻译
     */
    public synchronized String getTransback(String waitToTrans) {

        String salt = String.valueOf(System.currentTimeMillis());
        String from = "zh-CHS";
        String to = "EN";
        String sign = md5(appkey + waitToTrans + salt + key);

        Map<String, Object> params = new HashMap<>();
        params.put("q", waitToTrans);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appkey);

        return httpUtil.ClientPostRequest(uri, params);
    }

    /**
     * 获取翻译前的文本map
     */
    private Map ReadFromFile() {
        Properties pznp = fileUtil.getProps("Language/Language-zh.properties");
        Properties penp = fileUtil.getProps("Language/Language-en.properties");

        Map<Object, Object> map = new HashMap<>(); //要进行翻译的map
        if (0 != pznp.size()) {
            if (penp.size() != 0) {
                //遍历中文 文件中的键值对
                for (Map.Entry<Object, Object> entryZH : pznp.entrySet()) {
                    System.out.println("Key = " + entryZH.getKey() + ", Value = " + entryZH.getValue());
                    //  英文文件中没有的key 拿出来进行翻译
                    if (!penp.keySet().contains(entryZH.getKey())) {
                        map.put(entryZH.getKey(), entryZH.getValue());
                    }
                }
            } else {
                map.putAll((Map<?, ?>) pznp.entrySet());
            }
            return map;
        }

        return null;
    }

    /**
     * @Author: fulln
     * @Description: 调用threadutil进行翻译
     * @params:
     * @return:
     * @Date: 2018/5/17 0017 17:16
     */
    public void writeToFile() {
        Map map = ReadFromFile();
        if (map == null) {
            log.error("对应的文件中没有值");
        } else {
//            List<ThreadEntity> threadList = new ArrayList<>();
//            for (Object key : map.keySet()) {
//                Map map2 = new HashMap();
//                map2.put("waitToTrans", map.get(key));
//                ThreadEntity threadEntity = new ThreadEntity("getTransback", this.getClass(), map2);
//                threadList.add(threadEntity);
//            }
            /**
             * 用线程池不要装逼用反射
             */




        }


    }


}
