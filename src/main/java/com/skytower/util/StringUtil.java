package com.skytower.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class StringUtil {


    /**
     * 查看一个字符串是否可以转换为数字
     * @param str 字符串
     * @return true 可以; false 不可以;
     */
    public static boolean isCanTransformToNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 将String转换成ArrayList<String>, 例如: "['Android', 'iOS']" => ['Android', 'iOS']
     * @param str 字符串
     * @return res ArrayList<String>
     */
    public static ArrayList<String> transformArrayStringToArray(String str) {
        StringBuffer sb = new StringBuffer(str);

        int left = sb.indexOf("[");

        if (left != -1) {
            sb.deleteCharAt(left);
        }

        int right = sb.indexOf("]");

        if (right != -1) {
            sb.deleteCharAt(right);
        }

        ArrayList<String> res = new ArrayList<String>(Arrays.asList(sb.toString().split(",")));

        return res;
    }
}
