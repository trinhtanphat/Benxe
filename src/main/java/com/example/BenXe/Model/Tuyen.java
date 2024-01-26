package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Tuyen")
public class Tuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaTuyen;
    @Column(name = "DiemDi")
    private String DiemDi;
    @Column(name = "DiemDen")
    private String DiemDen;
    @Column(name = "ThoiGianXuatBen")
    private LocalTime ThoiGianXuatBen;
    @Column(name = "ThoiGianVeBen")
    private LocalTime ThoiGianVeBen;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "tuyen", cascade = CascadeType.ALL)
    private List<GiaVe> giaVes;

    public Tuyen() {
    }

    public Tuyen(Long MaTuyen, String DiemDi, String DiemDen, LocalTime ThoiGianXuatBen, LocalTime ThoiGianVeBen, List<ChuyenXe> chuyenXes, List<PhieuDangKyTuyen> phieuDangKyTuyens, List<GiaVe> giaVes) {
        this.MaTuyen = MaTuyen;
        this.DiemDi = DiemDi;
        this.DiemDen = DiemDen;
        this.ThoiGianXuatBen = ThoiGianXuatBen;
        this.ThoiGianVeBen = ThoiGianVeBen;
        this.chuyenXes = chuyenXes;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
        this.giaVes = giaVes;
    }

    public Long getMaTuyen() {
        return this.MaTuyen;
    }

    public void setMaTuyen(Long MaTuyen) {
        this.MaTuyen = MaTuyen;
    }

    public String getDiemDi() {
        return this.DiemDi;
    }

    public void setDiemDi(String DiemDi) {
        this.DiemDi = DiemDi;
    }

    public String getDiemDen() {
        return this.DiemDen;
    }

    public void setDiemDen(String DiemDen) {
        this.DiemDen = DiemDen;
    }

    public LocalTime getThoiGianXuatBen() {
        return this.ThoiGianXuatBen;
    }

    public void setThoiGianXuatBen(LocalTime ThoiGianXuatBen) {
        this.ThoiGianXuatBen = ThoiGianXuatBen;
    }

    public LocalTime getThoiGianVeBen() {
        return this.ThoiGianVeBen;
    }

    public void setThoiGianVeBen(LocalTime ThoiGianVeBen) {
        this.ThoiGianVeBen = ThoiGianVeBen;
    }

    public List<ChuyenXe> getChuyenXes() {
        return this.chuyenXes;
    }

    public void setChuyenXes(List<ChuyenXe> chuyenXes) {
        this.chuyenXes = chuyenXes;
    }

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return this.phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public List<GiaVe> getGiaVes() {
        return this.giaVes;
    }

    public void setGiaVes(List<GiaVe> giaVes) {
        this.giaVes = giaVes;
    }

    public Tuyen MaTuyen(Long MaTuyen) {
        setMaTuyen(MaTuyen);
        return this;
    }

    public Tuyen DiemDi(String DiemDi) {
        setDiemDi(DiemDi);
        return this;
    }

    public Tuyen DiemDen(String DiemDen) {
        setDiemDen(DiemDen);
        return this;
    }

    public Tuyen ThoiGianXuatBen(LocalTime ThoiGianXuatBen) {
        setThoiGianXuatBen(ThoiGianXuatBen);
        return this;
    }

    public Tuyen ThoiGianVeBen(LocalTime ThoiGianVeBen) {
        setThoiGianVeBen(ThoiGianVeBen);
        return this;
    }

    public Tuyen chuyenXes(List<ChuyenXe> chuyenXes) {
        setChuyenXes(chuyenXes);
        return this;
    }

    public Tuyen phieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        setPhieuDangKyTuyens(phieuDangKyTuyens);
        return this;
    }

    public Tuyen giaVes(List<GiaVe> giaVes) {
        setGiaVes(giaVes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tuyen)) {
            return false;
        }
        Tuyen tuyen = (Tuyen) o;
        return Objects.equals(MaTuyen, tuyen.MaTuyen) && Objects.equals(DiemDi, tuyen.DiemDi) && Objects.equals(DiemDen, tuyen.DiemDen) && Objects.equals(ThoiGianXuatBen, tuyen.ThoiGianXuatBen) && Objects.equals(ThoiGianVeBen, tuyen.ThoiGianVeBen) && Objects.equals(chuyenXes, tuyen.chuyenXes) && Objects.equals(phieuDangKyTuyens, tuyen.phieuDangKyTuyens) && Objects.equals(giaVes, tuyen.giaVes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaTuyen, DiemDi, DiemDen, ThoiGianXuatBen, ThoiGianVeBen, chuyenXes, phieuDangKyTuyens, giaVes);
    }

    @Override
    public String toString() {
        return "{" +
            " MaTuyen='" + getMaTuyen() + "'" +
            ", DiemDi='" + getDiemDi() + "'" +
            ", DiemDen='" + getDiemDen() + "'" +
            ", ThoiGianXuatBen='" + getThoiGianXuatBen() + "'" +
            ", ThoiGianVeBen='" + getThoiGianVeBen() + "'" +
            ", chuyenXes='" + getChuyenXes() + "'" +
            ", phieuDangKyTuyens='" + getPhieuDangKyTuyens() + "'" +
            ", giaVes='" + getGiaVes() + "'" +
            "}";
    }
    
    
}
