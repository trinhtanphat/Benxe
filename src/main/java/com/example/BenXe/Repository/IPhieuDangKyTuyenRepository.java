package com.example.BenXe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.PhieuDangKyTuyen;

@Repository
public interface IPhieuDangKyTuyenRepository extends JpaRepository<PhieuDangKyTuyen, Long> {
    @Query("SELECT u FROM PhieuDangKyTuyen u WHERE u.PhieuDangKyTuyen =?1")
    PhieuDangKyTuyen FindPdktById(Long id);
}