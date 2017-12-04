package com.fable.kscc.api.utils;

/**
 * Created by Administrator on 2017/9/14 0014.
 */
public class test {
    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
    public static void main(String[] args){
        String inStr = "123456";
        System.out.println(convertMD5("EFG@AB"));
    }
}
