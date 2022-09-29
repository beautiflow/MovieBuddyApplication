package moviebuddy.domain;

import moviebuddy.domain.Movie;

import java.util.List;

public interface MovieReader {

    List<Movie> loadMovies(); // 영화 목록을 반환하는 loadMovies 라는 메소드
}
