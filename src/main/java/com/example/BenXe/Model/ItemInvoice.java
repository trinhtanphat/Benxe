package com.example.BenXe.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Entity
@Table(name = "item_invoice")
public class ItemInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "quantity")
    @Positive(message = "Quantity must be positive")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_chuyen_xe_id", referencedColumnName = "MaChuyenXe")
    @ToString.Exclude
    private ChuyenXe chuyenXe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ToString.Exclude
    private Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        ItemInvoice that = (ItemInvoice) o;
        return getId() != null && Objects.equals(getId(),
                that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}