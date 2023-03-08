package com.tekion.GameOfCricket.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO{

    boolean success;
    String error;

    public ResponseDTO(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
