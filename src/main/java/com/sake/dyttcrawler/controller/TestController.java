package com.sake.dyttcrawler.controller;

import com.sake.dyttcrawler.entity.Test;
import com.sake.dyttcrawler.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/getAll")
    public List<Test> getAll(){
        List<Test> all = testService.getAll();
        for (Test test : all) {
            System.out.println(test);
        }
        return all;
    }

}
