package io.tzk.restful.generator.admin.api;

import io.tzk.restful.generator.ApplicationStarter;
import io.tzk.restful.generator.admin.api.domain.PathGrantedAuthority;
import io.tzk.restful.generator.common.util.serialize.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
@SpringBootTest(classes = ApplicationStarter.class)
public class JacksonTest {

    @Resource
    private JSON serializer;

    @Test
    public void grantedAuthority(){
        PathGrantedAuthority grantedAuthority = new PathGrantedAuthority("root");
        String json = serializer.serialize(grantedAuthority);
        PathGrantedAuthority authority = serializer.deserialize(json, PathGrantedAuthority.class);
        System.out.println(authority);
    }
}
