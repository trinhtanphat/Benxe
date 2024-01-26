package com.example.BenXe.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "ChucVu")
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaChucVu;

    @Column(name = "TenChucVu")
    private String TenChucVu;

    @Column(name = "MoTa")
    private String MoTa;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "chucVu", cascade = CascadeType.ALL)
    private List<NhanVien> nhanViens;

    public ChucVu(Long maChucVu, String tenChucVu, String moTa, List<NhanVien> nhanViens) {
        MaChucVu = maChucVu;
        TenChucVu = tenChucVu;
        MoTa = moTa;
        this.nhanViens = nhanViens;
    }

    public ChucVu() {
    }

    public Long getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(Long maChucVu) {
        MaChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        TenChucVu = tenChucVu;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public List<NhanVien> getNhanViens() {
        return nhanViens;
    }

    public void setNhanViens(List<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }
}
