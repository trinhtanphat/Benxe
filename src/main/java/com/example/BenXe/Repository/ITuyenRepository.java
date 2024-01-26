package com.example.BenXe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.Tuyen;

@Repository
public interface ITuyenRepository extends JpaRepository<Tuyen, Long> {
    @Query("SELECT DISTINCT u.DiemDen FROM Tuyen u")
    List<String> getDiemDen();

    @Query("SELECT DISTINCT u.DiemDi FROM Tuyen u")
    List<String> getDiemDi();
}