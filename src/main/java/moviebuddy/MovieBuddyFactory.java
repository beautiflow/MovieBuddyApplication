package moviebuddy;

import moviebuddy.data.CsvMovieReader;
import moviebuddy.data.XmlMovieReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

@Configuration // 이렇게 class 레벨에 @Configuration 을 붙이면 이 클래스는 이제 스프링의 빈 구성정보, 즉 Configuration metadata 로 사용이 될 수 있음을 선언.
@PropertySource("/application.properties")
@ComponentScan(basePackages = { "moviebuddy" }) // 스프링 컨테이너에 빈을 자동으로 등록해주는 기능을 제공
@Import({ MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class }) // @Import 는 다른 클래스에서 빈 구성 정보를 불러오기 위해서 사용하는 것

public class MovieBuddyFactory {  // 객체를 생성하고 구성하는 역할

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("moviebuddy");

        return marshaller;
    }

    @Configuration
    static class DomainModuleConfig{

    }

    @Configuration
    static class DataSourceModuleConfig{

        }

    }

