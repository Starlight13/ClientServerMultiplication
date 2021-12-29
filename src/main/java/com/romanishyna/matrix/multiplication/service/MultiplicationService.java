package com.romanishyna.matrix.multiplication.service;

import com.romanishyna.matrix.multiplication.model.MatrixServerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MultiplicationService {

    @Autowired
    @Qualifier(value = "matrix_size500")
    private int[][] matrixSize500;

    @Autowired
    @Qualifier(value = "matrix_size1000")
    private int[][] matrixSize1000;

    @Autowired
    @Qualifier(value = "matrix_size1500")
    private int[][] matrixSize1500;

    @Autowired
    @Qualifier(value = "matrix_size2000")
    private int[][] matrixSize2000;


    public Result multiplication(MatrixServerDTO matrixServerDTO) throws Exception{
        int[][] matrix;
        switch (matrixServerDTO.getMatrixSize()) {
            case 500:
                matrix = matrixSize500;
                break;
            case 1000:
                matrix = matrixSize1000;
                break;
            case 1500:
                matrix = matrixSize1500;
                break;
            default:
                matrix = matrixSize2000;
                break;
        }

        return multiplication(matrix, matrix, matrixServerDTO.getThreadsSize());
    }

    public Result multiplication(int[][] a, int[][] b, int threadsSize) throws Exception {
        Matrix.check(a, b);
        Result result = new Result(Matrix.zeroMatrix(a.length, b[0].length));

        long start = System.currentTimeMillis();
        if (threadsSize <= 0) threadsSize = a.length;
        LinearThread[] linearThread = new LinearThread[threadsSize];

        int rowsPerThread = a.length / threadsSize;
        for (int i = 0; i < threadsSize; i++) {
            linearThread[i] = new LinearThread(b, rowsPerThread, rowsPerThread * i, rowsPerThread * (i + 1));
            for (int l = 0; l < rowsPerThread; l++) {
                linearThread[i].setRowAAndC(a[rowsPerThread * i + l], result.getMatrix()[rowsPerThread * i + l], l);
            }
            linearThread[i].start();
        }
        for (LinearThread thread : linearThread) {
            thread.join();
        }
        long finish = System.currentTimeMillis();
        double t = (finish - start) / 1000.0;
        System.out.println("Time Linear: " + t + " sec.");
        return result;
    }
}
