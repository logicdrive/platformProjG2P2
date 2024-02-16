package bookGenerator._global.externalSystemProxy.google.generateSearchImage;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyReqDto;

@Getter
@ToString
public class GenerateSearchImageReqDto implements ExternalSystemProxyReqDto {
    private final String query;

    public GenerateSearchImageReqDto(String query) {
        this.query = query;
    }

    public Map<String, Object> hashMap() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("query", this.query);
        return hashMap;
    }
}