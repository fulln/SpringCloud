package com.fulln.pips.Controller.baseController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @program: SpringCloud
 * @description: 会话区域解析
 * @author: fulln
 * @create: 2018-07-20 15:28
 * @Version： 0.0.1
 **/
@RestController
@RequestMapping("/lang")
public class SessionLocaleResolverController {

    @GetMapping("/{lan}")
    private String changelang(HttpServletRequest request, HttpServletResponse response, @PathVariable String lan) {

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        if ("zh".equals(lan)) {
            if (localeResolver != null) {
                localeResolver.setLocale(request, response, new Locale("zh", "CN"));
            }
        } else if ("en".equals(lan)) {
            if (localeResolver != null) {
                localeResolver.setLocale(request, response, new Locale("en", "US"));
            }
        }

        return "redirect:/home";
    }


}
