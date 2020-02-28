package com.qingcheng.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class loginController {

    @RequestMapping("/getName")
    public Map<String, Object> showName() {
        Map<String, Object> map = new HashMap<>();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("name", name);
        return map;
    }

}
