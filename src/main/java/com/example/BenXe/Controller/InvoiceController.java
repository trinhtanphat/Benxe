package com.example.BenXe.Controller;

import com.example.BenXe.Model.Invoice;
import com.example.BenXe.Service.InvoiceService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public String showAllInvoices(
            @NotNull Model model,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        model.addAttribute("invoices",
                invoiceService.getAllInvoices(pageNo, pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                invoiceService.getAllInvoices().size() / pageSize);
        return "invoice/list";
    }

    @GetMapping("/search")
    public String searchInvoice(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
//        model.addAttribute("invoices", invoiceService.searchInvoice(keyword));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                invoiceService.getAllInvoices().size() / pageSize);
        return "invoice/list";
    }
    //hien thi trang detail hoa don
    //link request domain/invoice/detai/id : id cua hoa don theo phuong thuc get
    //id cua hoa don se duoc gan vao bien Id
    @GetMapping("/detail/{id}")
    public String detailForm(@PathVariable("id") Long id, Model model)
    {
        //lay thong tin cua hoa don tu csdl ve thong qua invoiceService by ID
        //gan thong tin hoa don do vao bien de truyen qua view
        model.addAttribute("invoice",invoiceService.getInvoiceById(id));
        return "invoice/detail";
    }
}
