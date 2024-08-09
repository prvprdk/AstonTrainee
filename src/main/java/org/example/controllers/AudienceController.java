package org.example.controllers;

import org.example.entity.Audience;
import org.example.service.AudienceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("audience/{id}")
public class AudienceController {


    private final AudienceService audienceService;


    public AudienceController(AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    @GetMapping
    public List<Audience> findAll() {
        return audienceService.findAll();
    }


}
