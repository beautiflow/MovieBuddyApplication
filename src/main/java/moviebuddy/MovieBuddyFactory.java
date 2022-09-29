package moviebuddy;

import moviebuddy.domain.CsvMovieReader;
import moviebuddy.domain.MovieFinder;
import moviebuddy.domain.MovieReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration // 이렇게 class 레벨에 @Configuration 을 붙이면 이 클래스는 이제 스프링의 빈 구성정보, 즉 Configuration metadata 로 사용이 될 수 있음을 선언.
@ComponentScan(basePackages = { "moviebuddy" }) // 스프링 컨테이너에 빈을 자동으로 등록해주는 기능을 제공
@Import({ MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class }) // @Import 는 다른 클래스에서 빈 구성 정보를 불러오기 위해서 사용하는 것
public class MovieBuddyFactory {  // 객체를 생성하고 구성하는 역할

    @Configuration
    static class DomainModuleConfig{

    }

    @Configuration
    static class DataSourceModuleConfig{

    }
}
