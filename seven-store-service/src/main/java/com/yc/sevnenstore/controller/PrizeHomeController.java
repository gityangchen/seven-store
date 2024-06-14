package com.yc.sevnenstore.controller;

import com.yc.sevnenstore.en.DrawCount;
import com.yc.sevnenstore.en.Prize;
import com.yc.sevnenstore.en.PrizeHistory;
import com.yc.sevnenstore.service.DrawCountService;
import com.yc.sevnenstore.service.PrizeHistoryService;
import com.yc.sevnenstore.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prizeView")
public class PrizeHomeController {
    @Autowired
    private PrizeService   prizeService;
    @Autowired
    private DrawCountService drawCountService;
    @Autowired
    private PrizeHistoryService prizeHistoryService;
    @GetMapping("/getShowList")
    public List<Prize> getAllPrizes() {
        List<Prize> prizes = prizeService.list();
        List<Prize> selectedPrizes = new ArrayList<>();
        Set<Prize> selectedPrizeSet = new HashSet<>();

        Random random = new Random();

        while (selectedPrizes.size() < 9) {
            while (selectedPrizes.size() < 9) {
                Prize prize = prizes.get(random.nextInt(prizes.size()));
                if (!selectedPrizeSet.contains(prize) && random.nextDouble() < prize.getDisplayProbability()) {
                    selectedPrizes.add(prize);
                    selectedPrizeSet.add(prize);
                }
            }
        }
        return selectedPrizes;
    }

    @GetMapping("/check")
    public Prize check(@RequestParam List<String> ids) {
        //List<Prize> prizes = prizeService.list();
        DrawCount drawCount = drawCountService.getById(1);
        if (drawCount == null||drawCount.getDrawCount()<1) {
            return null;
        }
        drawCount.setDrawCount(drawCount.getDrawCount()-1);
        List<Prize> prizes = prizeService.lambdaQuery().in(Prize::getId, ids).list();
        Map<Long, Double> probabilities = getProbabilities(prizes);
        Map<Long, Double> normalizedProbabilities = normalizeProbabilities(probabilities);
        drawCountService.saveOrUpdate(drawCount);
        PrizeHistory prizeHistory = new PrizeHistory();
        Prize serviceById = prizeService.getById(selectPrizeBasedOnProbability(normalizedProbabilities));
        prizeHistory.setPrizeName(serviceById.getName());
        prizeHistory.setProbability(serviceById.getProbability());
        prizeHistoryService.save(prizeHistory);
        return serviceById;
    }

    private Map<Long, Double> getProbabilities(List<Prize> prizes) {
        return prizes.stream()
                .filter(prize -> !prize.getDisabled()) // 过滤掉被禁用的奖品
                .collect(Collectors.toMap(Prize::getId, Prize::getProbability));
    }

    private Map<Long, Double> normalizeProbabilities(Map<Long, Double> probabilities) {
        double totalProbability = probabilities.values().stream().mapToDouble(Double::doubleValue).sum();
        Map<Long, Double> normalizedProbabilities = new HashMap<>();
        for (Map.Entry<Long, Double> entry : probabilities.entrySet()) {
            normalizedProbabilities.put(entry.getKey(), entry.getValue() / totalProbability);
        }
        return normalizedProbabilities;
    }

    private Long selectPrizeBasedOnProbability(Map<Long, Double> probabilities) {
        double randomValue = new Random().nextDouble();

        double cumulativeProbability = 0.0;
        for (Map.Entry<Long, Double> entry : probabilities.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (randomValue <= cumulativeProbability) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Failed to select a prize based on probability.");
    }


}
