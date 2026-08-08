package com.tatasteel.production.controller;

import com.tatasteel.production.entity.CoilPdi;
import com.tatasteel.production.repository.CoilPdiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductionController {

    private final CoilPdiRepository coilPdiRepository;

    @GetMapping("/live")
    public List<CoilPdi> getLiveData() {

        return coilPdiRepository
                .findAllByOrderByBirthTimeAsc();
    }
}