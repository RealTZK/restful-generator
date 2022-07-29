package io.tzk.restful.generator.admin.api;

import io.tzk.restful.generator.ApplicationStarter;
import io.tzk.restful.generator.admin.api.domain.PathGrantedAuthority;
import io.tzk.restful.generator.common.util.mapper.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
@RunWith(SpringRunner.class)
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
