package com.chatRoom.entity;

import lombok.Data;

import java.util.Map;

/**
 * @Description: 后端发送给前端的信息实体
 * @Author: zdd
 * @Date: 2019/8/10
 */
@Data
public class Message2Client {
    // 聊天内容
    private String content;
    // 服务端登录的所有用户列表
    private Map<String, String> names;

    public void setContent(String msg) {
        this.content = msg;
    }
    public void setContent(String userName,String msg) {
        this.content = userName + "说:" + msg;
    }
}
