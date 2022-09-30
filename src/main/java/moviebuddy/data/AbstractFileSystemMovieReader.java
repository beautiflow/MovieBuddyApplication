package moviebuddy.data;

import moviebuddy.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @PostConstruct // 메타데이터의 빈이 초기화될 때 메타데이터가 올바른 값인지를 검증해 주는 코드
    public void afterPropertiesSet() throws Exception {
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
