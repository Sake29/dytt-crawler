package com.sake.dyttcrawler;

import com.sake.dyttcrawler.entity.Movie;
import com.sake.dyttcrawler.mapper.MovieMapper;
import com.sake.dyttcrawler.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DyttCrawlerApplicationTests {

    @Resource
    private TestMapper testMapper;
    @Resource
    private MovieMapper movieMapper;

    @Test
    void contextLoads() {
        List<com.sake.dyttcrawler.entity.Test> all = testMapper.getAll();
        for (com.sake.dyttcrawler.entity.Test test : all) {
            System.out.println(test);
        }
    }

    @Test
    void movieMapperTest() {
        Movie movie = new Movie();
        movie.setCountry("中国额");
        movie.setAka("啊啊哦");
        movie.setDbScore("6.0/10");
        movie.setMovieName("aaa");
        movie.setMovieUrl("12312312312");
        movieMapper.insert(movie);
    }

}
