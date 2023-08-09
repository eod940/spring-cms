package com.zerobase.cms.user.service;

import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.repository.CustomerRepository;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static com.zerobase.cms.user.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form) {
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email) {
        email = email.toLowerCase(Locale.ROOT);
        return customerRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (customer.isVerify()) {
            throw new CustomException(ALREADY_VERIFY);
        }
        if (!customer.getVerificationCode().equals(code)) {
            throw new CustomException(WRONG_VERIFICATION);
        }
        if (customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(EXPIRE_CODE);
        }
        customer.setVerify(true);
    }

    @Transactional
    public LocalDateTime changeCustomerValidateEmail(Long customerId, String verificationCode) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            throw new CustomException(USER_NOT_FOUND);
        } else {
            Customer customer = customerOptional.get();
            customer.setVerificationCode(verificationCode);
            customer.setVerifyExpiredAt(LocalDateTime.now().plusHours(2));

            return customer.getVerifyExpiredAt();
        }
    }
}
