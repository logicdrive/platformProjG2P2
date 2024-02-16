package bookGenerator._global.externalSystemProxy.openai.generateTags;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyReqDto;

@Getter
@ToString
public class GenerateTagsReqDto implements ExternalSystemProxyReqDto {
    private final String query;

    public GenerateTagsReqDto(String query) {
        this.query = query;
    }

    public Map<String, Object> hashMap() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("query", this.query);
        return hashMap;
    }
}