package com.test.lambda.demo1;

import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
    }
}