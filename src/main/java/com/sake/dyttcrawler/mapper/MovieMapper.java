package com.sake.dyttcrawler.mapper;

import com.sake.dyttcrawler.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MovieMapper {

    void insert(Movie movie);
}
