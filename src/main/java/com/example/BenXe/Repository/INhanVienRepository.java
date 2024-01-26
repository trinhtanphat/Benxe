package com.example.BenXe.Repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BenXe.Model.NhanVien;

import java.util.List;

@Repository
public interface INhanVienRepository extends JpaRepository<NhanVien, Long> {
    default List<NhanVien> findAllNhanViens(Integer pageNo,
                                            Integer pageSize,
                                            String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }

    @Query("""
            SELECT n
            FROM NhanVien n
            LEFT JOIN ChucVu mcv ON n.chucVu.MaChucVu = mcv.MaChucVu
            WHERE n.TenNV LIKE %?1%
                OR n.DiaChi LIKE %?1%
                OR mcv.TenChucVu LIKE %?1%
            """)
    List<NhanVien> searchNhanVien(String keyword);
}