package com.example.BenXe.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long chuyenXeId;
    private String diemDi;
    private String diemDen;
    private Double price;
    private int quantity;
}