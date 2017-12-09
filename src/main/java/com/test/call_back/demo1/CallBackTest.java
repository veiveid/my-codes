package com.test.call_back.demo1;

/**
 * Created by vivi on 2017/12/9.
 */

interface WindowListener{
    void whenOpen();//当打开的时候
}

class Window{
    private WindowListener windowListener;

    public Window registListener(WindowListener windowListener){
        this.windowListener = windowListener;
        return this;
    }

    //打开窗户
    public void open(){
        if(null != windowListener)
            windowListener.whenOpen();//注册监听
    }

}

public class CallBackTest {
    public static void main(String[] args) {
        Window window = new Window();
        window.registListener(new WindowListener() {
            @Override
            public void whenOpen() {
                System.out.println("window is opening");//窗户打开了，我要做某某某事情了
            }
        }).open();
    }
}
