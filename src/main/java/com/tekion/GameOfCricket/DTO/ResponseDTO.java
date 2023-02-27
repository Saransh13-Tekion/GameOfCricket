package com.tekion.GameOfCricket.DTO;

import lombok.Data;

@Data
public class ResponseDTO{

    boolean success;
    String error;

    public ResponseDTO() {
    }

    public ResponseDTO(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
