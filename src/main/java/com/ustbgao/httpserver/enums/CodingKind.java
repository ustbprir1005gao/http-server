package com.ustbgao.httpserver.enums;

/**
 * 字符串编码格式
 * Created by ustbgao
 */
public enum CodingKind {
    UTF("utf-8",1),
    GBK("gbk",2),
    ISO("iso",3);

    CodingKind(String codingMsg, Integer codingCode) {
        this.codingMsg = codingMsg;
        this.codingCode = codingCode;
    }

    private String codingMsg;
    private Integer codingCode;

    public String getCodingMsg() {
        return codingMsg;
    }

    public void setCodingMsg(String codingMsg) {
        this.codingMsg = codingMsg;
    }

    public Integer getCodingCode() {
        return codingCode;
    }

    public void setCodingCode(Integer codingCode) {
        this.codingCode = codingCode;
    }
}
