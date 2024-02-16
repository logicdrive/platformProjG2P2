package bookGenerator._global.externalSystemProxy.sanityCheck.echoWithJson;

import org.springframework.stereotype.Component;

import bookGenerator.BootApplication;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyBase;

// JSON 송수신 여부를 간편하게 테스트해보기 위해서
@Component
public class ExternalSystemProxy_EchoWithJson extends ExternalSystemProxyBase {
    public static ExternalSystemProxy_EchoWithJson getInstance() {
        return BootApplication.applicationContext.getBean(
            ExternalSystemProxy_EchoWithJson.class
        );
    }


    public EchoWithJsonResDto echoWithJson(EchoWithJsonReqDto reqDto) throws Exception {
        return this.jsonCommunication("/sanityCheck/echoWithJson", reqDto, EchoWithJsonResDto.class);
    }
}
