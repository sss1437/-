package com.chatRoom.entity;

import lombok.Data;

/**
 * @Description:
 * 群聊:{"msg":"777","type":1}
 * 私聊:{"to":"0-","msg":"33333","type":2}
 * @Author: zdd
 * @Date: 2019/8/10
 */
@Data
public class MessageFromClient {
    // 聊天信息
    private String msg;
    // 聊天类别: 1表示群聊 2表示私聊
    private String type;
    // 私聊的对象SessionID
    private String to;
}
