package bookGenerator._global.externalSystemProxy.openai.generateContent;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyResDto;

@Getter
@ToString
public class GenerateContentResDto implements ExternalSystemProxyResDto {
    @JsonProperty
    private String content;
}