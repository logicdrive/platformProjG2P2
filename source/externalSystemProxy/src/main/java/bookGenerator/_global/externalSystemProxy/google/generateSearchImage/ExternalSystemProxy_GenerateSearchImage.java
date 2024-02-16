package bookGenerator._global.externalSystemProxy.google.generateSearchImage;

import org.springframework.stereotype.Component;

import bookGenerator.BootApplication;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyBase;

// 주어진 주제에 관한 이미지를 구글에 검색하고, 파일을 저장한 후, S3에 저장된 URL을 반환하기 위해서
@Component
public class ExternalSystemProxy_GenerateSearchImage extends ExternalSystemProxyBase {
    public static ExternalSystemProxy_GenerateSearchImage getInstance() {
        return BootApplication.applicationContext.getBean(
            ExternalSystemProxy_GenerateSearchImage.class
        );
    }


    public GenerateSearchImageResDto externalSystemProxy_GenerateSearchImage(GenerateSearchImageReqDto reqDto) throws Exception {
        return this.jsonCommunication("/google/generateSearchImage", reqDto, GenerateSearchImageResDto.class);
    }
}
