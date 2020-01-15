package com.l.library.demo.consumer.feign.rest;

import com.l.library.demo.consumer.feign.service.ClassService;
import com.l.library.manangement.common.core.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping("/classes")
    public Result hello(@RequestParam String name) {
        return classService.users(name);
    }

    @PostMapping("/classes")
    public Result hello(@RequestBody Map map) {
        return classService.users(map);
    }

}
