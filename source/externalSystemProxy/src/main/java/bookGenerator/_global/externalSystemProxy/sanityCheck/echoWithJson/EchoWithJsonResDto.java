package bookGenerator._global.externalSystemProxy.sanityCheck.echoWithJson;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyResDto;

@Getter
@ToString
public class EchoWithJsonResDto implements ExternalSystemProxyResDto {
    @JsonProperty
    private String message;
}