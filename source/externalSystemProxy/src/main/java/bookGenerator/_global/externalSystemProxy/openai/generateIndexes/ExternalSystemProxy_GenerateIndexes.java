package bookGenerator._global.externalSystemProxy.openai.generateIndexes;

import org.springframework.stereotype.Component;

import bookGenerator.BootApplication;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyBase;

// 주어진 쿼리에 해당하는 인덱스들을 생성하고, 관련 정보를 반환하기 위해서
@Component
public class ExternalSystemProxy_GenerateIndexes extends ExternalSystemProxyBase {
    public static ExternalSystemProxy_GenerateIndexes getInstance() {
        return BootApplication.applicationContext.getBean(
            ExternalSystemProxy_GenerateIndexes.class
        );
    }


    public GenerateIndexesResDto externalSystemProxy_GenerateIndexes(GenerateIndexesReqDto reqDto) throws Exception {
        return this.jsonCommunication("/openai/generateIndexes", reqDto, GenerateIndexesResDto.class);
    }
}
