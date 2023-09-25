package com.zerobase.cms.user.service;

import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepo;

    public Optional<Customer> findValidCustomerByEmailAndPassword(
            String email, String password) {

        return customerRepo.findByEmail(email).stream()
                .filter(e -> e.getPassword().equals(password) && e.isVerify())
                .findFirst();
    }

    public Optional<Customer> findByIdAndEmail(Long id,String email){
        return customerRepo.findById(id)
                .stream().filter(customer -> customer.getEmail().equals(email))
                .findFirst();
    }
}
