package com.ustbgao.httpserver.service;

import java.net.Socket;

/**
 * Created by ustbgao
 */
public interface RequestProcessThread {
    public void processClientRequest(Socket client);
}
