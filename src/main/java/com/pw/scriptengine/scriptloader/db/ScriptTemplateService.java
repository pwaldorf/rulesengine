package com.pw.scriptengine.scriptloader.db;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScriptTemplateService {

    private final ScriptTemplateRepository  scriptTemplateRepository;

    public ScriptTemplateService(ScriptTemplateRepository scriptTemplateRepository) {
        this.scriptTemplateRepository = scriptTemplateRepository;
    }

    public List<ScriptTemplate> getScriptTemplates(String profileName) {
        return scriptTemplateRepository.findByProfileNameOrderByScriptOrder(profileName);
    }
}