package com.example.BenXe.Controller;

import com.example.BenXe.Model.KhachHang;
import com.example.BenXe.Model.LoaiTK;
import com.example.BenXe.Model.TaiKhoan;
import com.example.BenXe.Service.KhachHangService;
import com.example.BenXe.Service.LoaiTKService;
import com.example.BenXe.Service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private LoaiTKService loaiTKService;
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("login")
    public String loginGET(Model model, HttpSession session, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            session.setAttribute("infoUser", userDetails);
            if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("NhanVien"))) {
                return "redirect:/admin";
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("KhachHang"))) {
                return "redirect:/khachhang";

            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("NhaXe"))) {
                return "redirect:/nhaxe";
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("Xe"))) {
                return "redirect:/xe";
            } else {
                return "redirect:/";
            }
        }
        model.addAttribute("taiKhoan", new TaiKhoan());
        return "Login/login";
    }

    @PostMapping("login")
    public String login(Model model, HttpSession session, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            session.setAttribute("infoUser", userDetails);
            if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("NhanVien"))) {
                return "redirect:/admin";
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("KhachHang"))) {
                return "redirect:/khachhang";

            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("NhaXe"))) {
                return "redirect:/nhaxe";
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("Xe"))) {
                return "redirect:/xe";
            } else {
                return "redirect:/";
            }
        }
        model.addAttribute("taiKhoan", new TaiKhoan());
        return "Login/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("taiKhoan", new TaiKhoan());
        model.addAttribute("khachHang", new KhachHang());
        return "Login/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
            @ModelAttribute("khachHang") KhachHang khachHang,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "Login/register";
        }
        LoaiTK loaiTK = loaiTKService.getLoaiTkById(1L);
        taiKhoan.setLoaitk(loaiTK);
        taiKhoan.setMatKhau(new BCryptPasswordEncoder().encode(taiKhoan.getMatKhau()));
        List<KhachHang> khs = new ArrayList<KhachHang>();
        khs.add(khachHang);
        taiKhoan.setKhachHangs(khs);
        taiKhoanService.save(taiKhoan);
        khachHang.setTaiKhoan(taiKhoan);
        khachHangService.save(khachHang);
        return "redirect:/login";
    }
    @GetMapping("/resetPassword")
    public String resetPassword() {
        return "Login/resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword( String oldPassword,String newPassword,String confirmPassword,Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String encodedOldPassword = userDetails.getPassword();

        if (newPassword != null && confirmPassword != null && newPassword.equals(confirmPassword) &&
                new BCryptPasswordEncoder().matches(oldPassword, encodedOldPassword)) {

            String username = userDetails.getUsername();
            TaiKhoan tk = taiKhoanService.getTaiKhoanByUsername(username);
            String encodedNewPassword = new BCryptPasswordEncoder().encode(newPassword);
            tk.setMatKhau(encodedNewPassword);
            taiKhoanService.save(tk);

            return "redirect:/";
        }

        return "redirect:/resetPassword";
    }
}