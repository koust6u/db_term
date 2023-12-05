package edu.pnu.pnumenuselector.web.mvc.repository;

import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
