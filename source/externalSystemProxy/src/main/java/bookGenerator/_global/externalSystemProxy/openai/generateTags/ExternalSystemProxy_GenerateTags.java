package bookGenerator._global.externalSystemProxy.openai.generateTags;

import org.springframework.stereotype.Component;

import bookGenerator.BootApplication;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyBase;

// 주어진 쿼리에 해당하는 태그들을 생성하고, 관련 정보를 반환하기 위해서
@Component
public class ExternalSystemProxy_GenerateTags extends ExternalSystemProxyBase {
    public static ExternalSystemProxy_GenerateTags getInstance() {
        return BootApplication.applicationContext.getBean(
            ExternalSystemProxy_GenerateTags.class
        );
    }


    public GenerateTagsResDto externalSystemProxy_GenerateTags(GenerateTagsReqDto reqDto) throws Exception {
        return this.jsonCommunication("/openai/generateTags", reqDto, GenerateTagsResDto.class);
    }
}
