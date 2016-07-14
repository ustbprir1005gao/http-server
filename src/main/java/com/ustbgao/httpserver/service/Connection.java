package com.ustbgao.httpserver.service;

import com.ustbgao.httpserver.wrapper.ConnectionWrapper;

/**
 * 服务器端负责客户端连接
 *
 * Created by ustbgao
 */
public interface Connection {
    public ConnectionWrapper acceptClientConnection();
}
