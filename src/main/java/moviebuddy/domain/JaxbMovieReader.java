package moviebuddy.domain;

import moviebuddy.ApplicationException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import moviebuddy.ApplicationException;
import org.springframework.stereotype.Repository;

// xml 문서에 매핑될 수 있는 자바 객체 구성하기
@Repository
public class  JaxbMovieReader implements MovieReader {

    @Override
    public List<Movie> loadMovies() {
        try {
            final JAXBContext jaxb = JAXBContext.newInstance(MovieMetadata.class); // JAXB 를 사용하기 위해서는 먼저 JAXBContext 객체를 생성해야 함
            final Unmarshaller unmarshaller = jaxb.createUnmarshaller();  // JAXBContext 로부터 Unmarshaller 라는 객체를 만들어내야 함

            final InputStream content = ClassLoader.getSystemResourceAsStream("movie_metadata.xml");
            final Source source = new StreamSource(content);
            final MovieMetadata metadata = (MovieMetadata) unmarshaller.unmarshal(source);

            return metadata.toMovies();
        } catch (JAXBException error){
            throw new ApplicationException("failed to load movies data", error);
        }
    }

    @XmlRootElement(name = "moviemetadata")
    public static class MovieMetadata {

        private List<MovieData> movies;  // 속성으로 movies 가짐

        public List<MovieData> getMovies() {
            return movies;
        }

        public void setMovies(List<MovieData> movies) {
            this.movies = movies;
        }

        public List<Movie> toMovies() {
            return movies.stream().map(MovieData::toMovie).collect(Collectors.toList()); // MovieData List 를 Movie List 로 변환 시켜줌
        }


    }

    public static class MovieData {  // 컴파일 에러를 해결할 수 있도록 클래스를 만들어줌

        private String title;
        private List<String> genres;
        private String language;
        private String country;
        private int releaseYear;
        private String director;
        private List<String> actors;
        private URL imdbLink;
        private String watchedDate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public List<String> getActors() {
            return actors;
        }

        public void setActors(List<String> actors) {
            this.actors = actors;
        }

        public URL getImdbLink() {
            return imdbLink;
        }

        public void setImdbLink(URL imdbLink) {
            this.imdbLink = imdbLink;
        }

        public String getWatchedDate() {
            return watchedDate;
        }

        public void setWatchedDate(String watchedDate) {
            this.watchedDate = watchedDate;
        }

        public Movie toMovie(){
            String title = getTitle();
            List<String> genres = getGenres();
            String language = getLanguage();
            String country = getCountry();
            int releaseYear = getReleaseYear();
            String director = getDirector();
            List<String> actors = getActors();
            URL imdbLink = getImdbLink();
            String watchedDate = getWatchedDate();

            return Movie.of(title, genres, language, country, releaseYear, director, actors, imdbLink, watchedDate);
        }
    }
}
