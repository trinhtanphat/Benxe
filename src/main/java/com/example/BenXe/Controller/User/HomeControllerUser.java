package com.example.BenXe.Controller.User;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.example.BenXe.Controller.EmailController;
import com.example.BenXe.Model.*;
import com.example.BenXe.Service.*;
import com.example.BenXe.daos.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;

@Controller
@RequestMapping("/khachhang")
public class HomeControllerUser {
    @Autowired
    private TuyenService tuyenService;
    @Autowired
    private ChuyenXeService chuyenXeService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private PhieuDatVeService phieuDatVeService;
    @Autowired
    private CartService cartService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public String home(HttpSession session, Model model) {
        List<String> diemdis = tuyenService.getDiemDi();
        List<String> diemdens = tuyenService.getDiemDen();
        LocalDate ngaydi = null;
        model.addAttribute("diemdis", diemdis);
        model.addAttribute("diemdens", diemdens);
        model.addAttribute("ngaydi", ngaydi);
        int cartSize = cartService.getCartSize(session);
        model.addAttribute("cartSize", cartSize);
        return "user/home/index";
    }

    @GetMapping("/timve")
    public String timve(HttpServletRequest request, HttpSession session, String diemdi, String diemden, LocalDate ngaydi, Model model) {
        List<ChuyenXe> findChuyenXes = new ArrayList<ChuyenXe>();
        List<ChuyenXe> cxs = chuyenXeService.getChuyenXeByNgayChay(ngaydi);
        for (ChuyenXe cx : cxs)
            if (cx.getTuyen().getDiemDi().equals(diemdi) && cx.getTuyen().getDiemDen().equals(diemden) && cx.getSoViTriConTrong() > 0)
                findChuyenXes.add(cx);
        model.addAttribute("chuyenxes", findChuyenXes);
        int cartSize = cartService.getCartSize(session);
        model.addAttribute("cartSize", cartSize);
        String url = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        session.setAttribute("backUrl", url + "?" + queryString);
        return "user/home/timve";
    }

    @GetMapping("/datve/{id}")
    public String datveForm(@PathVariable("id") Long id, Authentication authentication, Model model) {
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        ChuyenXe cx = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("chuyenXe", cx);
        model.addAttribute("phieuDatVe", new PhieuDatVe());
        return "user/home/datve";
    }

    @PostMapping("/datve/{id}")
    public String datve(@PathVariable("id") Long id, @ModelAttribute("phieuDatVe") PhieuDatVe phieuDatVe, Authentication authentication) {
        //----- get user
        CustomTaiKhoanDetail userDetail = (CustomTaiKhoanDetail) authentication.getPrincipal();
        List<KhachHang> kh = taiKhoanService.getTaiKhoanByUsername(userDetail.getUsername()).getKhachHangs();
        KhachHang khachHang = kh.get(0);
        //----- get chuyenxe
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        //----set list pdv cho khachhang
        List<PhieuDatVe> pdvs = khachHang.getPhieuDatVes();
        pdvs.add(phieuDatVe);
        khachHang.setPhieuDatVes(pdvs);
        phieuDatVe.setKhachHang(khachHang);
        //-----set inf cho chuyenXe
        phieuDatVe.setChuyenXe(chuyenXe);
        List<PhieuDatVe> pdvscx = chuyenXe.getPhieuDatVes();
        pdvscx.add(phieuDatVe);
        chuyenXe.setPhieuDatVes(pdvscx);
        chuyenXe.SoViTriConTrong(chuyenXe.getSoViTriConTrong() - 1);
        //-----set inf phiếu đặt vé
        phieuDatVe.setNgayDat(LocalDate.now());
        if (phieuDatVe.getViTriLenXe() == "") phieuDatVe.setViTriLenXe("Bến xe miền đông");
        phieuDatVe.setTinhTrangVe("Đã đặt");
        //----- lưu 
        phieuDatVeService.save(phieuDatVe);
        khachHangService.save(khachHang);
        chuyenXeService.save(chuyenXe);

        return "redirect:/khachhang";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam long id, @RequestParam String diemDi, @RequestParam String diemDen, @RequestParam String price, @RequestParam(defaultValue = "1") int quantity) {
        var cart = cartService.getCart(session);
        double priceDouble = Double.parseDouble(price);
        cart.addItems(new Item(id, diemDi, diemDen, priceDouble, quantity));
        cartService.updateCart(session, cart);
        String backUrl = (String) session.getAttribute("backUrl");
        if (backUrl != null) {
            return "redirect:" + backUrl.toString();
        } else {
            return "redirect:/khachhang";
        }
    }

    @Autowired
    public EmailService emailService;

    public void sendMail(String sendTo, String title, String context) {
        emailService.sendEmail(sendTo, title, context);
    }

    @GetMapping("/vnpayreturn")
    public String SuccessVNPay(Model model, String vnp_Amount, String vnp_BankCode, String vnp_BankTranNo, String vnp_CardType, String vnp_OrderInfo, String vnp_PayDate, String vnp_ResponseCode, String vnp_TmnCode, String vnp_TransactionNo, String vnp_TransactionStatus, String vnp_TxnRef, String vnp_SecureHash) {
        double totalPrice = (double) (Long.parseLong(vnp_Amount) / 100);
        model.addAttribute("totalPricePayment", totalPrice);
        if (vnp_ResponseCode.equals("00")) {
            sendMail("truongpham.270102@gmail.com", "Hi @" + "you", "Bạn đã thanh toán thành công đơn hàng/ có đơn hàng mới. Tổng tiền: " + vnp_Amount);
            return "user/home/paymentsuccessfull";
        }
        return "user/home/paymentfailed";
    }

    public static String prettyObject(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String generateQrCode(String data, int width, int height) {
        StringBuilder resultImage = new StringBuilder();

        if (!data.isEmpty()) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            try {
                String imagePath = "C:/Users/PC/Desktop/dang/Benxe-master/Benxe-master/src/main/resources/static/img/MoMo_Logo.png";
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height);

                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

                // Load the image to be added in the center
                BufferedImage centerImage = ImageIO.read(new File(imagePath));

                // Calculate the position to place the center image
                int centerX = (bufferedImage.getWidth() - centerImage.getWidth()) / 2;
                int centerY = (bufferedImage.getHeight() - centerImage.getHeight()) / 2;

                // Draw the center image onto the QR code image
                Graphics2D graphics = bufferedImage.createGraphics();
                graphics.drawImage(centerImage, centerX, centerY, 100, 100, null);
                graphics.dispose();

                ImageIO.write(bufferedImage, "png", os);

                resultImage.append("data:image/png;base64,");
                resultImage.append(new String(Base64.getEncoder().encode(os.toByteArray())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultImage.toString();
    }

    @GetMapping("/momoqr/{id}")
    public String momoQR(@PathVariable("id") Long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        String txtSDT = "0352191885";
        String txtHoTen = "Pham Dai Truong";
        double amount = invoice.getPrice();
        long amt = (long)amount;
        String txtTien = String.valueOf(amt);
        String txtMail = "truongpham.270102@gmail.com";
        String orderID = "DH:" + invoice.getId().toString();
        var qrcode_text = "$2|99|" + txtSDT + "|" + txtHoTen + "|" + txtMail + "|'Hello'|'Hi'|" + txtTien + "|" + orderID + "|1|0";
        String qrString = generateQrCode(qrcode_text, 300, 300);
        model.addAttribute("qrString", qrString);
        model.addAttribute("MaDonHang", orderID);
        model.addAttribute("SoTien", txtTien);
        model.addAttribute("BangChu", txtHoTen);

        return "user/home/momoQR";
    }
}