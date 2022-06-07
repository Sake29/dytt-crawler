package com.sake.dyttcrawler.controller;

import com.sake.dyttcrawler.entity.Movie;
import com.sake.dyttcrawler.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/run")
    public String run(){
        movieService.run();
        return "正在获取...";
    }
}
