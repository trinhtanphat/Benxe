package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ChuyenXe")
public class ChuyenXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaChuyenXe;

    @Column(name = "SoViTriConTrong")
    private Long SoViTriConTrong;

    @Column(name = "GiaTriChuyenXe")
    private Double GiaTriChuyenXe;

    @Column(name = "NgayChay")
    private LocalDate NgayChay;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "BKS")
    private Xe xe;

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
    @JoinColumn(name = "giave_id")
    private GiaVe giaVe;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "chuyenXe", cascade = CascadeType.ALL)
    private List<HoaDon> hoaDons;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "chuyenXe", cascade = CascadeType.ALL)
    private List<PhieuDatVe> phieuDatVes;

    @ManyToOne
    @JoinColumn(name = "ma_KH", referencedColumnName = "id")
    private TaiKhoan taiKhoan;

    public ChuyenXe() {
    }

    public ChuyenXe(Long MaChuyenXe, Long SoViTriConTrong, Double GiaTriChuyenXe, LocalDate NgayChay, Xe xe, Tuyen tuyen, LoaiXe loaiXe, GiaVe giaVe, List<HoaDon> hoaDons, List<PhieuDatVe> phieuDatVes) {
        this.MaChuyenXe = MaChuyenXe;
        this.SoViTriConTrong = SoViTriConTrong;
        this.GiaTriChuyenXe = GiaTriChuyenXe;
        this.NgayChay = NgayChay;
        this.xe = xe;
        this.tuyen = tuyen;
        this.loaiXe = loaiXe;
        this.giaVe = giaVe;
        this.hoaDons = hoaDons;
        this.phieuDatVes = phieuDatVes;
    }

    public Long getMaChuyenXe() {
        return this.MaChuyenXe;
    }

    public void setMaChuyenXe(Long MaChuyenXe) {
        this.MaChuyenXe = MaChuyenXe;
    }

    public Long getSoViTriConTrong() {
        return this.SoViTriConTrong;
    }

    public void setSoViTriConTrong(Long SoViTriConTrong) {
        this.SoViTriConTrong = SoViTriConTrong;
    }

    public Double getGiaTriChuyenXe() {
        return this.GiaTriChuyenXe;
    }

    public void setGiaTriChuyenXe(Double GiaTriChuyenXe) {
        this.GiaTriChuyenXe = GiaTriChuyenXe;
    }

    public LocalDate getNgayChay() {
        return this.NgayChay;
    }

    public void setNgayChay(LocalDate NgayChay) {
        this.NgayChay = NgayChay;
    }

    public Xe getXe() {
        return this.xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
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

    public GiaVe getGiaVe() {
        return this.giaVe;
    }

    public void setGiaVe(GiaVe giaVe) {
        this.giaVe = giaVe;
    }

    public List<HoaDon> getHoaDons() {
        return this.hoaDons;
    }

    public void setHoaDons(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }

    public List<PhieuDatVe> getPhieuDatVes() {
        return this.phieuDatVes;
    }

    public void setPhieuDatVes(List<PhieuDatVe> phieuDatVes) {
        this.phieuDatVes = phieuDatVes;
    }

    public ChuyenXe MaChuyenXe(Long MaChuyenXe) {
        setMaChuyenXe(MaChuyenXe);
        return this;
    }

    public ChuyenXe SoViTriConTrong(Long SoViTriConTrong) {
        setSoViTriConTrong(SoViTriConTrong);
        return this;
    }

    public ChuyenXe GiaTriChuyenXe(Double GiaTriChuyenXe) {
        setGiaTriChuyenXe(GiaTriChuyenXe);
        return this;
    }

    public ChuyenXe NgayChay(LocalDate NgayChay) {
        setNgayChay(NgayChay);
        return this;
    }

    public ChuyenXe xe(Xe xe) {
        setXe(xe);
        return this;
    }

    public ChuyenXe tuyen(Tuyen tuyen) {
        setTuyen(tuyen);
        return this;
    }

    public ChuyenXe loaiXe(LoaiXe loaiXe) {
        setLoaiXe(loaiXe);
        return this;
    }

    public ChuyenXe giaVe(GiaVe giaVe) {
        setGiaVe(giaVe);
        return this;
    }

    public ChuyenXe hoaDons(List<HoaDon> hoaDons) {
        setHoaDons(hoaDons);
        return this;
    }

    public ChuyenXe phieuDatVes(List<PhieuDatVe> phieuDatVes) {
        setPhieuDatVes(phieuDatVes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChuyenXe)) {
            return false;
        }
        ChuyenXe chuyenXe = (ChuyenXe) o;
        return Objects.equals(MaChuyenXe, chuyenXe.MaChuyenXe) && Objects.equals(SoViTriConTrong, chuyenXe.SoViTriConTrong) && Objects.equals(GiaTriChuyenXe, chuyenXe.GiaTriChuyenXe) && Objects.equals(NgayChay, chuyenXe.NgayChay) && Objects.equals(xe, chuyenXe.xe) && Objects.equals(tuyen, chuyenXe.tuyen) && Objects.equals(loaiXe, chuyenXe.loaiXe) && Objects.equals(giaVe, chuyenXe.giaVe) && Objects.equals(hoaDons, chuyenXe.hoaDons) && Objects.equals(phieuDatVes, chuyenXe.phieuDatVes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaChuyenXe, SoViTriConTrong, GiaTriChuyenXe, NgayChay, xe, tuyen, loaiXe, giaVe, hoaDons, phieuDatVes);
    }

    @Override
    public String toString() {
        return "{" +
            " MaChuyenXe='" + getMaChuyenXe() + "'" +
            ", SoViTriConTrong='" + getSoViTriConTrong() + "'" +
            ", GiaTriChuyenXe='" + getGiaTriChuyenXe() + "'" +
            ", NgayChay='" + getNgayChay() + "'" +
            ", xe='" + getXe() + "'" +
            ", tuyen='" + getTuyen() + "'" +
            ", loaiXe='" + getLoaiXe() + "'" +
            ", giaVe='" + getGiaVe() + "'" +
            ", hoaDons='" + getHoaDons() + "'" +
            ", phieuDatVes='" + getPhieuDatVes() + "'" +
            "}";
    }
    

}
