package com.ustbgao.httpserver.utils;

import com.ustbgao.httpserver.wrapper.RequestWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ustbgao
 */
public class TestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
    public void doPost(RequestWrapper req, HttpServletResponse resp){

    }
}
