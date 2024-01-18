package com.study.springDesign.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {
    private Integer code;
    private String msg;
    private Object data;

    public static R success(String msg){
        R r = new R();
        r.code=200;
        r.msg=msg;
        return r;
    }

    public static R success(String msg,Object obj){
        R r = new R();
        r.code=200;
        r.msg=msg;
        r.data=obj;
        return r;
    }

    public static R error(String msg){
        R r = new R();
        r.code=201;
        r.msg=msg;
        return r;
    }

    public static R error(String msg,Object obj){
        R r = new R();
        r.code=201;
        r.msg=msg;
        r.data=obj;
        return r;
    }
}