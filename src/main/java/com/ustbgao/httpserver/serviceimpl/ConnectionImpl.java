package com.ustbgao.httpserver.serviceimpl;

import com.ustbgao.httpserver.exception.ServerInitException;
import com.ustbgao.httpserver.service.Connection;
import com.ustbgao.httpserver.wrapper.ConnectionWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ustbgao
 */
public class ConnectionImpl implements Connection {
    @Override
    public ConnectionWrapper acceptClientConnection() {
        try {
            ServerSocket server = ServerInit.serverInit();
            while(true){
                try {
                    Socket client = server.accept();
                    new Thread(new ProcessRequestThread(client)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ServerInitException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String [] args){
        ConnectionImpl connection = new ConnectionImpl();
        connection.acceptClientConnection();
    }
}
