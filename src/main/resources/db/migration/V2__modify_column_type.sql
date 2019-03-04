alter table staff modify column create_by int(11)  NOT NULL DEFAULT '0';
alter table staff modify column update_by int(11)  NOT NULL DEFAULT '0';
alter table staff modify column delete_by int(11)  NOT NULL DEFAULT '0';
alter table staff modify column create_at datetime  default null;
alter table staff modify column update_at datetime  default null;
alter table staff modify column delete_at datetime  default null;