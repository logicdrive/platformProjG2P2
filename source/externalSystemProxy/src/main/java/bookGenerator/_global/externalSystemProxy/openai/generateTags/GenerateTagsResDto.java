package bookGenerator._global.externalSystemProxy.openai.generateTags;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyResDto;

@Getter
@ToString
public class GenerateTagsResDto implements ExternalSystemProxyResDto {
    @JsonProperty
    private List<String> tagNames;
}