package com.ustbgao.httpserver.service;

import com.ustbgao.httpserver.exception.ServerInitException;

import java.net.ServerSocket;

/**
 * Created by ustbgao
 */
public interface ServerInit {
    public ServerSocket serverInit() throws ServerInitException;
}
