CREATE TABLE script_template (
  id bigint not null AUTO_INCREMENT,
  profile_name varchar(256) NOT NULL,
  script_order integer NOT NULL,
  script_name varchar(256) NOT NULL,
  script_body varchar(4096) NOT NULL,
  description varchar(10000),
  PRIMARY KEY (id)
);

create sequence script_template_seq start with 1 increment by 50;