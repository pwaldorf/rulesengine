
INSERT INTO rules (ruleset_name, rule_id, priority, type, condition, action, class_name, condition_bean_name, condition_bean_method, action_bean_name, action_bean_method, description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_TEST1',
       '1',
       'MVEL',
       'debitAmount < 10',
       'System.out.println("PJW MVEL")',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'Debit Amount greater than 10');

-- *************************************************************************************************************************************************************************************
INSERT INTO rules (ruleset_name, rule_id, priority, type, condition, action, class_name, condition_bean_name, condition_bean_method, action_bean_name, action_bean_method, description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_TEST2',
       '2',
       'SPRING',
       ' ',
       ' ',
       ' ',
       'sampleSpringRules',
       'isAdult',
       'sampleSpringRules',
       'sayHello',
       'Debit Amount greater than 10');

-- *************************************************************************************************************************************************************************************
INSERT INTO rules (ruleset_name, rule_id, priority, type, condition, action, class_name, condition_bean_name, condition_bean_method, action_bean_name, action_bean_method, description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_TEST3',
       '3',
       'JAVA',
       ' ',
       ' ',
       'com.pw.rulesengine.samplerules.SampleJavaRule',
       ' ',
       ' ',
       ' ',
       ' ',
       'Animal Name Tiger');
