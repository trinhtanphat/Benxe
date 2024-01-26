package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ChuXe")
public class ChuXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaCX;

    @Column(name = "TenChuXe")
    private String TenChuXe;

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
    @OneToMany(mappedBy = "chuXe", cascade = CascadeType.ALL)
    private List<Xe> xes;

    public ChuXe() {
    }

    public ChuXe(Long MaCX, String TenChuXe, String DiaChi, String Email, String SDT, TaiKhoan taiKhoan, List<PhieuDangKyTuyen> phieuDangKyTuyens, List<Xe> xes) {
        this.MaCX = MaCX;
        this.TenChuXe = TenChuXe;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SDT = SDT;
        this.taiKhoan = taiKhoan;
        this.xes = xes;
    }

    public Long getMaCX() {
        return this.MaCX;
    }

    public void setMaCX(Long MaCX) {
        this.MaCX = MaCX;
    }

    public String getTenChuXe() {
        return this.TenChuXe;
    }

    public void setTenChuXe(String TenChuXe) {
        this.TenChuXe = TenChuXe;
    }

    public String getDiaChi() {
        return this.DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return this.SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public TaiKhoan getTaiKhoan() {
        return this.taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public List<Xe> getXes() {
        return this.xes;
    }

    public void setXes(List<Xe> xes) {
        this.xes = xes;
    }

    public ChuXe MaCX(Long MaCX) {
        setMaCX(MaCX);
        return this;
    }

    public ChuXe TenChuXe(String TenChuXe) {
        setTenChuXe(TenChuXe);
        return this;
    }

    public ChuXe DiaChi(String DiaChi) {
        setDiaChi(DiaChi);
        return this;
    }

    public ChuXe Email(String Email) {
        setEmail(Email);
        return this;
    }

    public ChuXe SDT(String SDT) {
        setSDT(SDT);
        return this;
    }

    public ChuXe taiKhoan(TaiKhoan taiKhoan) {
        setTaiKhoan(taiKhoan);
        return this;
    }

    public ChuXe xes(List<Xe> xes) {
        setXes(xes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChuXe)) {
            return false;
        }
        ChuXe chuXe = (ChuXe) o;
        return Objects.equals(MaCX, chuXe.MaCX) && Objects.equals(TenChuXe, chuXe.TenChuXe) && Objects.equals(DiaChi, chuXe.DiaChi) && Objects.equals(Email, chuXe.Email) && Objects.equals(SDT, chuXe.SDT) && Objects.equals(taiKhoan, chuXe.taiKhoan) && Objects.equals(xes, chuXe.xes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaCX, TenChuXe, DiaChi, Email, SDT, taiKhoan, xes);
    }

    @Override
    public String toString() {
        return "{" +
            " MaCX='" + getMaCX() + "'" +
            ", TenChuXe='" + getTenChuXe() + "'" +
            ", DiaChi='" + getDiaChi() + "'" +
            ", Email='" + getEmail() + "'" +
            ", SDT='" + getSDT() + "'" +
            ", taiKhoan='" + getTaiKhoan() + "'" +
            ", xes='" + getXes() + "'" +
            "}";
    }
}