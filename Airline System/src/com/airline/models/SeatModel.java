package com.airline.models;

public class SeatModel {
    final int ROW=6;
    final int COLUMN = 20;
    public char[][] mat  = new char[ROW][COLUMN];

    {
        for(int i=0;i<ROW;i++)
        {
            for(int j=0;j<COLUMN;j++)
            {
                mat[i][j]='+';
            }
        }
    }
}
