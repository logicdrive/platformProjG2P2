package bookGenerator._global.externalSystemProxy.s3.removeFile;

import org.springframework.stereotype.Component;

import bookGenerator.BootApplication;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyBase;

// 주어진 경로에 있는 파일을 삭제시키기 위해서
@Component
public class ExternalSystemProxy_RemoveFile extends ExternalSystemProxyBase {
    public static ExternalSystemProxy_RemoveFile getInstance() {
        return BootApplication.applicationContext.getBean(
            ExternalSystemProxy_RemoveFile.class
        );
    }


    public RemoveFileResDto externalSystemProxy_RemoveFile(RemoveFileReqDto reqDto) throws Exception {
        return this.jsonCommunication("/s3/removeFile", reqDto, RemoveFileResDto.class);
    }
}
