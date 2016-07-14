package com.ustbgao.httpserver.utils;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by ustbgao
 */
public class TestClassLoader {
    public static void main(String [] args){
        URL[] urls = ((URLClassLoader)ClassLoader.getSystemClassLoader().getParent()).getURLs();
        for(int i = 0; i < urls.length; i++){
            System.out.println(urls[i]);
        }
    }
}
