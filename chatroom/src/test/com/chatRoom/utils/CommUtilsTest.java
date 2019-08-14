package com.chatRoom.utils;

import com.chatRoom.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: zdd
 * @Date: 2019/8/8
 */
public class CommUtilsTest {

    @Test
    public void loadProperties() {
        String fileName = "db.properties";
        Properties properties = CommUtils.loadProperties(fileName);
        String url = properties.getProperty("url");
        Assert.assertNotNull(url);
    }

    @Test
    public void gsonTest1(){
        User user = new User();
        user.setId(10);
        user.setUserName("test");
        user.setPassword("123");
        String jsonStr = CommUtils.object2Json(user);
        System.out.println(jsonStr);
    }

    @Test
    public void gsonTest2(){
        String jsonStr = "{\"id\":10,\"userName\":\"test\",\"password\":\"123\"}";
        User user = (User) CommUtils.json2Object(jsonStr,User.class);
        System.out.println(user);
    }
}