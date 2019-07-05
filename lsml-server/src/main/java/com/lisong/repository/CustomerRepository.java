package com.lisong.repository;

import com.lisong.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByIdAndDeleted(Long custId, Integer deleted);

    Customer findByOpenIdAndDeleted(String openId, Integer deleted);
}
