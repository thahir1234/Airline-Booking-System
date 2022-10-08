package com.airline.models;

public class Main {
    public static void main(String[] args) {
        SeatModel obj = new SeatModel();
        SeatModel obj2 = new SeatModel();
        obj.mat[0][0]='-';
        System.out.println(obj.mat[0][0]);
        System.out.println(obj2.mat[0][0]);
    }
}
