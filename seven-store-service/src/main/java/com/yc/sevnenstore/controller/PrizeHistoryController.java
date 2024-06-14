package com.yc.sevnenstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.yc.sevnenstore.en.PrizeHistory;
import com.yc.sevnenstore.service.PrizeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prizeHistory")
public class PrizeHistoryController {

    @Autowired
    private PrizeHistoryService prizeHistoryService;

    @PostMapping
    public boolean addPrizeHistory(@RequestBody PrizeHistory prizeHistory) {
        return prizeHistoryService.save(prizeHistory);
    }

    @DeleteMapping("/{id}")
    public boolean deletePrizeHistory(@PathVariable Long id) {
        return prizeHistoryService.removeById(id);
    }

    @PutMapping
    public boolean updatePrizeHistory(@RequestBody PrizeHistory prizeHistory) {
        return prizeHistoryService.updateById(prizeHistory);
    }

    @GetMapping("/{id}")
    public PrizeHistory getPrizeHistory(@PathVariable Long id) {
        return prizeHistoryService.getById(id);
    }

    @GetMapping
    public List<PrizeHistory> getAllPrizeHistories() {
        return prizeHistoryService.list();
    }

    @GetMapping("/probability")
    public List<PrizeHistory> getPrizeHistoriesByProbability(@RequestParam Double probability) {
        QueryWrapper<PrizeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("probability", probability);
        return prizeHistoryService.list(queryWrapper);
    }
}
