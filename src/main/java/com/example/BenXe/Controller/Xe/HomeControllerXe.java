package com.example.BenXe.Controller.Xe;

import com.example.BenXe.Model.HoaDon;
import com.example.BenXe.Model.PhieuDatVe;
import com.example.BenXe.Service.PhieuDatVeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BenXe.Model.ChuyenXe;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Service.ChuyenXeService;
import com.example.BenXe.Service.TaiKhoanService;

import java.util.List;

@Controller
@RequestMapping("/xe")
public class HomeControllerXe {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private ChuyenXeService chuyenXeService;
    @Autowired
    private PhieuDatVeService phieuDatVeService;

    @GetMapping
    public String index() {
        return "xe/home/index";
    }

    @GetMapping("/listchuyenxe")
    public String chuyenxe(Model model, Authentication authentication) {
        List<ChuyenXe> list = chuyenXeService.getAllChuyenXes();
        model.addAttribute("chuyenxes", list);
        return "xe/home/listchuyenxe";
    }

    @GetMapping("/themchuyen")
    public String themchuyenxe(Model model) {
        model.addAttribute("chuyen", new ChuyenXe());
        return "xe/home/themchuyen";
    }

    @GetMapping("/listhoadon")
    public String getAllInvoice(Model model) {
        List<PhieuDatVe> list = phieuDatVeService.getAllPhieuDatVes();
        model.addAttribute("list", new HoaDon());
        return "xe/home/listhoadon";
    }

    @PostMapping("/themchuyen")
    public String themchuyen(Model model, Authentication authentication, @ModelAttribute("chuyen") ChuyenXe chuyen) {
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();

        chuyenXeService.save(chuyen);
        return "redirect:/xe/listchuyenxe";
    }

    @GetMapping("/xemkhachhang/{id}")
    public String xemkhachhang(@PathVariable("id") Long id, Model model) {
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("ves", chuyenXe.getPhieuDatVes());
        return "/xe/home/xemkhachhang";
    }
}