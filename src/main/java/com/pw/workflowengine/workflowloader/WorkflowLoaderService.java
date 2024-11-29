package com.pw.workflowengine.workflowloader;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pw.workflowengine.stepengine.WorkflowResource;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.StepGroups;
import com.pw.workflowengine.workflow.Steps;
import com.pw.workflowengine.workflowloader.db.WorkflowRepository;

@Service
public class WorkflowLoaderService implements WorkflowResource {

    private final WorkflowRepository rulesRepository;

    public WorkflowLoaderService(WorkflowRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    @SuppressWarnings("null")
    @Override
    public List<StepGroup> getStepGroupsByProcessName(String processName){

        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the JSON string into the StepGroup object
        StepGroups stepGroups = null;
        try {
            stepGroups = objectMapper.readValue(rulesRepository.findByProcessName(processName).getStepTemplate(), StepGroups.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stepGroups.getStepGroups();
    }

    @SuppressWarnings("null")
    @Override
    public List<Step> getStepsByProcessName(String processName) {

        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the JSON string into the StepGroup object
        Steps steps = null;
        try {
            steps = objectMapper.readValue(rulesRepository.findByProcessName(processName).getSteps(), Steps.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return steps.getSteps();

    }

    // private Map<String, Object> createMapFromDelimitedString(String input) {
    //     Map<String, Object> map = new HashMap<>();

    //     // Split the input string by commas to get key-value pairs
    //     String[] pairs = input.split(",");

    //     for (String pair : pairs) {
    //         // Split each pair by the colon to get the key and value
    //         String[] keyValue = pair.split(":");
    //         if (keyValue.length == 2) {
    //             String key = keyValue[0].trim();
    //             String value = keyValue[1].trim();
    //             map.put(key, value);
    //         }
    //     }
    //     return map;
    // }

}
