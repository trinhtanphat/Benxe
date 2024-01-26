package com.example.BenXe.Repository;

import com.example.BenXe.Model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKhachHangRepository extends JpaRepository<KhachHang, Long> {
    @Query("SELECT u FROM KhachHang u WHERE u.MaKH = ?1")
    KhachHang findKH(String Id);
}
