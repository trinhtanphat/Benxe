package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaKH;

    @Column(name = "TenKH")
    private String TenKH;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "Email")
    private String Email;

    @Column(name = "SDT")
    private String SDT;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "Id")
    private TaiKhoan taiKhoan;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<PhieuDatVe> phieuDatVes;

    public KhachHang() {
    }

    public KhachHang(Long maKH, String tenKH, String diaChi, String email, String SDT, TaiKhoan taiKhoan, List<PhieuDatVe> phieuDatVes) {
        MaKH = maKH;
        TenKH = tenKH;
        DiaChi = diaChi;
        Email = email;
        this.SDT = SDT;
        this.taiKhoan = taiKhoan;
        this.phieuDatVes = phieuDatVes;
    }

    public Long getMaKH() {
        return MaKH;
    }

    public void setMaKH(Long maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public List<PhieuDatVe> getPhieuDatVes() {
        return phieuDatVes;
    }

    public void setPhieuDatVes(List<PhieuDatVe> phieuDatVes) {
        this.phieuDatVes = phieuDatVes;
    }
}
