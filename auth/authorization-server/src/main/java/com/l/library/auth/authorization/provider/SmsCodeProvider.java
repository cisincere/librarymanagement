package com.l.library.auth.authorization.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Sms", fallback = SmsCodeProviderFallback.class)
public interface SmsCodeProvider {
    /**
     * @param mobile
     * @return
     */
    @GetMapping(value = "/sms/{mobile}")
    String getSmsCode(@PathVariable("mobile") String mobile, @RequestParam("businessType") String businessType);
}
