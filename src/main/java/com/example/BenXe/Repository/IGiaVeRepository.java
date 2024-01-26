package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BenXe.Model.GiaVe;
import com.example.BenXe.Model.LoaiXe;
import com.example.BenXe.Model.Tuyen;


@Repository
public interface IGiaVeRepository extends JpaRepository<GiaVe, Long> {
}