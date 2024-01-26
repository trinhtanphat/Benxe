package com.example.BenXe.Service;

import com.example.BenXe.Repository.IChuyenXeRepository;
import com.example.BenXe.daos.Cart;
import com.example.BenXe.daos.Item;
import com.example.BenXe.Model.CustomTaiKhoanDetail;
import com.example.BenXe.Model.Invoice;
import com.example.BenXe.Model.ItemInvoice;
import com.example.BenXe.Repository.IInvoiceRepository;
import com.example.BenXe.Repository.IItemInvoiceRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    //Định danh cookie/session lưu ở dữ liệu
    private static final String CART_SESSION_KEY = "cart";

//    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public Cart getCart(@NotNull HttpSession session) {
        //Trả về Cart cho khách hàng
        return Optional.ofNullable(
                        //Nếu như giỏ hàng tồn tại thì nó trả về cái đã có
                        (Cart) session.getAttribute(CART_SESSION_KEY))
                .orElseGet(() -> {
                    //Trường hợp chưa có thì sẽ tạo mới giỏ hàng
                    Cart cart = new Cart();
                    //Lưu trữ vào trong session của ứng dụnn
                    session.setAttribute(CART_SESSION_KEY, cart);
                    //trả về cart
                    return cart;
                });
    }

    public int getCartSize(@NotNull HttpSession session) {
        Cart cart = getCart(session);
        return cart.getCartItems().size();
    }

    //Viết hàm updata Cart vào trong biến session
    //Lưu ý mỗi USER.khách hàng khi mà truy cập vào trang web sẽ tự động tạo
    //1 biến session riêng cho user đó
//    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public void updateCart(@NotNull HttpSession session, Cart cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void removeCart(@NotNull HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

//    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public int getSumQuantity(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToInt(Item::getQuantity)
                .sum();
    }

//    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public double getSumPrice(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    //viet ham luu gio hang
    private final IInvoiceRepository iInvoiceRepository;

    private final IItemInvoiceRepository iItemInvoiceRepository;

    private final IChuyenXeRepository iChuyenXeRepository;

//    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
    public Invoice saveCart(@NotNull HttpSession session) {
        //lay ve thong tin cart trong session
        var cart = getCart(session);
        //neu cart rong thi khong lam gi het
        if (cart.getCartItems().isEmpty())
            return null; //thoat khoi ham

        var invoice = new Invoice();

        invoice.setInvoiceDate(new Date());

        invoice.setPrice((getSumPrice(session)));

        //set user/khách hàng mua
        invoice.setTaiKhoan(
                ((CustomTaiKhoanDetail) SecurityContextHolder.getContext().
                        getAuthentication().getPrincipal()).getTaiKhoan());

        iInvoiceRepository.save(invoice);

        //tien hanh them chi tiet san pham trong hoa don
        //dung vong lap de quet tung san pham trong cart
        //moi san pham trong cart se luu vao bien item
        cart.getCartItems().forEach(item -> {
            //tao chi tiet don hang invoice
            var items = new ItemInvoice();
            //set hoa don tong
            items.setInvoice(invoice);
            //set so luong cua item san pham
            items.setQuantity(item.getQuantity());
            //set thong tin cua quyen sach mua
            items.setChuyenXe(iChuyenXeRepository.findById(item.getChuyenXeId()).orElseThrow());
            //tien hanh luu vao csdl
            iItemInvoiceRepository.save(items);
        });
        //xoa gio hang cu
        removeCart(session);
        return invoice;
    }
}