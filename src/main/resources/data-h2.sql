
INSERT INTO workflow (process_name
                 , step_group
                 , step_order
                 , step_rule_type
                 , step_pass_type
                 , step_fail_type
                 , step_always_type
                 , break_on_pass
                 , break_on_fail
                 , step_rule_context
                 , step_rule
                 , step_pass_context
                 , step_pass
                 , step_fail_context
                 , step_fail
                 , step_always_context
                 , step_always
                 , step_rule_class_name
                 , step_pass_class_name
                 , step_fail_class_name
                 , step_always_class_name
                 , step_rule_bean_name
                 , step_rule_bean_method
                 , step_pass_bean_name
                 , step_pass_bean_method
                 , step_fail_bean_name
                 , step_fail_bean_method
                 , step_always_bean_name
                 , step_always_bean_method
                 , description)
       VALUES (
       'NRT_RULESET_TEST1',
       'startGroup',
       1,
       'SPRING',
       'STEPGROUP',
       ' ',
       ' ',
       'false',
       'true',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'NRT_RULESET_TEST2',
       ' ',
       ' ',
       'testPerfRules',
       'isSourceSystemGTM',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'Source System GTM');

INSERT INTO workflow (process_name
                 , step_group
                 , step_order
                 , step_rule_type
                 , step_pass_type
                 , step_fail_type
                 , step_always_type
                 , break_on_pass
                 , break_on_fail
                 , step_rule_context
                 , step_rule
                 , step_pass_context
                 , step_pass
                 , step_fail_context
                 , step_fail
                 , step_always_context
                 , step_always
                 , step_rule_class_name
                 , step_pass_class_name
                 , step_fail_class_name
                 , step_always_class_name
                 , step_rule_bean_name
                 , step_rule_bean_method
                 , step_pass_bean_name
                 , step_pass_bean_method
                 , step_fail_bean_name
                 , step_fail_bean_method
                 , step_always_bean_name
                 , step_always_bean_method
                 , description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_RULESET_TEST2',
       2,
       'SPRING',
       'SPRING',
       ' ',
       ' ',
       'false',
       'false',
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
       ' ',
       'testPerfRules',
       'isCurrencyCLS',
       'testPerfRules',
       'setStatusCode',
       ' ',
       ' ',
       ' ',
       ' ',
       'Check CLS Currency');

-- *************************************************************************************************************************************************************************************
INSERT INTO workflow (process_name
                 , step_group
                 , step_order
                 , step_rule_type
                 , step_pass_type
                 , step_fail_type
                 , step_always_type
                 , break_on_pass
                 , break_on_fail
                 , step_rule_context
                 , step_rule
                 , step_pass_context
                 , step_pass
                 , step_fail_context
                 , step_fail
                 , step_always_context
                 , step_always
                 , step_rule_class_name
                 , step_pass_class_name
                 , step_fail_class_name
                 , step_always_class_name
                 , step_rule_bean_name
                 , step_rule_bean_method
                 , step_pass_bean_name
                 , step_pass_bean_method
                 , step_fail_bean_name
                 , step_fail_bean_method
                 , step_always_bean_name
                 , step_always_bean_method
                 , description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_RULESET_TEST2',
       3,
       'SPRING',
       'STEPGROUP',
       ' ',
       ' ',
       'false',
       'false',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'NRT_RULESET_TEST3',
       ' ',
       ' ',
       'testPerfRules',
       'isStatusCodeSOKS',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       ' ',
       'Debit Amount greater than 10');

-- *************************************************************************************************************************************************************************************
INSERT INTO workflow (process_name
                 , step_group
                 , step_order
                 , step_rule_type
                 , step_pass_type
                 , step_fail_type
                 , step_always_type
                 , break_on_pass
                 , break_on_fail
                 , step_rule_context
                 , step_rule
                 , step_pass_context
                 , step_pass
                 , step_fail_context
                 , step_fail
                 , step_always_context
                 , step_always
                 , step_rule_class_name
                 , step_pass_class_name
                 , step_fail_class_name
                 , step_always_class_name
                 , step_rule_bean_name
                 , step_rule_bean_method
                 , step_pass_bean_name
                 , step_pass_bean_method
                 , step_fail_bean_name
                 , step_fail_bean_method
                 , step_always_bean_name
                 , step_always_bean_method
                 , description)
       VALUES (
       'NRT_RULESET_TEST1',
       'NRT_RULESET_TEST3',
       4,
       'SPRING',
       ' ',
       ' ',
       'SPRING',
       'false',
       'false',
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
       ' ',
       'testPerfRules',
       'isFullSwiftBic',
       ' ',
       ' ',
       ' ',
       ' ',
       'testPerfRules',
       'setSourceSystemId',
       'Animal Name Tiger');
