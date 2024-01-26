package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ghe")
public class Ghe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ghe;

    @Column(name = "MoTa")
    private String MoTa;

    @Column(name = "TrangThai")
    private Boolean TrangThai;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaXe")
    private Xe xe;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "ghe", cascade = CascadeType.ALL)
    private List<PhieuDatVe> phieuDatVes;

    public Ghe() {
    }

    public Ghe(Long Ghe, String MoTa, Boolean TrangThai, Xe xe, List<PhieuDatVe> phieuDatVes) {
        this.Ghe = Ghe;
        this.MoTa = MoTa;
        this.TrangThai = TrangThai;
        this.xe = xe;
        this.phieuDatVes = phieuDatVes;
    }

    public Long getGhe() {
        return this.Ghe;
    }

    public void setGhe(Long Ghe) {
        this.Ghe = Ghe;
    }

    public String getMoTa() {
        return this.MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
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

    public Xe getXe() {
        return this.xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
    }

    public List<PhieuDatVe> getPhieuDatVes() {
        return this.phieuDatVes;
    }

    public void setPhieuDatVes(List<PhieuDatVe> phieuDatVes) {
        this.phieuDatVes = phieuDatVes;
    }

    public Ghe Ghe(Long Ghe) {
        setGhe(Ghe);
        return this;
    }

    public Ghe MoTa(String MoTa) {
        setMoTa(MoTa);
        return this;
    }

    public Ghe TrangThai(Boolean TrangThai) {
        setTrangThai(TrangThai);
        return this;
    }

    public Ghe xe(Xe xe) {
        setXe(xe);
        return this;
    }

    public Ghe phieuDatVes(List<PhieuDatVe> phieuDatVes) {
        setPhieuDatVes(phieuDatVes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ghe)) {
            return false;
        }
        Ghe ghe = (Ghe) o;
        return Objects.equals(Ghe, ghe.Ghe) && Objects.equals(MoTa, ghe.MoTa) && Objects.equals(TrangThai, ghe.TrangThai) && Objects.equals(xe, ghe.xe) && Objects.equals(phieuDatVes, ghe.phieuDatVes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Ghe, MoTa, TrangThai, xe, phieuDatVes);
    }

    @Override
    public String toString() {
        return "{" +
            " Ghe='" + getGhe() + "'" +
            ", MoTa='" + getMoTa() + "'" +
            ", TrangThai='" + isTrangThai() + "'" +
            ", xe='" + getXe() + "'" +
            ", phieuDatVes='" + getPhieuDatVes() + "'" +
            "}";
    }

    
}
