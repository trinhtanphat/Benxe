package com.example.BenXe.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@Entity
@Table(name = "GiaVe")
@Embeddable
public class GiaVe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giaveId;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaTuyen")
    private Tuyen tuyen;

    @Fetch(FetchMode.JOIN)
    @ManyToOne
    @JoinColumn(name = "MaLX")
    private LoaiXe loaiXe;

    @Column(name = "Gia")
    private Double Gia;

    @Column(name = "DichVuDiKem")
    private String DichVuDiKem;

    @OneToMany(mappedBy = "giaVe", cascade = CascadeType.ALL)
    private List<ChuyenXe> chuyenXes;

    @OneToMany(mappedBy = "giaVe", cascade = CascadeType.ALL)
    private List<PhieuDangKyTuyen> phieuDangKyTuyens;

    public GiaVe() {
    }

    public GiaVe(Long giaveId, Tuyen tuyen, LoaiXe loaiXe, Double gia, String dichVuDiKem, List<ChuyenXe> chuyenXes, List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.giaveId = giaveId;
        this.tuyen = tuyen;
        this.loaiXe = loaiXe;
        Gia = gia;
        DichVuDiKem = dichVuDiKem;
        this.chuyenXes = chuyenXes;
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }

    public Long getGiaveId() {
        return giaveId;
    }

    public void setGiaveId(Long giaveId) {
        this.giaveId = giaveId;
    }

    public Tuyen getTuyen() {
        return tuyen;
    }

    public void setTuyen(Tuyen tuyen) {
        this.tuyen = tuyen;
    }

    public LoaiXe getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double gia) {
        Gia = gia;
    }

    public String getDichVuDiKem() {
        return DichVuDiKem;
    }

    public void setDichVuDiKem(String dichVuDiKem) {
        DichVuDiKem = dichVuDiKem;
    }

    public List<ChuyenXe> getChuyenXes() {
        return chuyenXes;
    }

    public void setChuyenXes(List<ChuyenXe> chuyenXes) {
        this.chuyenXes = chuyenXes;
    }

    public List<PhieuDangKyTuyen> getPhieuDangKyTuyens() {
        return phieuDangKyTuyens;
    }

    public void setPhieuDangKyTuyens(List<PhieuDangKyTuyen> phieuDangKyTuyens) {
        this.phieuDangKyTuyens = phieuDangKyTuyens;
    }
}
