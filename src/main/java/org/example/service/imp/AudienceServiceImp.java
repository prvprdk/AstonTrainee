package org.example.service.imp;

import org.example.entity.Audience;
import org.example.repo.AudienceRepo;
import org.example.service.AudienceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AudienceServiceImp implements AudienceService {


    private final AudienceRepo audienceRepo;


    public AudienceServiceImp(AudienceRepo audienceRepo) {
        this.audienceRepo = audienceRepo;
    }

    @Override
    public void add(Audience audience) {
        audienceRepo.add(audience);
    }

    @Override
    public void update(int id, Audience updateAudience) {
        audienceRepo.update(updateAudience);
    }

    @Override
    public void deleteById(int id) {
        audienceRepo.deleteById(id);
    }

    @Override
    public Optional<Audience> findById(int id) {
        return audienceRepo.findById(id);
    }

    @Override
    public List<Audience> findAll() {
        return audienceRepo.findAll();
    }
}
