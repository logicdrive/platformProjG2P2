package bookGenerator._global.externalSystemProxy.s3.uploadFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyResDto;

@Getter
@ToString
public class UploadFileResDto implements ExternalSystemProxyResDto {
    @JsonProperty
    private String fileUrl;
}