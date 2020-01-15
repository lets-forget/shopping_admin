package com.ning.home_admin.monitor.controller;

import com.ning.home_admin.commons.configure.WebSocketServer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class SocketController {

    @Resource
    private WebSocketServer webSocketServer;

    /**
     * 给指定用户推送消息
     *
     * @param username 用户名
     * @param message  消息
     * @throws IOException
     */
    @RequestMapping(value = "/socket")
    public void testSocket1(@RequestParam String username, @RequestParam String message) {
        webSocketServer.sendInfo(username, message);
    }

    /**
     * 给所有用户推送消息
     *
     * @param message 消息
     * @throws IOException
     */
    @RequestMapping(value = "/socket/all")
    public void testSocket2(@RequestParam String message) {
        try {
            webSocketServer.onMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}