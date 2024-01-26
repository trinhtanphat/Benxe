package com.example.BenXe.Controller.Admin;

import java.util.ArrayList;
import java.util.List;

import com.example.BenXe.Service.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.example.BenXe.Model.BaiDauXe;
import com.example.BenXe.Model.LoaiTK;
import com.example.BenXe.Model.NhanVien;
import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Model.Tuyen;

import jakarta.validation.Valid;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class HomeControllerAdmin {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private LoaiTKService loaiTKService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private PhieuDatVeService phieuDatVeService;
    @Autowired
    private BaiDauXeService baiDauXeService;
    @Autowired
    private GiaVeService giaVeService;
    @Autowired
    private TuyenService tuyenService;

//    @Autowired
//    private HttpServletRequest request;
//    String username = (String) request.getSession().getAttribute("username");

    @GetMapping()
    public String listNhanVien(
            Model model,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
//        model.addAttribute("userName", username);
        List<NhanVien> nhanViens = nhanVienService.getAllNhanVien(pageNo, pageSize, sortBy);
        model.addAttribute("NhanViens", nhanViens);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                nhanVienService.getAllNhanVien().size() / pageSize);
        return "admin/listnhanvien";
    }

    @GetMapping("/createnhanvien")
    public String registernhanvien(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("nhanVien", new NhanVien());
        return "admin/createtknhanvien";
    }

    @PostMapping("/createnhanvien")
    public String registernhanvien(@Valid @ModelAttribute("taiKhoan") TaiKhoan taiKhoan, @ModelAttribute("nhanVien") NhanVien nhanVien,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "admin/createtknhanvien";
        }
        LoaiTK loaiTK = loaiTKService.getLoaiTkById(2L);
        taiKhoan.setLoaitk(loaiTK);
        taiKhoan.setMatKhau(new BCryptPasswordEncoder().encode(taiKhoan.getMatKhau()));

        List<NhanVien> khs = new ArrayList<NhanVien>();
        khs.add(nhanVien);
        taiKhoan.setNhanViens(khs);
        taiKhoanService.save(taiKhoan);
        nhanVien.setTaiKhoan(taiKhoan);
        nhanVienService.save(nhanVien);
        return "redirect:/admin";
    }

    @GetMapping("/qltuyen")
    public String listTuyen(Model model) {
        model.addAttribute("tuyens", tuyenService.getAllTuyens());
        return "admin/listtuyen";
    }

    @GetMapping("/createtuyen")
    public String createtuyen(Model model) {
        model.addAttribute("tuyen", new Tuyen());
        return "admin/createtuyen";
    }

    @PostMapping("createtuyen")
    public String createtuyen(@ModelAttribute("tuyen") Tuyen tuyen) {
        tuyenService.save(tuyen);
        return "redirect:/admin/qltuyen";
    }

    @GetMapping("/qlgiave")
    public String listGiaVe(Model model) {
        model.addAttribute("giaVes", giaVeService.getAllGiaVes());
        return "admin/listgiave";
    }

    @GetMapping("/qlbaidauxe")
    public String listBaiDauXe(Model model) {
        model.addAttribute("baiDauXes", baiDauXeService.getAllBaiDauXes());
        return "admin/listbaidauxe";
    }

    @GetMapping("/createbaidauxe")
    public String createBDX(Model model) {
        model.addAttribute("bdx", new BaiDauXe());
        return "admin/createbaidauxe";
    }

    @PostMapping("createbaidauxe")
    public String createtBDX(@ModelAttribute("bdx") BaiDauXe bdx, Model model) {
        List<BaiDauXe> listBDX = new ArrayList<BaiDauXe>();
        listBDX = baiDauXeService.getAllBaiDauXes();
        for (BaiDauXe bdxchecked : listBDX) {
            if ((bdxchecked.getMoTaViTri().equals(bdx.getMoTaViTri()) && bdxchecked.getThoiGianDi().equals(bdx.getThoiGianDi()) && bdxchecked.getThoiGianDen().equals(bdx.getThoiGianDen()))
                    || bdxchecked.getMoTaViTri() == null || bdxchecked.getThoiGianDi() == null || bdxchecked.getThoiGianDen() == null) {
                model.addAttribute("bdx", new BaiDauXe());
                return "admin/createbaidauxe";
            }
        }
        bdx.setTinhTrang(false);
        baiDauXeService.save(bdx);
        return "redirect:/admin/qlbaidauxe";
    }

    @GetMapping("/search")
    public String searchNhanVien(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        model.addAttribute("NhanViens", nhanVienService.searchNhanVien(keyword));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                nhanVienService.getAllNhanVien().size() / pageSize);
        return "admin/listnhanvien";
    }

    @GetMapping("/nhanvien/edit/{id}")
    public String editTKNhanVienForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("taiKhoan", taiKhoanService.getTaiKhoanById(id));
        model.addAttribute("nhanVien", nhanVienService.getNhanVienById(id));
        return "admin/edittknhanvien";
    }

    @PostMapping("/nhanvien/edit")
    public String editTKNhanVienForm(@ModelAttribute("nhanVien") NhanVien updateNhanVien) {
        for (int i = 0; i < nhanVienService.getAllNhanVien().size(); i++) {
            NhanVien nv = nhanVienService.getAllNhanVien().get(i);
            if (nv.getMaNV() == updateNhanVien.getMaNV()) {
                nhanVienService.updateNhanVien(nv.getMaNV(), updateNhanVien);
                break;
            }
        }
        return "redirect:/admin";
    }

    @GetMapping("/nhanvien/delete/{id}")
    public String deleteNhanVien(@PathVariable("id") Long maNV) {
        nhanVienService.deleteNhanVienById(maNV);
        var cnt = nhanVienService.getAllNhanVien();
        return "redirect:/admin";
    }
}