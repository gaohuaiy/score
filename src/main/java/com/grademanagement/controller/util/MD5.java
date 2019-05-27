package com.grademanagement.controller.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5 {
    public  static String toPassWord(String password){
        return new Md5Hash(password, null,1).toString();
    }

    public static void main(String[] args) {
        String s = MD5.toPassWord("1111");
        System.out.println(s);
    }
}