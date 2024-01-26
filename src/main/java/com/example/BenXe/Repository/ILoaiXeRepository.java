package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.LoaiXe;

@Repository
public interface ILoaiXeRepository extends JpaRepository<LoaiXe, Long> {
}