
INSERT INTO rules (ruleset_name, rule_id, priority, type, condition, pass, fail, always, context, class_name, condition_bean_name, condition_bean_method, pass_bean_name, pass_bean_method, fail_bean_name, fail_bean_method, always_bean_name, always_bean_method, description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_TEST1',
       '1',
       'MVEL',
       'debitAmount < 10',
       'System.out.println("PJW MVEL")',
       'Field1:Value1',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'Debit Amount greater than 10');

-- *************************************************************************************************************************************************************************************
INSERT INTO rules (ruleset_name, rule_id, priority, type, condition, pass, fail, always, context, class_name, condition_bean_name, condition_bean_method, pass_bean_name, pass_bean_method, fail_bean_name, fail_bean_method, always_bean_name, always_bean_method, description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_TEST2',
       '2',
       'SPRING',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'sampleSpringRules',
       'isAdult',
       'sampleSpringRules',
       'sayHello',
       ' ',
       ' ',
       ' ',
       ' ',
       'Debit Amount greater than 10');

-- *************************************************************************************************************************************************************************************
INSERT INTO rules (ruleset_name, rule_id, priority, type, condition, pass, fail, always, context, class_name, condition_bean_name, condition_bean_method, pass_bean_name, pass_bean_method, fail_bean_name, fail_bean_method, always_bean_name, always_bean_method, description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_TEST3',
       '3',
       'JAVA',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'com.pw.rulesengine.samplerules.SampleJavaRule',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'Animal Name Tiger');
