package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaNV;

    @Column(name = "TenNV")
    private String TenNV;

    @Column(name = "namsinh")
    private Long namsinh;
    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "Email")
    private String Email;

    @Column(name = "SDT")
    private String SDT;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaPhongBan")
    private PhongBan phongBan;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaChucVu")
    private ChucVu chucVu;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "Id")
    private TaiKhoan taiKhoan;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private List<PhieuDatVe> phieuDatVes;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    public NhanVien() {
    }

    public NhanVien(Long maNV, String tenNV, Long namSinh, String diaChi, String email, String SDT, PhongBan phongBan, ChucVu chucVu, TaiKhoan taiKhoan, List<PhieuDatVe> phieuDatVes, List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        MaNV = maNV;
        TenNV = tenNV;
        namsinh = namSinh;
        DiaChi = diaChi;
        Email = email;
        this.SDT = SDT;
        this.phongBan = phongBan;
        this.chucVu = chucVu;
        this.taiKhoan = taiKhoan;
        this.phieuDatVes = phieuDatVes;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public Long getMaNV() {
        return MaNV;
    }

    public Long getId() {
        return taiKhoan.getId();
    }

    public void setMaNV(Long maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public Long getNamSinh() {
        return namsinh;
    }

    public void setNamSinh(Long namSinh) {
        namsinh = namSinh;
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

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
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

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }
}
