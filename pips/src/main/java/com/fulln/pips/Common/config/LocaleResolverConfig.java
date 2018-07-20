package com.fulln.pips.Common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


/**
 * @program: SpringCloud
 * @description: 初始化当前的会话区域
 * @author: fulln
 * @create: 2018-07-20 15:33
 * @Version： 0.0.1
 **/
@Configuration
public class LocaleResolverConfig {


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();

        //设置默认区域,
        slr.setDefaultLocale(Locale.CHINA);
        return slr;

    }

}
