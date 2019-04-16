package com.work.vladimirs.shawermacloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Deprecated
//Вместо этого контроллера теперь есть конфиг, что гораздо удобнее
//@Controller
public class HomeController {

    //@GetMapping("/")
    public String home() {
        return "home";
    }
}
