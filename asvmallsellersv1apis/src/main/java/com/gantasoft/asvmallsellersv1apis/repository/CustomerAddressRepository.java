package com.gantasoft.asvmallsellersv1apis.repository;

import com.gantasoft.asvmallsellersv1apis.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

    // Find by customer_id (Long type)
    List<CustomerAddress> findByCustomerCustomerId(Long customerId);
}
