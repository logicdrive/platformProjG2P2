package bookGenerator._global.externalSystemProxy.s3.uploadFile;

import org.springframework.stereotype.Component;

import bookGenerator.BootApplication;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyBase;

// 주어진 DataUrl을 기반으로 파일을 S3에 업로드시키고, 관련 URL을 반환하기 위해서
@Component
public class ExternalSystemProxy_UploadFile extends ExternalSystemProxyBase {
    public static ExternalSystemProxy_UploadFile getInstance() {
        return BootApplication.applicationContext.getBean(
            ExternalSystemProxy_UploadFile.class
        );
    }


    public UploadFileResDto externalSystemProxy_UploadFile(UploadFileReqDto reqDto) throws Exception {
        return this.jsonCommunication("/s3/uploadFile", reqDto, UploadFileResDto.class);
    }
}
