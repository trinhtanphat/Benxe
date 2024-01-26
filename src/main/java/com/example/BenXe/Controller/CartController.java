package com.example.BenXe.Controller;

import com.example.BenXe.Model.Invoice;
import com.example.BenXe.Service.CartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @Autowired
    private PaymentController paymentController;

    @GetMapping
    public String showCart(HttpSession session,
                           @NotNull Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("totalPrice",
                cartService.getSumPrice(session));
        model.addAttribute("totalQuantity",
                cartService.getSumQuantity(session));
        return "user/home/cart";
    }
    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(HttpSession session,
                                 @PathVariable Long id) {
        var cart = cartService.getCart(session);
        cart.removeItems(id);
        return "redirect:/cart";
    }
    @GetMapping("/updatecart/{id}/{quantity}")
    public String updateCart(HttpSession session,
                             @PathVariable String id,
                             @PathVariable String quantity) {
        long idLong = Integer.parseInt(id);
        Integer quantityInteger = Integer.parseInt(quantity);
        var cart = cartService.getCart(session);
        cart.updateItems(idLong, quantityInteger);
        return "redirect:/cart";
    }
    @GetMapping("/clearCart")
    public String clearCart(HttpSession session) {
        cartService.removeCart(session);
        return "redirect:/cart ";
    }

    @GetMapping("/checkoutvnpay")
    public RedirectView checkoutvnpay(HttpSession session) throws UnsupportedEncodingException {
        Invoice invoice = cartService.saveCart(session);
        String url = paymentController.createPaymentVNP(invoice);
        return new RedirectView(url);
//        return "redirect:/cart ";
    }

    @GetMapping("/checkoutmomo")
    public String checkout(HttpSession session, Model model) throws UnsupportedEncodingException {
        Invoice invoice = cartService.saveCart(session);
        model.addAttribute("MaDonHang", invoice.getId());
        model.addAttribute("SoTien", invoice.getPrice());
        return "redirect:/khachhang/momoqr/" + invoice.getId();
    }
}