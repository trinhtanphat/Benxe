package com.example.BenXe.daos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Cart {
    //danh sch sản phẩm trong giỏ hàng
    private List<Item> cartItems = new ArrayList<>();

    //hàm thêm item vào giỏ hàng
    public void addItems(Item item) {
        //Kiểm tra sản phẩm có trong giỏ hàng chưa
        boolean isExist = cartItems.stream()
                .filter(i -> Objects.equals(i.getChuyenXeId(), item.getChuyenXeId()))
                .findFirst()
                //Nếu tìm thấy thì tăng số lượng lên
                .map(i -> {
                    i.setQuantity(i.getQuantity() +
                            item.getQuantity());
                    return true;
                })
                .orElse(false);
        if (!isExist) {
            cartItems.add(item);
        }
    }

    public int getItemsQuantity() {
        return  cartItems.size();
    }

    public void removeItems(Long chuyenXeId) {
        cartItems.removeIf(item -> Objects.equals(item.getChuyenXeId(), chuyenXeId));
    }

    //Cập nhật số lượng giỏ hàng
    public void updateItems(Long chuyenXeId, int quantity) {
        cartItems.stream()
                .filter(item -> Objects.equals(item.getChuyenXeId(), chuyenXeId))
                .forEach(item -> item.setQuantity(quantity));
    }
}