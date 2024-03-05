package org.db.JpaRepositories;

import org.db.Models.OrderProductId;
import org.db.Models.OrderProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductInfoRepository extends JpaRepository<OrderProductInfo, OrderProductId> {

    List<OrderProductInfo> findByOrder(int orderId);
    List<OrderProductInfo> findByCompany(int company_id);
}

