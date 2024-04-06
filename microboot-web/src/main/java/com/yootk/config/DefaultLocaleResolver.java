package com.yootk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @ClassName:DefaultLocaleResolver
 * @Description: Locale转换器
 * @Author:zfl19
 * @CreateDate:2024/4/6 19:54
 */

@Configuration
public class DefaultLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String loc = request.getParameter("loc");
        if (!StringUtils.hasLength(loc)) {
            Locale locale = Locale.getDefault();
            return locale;
        } else {
            String[] split = loc.split("_");
            return new Locale(split[0], split[1]);
        }
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }

    @Bean
    public LocaleResolver localeResolver() {
        return new DefaultLocaleResolver();
    }

}
