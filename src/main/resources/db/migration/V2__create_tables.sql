use learn;
CREATE TABLE `staff` (
          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
          `name` varchar(20)  NOT NULL DEFAULT '',
          `birthday` datetime not null default CURRENT_TIMESTAMP,
	        `phone` varchar(16) not null default '',
          `gender` int(1) NOT NULL DEFAULT '0',
          `img_url` varchar(128)  NOT NULL DEFAULT '' COMMENT '头像',
          `document_type` int(1) NOT NULL DEFAULT '0' COMMENT '证件类型',
          `document_number` varchar(64) NOT NULL DEFAULT '' COMMENT '证件号',
          `status` int(1) NOT NULL DEFAULT '0',
          `create_by` varchar(20)  NOT NULL DEFAULT '' COMMENT '创建人',
          `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
          `update_by` varchar(20)  NOT NULL DEFAULT '' COMMENT '修改人',
          `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
          `delete_by` varchar(20)  NOT NULL DEFAULT '' COMMENT '删除人',
          `delete_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
          PRIMARY KEY (`id`),
          KEY `ix_doc_num` (`document_number`)
        ) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;