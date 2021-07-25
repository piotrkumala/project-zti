package com.example.projectzti.database.services;

import com.example.projectzti.database.models.Survey;
import com.example.projectzti.database.repositories.SurveyRepository;
import com.example.projectzti.shared.ClientSurvey;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class SurveyService {
    private final SurveyRepository repository;

    public SurveyService(SurveyRepository repository) {
        this.repository = repository;
    }

    public Stream<Survey> getAllSurveys() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false);
    }

    public UUID insertSurvey(ClientSurvey survey) {
        var entityToSave = new Survey(survey);
        var entity = this.repository.save(entityToSave);
        return entity.getId();
    }

    public Survey getSurvey(UUID id) {
        return this.repository.findById(id).orElse(null);
    }
}
