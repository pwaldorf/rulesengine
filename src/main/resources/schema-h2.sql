CREATE TABLE workflow (
  process_name varchar(256) NOT NULL,
  step_group varchar(256) NOT NULL,
  step_order integer,
  step_rule_type varchar(256) NOT NULL,
  step_pass_type varchar(256) NOT NULL,
  step_fail_type varchar(256) NOT NULL,
  step_always_type varchar(256) NOT NULL,

  break_on_pass boolean,
  break_on_fail boolean,

  -- MVEL Rules Fields
  step_rule_context varchar(2000),
  step_rule varchar(2000),
  step_pass_context varchar(2000),
  step_pass varchar(2000),
  step_fail_context varchar(2000),
  step_fail varchar(2000),
  step_always_context varchar(2000),
  step_always varchar(2000),

  -- Java Class Rules
  step_rule_class_name varchar(256) NOT NULL,
  step_pass_class_name varchar(256) NOT NULL,
  step_fail_class_name varchar(256) NOT NULL,
  step_always_class_name varchar(256) NOT NULL,

  -- Bean Rules
  step_rule_bean_name varchar(256) NOT NULL,
  step_rule_bean_method varchar(256) NOT NULL,
  step_pass_bean_name varchar(256) NOT NULL,
  step_pass_bean_method varchar(256) NOT NULL,
  step_fail_bean_name varchar(256) NOT NULL,
  step_fail_bean_method varchar(256) NOT NULL,
  step_always_bean_name varchar(256) NOT NULL,
  step_always_bean_method varchar(256) NOT NULL,

  description varchar(10000),
  PRIMARY KEY (process_name, step_group, step_order)
);
