package com.yc.sevnenstore.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yc.sevnenstore.en.PrizeHistory;
import com.yc.sevnenstore.mapper.PrizeHistoryMapper;
import org.springframework.stereotype.Service;

@Service
public class PrizeHistoryServiceImpl extends ServiceImpl<PrizeHistoryMapper, PrizeHistory> implements PrizeHistoryService {
}
