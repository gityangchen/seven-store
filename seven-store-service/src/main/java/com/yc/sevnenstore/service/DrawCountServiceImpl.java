package com.yc.sevnenstore.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.sevnenstore.en.DrawCount;
import com.yc.sevnenstore.mapper.DrawCountMapper;
import org.springframework.stereotype.Service;

@Service
public class DrawCountServiceImpl extends ServiceImpl<DrawCountMapper, DrawCount> implements DrawCountService {
}
