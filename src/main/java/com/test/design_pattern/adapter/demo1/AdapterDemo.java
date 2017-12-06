package com.test.design_pattern.adapter.demo1;

/**
 * 在Java中，一个子类实现了一个接口，则肯定在子类中覆写此接口中全部抽象方法。那么这样一来，
 如果一个接口中提供的抽象方法过多，而且没有必要全部实现的话，肯定很浪费，此时就需要一个中间过渡，
 但是此过渡又不希望被直接调用，所以将此过渡器定义成抽象类更合适，即：一个接口首先被一个抽象类（此抽象类成为适配器类）继承，
 并在此抽象类中实现若干方法（方法体为空），则以后的子类直接继承此抽象类（适配器），就可以有选择的覆写所需方法了。
 */
interface Window{ // 定义Window接口，表示窗口操作
    public void open() ;    // 打开
    public void close() ;    // 关闭
    public void activated() ;    // 窗口活动
    public void iconified() ;    // 窗口最小化
    public void deiconified();// 窗口恢复大小
}

class WindowAdapter implements Window{

    @Override
    public void open() {}

    @Override
    public void close() {}

    @Override
    public void activated() {}

    @Override
    public void iconified() {}

    @Override
    public void deiconified() {}
}
class WindowImpl extends WindowAdapter{
    public void open(){
        System.out.println("open");
    }

    public void close(){
        System.out.println("close");
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        Window window = new WindowImpl();
        window.open();
        window.close();
    }

}