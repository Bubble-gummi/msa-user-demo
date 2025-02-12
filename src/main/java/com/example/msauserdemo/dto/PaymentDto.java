package com.example.msauserdemo.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PaymentDto {
    private String pdtId;
    private String quantity;
    private String email;

    @Builder
    public PaymentDto(String pdtId, String quantity, String email) {
        this.pdtId = pdtId;
        this.quantity = quantity;
        this.email = email;
    }
}
