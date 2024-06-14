package com.yc.sevnenstore.controller;

import com.yc.sevnenstore.en.DrawCount;
import com.yc.sevnenstore.service.DrawCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/draw-count")
public class DrawCountController {

    @Autowired
    private DrawCountService drawCountService;

    @GetMapping("/{id}")
    public DrawCount getDrawCountById(@PathVariable("id") Integer id) {
        return drawCountService.getById(id);
    }

    @GetMapping
    public List<DrawCount> getAllDrawCounts() {
        return drawCountService.list();
    }

    @PostMapping
    public boolean insertDrawCount(@RequestBody DrawCount drawCount) {
        return drawCountService.save(drawCount);
    }


    @PutMapping
    public boolean updateDrawCount(@RequestBody DrawCount drawCount) {
        return drawCountService.updateById(drawCount);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDrawCount(@PathVariable("id") Integer id) {
        return drawCountService.removeById(id);
    }
}
