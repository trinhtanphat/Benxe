package com.example.BenXe.Controller.NhanVien;

import java.util.ArrayList;
import java.util.List;

import com.example.BenXe.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.BaiDauXe;
import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.GiaVe;
import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Model.NhanVien;
import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Model.Xe;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/nhanvien")
public class HomeControllerNhanVien {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private LoaiTKService loaiTKService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private ChuXeService chuXeService;
    @Autowired 
    private XeService xeService;
    @Autowired
    private PhieuDangKyTuyenService phieuDangKyTuyenService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired
    private GiaVeService giaVeService;
    @Autowired
    private PhieuDatVeService phieuDatVeService;

    @GetMapping
    public String index(Model model){
        List<PhieuDangKyTuyen> PDKTList = phieuDangKyTuyenService.getAllPhieuDangKyTuyens();
        Integer countPDKTWait = 0;
        List<ChuXe> chuXes = chuXeService.getAllChuXes();
        List<Xe> xes = xeService.getAllXes();
        List<BaiDauXe> baiDauXes = baiDauXeService.getAllBaiDauXes();
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        for (PhieuDangKyTuyen pdkt : PDKTList) 
            if(!pdkt.getTrangThai()){
            countPDKTWait++;
        }
        model.addAttribute("PDKTDoiDuyet",countPDKTWait);
        model.addAttribute("SoChuXe",chuXes.size());
        model.addAttribute("SoXe",xes.size());
        model.addAttribute("SoBDX",baiDauXes.size());
        model.addAttribute("SoKhachHang",khachHangs.size());
        
        return "nhanvien/home/index";
    }
 
    @GetMapping("/qlkhachhang")
    public String listKhachHang(Model model){
        List<KhachHang> khachHangs = khachHangService.getAllKhachHang();
        model.addAttribute("KhachHangs", khachHangs);
        return "nhanvien/home/listkhachhang";
    }
    @GetMapping("/qlnhaxe")
    public String listNhaXe(Model model){
        List<ChuXe> nhaXes = chuXeService.getAllChuXes();
        model.addAttribute("nhaXes", nhaXes);
        return "nhanvien/home/listnhaxe";
    }
    @GetMapping("/qlxe")
    public String listXe(Model model){
        List<Xe> xes = xeService.getAllXes();
        model.addAttribute("xes",xes);
        return "nhanvien/home/listxe";
    }
    @GetMapping("/qlve")
    public String listVe(Model model){
        model.addAttribute("phieuDatVes", phieuDatVeService.getAllPhieuDatVes());
        return "nhanvien/home/listve";
    }
    @GetMapping("/qldondangkytuyen")
    public String listDonDangKyTuyen(Model model){
        model.addAttribute("donDangKyTuyens",phieuDangKyTuyenService.getAllPhieuDangKyTuyens());
        return "nhanvien/home/listdon";
    }


    @GetMapping("/duyetdangkytuyen/{id}")
    public String duyetdangkytuyenForm(@PathVariable("id") Long id,Model model){
        PhieuDangKyTuyen pdkt = phieuDangKyTuyenService.getPhieuDanhKyTuyenById(id);
        if(pdkt!= null){
            model.addAttribute("pdkt",pdkt);
            model.addAttribute("taiKhoanChuXe", new TaiKhoan());
            model.addAttribute("taiKhoanXe", new TaiKhoan());
            return "nhanvien/home/duyetdangkytuyen";
        }else
            return "not-found!";
    }

    @PostMapping("/duyetdangkytuyen/{id}")
    public String duyetdangkytuyen(@PathVariable("id") Long id,HttpServletRequest request, Authentication authentication) {
        PhieuDangKyTuyen pdkt = phieuDangKyTuyenService.getPhieuDanhKyTuyenById(id);
        if(pdkt!= null){
            //Lấy xe từ phiếu đăng ký tuyến
            Xe xe = pdkt.getXe();
            ChuXe chuXe = pdkt.getChuXe();
            //Set info tài khoản cho chủ xe và xe
            TaiKhoan taiKhoanChuXe = new TaiKhoan();
            TaiKhoan taiKhoanXe = new TaiKhoan();
            //-----
            taiKhoanChuXe.setLoaitk(loaiTKService.getLoaiTkById(3L));
            taiKhoanXe.setLoaitk(loaiTKService.getLoaiTkById(4L));
            //-----
            taiKhoanChuXe.setTenDangNhap(chuXe.getEmail());
            taiKhoanChuXe.setMatKhau(new BCryptPasswordEncoder().encode("123"));
            //------
            taiKhoanXe.setMatKhau(new BCryptPasswordEncoder().encode("123"));
            taiKhoanXe.setTenDangNhap(xe.getBKS());
            //------
            //================================================================
            List<GiaVe> giaVe = giaVeService.getAllGiaVes();
            for(GiaVe gv : giaVe)
                if(gv.getTuyen()==pdkt.getTuyen() && gv.getLoaiXe()==pdkt.getLoaiXe()){
                    pdkt.setGiaVe(gv);
                    break;
            }
            //================================================================
            List<ChuXe> chuXes = new ArrayList<ChuXe>();
            chuXes.add(chuXe);
            taiKhoanChuXe.setChuXes(chuXes);
            List<Xe> xes = new ArrayList<Xe>();
            xes.add(xe);

            CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
            List<NhanVien> nv = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getNhanViens();
            pdkt.setNhanVien(nv.get(0));
            BaiDauXe bdx = pdkt.getXe().getBaiDauXe();
            bdx.setTinhTrang(true);
            baiDauXeService.save(bdx);
            taiKhoanService.save(taiKhoanXe);
            xeService.save(xe);

            taiKhoanService.save(taiKhoanChuXe);
            chuXe.setTaiKhoan(taiKhoanChuXe);
            chuXeService.save(chuXe);
            
            pdkt.setTrangThai(true);
            phieuDangKyTuyenService.save(pdkt);
            return "redirect:/nhanvien/qldondangkytuyen";
        }else
            return "not-found!";
    }
}