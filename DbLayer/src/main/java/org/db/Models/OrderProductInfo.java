package org.db.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "order_product", uniqueConstraints={
        @UniqueConstraint(columnNames = {"order_id", "product_id"})
})
@NamedNativeQuery(name = "OrderProductInfo.findByOrder",
        query = "SELECT * FROM order_product WHERE order_id = ?",
        resultClass = OrderProductInfo.class)
@NamedNativeQuery(name = "OrderProductInfo.findByCompany",
        query = "SELECT order_product.order_id, order_product.product_id, order_product.amount, order_product.price " +
                "FROM order_product " +
                "INNER JOIN products ON products.product_id=order_product.product_id " +
                "INNER JOIN companies ON products.company_id=companies.company_id " +
                "WHERE companies.company_id = ?",
        resultClass = OrderProductInfo.class)
public class OrderProductInfo {
    @EmbeddedId
    private OrderProductId orderProductId;
    @Transient
    private String productName;
    @Column(name = "price", updatable = true)
    private float price;
    @Column(name = "amount", updatable = true)
    private int num;
}
