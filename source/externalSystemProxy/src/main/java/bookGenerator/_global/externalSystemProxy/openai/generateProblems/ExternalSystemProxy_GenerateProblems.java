package bookGenerator._global.externalSystemProxy.openai.generateProblems;

import org.springframework.stereotype.Component;

import bookGenerator.BootApplication;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyBase;

// 주어진 쿼리에 해당하는 문제들을 생성하고, 관련 정보를 반환하기 위해서
@Component
public class ExternalSystemProxy_GenerateProblems extends ExternalSystemProxyBase {
    public static ExternalSystemProxy_GenerateProblems getInstance() {
        return BootApplication.applicationContext.getBean(
            ExternalSystemProxy_GenerateProblems.class
        );
    }


    public GenerateProblemsResDto externalSystemProxy_GenerateProblems(GenerateProblemsReqDto reqDto) throws Exception {
        return this.jsonCommunication("/openai/generateProblems", reqDto, GenerateProblemsResDto.class);
    }
}
