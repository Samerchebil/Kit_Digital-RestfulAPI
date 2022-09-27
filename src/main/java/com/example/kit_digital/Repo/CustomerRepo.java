package com.example.kit_digital.Repo;

import com.example.kit_digital.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo
        extends JpaRepository<Customer,Long> {
Optional<Customer> findByUserName(String username);
}
