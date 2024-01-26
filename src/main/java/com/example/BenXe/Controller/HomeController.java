package com.example.BenXe.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.BaiDauXe;
import com.example.BenXe.Model.ChuXe;
import com.example.BenXe.Model.PhieuDangKyTuyen;
import com.example.BenXe.Model.Xe;
import com.example.BenXe.Service.BaiDauXeService;
import com.example.BenXe.Service.ChuXeService;
import com.example.BenXe.Service.GiaVeService;
import com.example.BenXe.Service.LoaiXeService;
import com.example.BenXe.Service.PhieuDangKyTuyenService;
import com.example.BenXe.Service.TuyenService;
import com.example.BenXe.Service.XeService;

@Controller
@RequestMapping()
public class HomeController {
    @Autowired
    private TuyenService tuyenService;
    @Autowired
    private LoaiXeService loaiXeService;
    @Autowired
    private GiaVeService giaVeService;
    @Autowired
    private PhieuDangKyTuyenService pdktService;
    @Autowired
    private ChuXeService chuXeService;
    @Autowired
    private XeService xeService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired
    PhieuDangKyTuyenService phieuDangKyTuyenService;

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("tuyens", tuyenService.getAllTuyens());
        model.addAttribute("loaiXes", loaiXeService.getAllLoaiXes());
        model.addAttribute("baiDauXes", baiDauXeService.getAllBaiDauXes());
        model.addAttribute("pdkt", new PhieuDangKyTuyen());
        model.addAttribute("chuXe", new ChuXe());
        model.addAttribute("xe", new Xe());
        return "contact";
    }

    @PostMapping("/contact")
    public String dangkytuyen(@ModelAttribute("chuXe") ChuXe chuXe, @ModelAttribute("pdkt") PhieuDangKyTuyen pdkt, @ModelAttribute("xe") Xe xe, @ModelAttribute("baiDauXe") BaiDauXe baiDauXe) {
        LocalDate date = LocalDate.now();
        pdkt.setThoiGianNopPhieu(date);
        List<PhieuDangKyTuyen> p = new ArrayList<PhieuDangKyTuyen>();
        p.add(pdkt);
        List<Xe> x = new ArrayList<Xe>();
        x.add(xe);
        chuXe.setXes(x);
        xe.setPhieuDangKyTuyens(p);
        xe.chuXe(chuXe);
        pdkt.chuXe(chuXe);
        pdkt.xe(xe);
        pdkt.TrangThai(false);
        //pdkt.giaVe(giaVeService.FindIdByMaLXMaTuyen(pdkt.getLoaiXe().getMaLX(), pdkt.getTuyen()));
        chuXeService.save(chuXe);
        xeService.save(xe);
        phieuDangKyTuyenService.save(pdkt);
        return "redirect:/";
    }
}