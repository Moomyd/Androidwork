package com.bignerdranch.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


class MainActivity extends Activity  implements View.OnClickListener {
    private EditText input;
    private EditText output;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnplus;
    private Button btnsubtract;
    private Button btnmultiply;
    private Button btndivide;
    private Button btnclear;
    private Button btnresult;

    private String str;//保存数字
    private String strold;
    private char act;//记录符号
    private int count;//判断要计算的次数，如果超过一个符号，先算出来一部分
    private int result;//计算的结果

    private int error;//无错误的时候为0就可以开始计算，如果为1就不能计算
    private int flag;//一个标志，如果为1，可以响应运算消息，如果为0，不响应运算消息，只有前面是数字才可以响应运算消息


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridlayout);

        input=(EditText)findViewById(R.id.input);
        output=(EditText)findViewById(R.id.output);
        btn0=(Button)findViewById(R.id.zero);

        btn1=(Button)findViewById(R.id.one);

        btn2=(Button)findViewById(R.id.two);

        btn3=(Button)findViewById(R.id.three);

        btn4=(Button)findViewById(R.id.four);

        btn5=(Button)findViewById(R.id.five);

        btn6=(Button)findViewById(R.id.six);

        btn7=(Button)findViewById(R.id.seven);

        btn8=(Button)findViewById(R.id.eight);

        btn9=(Button)findViewById(R.id.nine);

        btnplus=(Button)findViewById(R.id.plus);

        btnsubtract=(Button)findViewById(R.id.minus);

        btnmultiply=(Button)findViewById(R.id.multiply);

        btndivide=(Button)findViewById(R.id.devide);

        btnclear=(Button)findViewById(R.id.clear);

        btnresult=(Button)findViewById(R.id.result);



        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnplus.setOnClickListener(this);
        btnsubtract.setOnClickListener(this);
        btnmultiply.setOnClickListener(this);
        btndivide.setOnClickListener(this);
        btnclear.setOnClickListener(this);
        btnresult.setOnClickListener(this);

        act=' ';
        str="";
        strold="";
        count=0;
        result=0;
        error=0;//处理异常，默认无异常
        flag=0;//默认不能响应，按下第一个数字后改为1，可以响应


    }
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.zero:  num(0) ;
                break;
            case R.id.one:  num(1) ;
                break;
            case R.id.two:  num(2) ;
                break;
            case R.id.three:  num(3) ;
                break;
            case R.id.four:  num(4) ;
                break;
            case R.id.five:  num(5) ;
                break;
            case R.id.six:  num(6) ;
                break;
            case R.id.seven:  num(7) ;
                break;
            case R.id.eight:  num(8) ;
                break;
            case R.id.nine:  num(9) ;
                break;


            case R.id.plus:  add() ;
                break;
            case R.id.minus:  sub() ;
                break;
            case R.id.multiply:  multiply() ;
                break;
            case R.id.devide:  divide() ;
                break;
            case R.id.clear: clear();
                break;
            case R.id.result:
                result() ;
                if(error!=1&&flag!=0)
                    output.setText(String.valueOf(result));
                break;
            default:
                break;
        }
        input.setText(strold+act+str);
    }
    public void num(int n){
        str=str+String.valueOf(n);
        flag=1;  //每次输入数字之后就可以计算
    }
    public void add(){
        if(flag!=0)
        {
            check();
            act='+';
            flag=0;
        }
    }
    public void sub(){
        if(flag!=0)
        {
            check();
            act='-';
            flag=0;
        }

    }
    public void multiply(){
        if(flag!=0)
        {
            check();
            act='*';
            flag=0;
        }
    }
    public void divide(){
        if(flag!=0)
        {
            check();
            act='/';
            flag=0;
        }
    }
    public void clear(){
        str=strold="";
        count=0;
        act=' ';
        result=0;
        flag=0;
        input.setText(strold+act+str);
        output.setText("");
    }
    public void check(){
        //count不为0之后这个就可以开始计算
        if(count>=1)
        {
            result();
            str=String.valueOf(result);

        }
        strold=str;
        str="";
        count++;

    }
    public void result(){
        int a,b;
        a=Integer.parseInt(strold);
        b=Integer.parseInt(str);
        if(flag!=0)
        {
            if(b==0&&act=='/') {
                clear();
                Toast.makeText(MainActivity.this, "除数不能为零",
                        Toast.LENGTH_LONG).show();
                error=0;
            }
            result=0;
            if(error!=1){
                switch(act){
                    case '+':
                        result=a+b;
                        break;
                    case '-':
                        result=a-b;
                        break;
                    case '*':
                        result=a*b;
                        break;
                    case '/':
                        result=a/b;
                        break;
                    default:
                        break;
                }
            }
        }
    }


}