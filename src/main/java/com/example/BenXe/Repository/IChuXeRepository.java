package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.ChuXe;

@Repository
public interface IChuXeRepository extends JpaRepository<ChuXe, Long> {
}