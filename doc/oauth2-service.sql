CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `client_id` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端ID',
  `client_name` varchar(100) NOT NULL DEFAULT '' COMMENT '客户端名称',
  `client_uri` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端的首页',
  `client_secret` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端秘钥',
  `authorized_grant_types` varchar(256) NOT NULL DEFAULT '' COMMENT '授权类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `status` tinyint(4) NOT NULL COMMENT '状态，1可用，-1已删除，0屏蔽',
  `memo` varchar(256) DEFAULT '' COMMENT '附件信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='客户端详细信息';

CREATE TABLE `oauth_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `client_id` varchar(256) NOT NULL DEFAULT '' COMMENT '客户端id',
  `token_id` varchar(256) NOT NULL DEFAULT '' COMMENT '令牌',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `available_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '有效时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `status` int(4) NOT NULL COMMENT '状态，1正常，-1删除，0屏蔽',
  `memo` varchar(256) DEFAULT '' COMMENT '附加信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='发放令牌';

CREATE TABLE `oauth_access_log_201803` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户名称',
  `client_id` varchar(50) NOT NULL DEFAULT '' COMMENT '客户端id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '授权时间',
  `user_ip` varchar(25) DEFAULT NULL COMMENT '用户ip',
  `sso_wsid` char(32) NOT NULL DEFAULT '' COMMENT '用户sso',
  `memo` varchar(256) DEFAULT '' COMMENT '附加信息',
  PRIMARY KEY (`user_id`,`client_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权日志';