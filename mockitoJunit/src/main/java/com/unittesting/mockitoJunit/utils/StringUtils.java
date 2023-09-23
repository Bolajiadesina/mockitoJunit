package com.unittesting.mockitoJunit.utils;

public class StringUtils {
    

    public static boolean isEmptyBlank(String text) {

        return text == null || text.isBlank() || text.isEmpty();
    }



       public static void main(String[] args) {
        
       
           System.out.println(isEmptyBlank("word")); ;
        }
}
