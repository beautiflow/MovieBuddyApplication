package moviebuddy.domain;

import moviebuddy.ApplicationException;
import moviebuddy.util.FileSystemUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieFinder {

    private MovieReader movieReader = new CsvMovieReader();

    /**
     * 저장된 영화 목록에서 감독으로 영화를 검색한다.
     *
     * @param directedBy 감독
     * @return 검색된 영화 목록
     */
    public List<Movie> directedBy(String directedBy) {
        return movieReader.loadMovies()
                .stream()
                .filter(it -> it.getDirector().toLowerCase().contains(directedBy.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * 저장된 영화 목록에서 개봉년도로 영화를 검색한다.
     *
     * @param releasedYearBy
     * @return 검색된 영화 목록
     */
    public List<Movie> releasedYearBy(int releasedYearBy) {
        return movieReader.loadMovies()
                .stream()
                .filter(it -> Objects.equals(it.getReleaseYear(), releasedYearBy))
                .collect(Collectors.toList());
    }

}


// MovieFinder 클래스를 천천히 다시 한번 살펴보면 MovieBuddyApplication 클래스의 첫 번째 버전과 동일하게 두 가지 관심사가 섞여있다는 것을 알 수 있다.

// MovieFinder 의 두 가지 관심사
// 첫번째, CSV 파일로 작성된 영화 메타데이터를 읽어들인다.
// 두번째, 조건에 맞는 영화를 검색한다.

// 이번에는 객체지향 개념 중 상속과 다형성을 이용해서 문제를 해결해보자.
// 두 개념의 기저에는 추상화라고 하는 원리가 숨겨져 있다. 추상화라는 것은 어떤 것들의 공통적인 성격을 뽑아내서 이를 따로 분리해내는 작업을 의미

