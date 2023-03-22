package com.zhixi.md5;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName ShiroMd5Utils
 * @Author zhangzhixi
 * @Description
 * @Date 2023-03-12 23:13
 * @Version 1.0
 */
public class ShiroMd5Utils {
    public static void main(String[] args) {
        // 使用用户名做盐值，为了让盐是动态的
        String userName = "xiaochen";

        //使用md5
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println(md5Hash.toHex());

        //使用MD5 + salt处理
        Md5Hash md5Hash1 = new Md5Hash("123", userName);
        System.out.println(md5Hash1.toHex());

        //使用md5 + salt + hash散列
        Md5Hash md5Hash2 = new Md5Hash("123", userName, 1024);
        System.out.println(md5Hash2.toHex());
    }
}
