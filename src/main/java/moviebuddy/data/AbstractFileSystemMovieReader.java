package moviebuddy.data;

import moviebuddy.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public abstract class AbstractFileSystemMovieReader {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private String metadata;

    public String getMetadata() {
        return metadata;
    }

    @Value("${movie.metadata}")
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public URL getMetadataUrl(){
        String location = getMetadata();
        if(location.startsWith("file:")){
            // file URL 처리
        }else if(location.startsWith("http:")){
            //http URL 처리
        }

        return ClassLoader.getSystemResource(location);
    }

    @PostConstruct // 메타데이터의 빈이 초기화될 때 메타데이터가 올바른 값인지를 검증해 주는 코드
    public void afterPropertiesSet() throws Exception {
        // ClassLoader.getSystemResource() 클래스패스 상의 자원만 처리할 수 있다.
        // 애플리케이션 외부인 file:, http:, ftp: 등의 다른 프로토콜을 이용해야 한다면 이런 방식으로 네트워크 상의 파일을 읽어야 한다면 프로토콜에 따라서 URL 객체를 다루기 위한 방식이 변경이 되어야 한다.

        URL metadataUrl = ClassLoader.getSystemResource(metadata);
        if (Objects.isNull(metadataUrl)) {
            throw new FileNotFoundException(metadata);
        }

        if (Files.isReadable(Path.of(metadataUrl.toURI())) == false) {
            throw new ApplicationException(String.format("cannnot read to metadata. [%s]", metadata));
        }
    }

    @PreDestroy
    public void destroy() throws Exception {
        log.info("Destoryed bean");

    }
}
