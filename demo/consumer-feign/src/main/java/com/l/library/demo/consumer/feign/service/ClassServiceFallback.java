package com.l.library.demo.consumer.feign.service;

import com.l.library.manangement.common.core.entity.vo.Result;
import com.l.library.manangement.common.core.exception.SystemErrorType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClassServiceFallback implements ClassService {
    @Override
    public Result users(String name) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    @Override
    public Result users(Map map) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }
}

