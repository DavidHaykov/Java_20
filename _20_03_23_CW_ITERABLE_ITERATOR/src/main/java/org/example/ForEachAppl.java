package org.example;

public class ForEachAppl {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        int sum = 0;
        for(int i = 0; i< array.length;i++){
            sum += array[i];
        }
        System.out.println(sum);
        sum = 0;
        for(int item : array){
            sum += item;
        }
        System.out.println(sum);
    }
}