package com.biologix.v1.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {
    public String message;
    public Boolean status;
}
