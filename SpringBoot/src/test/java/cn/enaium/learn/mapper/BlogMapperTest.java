package cn.enaium.learn.mapper;

import cn.enaium.misc.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Enaium
 */
@SpringBootTest
class BlogMapperTest {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void test() {
        blogMapper.selectList(null).forEach(System.out::println);
    }
}