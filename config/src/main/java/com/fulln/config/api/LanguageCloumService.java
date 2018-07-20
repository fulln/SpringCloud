package com.fulln.config.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @program: SpringCloud
 * @description: 代码国际化配置
 * @author: fulln
 * @create: 2018-07-20 14:51
 * @Version： 0.0.1
 **/
@Service
@Slf4j
public class LanguageCloumService {

    @Autowired
    private MessageSource messageSource;

    public String getCloums(String code) {

        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(code, null, locale);

    }

    public String getCloums(String code, String text) {

        String output;

        if ("zh".equals(text)) {
            output = messageSource.getMessage(code, null, Locale.CHINA);
        } else if ("en".equals(text)) {
            output = messageSource.getMessage(code, null, Locale.US);
        } else {
            output = null;
        }
        return output;

    }
}
