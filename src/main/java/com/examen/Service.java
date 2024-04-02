package com.examen;

import java.util.ArrayList;
import java.util.List;

public class Service {
    
    public static List<String> factores(String value){
        List<String> factores = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(value); i++) {
            if(Integer.parseInt(value) % i == 0){
                factores.add(String.valueOf(i));
            }
        }

        return factores;
    }


    public static List<String> primos(String value){
        List<String> primos = new ArrayList<>();

        if(factores(value).size() == 2){
            primos.add(value);
            for (int i = Integer.parseInt(value)-1; i >= 2; i--) {

                if(factores(String.valueOf(i)).size() == 2){
                    primos.add(String.valueOf(i));
                }
            }
        }

        return primos;
    }
    
}
