package cn.testFrame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CommonController {


    @GetMapping("/testConnect")
    public String testConnect(){
        return "Success";
    }



}
