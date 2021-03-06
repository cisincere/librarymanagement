package com.l.library.auth.authorization.provider;

import com.l.library.auth.authorization.entity.Role;
import com.l.library.auth.authorization.entity.User;
import com.l.library.manangement.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

// feign 服务内部的接口调用调用 name:服务名称 fallback:失败回调的类(该类中写失败回调的方法)
@FeignClient(name = "organization", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {

    @GetMapping(value = "/user")
    Result<User> getUserByUniqueId(@RequestParam("uniqueId") String uniqueId);

    @GetMapping(value = "/role/user/{userId}")
    Result<Set<Role>> queryRolesByUserId(@PathVariable("userId") String userId);
}
