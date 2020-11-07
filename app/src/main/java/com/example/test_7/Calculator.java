package com.example.test_7;
import java.util.regex.Pattern;

/**
 * 实现计算功能的封装类
 * 这里的计算只需要考虑从左到右依次计算，且不需考虑括号
 */
public class Calculator {
    public static final String PRE = "(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)";//数字及小数校验正则表达式
    public static final String PRE_SYMBOL = "[\\+\\-\\×\\÷\\%]";//运算符匹配表达式
    /**
     * 传入表达式，计算并返回结果
     * @param expre 传入的表达式
     * @return 计算结果
     */
    public static String calculation(String expre){
        //分别获取数字和运算符的数组集合
        String[] sSymbol = Pattern.compile(PRE).matcher(expre).replaceAll("").split("");
        String[] sNum = expre.split(PRE_SYMBOL);

        String result = sNum[0];
        for (int i = 1; i < sNum.length; i++) {
            //这里需要注意
            //在模拟器上，split后的长度不一定就是0
            //字符串使用split后的数组长度问题，我暂时还没搞懂原因，后面弄懂了再补上
            result = calc(result,sNum[i],sSymbol[i-1]);
        }
        return result;
    }

    //计算两个数的结果
    public static String calc(String a,String b,String symbol){
        double result = 0;
        switch (symbol){
            case "+":
                result = Double.parseDouble(a) + Double.parseDouble(b);break;
            case "-":
                result = Double.parseDouble(a) - Double.parseDouble(b);break;
            case "×":
                result = Double.parseDouble(a) * Double.parseDouble(b);break;
            case "÷":
                result = Double.parseDouble(a) / Double.parseDouble(b);break;
            case "%":
                if(b.equals("") || b == "")//只有一个数就除以100
                    result = Double.parseDouble(a) / 100;
                else//两个数就取模运算
                    result = Double.parseDouble(a) % Double.parseDouble(b);
                break;
            default:
                throw new RuntimeException("参数不合法："+"a="+a+",b="+b+",symbol="+symbol);
        }
        //如果resutl结果是整数，就去掉.0后缀
        return (Math.round(result)-result==0) ? String.valueOf((long)result) : String.valueOf(result);
    }
}
