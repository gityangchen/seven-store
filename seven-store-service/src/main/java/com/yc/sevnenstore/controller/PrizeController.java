package com.yc.sevnenstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yc.sevnenstore.en.Prize;
import com.yc.sevnenstore.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prize")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @PostMapping
    public boolean addPrize(@RequestBody Prize prize) {
        return prizeService.save(prize);
    }

    @DeleteMapping("/{id}")
    public boolean deletePrize(@PathVariable Long id) {
        return prizeService.removeById(id);
    }

    @PutMapping
    public boolean updatePrize(@RequestBody Prize prize) {
        return prizeService.updateById(prize);
    }

    @GetMapping("/{id}")
    public Prize getPrize(@PathVariable Long id) {
        return prizeService.getById(id);
    }

    @GetMapping
    public List<Prize> getAllPrizes() {
        return prizeService.list();
    }

    @GetMapping("/search")
    public List<Prize> searchPrizes(@RequestParam String name) {
        QueryWrapper<Prize> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return prizeService.list(queryWrapper);
    }

    @GetMapping("/enabled")
    public List<Prize> getEnabledPrizes() {
        QueryWrapper<Prize> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("disabled", false);
        return prizeService.list(queryWrapper);
    }
}
