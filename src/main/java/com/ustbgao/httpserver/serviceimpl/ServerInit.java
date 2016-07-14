package com.ustbgao.httpserver.serviceimpl;

import com.ustbgao.httpserver.constant.ServerConfig;
import com.ustbgao.httpserver.exception.ServerInitException;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by ustbgao
 */
public class ServerInit  {

    public static ServerSocket serverInit() throws ServerInitException{
        ServerSocket server = null;
        try {
            server = new ServerSocket(ServerConfig.SERVER_PORT, ServerConfig.SERVER_MAX_CONNECTION);
        } catch (IOException e) {
            throw new ServerInitException("when server init occur exceptions");
        }
        return server;
    }
}
