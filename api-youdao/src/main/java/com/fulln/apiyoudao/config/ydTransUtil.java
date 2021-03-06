package com.fulln.apiyoudao.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fulln.apiyoudao.Entity.youdaoTransVO;
import com.fulln.apiyoudao.service.Impl.TranslateServiceImpl;
import com.fulln.config.util.fileUtil;
import com.fulln.config.util.httpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fulln.config.util.MD5Util.md5;

/**
 * @AUthor: fulln
 * @Description: 不用springbean的方式进行翻译  翻译api接口
 * @Date : Created in  14:54  2018/5/20.
 */
@Slf4j
public  class ydTransUtil {

    /**
     * 调取接口进行翻译
     */
    public  static String getTrans(String waitToTrans) {

        String salt = String.valueOf(System.currentTimeMillis());
        String from = "zh-CHS";
        String to = "EN";
        String sign = md5(fileUtil.getProperty("appkey") + waitToTrans + salt + fileUtil.getProperty("key"));

        Map<String, Object> params = new HashMap<>();
        params.put("q", waitToTrans);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", fileUtil.getProperty("appkey"));

        String Res = httpUtil.ClientPostRequest(fileUtil.getProperty("uri"), params);
        return TransTo(Res);

    }



    private  static String TransTo(String resultMap){
        youdaoTransVO youdao =  JSON.parseObject(resultMap,new TypeReference<youdaoTransVO>(){});
        List<String> list =  youdao.getTranslation();
        return list.get(0);
    }


}
