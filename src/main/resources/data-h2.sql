INSERT INTO workflow (process_name
                 , step_template
                 , steps
                 , description)
       VALUES (
       'NRT_RULESET_TEST1',
       '{
            "stepGroups": [
                {
                    "stepGroupName": "startGroup",
                    "stepTemplates": [
                        {
                            "stepGroupName": "startGroup",
                            "stepOrder": 1,
                            "stepRule": "isCurrencyCLS",
                            "stepPass": "NRT_RULESET_TEST2",
                            "stepFail": "NRT_RULESET_TEST2",
                            "breakOnPass": "false",
                            "breakOnFail": "true",
                            "stepDescription": "source system GTM"
                        }
                    ]
                },
                {
                    "stepGroupName": "NRT_RULESET_TEST2",
                    "stepTemplates": [
                        {
                            "stepGroupName": "NRT_RULESET_TEST2",
                            "stepOrder": 1,
                            "stepRule": "isStatusCodeSOKS",
                            "breakOnPass": "false",
                            "breakOnFail": "false",
                            "stepDescription": "source system GTM"
                        }
                    ]
                }
            ]
}',
      '{
  "steps": [
    {
      "stepName": "NRT_RULESET_TEST2",
      "stepType": "STEPGROUP",
      "stepObject": "NRT_RULESET_TEST2"
    },
    {
      "stepName": "NRT_RULESET_TEST3",
      "stepType": "STEPGROUP",
      "stepObject": "NRT_RULESET_TEST3"
    },
    {
      "stepName": "isCurrencyCLS",
      "stepType": "JAVA",
      "stepObject": "com.pw.workflowengine.sampleworkflow.SampleJavaAction"
    },
    {
      "stepName": "isStatusCodeSOKS",
      "stepType": "JAVA",
      "stepObject": "com.pw.workflowengine.sampleworkflow.SampleJavaCondition"
    }
  ]
     }',
       'Source System GTM');
