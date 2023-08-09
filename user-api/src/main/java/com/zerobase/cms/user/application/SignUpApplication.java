package com.zerobase.cms.user.application;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import com.zerobase.cms.user.service.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code) {
        signUpCustomerService.verifyEmail(email, code);
    }
    public String customerSignUp(SignUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            // 이미 가입된 상태는 가입을 할 필요 없음
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_USER);
        } else {
            Customer customer = signUpCustomerService.signUp(form);

            String code = getRandomCode();

            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("tester@email.com")
                    .to(form.getEmail())
                    .subject("Account Verification")
                     .text(getVerificationEmailBody(form.getEmail(), form.getName(), code))
                    .build();
            log.info("Send email result : "+mailgunClient.sendEmail(sendMailForm).getBody());

            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.changeCustomerValidateEmail(customer.getId(), code);

            return "회원가입 완료";
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("안녕하세요 ").append(name)
                .append("!\n아래 링크를 눌러주세요.\n\n")
                .append("http://localhost:8080/signup/customer/verify?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }

}
