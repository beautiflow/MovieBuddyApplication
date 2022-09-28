package moviebuddy;

import moviebuddy.domain.CsvMovieReader;
import moviebuddy.domain.MovieFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이렇게 class 레벨에 @Configuration 을 붙이면 이 클래스는 이제 스프링의 빈 구성정보, 즉 Configuration metadata 로 사용이 될 수 있음을 선언.
public class MovieBuddyFactory {  // 객체를 생성하고 구성하는 역

    @Bean  //그리고 빈 구성정보 내부에 어떤 빈이 있는가를 알려주기 위해서 @Bean 어노테이션을 사용함
    public MovieFinder movieFinder(){
        return new MovieFinder(new CsvMovieReader());
    }
}
