package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "HoaDon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaHoaDon;

    @Column(name = "ThongTinChiTiet")
    private String ThongTinChiTiet;

    @Column(name = "ChiPhi")
    private Double ChiPhi;

    @Column(name = "AnhHoaDon")
    private String AnhHoaDon;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaChuyenXe")
    private ChuyenXe chuyenXe;

    public HoaDon() {
    }

    public HoaDon(Long maHoaDon, String thongTinChiTiet, Double chiPhi, String anhHoaDon, ChuyenXe chuyenXe) {
        MaHoaDon = maHoaDon;
        ThongTinChiTiet = thongTinChiTiet;
        ChiPhi = chiPhi;
        AnhHoaDon = anhHoaDon;
        this.chuyenXe = chuyenXe;
    }

    public Long getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(Long maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public String getThongTinChiTiet() {
        return ThongTinChiTiet;
    }

    public void setThongTinChiTiet(String thongTinChiTiet) {
        ThongTinChiTiet = thongTinChiTiet;
    }

    public Double getChiPhi() {
        return ChiPhi;
    }

    public void setChiPhi(Double chiPhi) {
        ChiPhi = chiPhi;
    }

    public String getAnhHoaDon() {
        return AnhHoaDon;
    }

    public void setAnhHoaDon(String anhHoaDon) {
        AnhHoaDon = anhHoaDon;
    }

    public ChuyenXe getChuyenXe() {
        return chuyenXe;
    }

    public void setChuyenXe(ChuyenXe chuyenXe) {
        this.chuyenXe = chuyenXe;
    }
}
