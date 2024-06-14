package com.yc.sevnenstore.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yc.sevnenstore.en.Prize;
import com.yc.sevnenstore.mapper.PrizeMapper;
import org.springframework.stereotype.Service;

@Service
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, Prize> implements PrizeService {
}
