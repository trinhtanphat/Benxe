package com.example.BenXe.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.ChuyenXe;

@Repository
public interface IChuyenXeRepository extends JpaRepository<ChuyenXe, Long> {
    @Query("SELECT u FROM ChuyenXe u WHERE u.NgayChay = ?1")
    List<ChuyenXe> findAllbyNgayChay(LocalDate ngayChay);
}