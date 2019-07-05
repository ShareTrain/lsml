package com.lisong.component.sms;

import com.lisong.component.sms.domain.SmsVerify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsVerifyRepository extends JpaRepository<SmsVerify, Long> {

    SmsVerify findByMobileAndDeleted(String mobile, Integer deleted);
}
