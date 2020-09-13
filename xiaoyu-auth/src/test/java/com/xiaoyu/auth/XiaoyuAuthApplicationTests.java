package com.xiaoyu.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class XiaoyuAuthApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
    }

}
