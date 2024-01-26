package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.Xe;

@Repository
public interface IXeRepository extends JpaRepository<Xe, Long> {
}