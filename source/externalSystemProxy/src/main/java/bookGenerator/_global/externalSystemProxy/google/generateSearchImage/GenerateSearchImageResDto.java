package bookGenerator._global.externalSystemProxy.google.generateSearchImage;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyResDto;

@Getter
@ToString
public class GenerateSearchImageResDto implements ExternalSystemProxyResDto {
    @JsonProperty
    private String fileUrl;
}