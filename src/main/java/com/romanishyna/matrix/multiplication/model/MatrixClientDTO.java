package com.romanishyna.matrix.multiplication.model;

import lombok.Data;

@Data
public class MatrixClientDTO {

    private int[][] matrixA;
    private int[][] matrixB;
    private int threadsSize;
}
