package com.example.BenXe.Service;

import com.example.BenXe.Model.Invoice;
import com.example.BenXe.Repository.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private IInvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> getAllInvoices(Integer pageNo,
                                        Integer pageSize,
                                        String sortBy) {
        return invoiceRepository.findAllInvoices(pageNo, pageSize, sortBy);
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

//    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('ADMIN')")
//    public List<Invoice> searchInvoice(String keyword) {
//        return invoiceRepository.searchInvoice(keyword);
//    }
}