package com.ustbgao.httpserver.serviceimpl;

import com.ustbgao.httpserver.wrapper.RequestWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by ustbgao
 */
public class ProcessRequestThread implements Runnable {
    private Socket client;

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public ProcessRequestThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            InputStream is = client.getInputStream();
            System.out.println("客户端发送的请求信息: >>>>>>>>>>>>>>>>>>>>>>>>>");
            // 读取第一行, 请求地址
            String line = readLine(is, 0);
            //打印请求行
            System.out.print(line);
            // < Method > < URL > < HTTP Version > <\r\n>  取的是URL部分
            String resource = line.substring(line.indexOf('/'), line
                    .lastIndexOf('/') - 5);
            //获得请求的资源的地址
            resource = URLDecoder.decode(resource, "utf-8");//反编码 URL 地址
            String method = new StringTokenizer(line).nextElement()
                    .toString();// 获取请求方法, GET 或者 POST
            int contentLength = 0;//如果为POST方法，则会有消息体长度

            // 读取所有浏览器发送过来的请求参数头部信息
            do {
                line = readLine(is, 0);
                //如果有Content-Length消息头时取出
                if (line.startsWith("Content-Length")) {
                    contentLength = Integer.parseInt(line.split(":")[1]
                            .trim());
                }
                //打印请求部信息
                System.out.print(line);
                //如果遇到了一个单独的回车换行，则表示请求头结束
            } while (!line.equals("\r\n"));
            //如果是POST请求，则有请求体
            if ("POST".equalsIgnoreCase(method)) {
                //注，这里只是简单的处理表单提交的参数，而对于上传文件这里是不能这样处理的，
                //因为上传的文件时消息体不只是一行，会有多行消息体
                System.out.print(readLine(is, contentLength));
                System.out.println();
            }

           /* int len = 0;
            char[] bs = new char[2048];
            InputStreamReader is = new InputStreamReader(inputStream);
            StringBuilder msg = new StringBuilder();
            try {
                while ((len = is.read(bs)) != -1) {
                    msg.append(bs, 0, len);
                    msg.append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String str= msg.toString();
            System.out.println(str);*/
            /*do{
                temp = reader.readLine();
                System.out.println(temp);
                if(temp.equals("\r\n")){
                    break;
                }
            }while(!temp.equals("\r\n"));
            System.out.println(reader.readLine());*/
            /*while((temp = reader.readLine()) != null){
                System.out.println(temp);
                if(temp.equals("\r\n")){
                    break;
                }
            }*/
            //System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String readLine(InputStream is, int contentLe) throws IOException {
        ArrayList lineByteList = new ArrayList();
        byte readByte;
        int total = 0;
        if (contentLe != 0) {
            do {
                readByte = (byte) is.read();
                lineByteList.add(Byte.valueOf(readByte));
                total++;
            } while (total < contentLe);//消息体读还未读完
        } else {
            do {
                readByte = (byte) is.read();
                lineByteList.add(Byte.valueOf(readByte));
            } while (readByte != 10);
        }

        byte[] tmpByteArr = new byte[lineByteList.size()];
        for (int i = 0; i < lineByteList.size(); i++) {
            tmpByteArr[i] = ((Byte) lineByteList.get(i)).byteValue();
        }
        lineByteList.clear();

        String tmpStr = new String(tmpByteArr, "utf-8");
        /* http请求的header中有一个Referer属性，这个属性的意思就是如果当前请求是从别的页面链接过
         * 来的，那个属性就是那个页面的url，如果请求的url是直接从浏览器地址栏输入的就没有这个值。得
         * 到这个值可以实现很多有用的功能，例如防盗链，记录访问来源以及记住刚才访问的链接等。另外，浏
         * 览器发送这个Referer链接时好像固定用UTF-8编码的，所以在GBK下出现乱码，我们在这里纠正一下
         */
        if (tmpStr.startsWith("Referer")) {//如果有Referer头时，使用UTF-8编码
            tmpStr = new String(tmpByteArr, "UTF-8");
        }
        return tmpStr;
    }
}
