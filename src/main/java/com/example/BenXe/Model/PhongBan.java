package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "PhongBan")
public class PhongBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaPhongBan;

    @Column(name = "TenPhongBan")
    private String TenPhongBan;

    @Column(name = "BoPhanPhuTrach")
    private String BoPhanPhuTrach;

    @Column(name = "MoTa")
    private String MoTa;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "phongBan", cascade = CascadeType.ALL)
    private List<NhanVien> nhanViens;

    public Long getMaPhongBan() {
        return MaPhongBan;
    }

    public void setMaPhongBan(Long maPhongBan) {
        MaPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return TenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        TenPhongBan = tenPhongBan;
    }

    public String getBoPhanPhuTrach() {
        return BoPhanPhuTrach;
    }

    public void setBoPhanPhuTrach(String boPhanPhuTrach) {
        BoPhanPhuTrach = boPhanPhuTrach;
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

    public PhongBan() {
    }

    public PhongBan(Long maPhongBan, String tenPhongBan, String boPhanPhuTrach, String moTa, List<NhanVien> nhanViens) {
        MaPhongBan = maPhongBan;
        TenPhongBan = tenPhongBan;
        BoPhanPhuTrach = boPhanPhuTrach;
        MoTa = moTa;
        this.nhanViens = nhanViens;
    }
}
