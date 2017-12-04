package com.test.day_171129.demo1;

public class TrafficLight {
    Color color = Color.BLACK;

    public void change(){
        switch (color){
            case RED:
                color = Color.GREEN;
                break;
            case YELLOW:
                color = Color.RED;
                break;
            case GREEN:
                color = Color.YELLOW;
                break;
        }
    }
}