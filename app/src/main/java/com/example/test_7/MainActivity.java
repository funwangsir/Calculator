package com.example.test_7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.regex.Pattern;

//注意继承AppCompatActivity 和 Activity的区别
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //手机屏幕的宽高
    int screenWidth;
    int screenHeigh;

    //文本显示模块占手机屏幕的高度比例
//    private static final double EDDIT_CONTENT = 0.46;
    //输入模块占手机屏幕的高度比例
    private static final double INPUT_KEYWORD_HEIGHT = 0.55;

    //输入的文本内容
    private static StringBuilder InputTextContent = new StringBuilder("0");
    //显示结果的文本内容
    private static StringBuilder resultTextContent = new StringBuilder("=");

    //输入内容的文本对象，下面统称为【输入文本框】
    TextView inputText;
    //计算结果的文本对象，下面统称为【计算结果文本框】
    TextView resultText;

    public static final String PRE = "(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)";//数字校验正则表达式
    public static final String PRE_SYMBOL = "[\\+\\-\\×\\÷\\%]";//运算符匹配表达式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText  = (TextView)findViewById(R.id.input_text);
        resultText = (TextView)findViewById(R.id.result_text);

        adapt();//适应屏幕
        bingButton();//绑定按钮点击事件

    }

    /**
     * 键盘高度自适应
     */
    private void adapt(){

        //获取手机屏幕的宽高信息
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeigh = dm.heightPixels;
/**
 // 非小米全面屏需要适配下面的状态栏
 //获取手机状态栏的高度(单位是px 分辨率)
 int statusBarHeight = -1;
 int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
 if (resourceId > 0) {
 statusBarHeight = getResources().getDimensionPixelSize(resourceId);
 }
 float scale = this.getResources().getDisplayMetrics().density;
 statusBarHeight =  (int) (statusBarHeight / scale + 0.5f);//将状态栏的高度px单位转换为dp单位
 */

//设置文本输入模块的高度占手机屏幕高度的比例
//        LinearLayout editContent = (LinearLayout)findViewById(R.id.edit_content);
//        ViewGroup.LayoutParams lp_edit = editContent.getLayoutParams();
//        lp_edit.height = (int)(screenHeigh * EDDIT_CONTENT);
//        editContent.setLayoutParams(lp_edit);

        //设置键盘输入模块的高度占手机屏幕高度的比例
        LinearLayout inputKeyWord = (LinearLayout)findViewById(R.id.input_keyword);
        ViewGroup.LayoutParams lp_key = inputKeyWord.getLayoutParams();
        lp_key.height = (int)(screenHeigh * INPUT_KEYWORD_HEIGHT);
        inputKeyWord.setLayoutParams(lp_key);

        //除了等于符号外的输入键的每一行的LinearLayout高度
        // 这个高度计算时通常需要去除顶部状态栏的高度，全面屏有些特殊
        int oneLinearLayouHeight =(int) ((screenHeigh * INPUT_KEYWORD_HEIGHT) / 5 );

        //为保证自适应，按钮高度应该适应输入模块的高度
        LinearLayout line1 = (LinearLayout)findViewById(R.id.line_1);
        ViewGroup.LayoutParams lp1 = line1.getLayoutParams();
        lp1.height = oneLinearLayouHeight;//该行的高度 = （输入模块的总高度-边框总宽度） / 4
        line1.setLayoutParams(lp1);

        LinearLayout line2 = (LinearLayout)findViewById(R.id.line_2);
        ViewGroup.LayoutParams lp2 = line2.getLayoutParams();
        lp2.height = oneLinearLayouHeight;
        line2.setLayoutParams(lp2);

        LinearLayout line3 = (LinearLayout)findViewById(R.id.line_3);
        ViewGroup.LayoutParams lp3 = line1.getLayoutParams();
        lp3.height = oneLinearLayouHeight;
        line3.setLayoutParams(lp3);


        LinearLayout line4 = (LinearLayout)findViewById(R.id.line_4);
        ViewGroup.LayoutParams lp4 = line4.getLayoutParams();
        lp4.height = oneLinearLayouHeight;
        line4.setLayoutParams(lp4);

        LinearLayout line5 = (LinearLayout)findViewById(R.id.line_5);
        ViewGroup.LayoutParams lp5 = line5.getLayoutParams();
        lp5.height = oneLinearLayouHeight;
        line5.setLayoutParams(lp5);

        //等号的高度设为两倍+边框高度
        LinearLayout equal_sign = (LinearLayout)findViewById(R.id.equal_sign);
        ViewGroup.LayoutParams lp6 = equal_sign.getLayoutParams();
        lp6.height = oneLinearLayouHeight * 2 ;
        equal_sign.setLayoutParams(lp6);

    }

    /**
     * 按钮及监听事件的绑定
     * 共19个按钮
     */
    private void bingButton(){
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);//绑定事件
        ImageButton button2 = (ImageButton)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(this);
        Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(this);
        Button button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(this);
        Button button10 = (Button)findViewById(R.id.button10);
        button10.setOnClickListener(this);
        Button button11 = (Button)findViewById(R.id.button11);
        button11.setOnClickListener(this);
        Button button12 = (Button)findViewById(R.id.button12);
        button12.setOnClickListener(this);
        Button button13 = (Button)findViewById(R.id.button13);
        button13.setOnClickListener(this);
        Button button14 = (Button)findViewById(R.id.button14);
        button14.setOnClickListener(this);
        Button button15 = (Button)findViewById(R.id.button15);
        button15.setOnClickListener(this);
        Button button16 = (Button)findViewById(R.id.button16);
        button16.setOnClickListener(this);
        Button button17 = (Button)findViewById(R.id.button17);
        button17.setOnClickListener(this);
        Button button18 = (Button)findViewById(R.id.button18);
        button18.setOnClickListener(this);
        Button button19 = (Button)findViewById(R.id.button19);
        button19.setOnClickListener(this);
    }


    //实现OnClickListener接口的onClick方法，自定义实现其点击事件
    @Override
    public void onClick(View view) {
        switch (view.getResources().getResourceEntryName(view.getId())){
            case "button1" : // C
                OptionC();
                break;
            case "button2" : // 回退
                back();
                break;
            case "button3" : // ÷
                addAndCalc("÷");
                break;
            case "button4" : // ×
                addAndCalc("×");
                break;
            case "button5" :// 7
                addAndCalc("7");
                break;
            case "button6" :// 8
                addAndCalc("8");
                break;
            case "button7" :// 9
                addAndCalc("9");
                break;
            case "button8" :// -
                addAndCalc("-");
                break;
            case "button9" :// 4
                addAndCalc("4");
                break;
            case "button10" :// 5
                addAndCalc("5");
                break;
            case "button11" :// 6
                addAndCalc("6");
                break;
            case "button12" :// +
                addAndCalc("+");
                break;
            case "button13" :// 1
                addAndCalc("1");
                break;
            case "button14" :// 2
                addAndCalc("2");
                break;
            case "button15" :// 3
                addAndCalc("3");
                break;
            case "button16" :// %
                addAndCalc("%");
                break;
            case "button17" :// 0
                addAndCalc("0");
                break;
            case "button18" :// .
                addAndCalc(".");
                break;
            case "button19" :// =
                // 传入表达式，计算结果，赋给结果文本框对象显示
                clickEqual();
                break;
            default:
                break;
        }

        //添加字符时，要将【输入文本框】字体变大
        inputText.setTextSize(60);
        inputText.setText(InputTextContent);

        //监听两个TextView的长度，超过一定长度就缩小字体
        TextViewLenListener();
    }


    /**
     * 各操作方法的具体实现
     */

    //C 清除
    public void OptionC(){
        InputTextContent = new StringBuilder("0");//清空结果，恢复为初始的0
        resultText.setVisibility(View.INVISIBLE);//隐藏计算结果文本框
    }

    //回退 清除一个数
    public void back(){
        if(InputTextContent.length()>1){//不止一个字符
            InputTextContent.delete(InputTextContent.length()-1,InputTextContent.length());
            //获取当前输入的字符前面一个字符
            String lastElement = InputTextContent.substring(InputTextContent.length()-1,InputTextContent.length());
            //暂未完成
        }
        else{
            InputTextContent = new StringBuilder("0");//删除完后归零
            resultText.setVisibility(View.INVISIBLE);//隐藏计算结果文本框
        }


    }

    //点击等号
    public void clickEqual(){
        if(isCorrect(InputTextContent.toString())){//表达式能够计算就进行计算并显示结果
            resultText.setText("="+Calculator.calculation(InputTextContent.toString()));//设置内容
            resultText.setVisibility(View.VISIBLE);//设为可见
            resultText.setTextSize(60);//设置大小
        }
    }


    //每次添加一个元素到字符串，就对该字符串进行计算
    public void addAndCalc(String newNext){
        //获取当前输入的字符前面一个字符
        String lastElement = InputTextContent.substring(InputTextContent.length()-1,InputTextContent.length());
        if(
            Pattern.compile(PRE_SYMBOL).matcher(newNext).find()//输入的是符号
            &&
            (
                InputTextContent.length() == 1 && InputTextContent.toString().equals("0")//是第一次输入
                ||
                Pattern.compile(PRE_SYMBOL).matcher(lastElement).find()//输入前的最后一个元素也是符号
            )
        ){
            //Nothing...
        }else{
            if(InputTextContent.length() == 1 && InputTextContent.toString().equals("0")){
                //当只有一个数字且为0时，这个0就要被清除掉，从而解决刚开始输入去除0的问题
                InputTextContent = new StringBuilder("");
            }
            //将新的数字添加到尾部
            InputTextContent.append(newNext);

            //将输入的数据显示到【输入文本框】
            inputText.setText(InputTextContent);

            //字符串符合计算表达式规则就进行运算
            if(isCorrect(InputTextContent.toString())){
                //将该字符串计算出来，并显示到【计算结果文本框】
                resultText.setText("="+Calculator.calculation(InputTextContent.toString()));
                resultText.setVisibility(View.VISIBLE);
            }
        }
    }

    //验证表达式是否能够进行计算
    public boolean isCorrect(String expre){
        String[] symbols = {};
        String[] nums = expre.split(PRE_SYMBOL);
        if(Pattern.compile(PRE_SYMBOL).matcher(expre).find())//字符串中有符号就进行拆分{
        {
            symbols = Pattern.compile(PRE).matcher(expre).replaceAll("").split("");
        }

        //判断规则就是“符号数组比数字数字少1”
        if(symbols.length < nums.length)
            //这里需要注意
            //在模拟器上，split后的长度不一定就是0
            //字符串使用split后的数组长度问题，我暂时还没搞懂原因，后面弄懂了再补上
            return true;
        return false;
    }

    //监听TextView字符串长度，过长就缩小字体大小
    public void TextViewLenListener(){
        //将getTextSize()获取的px单位转换为dp
        float scale = this.getResources().getDisplayMetrics().density;
        int inputTextSize =  (int) (inputText.getTextSize() / scale + 0.5f);
        int resultTestSize = (int) (resultText.getTextSize() / scale + 0.5f);
        if(inputText.getText().length() > 10 ){//长度大于10就缩小
            inputText.setTextSize((int)(inputTextSize * 0.6));
        }
        if(resultText.getText().length() > 10 ){
            resultText.setTextSize((int)(resultTestSize * 0.6));
        }
    }
}
