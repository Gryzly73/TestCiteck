package com.company;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //первое
        System.out.println(first(new ArrayList<Integer>(Arrays.asList(7,2,4,5,5,4,6,6,7,0,7,4,0,5))) + "\n");

        //второе
        String reg = "[([(lkk(jk)po)]po)]";
        // для наглядности
        System.out.println(reg);
        System.out.println(second(reg) + "\n");

        //третье, число - int, без проверки ввода
        System.out.println(third(9007280));
    }

    private static Set<Map.Entry<Integer, Integer>>  first(List<Integer> integerList){

        Map<Integer, Integer> map = new HashMap<>();
        integerList.stream().forEach(t->{
            if (!map.containsKey(t)){
                map.put(t, 1);
            }
           else {
                map.computeIfPresent(t, (key, value) -> ++value);
            }
        });

        SortedSet<Map.Entry<Integer, Integer>> sortedSet =
                new TreeSet<>(Comparator.comparingInt((ToIntFunction<Map.Entry<Integer, Integer>>) Map.Entry::getValue).thenComparingInt(Map.Entry::getKey));

         sortedSet.addAll(map.entrySet());
         return sortedSet;
    }


    private static boolean second(String regString) {

        String s = regString.replaceAll("[^(\\[|\\)|\\]|\\))]", "");
        System.out.println(s);
       return newString(s).length() == 0;
    }

    // рекурсивно удаляем "()" "[]" пока строка не перестанет укорачиваться, потом проверим размер на 0
    private static String newString(String s){
        int length = s.length();

        String s1 = s
                    .replaceAll("\\(\\)", "")
                    .replaceAll("\\[]", "")
                     ;

        if (s1.length() != length) return newString(s1);
        else  return s1;
    }

    private static int third(Integer number){

        StringBuilder stringBuilder = new StringBuilder(number.toString());
        int index = stringBuilder.reverse().indexOf("0");
        if(index >= 0){
            double pow = Math.pow(10, index);
           return number + (int)pow;
        }
        return number;
    }
}
