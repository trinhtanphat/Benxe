package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "LoaiXe")
public class LoaiXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaLX;

    @Column(name = "TenLoaiXe")
    private String TenLoaiXe;

    @Column(name = "MoTa")
    private String MoTa;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "loaiXe", cascade = CascadeType.ALL)
    private List<Xe> xes;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "loaiXe", cascade = CascadeType.ALL)
    private List<GiaVe> giaVes;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "loaiXe", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "loaiXe", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;

    public LoaiXe() {
    }

    public LoaiXe(Long maLX, String tenLoaiXe, String moTa, List<Xe> xes, List<GiaVe> giaVes, List<PhieuDangKyTuyen> phieuDangKyTuyens, List<ChuyenXe> chuyenXes) {
        MaLX = maLX;
        TenLoaiXe = tenLoaiXe;
        MoTa = moTa;
        this.xes = xes;
        this.giaVes = giaVes;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
        this.chuyenXes = chuyenXes;
    }

    public Long getMaLX() {
        return MaLX;
    }

    public void setMaLX(Long maLX) {
        MaLX = maLX;
    }

    public String getTenLoaiXe() {
        return TenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        TenLoaiXe = tenLoaiXe;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public List<Xe> getXes() {
        return xes;
    }

    public void setXes(List<Xe> xes) {
        this.xes = xes;
    }

    public List<GiaVe> getGiaVes() {
        return giaVes;
    }

    public void setGiaVes(List<GiaVe> giaVes) {
        this.giaVes = giaVes;
    }

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public List<ChuyenXe> getChuyenXes() {
        return chuyenXes;
    }

    public void setChuyenXes(List<ChuyenXe> chuyenXes) {
        this.chuyenXes = chuyenXes;
    }
}

