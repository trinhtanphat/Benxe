package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PhieuDangKyTuyen")
public class PhieuDangKyTuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PhieuDangKyTuyen;

    @Column(name = "ThoiGianBatDauVanHanh")
    private LocalDate ThoiGianBatDauVanHanh;

    @Column(name = "ThoiGianNopPhieu")
    private LocalDate ThoiGianNopPhieu;

    @Column(name = "TrangThai")
    private Boolean TrangThai;
    
    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaNV")
    private NhanVien nhanVien;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaCX")
    private ChuXe chuXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaTuyen")
    private Tuyen tuyen;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaLX")
    private LoaiXe loaiXe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "BKS")
    private Xe xe;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "giaveId")
    private GiaVe giaVe;


    public PhieuDangKyTuyen() {
    }

    public PhieuDangKyTuyen(Long PhieuDangKyTuyen, LocalDate ThoiGianBatDauVanHanh, LocalDate ThoiGianNopPhieu, Boolean TrangThai, NhanVien nhanVien, ChuXe chuXe, Tuyen tuyen, LoaiXe loaiXe, Xe xe, GiaVe giaVe) {
        this.PhieuDangKyTuyen = PhieuDangKyTuyen;
        this.ThoiGianBatDauVanHanh = ThoiGianBatDauVanHanh;
        this.ThoiGianNopPhieu = ThoiGianNopPhieu;
        this.TrangThai = TrangThai;
        this.nhanVien = nhanVien;
        this.chuXe = chuXe;
        this.tuyen = tuyen;
        this.loaiXe = loaiXe;
        this.xe = xe;
        this.giaVe = giaVe;
    }

    public Long getPhieuDangKyTuyen() {
        return this.PhieuDangKyTuyen;
    }

    public void setPhieuDangKyTuyen(Long PhieuDangKyTuyen) {
        this.PhieuDangKyTuyen = PhieuDangKyTuyen;
    }

    public LocalDate getThoiGianBatDauVanHanh() {
        return this.ThoiGianBatDauVanHanh;
    }

    public void setThoiGianBatDauVanHanh(LocalDate ThoiGianBatDauVanHanh) {
        this.ThoiGianBatDauVanHanh = ThoiGianBatDauVanHanh;
    }

    public LocalDate getThoiGianNopPhieu() {
        return this.ThoiGianNopPhieu;
    }

    public void setThoiGianNopPhieu(LocalDate ThoiGianNopPhieu) {
        this.ThoiGianNopPhieu = ThoiGianNopPhieu;
    }

    public Boolean isTrangThai() {
        return this.TrangThai;
    }

    public Boolean getTrangThai() {
        return this.TrangThai;
    }

    public void setTrangThai(Boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ChuXe getChuXe() {
        return this.chuXe;
    }

    public void setChuXe(ChuXe chuXe) {
        this.chuXe = chuXe;
    }

    public Tuyen getTuyen() {
        return this.tuyen;
    }

    public void setTuyen(Tuyen tuyen) {
        this.tuyen = tuyen;
    }

    public LoaiXe getLoaiXe() {
        return this.loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public Xe getXe() {
        return this.xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
    }

    public GiaVe getGiaVe() {
        return this.giaVe;
    }

    public void setGiaVe(GiaVe giaVe) {
        this.giaVe = giaVe;
    }

    public PhieuDangKyTuyen PhieuDangKyTuyen(Long PhieuDangKyTuyen) {
        setPhieuDangKyTuyen(PhieuDangKyTuyen);
        return this;
    }

    public PhieuDangKyTuyen ThoiGianBatDauVanHanh(LocalDate ThoiGianBatDauVanHanh) {
        setThoiGianBatDauVanHanh(ThoiGianBatDauVanHanh);
        return this;
    }

    public PhieuDangKyTuyen ThoiGianNopPhieu(LocalDate ThoiGianNopPhieu) {
        setThoiGianNopPhieu(ThoiGianNopPhieu);
        return this;
    }

    public PhieuDangKyTuyen TrangThai(Boolean TrangThai) {
        setTrangThai(TrangThai);
        return this;
    }

    public PhieuDangKyTuyen nhanVien(NhanVien nhanVien) {
        setNhanVien(nhanVien);
        return this;
    }

    public PhieuDangKyTuyen chuXe(ChuXe chuXe) {
        setChuXe(chuXe);
        return this;
    }

    public PhieuDangKyTuyen tuyen(Tuyen tuyen) {
        setTuyen(tuyen);
        return this;
    }

    public PhieuDangKyTuyen loaiXe(LoaiXe loaiXe) {
        setLoaiXe(loaiXe);
        return this;
    }

    public PhieuDangKyTuyen xe(Xe xe) {
        setXe(xe);
        return this;
    }

    public PhieuDangKyTuyen giaVe(GiaVe giaVe) {
        setGiaVe(giaVe);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhieuDangKyTuyen)) {
            return false;
        }
        PhieuDangKyTuyen phieuDangKyTuyen = (PhieuDangKyTuyen) o;
        return Objects.equals(PhieuDangKyTuyen, phieuDangKyTuyen.PhieuDangKyTuyen) && Objects.equals(ThoiGianBatDauVanHanh, phieuDangKyTuyen.ThoiGianBatDauVanHanh) && Objects.equals(ThoiGianNopPhieu, phieuDangKyTuyen.ThoiGianNopPhieu) && Objects.equals(TrangThai, phieuDangKyTuyen.TrangThai) && Objects.equals(nhanVien, phieuDangKyTuyen.nhanVien) && Objects.equals(chuXe, phieuDangKyTuyen.chuXe) && Objects.equals(tuyen, phieuDangKyTuyen.tuyen) && Objects.equals(loaiXe, phieuDangKyTuyen.loaiXe) && Objects.equals(xe, phieuDangKyTuyen.xe) && Objects.equals(giaVe, phieuDangKyTuyen.giaVe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PhieuDangKyTuyen, ThoiGianBatDauVanHanh, ThoiGianNopPhieu, TrangThai, nhanVien, chuXe, tuyen, loaiXe, xe, giaVe);
    }

    @Override
    public String toString() {
        return "{" +
            " PhieuDangKyTuyen='" + getPhieuDangKyTuyen() + "'" +
            ", ThoiGianBatDauVanHanh='" + getThoiGianBatDauVanHanh() + "'" +
            ", ThoiGianNopPhieu='" + getThoiGianNopPhieu() + "'" +
            ", TrangThai='" + isTrangThai() + "'" +
            ", nhanVien='" + getNhanVien() + "'" +
            ", chuXe='" + getChuXe() + "'" +
            ", tuyen='" + getTuyen() + "'" +
            ", loaiXe='" + getLoaiXe() + "'" +
            ", xe='" + getXe() + "'" +
            ", giaVe='" + getGiaVe() + "'" +
            "}";
    }

}
