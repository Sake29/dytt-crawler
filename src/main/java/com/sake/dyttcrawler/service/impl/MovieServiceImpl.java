package com.sake.dyttcrawler.service.impl;

import com.sake.dyttcrawler.entity.Movie;
import com.sake.dyttcrawler.mapper.MovieMapper;
import com.sake.dyttcrawler.service.MovieService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private static final String urlPrefix = "https://m.dytt8.net/html/gndy/dyzz/list_23_";
    private static final String urlSuffix = ".html";
    private static final String moviePrefix = "https://m.dytt8.net";
    private static final int maxPage = 246;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public void run() {
        try {
            for (int i = 1; i <= maxPage; i++) {
                String url = urlPrefix + i + urlSuffix;
                Document documents = Jsoup.parse(new URL(url),Integer.MAX_VALUE);
                Element body = documents.body();
                Elements movieElements = body.select("div .co_content8").select("ul").select("tbody");
                for (Element movieElement : movieElements) {
                    Movie movie = new Movie();
                    Elements a = movieElement.select("a");
                    String movieName = a.text();
                    movie.setMovieName(movieName);
                    String movieSuffix = a.attr("href");
                    String movieUrl = moviePrefix + movieSuffix;
                    movie.setMovieUrl(movieUrl);
                    // 解析电影下载页的路径
                    Document movieDocuments = Jsoup.parse(new URL(movieUrl),Integer.MAX_VALUE);
                    Element movieBody = movieDocuments.body();
                    Elements movieZooms = movieBody.select("#Zoom");
                    Elements imgElement = movieZooms.select("img");
                    if (imgElement.size() != 0){
                        String img = imgElement.get(0).attr("src");
                        movie.setImgUrl(img);
                    }
                    Elements spans = movieZooms.select("span");
                    String magnet = spans.select("a").attr("href");
                    movie.setMagnet(magnet);
                    for (Element span : spans) {
                        String movieInfos = span.text();
                        String[] movieInfo = movieInfos.split("◎");
                        for (String context : movieInfo) {
                            if (context.contains("译　　名")){
                                movie.setMovieName(context.substring(5));
                            }
                            else if (context.contains("片　　名")){
                                movie.setAka(context.substring(5));
                            } else if (context.contains("IMDb评分")){
                                try {
                                    String[] split = context.split(" ");
                                    String score = split[0].substring(6).trim();
                                    movie.setImdbScore(score);
                                } catch (Exception e){
                                    System.out.println("第" + i + "页出现异常");
                                }
                            } else if (context.contains("豆瓣评分")){
                                try {
                                    String[] split = context.split(" ");
                                    String score = split[0].substring(5);
                                    movie.setDbScore(score);
                                } catch (Exception e){
                                    System.out.println("第" + i + "页出现异常");
                                }
                            } else if (context.contains("片　　长")){
                                movie.setLength(context.substring(5));
                            } else if (context.contains("产　　地")){
                                movie.setCountry(context.substring(5));
                            } else if (context.contains("年　　代")){
                                movie.setYear(context.substring(5));
                            } else if (context.contains("类　　别")){
                                movie.setType(context.substring(5));
                            } else if (context.contains("导　　演")){
                                movie.setDirector(context.substring(5));
                            }
                        }
                    }
                    // 存在则更新，否则直接插入
                    movieMapper.insert(movie);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
