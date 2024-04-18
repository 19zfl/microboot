package com.yootk.action;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/pages/session/")
public class SessionAction {
    @RequestMapping("set")
    public Object set(String key, String value) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes(); // 获取请求属性
        HttpServletRequest request = attributes.getRequest(); // 获取Request
        request.getSession().setAttribute(key, value); // 设置Session属性
        return Map.of("result", true, "session-id", request.getSession().getId());
    }
    @RequestMapping("get")
    public Object get(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        result.put("session-id", session.getId());
        Enumeration<String> enu = session.getAttributeNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            result.put(name, session.getAttribute(name));
        }
        return result;
    }
    @RequestMapping("remove")
    public Object remove(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        result.put("session-id", session.getId());
        session.invalidate();
        return result;
    }
}
