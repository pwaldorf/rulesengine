CREATE TABLE rules (
  ruleset_name varchar(256) NOT NULL,
  rule_id varchar(256) NOT NULL,
  priority integer,
  type varchar(256) NOT NULL,
  condition varchar(2000),
  action varchar(2000),
  class_name varchar(256) NOT NULL,
  condition_bean_name varchar(256) NOT NULL,
  condition_bean_method varchar(256) NOT NULL,
  action_bean_name varchar(256) NOT NULL,
  action_bean_method varchar(256) NOT NULL,
  description varchar(10000),
  PRIMARY KEY (ruleset_name, rule_id)
);
