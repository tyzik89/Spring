package com.work.vladimirs.rocketscloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер стартовой страницы.
 * UPD: Вместо него теперь использутеся WebConfig,
 * т.к. этот контроллер довольно простой, чтобы заводить отдельный класс.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
