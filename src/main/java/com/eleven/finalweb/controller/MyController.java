package com.eleven.finalweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class MyController {

    @GetMapping("/main")
    public String main() {
        return "main"; // main.html 파일을 보여줍니다.
    }

    @GetMapping("/card")
    public String card() {
        return "card"; // main.html 파일을 보여줍니다.
    }

    @GetMapping("/gameinfor")
    public String gameinfor() {
        return "gameinfor"; // main.html 파일을 보여줍니다.
    }
}
