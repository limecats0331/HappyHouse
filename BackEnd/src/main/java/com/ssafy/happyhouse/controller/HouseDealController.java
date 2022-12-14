package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.entity.HouseDeal;
import com.ssafy.happyhouse.service.HouseDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/housedeal")
public class HouseDealController {

    private final HouseDealService houseDealService;

    @GetMapping("/dongcode/{dongCode}")
    public List<HouseDeal> getDealByDongCode(@PathVariable String dongCode) {
        return houseDealService.findDealByDongCode(dongCode);
    }

    @GetMapping("/aptcode/{aptCode}")
    public List<HouseDeal> getDealByApt(@PathVariable Long aptCode) {
        return houseDealService.findDealByAptCode(aptCode);
    }
}
