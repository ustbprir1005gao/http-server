package com.ustbgao.httpserver.wrapper;

import java.net.Socket;

/**
 * Created by ustbgao
 */
public class ConnectionWrapper {
    private String client_host;
    private Socket clientSocket;

    public String getClient_host() {
        return client_host;
    }

    public void setClient_host(String client_host) {
        this.client_host = client_host;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
