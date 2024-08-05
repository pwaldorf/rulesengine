CREATE TABLE rules (
  ruleset_name varchar(256) NOT NULL,
  rule_id varchar(256) NOT NULL,
  priority integer,
  condition_type varchar(256) NOT NULL,
  pass_action_type varchar(256) NOT NULL,
  fail_action_type varchar(256) NOT NULL,
  always_action_type varchar(256) NOT NULL,

  -- MVEL Rules Fields
  condition_context varchar(2000),
  condition varchar(2000),
  pass_context varchar(2000),
  pass varchar(2000),
  fail_context varchar(2000),
  fail varchar(2000),
  always_context varchar(2000),
  always varchar(2000),

  condition_class_name varchar(256) NOT NULL,
  pass_class_name varchar(256) NOT NULL,
  fail_class_name varchar(256) NOT NULL,
  always_class_name varchar(256) NOT NULL,

  -- Bean Rules Fields_class_name varchar(256) NOT NULL,
  condition_bean_name varchar(256) NOT NULL,
  condition_bean_method varchar(256) NOT NULL,
  pass_bean_name varchar(256) NOT NULL,
  pass_bean_method varchar(256) NOT NULL,
  fail_bean_name varchar(256) NOT NULL,
  fail_bean_method varchar(256) NOT NULL,
  always_bean_name varchar(256) NOT NULL,
  always_bean_method varchar(256) NOT NULL,
  description varchar(10000),
  PRIMARY KEY (ruleset_name, rule_id)
);
