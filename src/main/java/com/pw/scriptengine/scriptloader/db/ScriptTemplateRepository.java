package com.pw.scriptengine.scriptloader.db;

import java.util.List;

import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptTemplateRepository extends JpaRepository<ScriptTemplate, Long> {

    @SuppressWarnings("null")
    @OrderBy("scriptOrder")
    List<ScriptTemplate> findByProfileNameOrderByScriptOrder(String profileName);

    @SuppressWarnings("null")
    @OrderBy("scriptOrder")
    List<ScriptTemplate> findAll();
}