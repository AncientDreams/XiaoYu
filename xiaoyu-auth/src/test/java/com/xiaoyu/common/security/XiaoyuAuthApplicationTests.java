package com.xiaoyu.common.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class XiaoyuAuthApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(BCrypt.hashpw("secret", BCrypt.gensalt()));
        System.out.println(BCrypt.checkpw("secret", "$2a$10$jHPWu2ScGNIPxw8M07Uw6umCcejbfJRS7w6Xpn8S/uCKxyQwCXHfC"));
    }

}
