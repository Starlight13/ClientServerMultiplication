package com.romanishyna.matrix.multiplication.config;

import com.romanishyna.matrix.multiplication.service.Matrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MatrixCreation {

    @Bean(name = "matrix_size500")
    @Scope("singleton")
    public int[][] createMatrix500() {
        return Matrix.testMatrix(500, 500);
    }

    @Bean(name = "matrix_size1000")
    @Scope("singleton")
    public int[][] createMatrix1000() {
        return Matrix.testMatrix(1000, 1000);
    }

    @Bean(name = "matrix_size1500")
    @Scope("singleton")
    public int[][] createMatrix1500() {
        return Matrix.testMatrix(1500, 1500);
    }

    @Bean(name = "matrix_size2000")
    @Scope("singleton")
    public int[][] createMatrix2000() {
        return Matrix.testMatrix(2000, 2000);
    }
}
