package com.example.BenXe.Repository;

import com.example.BenXe.Model.Invoice;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
    default List<Invoice> findAllInvoices(Integer pageNo,
                                          Integer pageSize,
                                          String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }

    //cac doi tuong thuc hien trong query phai anh xa khop voi class entities ma khai bao
    //SELECT * FROM Invoices i LEFT JOIN tai_khoan t on t.id = i.makh_id WHERE t.ten_dang_nhap LIKE "%khachhang%" or i.total LIKE "%khachhang%" or i.invoice_date LIKE "%khachhang%";
//    @Query("""
//            SELECT i.*
//            FROM Invoices i
//               WHERE i.total LIKE %?1%
//            """)
//    List<Invoice> searchInvoice(String keyword);
}