package com.sake.dyttcrawler.entity;

import lombok.Data;

@Data
public class Movie {
    private int id;
    private String movieName;
    private String aka;
    private String movieUrl;
    private String magnet;
    private String imgUrl;
    private String dbScore;
    private String imdbScore;
    private String length;//片长
    private String country;
    private String year;
    private String type;
    private String director;
}
