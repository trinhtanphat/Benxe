package com.example.BenXe.Repository;

import com.example.BenXe.Model.TaiKhoan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    @Query("SELECT u FROM TaiKhoan u WHERE u.TenDangNhap = ?1")
    TaiKhoan findByUsername(String TenDangNhap);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO LoaiTK (Id, MaLoai) " +
            "VALUES (?1, ?2)", nativeQuery = true)
    void addRoleToUser(Long Id, Long MaLoai);

    @Query("SELECT u.Id FROM TaiKhoan u WHERE u.TenDangNhap = ?1")
    Long getUserIdByUsername(String TenDangNhap);

    @Query(value = "SELECT l.ten_loai FROM LoaiTK l INNER JOIN tai_khoan ur " +
            "ON l.ma_loai = ur.ma_loai WHERE ur.Id = ?1", nativeQuery = true)
    String getRolesOfUser(Long Id);
}