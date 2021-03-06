package com.fulln.apiyoudao.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fulln.apiyoudao.Entity.youdaoTransVO;
import com.fulln.apiyoudao.service.ITranslateService;
import com.fulln.config.util.httpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fulln.config.util.MD5Util.md5;

/**
 * @AUthor: fulln
 * @Description: 翻译api
 * @Date : Created in  16:11  2018/5/13.
 */
@Service("translateService")
public class TranslateServiceImpl implements ITranslateService {

    private static Logger log = LoggerFactory.getLogger(TranslateServiceImpl.class);

    private static final String appkey = "36fbdeb54e581879"; //产品id

    private static final String key = "fPPpzhtuE05qRRFm1QaQxEd2FzBzHnsz"; //产品密钥

    private static final String uri = "http://openapi.youdao.com/api"; //api地址

    /**
     * 调取接口进行翻译
     */
    public  String getTransback(String waitToTrans) {

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

        String Res = httpUtil.ClientPostRequest(uri, params);
        return TransTo(Res);
    }



    private  static String TransTo(String resultMap){
        youdaoTransVO youdao =  JSON.parseObject(resultMap,new TypeReference<youdaoTransVO>(){});
        List<String> list =  youdao.getTranslation();
        return list.get(0);
    }

}
