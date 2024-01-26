package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.BaiDauXe;

@Repository
public interface IBaiDauXeRepository extends JpaRepository<BaiDauXe, Long> {
}