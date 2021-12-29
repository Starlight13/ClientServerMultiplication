package com.romanishyna.matrix.multiplication.web;

import com.romanishyna.matrix.multiplication.model.MatrixClientDTO;
import com.romanishyna.matrix.multiplication.model.MatrixServerDTO;
import com.romanishyna.matrix.multiplication.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplicationController {

    @Autowired
    private MultiplicationService multiplicationService;

    @PostMapping("/multiplyClient")
    public ResponseEntity multiplyClient(@RequestBody MatrixClientDTO matrixClientDTO) throws Exception {
        multiplicationService.multiplication(matrixClientDTO.getMatrixA(), matrixClientDTO.getMatrixB(), matrixClientDTO.getThreadsSize());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/multiplyServer")
    public ResponseEntity multiplyServer(@RequestBody MatrixServerDTO matrixServerDTO) throws Exception {
        multiplicationService.multiplication(matrixServerDTO);
        return ResponseEntity.ok().build();
    }
}
