package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BaiDauXe")
public class BaiDauXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaBDX;

    @Column(name = "MoTaViTri")
    private String MoTaViTri;

    @Column(name ="ThoiGianDen")
    private LocalTime ThoiGianDen;

    @Column(name ="ThoiGianDi")
    private LocalTime ThoiGianDi;

    @Column(name = "TinhTrang")
    private Boolean TinhTrang;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "baiDauXe", cascade = CascadeType.ALL)
    private List<Xe> xes;

    public BaiDauXe() {
    }

    public BaiDauXe(Long MaBDX, String MoTaViTri, LocalTime ThoiGianDen, LocalTime ThoiGianDi, Boolean TinhTrang, List<Xe> xes) {
        this.MaBDX = MaBDX;
        this.MoTaViTri = MoTaViTri;
        this.ThoiGianDen = ThoiGianDen;
        this.ThoiGianDi = ThoiGianDi;
        this.TinhTrang = TinhTrang;
        this.xes = xes;
    }

    public Long getMaBDX() {
        return this.MaBDX;
    }

    public void setMaBDX(Long MaBDX) {
        this.MaBDX = MaBDX;
    }

    public String getMoTaViTri() {
        return this.MoTaViTri;
    }

    public void setMoTaViTri(String MoTaViTri) {
        this.MoTaViTri = MoTaViTri;
    }

    public LocalTime getThoiGianDen() {
        return this.ThoiGianDen;
    }

    public void setThoiGianDen(LocalTime ThoiGianDen) {
        this.ThoiGianDen = ThoiGianDen;
    }

    public LocalTime getThoiGianDi() {
        return this.ThoiGianDi;
    }

    public void setThoiGianDi(LocalTime ThoiGianDi) {
        this.ThoiGianDi = ThoiGianDi;
    }

    public Boolean isTinhTrang() {
        return this.TinhTrang;
    }

    public Boolean getTinhTrang() {
        return this.TinhTrang;
    }

    public void setTinhTrang(Boolean TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public List<Xe> getXes() {
        return this.xes;
    }

    public void setXes(List<Xe> xes) {
        this.xes = xes;
    }

    public BaiDauXe MaBDX(Long MaBDX) {
        setMaBDX(MaBDX);
        return this;
    }

    public BaiDauXe MoTaViTri(String MoTaViTri) {
        setMoTaViTri(MoTaViTri);
        return this;
    }

    public BaiDauXe ThoiGianDen(LocalTime ThoiGianDen) {
        setThoiGianDen(ThoiGianDen);
        return this;
    }

    public BaiDauXe ThoiGianDi(LocalTime ThoiGianDi) {
        setThoiGianDi(ThoiGianDi);
        return this;
    }

    public BaiDauXe TinhTrang(Boolean TinhTrang) {
        setTinhTrang(TinhTrang);
        return this;
    }

    public BaiDauXe xes(List<Xe> xes) {
        setXes(xes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BaiDauXe)) {
            return false;
        }
        BaiDauXe baiDauXe = (BaiDauXe) o;
        return Objects.equals(MaBDX, baiDauXe.MaBDX) && Objects.equals(MoTaViTri, baiDauXe.MoTaViTri) && Objects.equals(ThoiGianDen, baiDauXe.ThoiGianDen) && Objects.equals(ThoiGianDi, baiDauXe.ThoiGianDi) && Objects.equals(TinhTrang, baiDauXe.TinhTrang) && Objects.equals(xes, baiDauXe.xes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MaBDX, MoTaViTri, ThoiGianDen, ThoiGianDi, TinhTrang, xes);
    }

    @Override
    public String toString() {
        return "{" +
            " MaBDX='" + getMaBDX() + "'" +
            ", MoTaViTri='" + getMoTaViTri() + "'" +
            ", ThoiGianDen='" + getThoiGianDen() + "'" +
            ", ThoiGianDi='" + getThoiGianDi() + "'" +
            ", TinhTrang='" + isTinhTrang() + "'" +
            ", xes='" + getXes() + "'" +
            "}";
    }

}