package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "TenDangNhap")
    private String TenDangNhap;

    @Column(name = "MatKhau")
    private String MatKhau;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaLoai")
    private LoaiTK loaitk;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private List<ChuXe> chuXes;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private List<KhachHang> khachHangs;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private List<NhanVien> nhanViens;

    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes = new ArrayList<>();

    public TaiKhoan() {
    }

    public TaiKhoan(Long id, String tenDangNhap, String matKhau, LoaiTK loaitk, List<ChuXe> chuXes, List<KhachHang> khachHangs, List<NhanVien> nhanViens, List<Xe> xes) {
        Id = id;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        this.loaitk = loaitk;
        this.chuXes = chuXes;
        this.khachHangs = khachHangs;
        this.nhanViens = nhanViens;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public LoaiTK getLoaitk() {
        return loaitk;
    }

    public void setLoaitk(LoaiTK loaitk) {
        this.loaitk = loaitk;
    }

    public List<ChuXe> getChuXes() {
        return chuXes;
    }

    public void setChuXes(List<ChuXe> chuXes) {
        this.chuXes = chuXes;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(List<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    public List<NhanVien> getNhanViens() {
        return nhanViens;
    }

    public void setNhanViens(List<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }
}
