package io.mycat.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;


@Getter
@ToString
@Builder
/**
 * @author Junwen Chen
 **/
public class MycatRequest {
    final int sessionId;
    final String text;
    final Map<String,Object> context;
    public MycatRequest(int sessionId, String text, Map<String, Object> context) {
        this.sessionId = sessionId;
        this.text = text;
        this.context = context;
    }
    public <T> T get(String key) {
        return (T)context.get(key);
    }
    public <T> T getOrDefault(String key, String defaultValue) {
        return (T)context.getOrDefault(key,defaultValue);
    }

}