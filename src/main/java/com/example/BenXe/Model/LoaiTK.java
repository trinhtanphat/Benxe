package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "LoaiTK")
public class LoaiTK {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaLoai;

    @Column(name = "TenLoai")
    private String TenLoai;

    @Column(name = "MoTa")
    private String MoTa;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "loaitk", cascade = CascadeType.ALL)
    private List<TaiKhoan> TaiKhoans;

    public LoaiTK() {
    }

    public LoaiTK(Long maLoai, String tenLoai, String moTa, List<TaiKhoan> taiKhoans) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        MoTa = moTa;
        TaiKhoans = taiKhoans;
    }

    public Long getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(Long maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public List<TaiKhoan> getTaiKhoans() {
        return TaiKhoans;
    }

    public void setTaiKhoans(List<TaiKhoan> taiKhoans) {
        TaiKhoans = taiKhoans;
    }
}
