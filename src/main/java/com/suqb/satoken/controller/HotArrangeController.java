package com.suqb.satoken.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suqb 2023/4/20
 */
@RestController
@RequestMapping("/hot")
public class HotArrangeController {

    @GetMapping("/get")
    public String test() {
        return "hotArrange success......";
    }
}
