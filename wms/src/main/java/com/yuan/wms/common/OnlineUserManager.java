package com.yuan.wms.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class OnlineUserManager {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public OnlineUserManager(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void userLogin(String userId) {
        redisTemplate.opsForSet().add("online_users", userId);
    }

    public void userLogout(String userId) {
        redisTemplate.opsForSet().remove("online_users", userId);
    }

    public long getOnlineUserCount() {
        return redisTemplate.opsForSet().size("online_users");
    }

    public boolean isExist(String userId) {
        return redisTemplate.opsForSet().isMember("online_users",userId);
    }
}
