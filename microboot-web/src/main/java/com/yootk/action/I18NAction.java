package com.yootk.action;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName:I18NAction
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/4/6 19:46
 */

@RestController
@RequestMapping("/i18n/*")
public class I18NAction {

    @Resource
    private MessageSource messageSource;

    @RequestMapping("base")
    public Object shouBase() {
        Map<String, String> map = new HashMap<>();
        map.put("message", this.messageSource.getMessage("yootk.message", null, Locale.getDefault()));
        map.put("url", this.messageSource.getMessage("yootk.url", null, Locale.getDefault()));
        return map;
    }

    @RequestMapping("locale")
    public Object shouLocale(Locale loc) {
        Map<String, String> map = new HashMap<>();
        map.put("message", this.messageSource.getMessage("yootk.message", null, loc));
        map.put("url", this.messageSource.getMessage("yootk.url", null, loc));
        return map;
    }

}
