-- MySQL 数据库初始化脚本
-- 用于生产环境
-- 创建时间: 2025-07-22

-- 设置字符集
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 通知信息表
DROP TABLE IF EXISTS `t_notice_info`;
CREATE TABLE `t_notice_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userId` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(100) DEFAULT NULL COMMENT '用户名',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `type` varchar(50) DEFAULT NULL COMMENT '存证类型',
  `remark` longtext COMMENT '描述',
  `fileUrl` varchar(500) DEFAULT NULL COMMENT '文件地址',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sceneType` varchar(50) DEFAULT NULL COMMENT '业务场景',
  `code` varchar(100) DEFAULT NULL COMMENT '存证编号',
  `source` varchar(50) DEFAULT NULL COMMENT '客户来源',
  `noticeUrl` varchar(500) DEFAULT NULL COMMENT '通知URL',
  `platform` varchar(50) DEFAULT NULL COMMENT '存证平台',
  `hash` varchar(255) DEFAULT NULL COMMENT '存证hash',
  `sourceTag` varchar(50) DEFAULT NULL COMMENT '数据来源',
  `creatorName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`),
  KEY `idx_creator` (`creator`),
  KEY `idx_create_time` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知信息表';

-- 通知发布表
DROP TABLE IF EXISTS `t_notice_publish`;
CREATE TABLE `t_notice_publish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userId` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(100) DEFAULT NULL COMMENT '公告方',
  `title` varchar(500) DEFAULT NULL COMMENT '公告名称',
  `type` varchar(50) DEFAULT NULL COMMENT '存证类型',
  `remark` longtext COMMENT '公告正文',
  `fileUrl` varchar(500) DEFAULT NULL COMMENT '存证文件',
  `fileName` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `fileHash` varchar(255) DEFAULT NULL COMMENT '文件hash',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sceneType` varchar(50) DEFAULT NULL COMMENT '业务场景',
  `code` varchar(100) DEFAULT NULL COMMENT '公告编号',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `sourceType` varchar(50) DEFAULT NULL COMMENT '客户来源类型',
  `platform` varchar(50) DEFAULT NULL COMMENT '发布渠道',
  `noticeUrl` varchar(500) DEFAULT NULL COMMENT '公告链接',
  `hash` varchar(255) DEFAULT NULL COMMENT '存证hash',
  `sourceTag` varchar(50) DEFAULT NULL COMMENT '数据来源',
  `creatorName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  `managerName` varchar(100) DEFAULT NULL COMMENT '客户经理姓名',
  `managerPhone` varchar(20) DEFAULT NULL COMMENT '客户经理手机号',
  `authUrl` varchar(500) DEFAULT NULL COMMENT '认证书',
  `payUrl` varchar(500) DEFAULT NULL COMMENT '付款凭证',
  `status` varchar(10) DEFAULT '0' COMMENT '状态：0提交 1通过 2驳回 3发布 4隐藏',
  `reason` varchar(1000) DEFAULT NULL COMMENT '审核原因',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `publishTime` datetime DEFAULT NULL COMMENT '发布时间',
  `views` int(11) DEFAULT '0' COMMENT '浏览量',
  `pay_order_id` varchar(100) DEFAULT NULL COMMENT '支付订单流水号',
  `pay_type` varchar(20) DEFAULT NULL COMMENT '支付方式',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额(元)',
  `pay_status` varchar(10) DEFAULT '0' COMMENT '是否付款:0-待付款，1-已付款，2-等待支付结果',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_callback_time` datetime DEFAULT NULL COMMENT '支付回调时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`),
  KEY `idx_status` (`status`),
  KEY `idx_publish_time` (`publishTime`),
  KEY `idx_create_time` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知发布表';

-- 通知审核日志表
DROP TABLE IF EXISTS `t_notice_audit_log`;
CREATE TABLE `t_notice_audit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `noticeId` bigint(20) DEFAULT NULL COMMENT '公告ID',
  `userId` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(100) DEFAULT NULL COMMENT '用户名',
  `role` varchar(50) DEFAULT NULL COMMENT '角色',
  `reason` varchar(1000) DEFAULT NULL COMMENT '审批意见',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`id`),
  KEY `idx_notice_id` (`noticeId`),
  KEY `idx_create_time` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知审核日志表';

-- 合同信息表
DROP TABLE IF EXISTS `t_contract_info`;
CREATE TABLE `t_contract_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userId` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(100) DEFAULT NULL COMMENT '用户名',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `type` varchar(50) DEFAULT NULL COMMENT '存证类型',
  `remark` longtext COMMENT '描述',
  `fileUrl` varchar(500) DEFAULT NULL COMMENT '文件地址',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sceneType` varchar(50) DEFAULT NULL COMMENT '业务场景',
  `code` varchar(100) DEFAULT NULL COMMENT '存证编号',
  `hash` varchar(255) DEFAULT NULL COMMENT '存证hash',
  `sourceTag` varchar(50) DEFAULT NULL COMMENT '数据来源',
  `creatorName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  `threeInOneFileUrl` varchar(500) DEFAULT NULL COMMENT '三证合一',
  `idCardImageUrl` varchar(500) DEFAULT NULL COMMENT '法人代表身份证',
  `companyDescUrl` varchar(500) DEFAULT NULL COMMENT '公司简介',
  `companyRuleUrl` varchar(500) DEFAULT NULL COMMENT '公司章程附件',
  `resumeUrl` varchar(500) DEFAULT NULL COMMENT '高管简历附件',
  `financeReportUrl` varchar(500) DEFAULT NULL COMMENT '财务报表',
  `grayListUrl` varchar(500) DEFAULT NULL COMMENT '灰名单结果',
  `creditReportUrl` varchar(500) DEFAULT NULL COMMENT '征信报告',
  `aiRtingReportUrl` varchar(500) DEFAULT NULL COMMENT '评级报告',
  `serviceReportUrl` varchar(500) DEFAULT NULL COMMENT '服务协议书',
  `applyReportUrl` varchar(500) DEFAULT NULL COMMENT '申请书',
  `standingBookReportUrl` varchar(500) DEFAULT NULL COMMENT '业务台账',
  `promiseUrl` varchar(500) DEFAULT NULL COMMENT '承诺书',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`),
  KEY `idx_creator` (`creator`),
  KEY `idx_create_time` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='合同信息表';

-- 证据类型表
DROP TABLE IF EXISTS `evidence_type`;
CREATE TABLE `evidence_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '证据类型名称',
  `arbitrate_type_name` varchar(100) DEFAULT NULL COMMENT '仲裁类型名称',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modifier` varchar(100) DEFAULT NULL COMMENT '最后修改人',
  `last_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`),
  KEY `idx_arbitrate_type_name` (`arbitrate_type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='证据类型表';

-- 仲裁类型表
DROP TABLE IF EXISTS `arbitrate_type`;
CREATE TABLE `arbitrate_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '仲裁类型名称',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modifier` varchar(100) DEFAULT NULL COMMENT '最后修改人',
  `last_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仲裁类型表';

-- 仲裁机构表
DROP TABLE IF EXISTS `arbitral_institution`;
CREATE TABLE `arbitral_institution` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '仲裁机构名称',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modifier` varchar(100) DEFAULT NULL COMMENT '最后修改人',
  `last_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仲裁机构表';

-- 仲裁证据类型关联表
DROP TABLE IF EXISTS `arbitrate_evidence_type`;
CREATE TABLE `arbitrate_evidence_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `arbitrate_type_name` varchar(100) DEFAULT NULL COMMENT '仲裁类型名称',
  `evidence_type_name` varchar(100) DEFAULT NULL COMMENT '证据类型名称',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modifier` varchar(100) DEFAULT NULL COMMENT '最后修改人',
  `last_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `required` tinyint(1) DEFAULT '1' COMMENT '是否必需',
  PRIMARY KEY (`id`),
  KEY `idx_arbitrate_type` (`arbitrate_type_name`),
  KEY `idx_evidence_type` (`evidence_type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仲裁证据类型关联表';

-- 仲裁记录表
DROP TABLE IF EXISTS `arbitrate_record`;
CREATE TABLE `arbitrate_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `evidence_digital_code` varchar(100) DEFAULT NULL COMMENT '证据数字编码',
  `acceptance_institution` varchar(100) DEFAULT NULL COMMENT '受理机构',
  `arbitrate_type_name` varchar(100) DEFAULT NULL COMMENT '仲裁类型名称',
  `case_id` varchar(100) DEFAULT NULL COMMENT '案件ID',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `user_platform` varchar(50) DEFAULT NULL COMMENT '用户平台',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `business_side` varchar(100) DEFAULT NULL COMMENT '业务方',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_evidence_code` (`evidence_digital_code`),
  KEY `idx_creator` (`creator`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仲裁记录表';

-- 仲裁记录文件项表
DROP TABLE IF EXISTS `arbitrate_record_file_item`;
CREATE TABLE `arbitrate_record_file_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `record_id` bigint(20) DEFAULT NULL COMMENT '记录ID',
  `evidence_digital_code` varchar(100) DEFAULT NULL COMMENT '证据数字编码',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `hash` varchar(255) DEFAULT NULL COMMENT '哈希值',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `file_sn` varchar(100) DEFAULT NULL COMMENT '文件序号',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`),
  KEY `idx_evidence_code` (`evidence_digital_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仲裁记录文件项表';

-- 仲裁记录申请项表
DROP TABLE IF EXISTS `arbitrate_record_application_item`;
CREATE TABLE `arbitrate_record_application_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `record_id` bigint(20) DEFAULT NULL COMMENT '记录ID',
  `evidence_digital_code` varchar(100) DEFAULT NULL COMMENT '证据数字编码',
  `image_name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `image_url` varchar(500) DEFAULT NULL COMMENT '图片URL',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`),
  KEY `idx_evidence_code` (`evidence_digital_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仲裁记录申请项表';

-- 证据新闻表
DROP TABLE IF EXISTS `evidence_news`;
CREATE TABLE `evidence_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tx_hash` varchar(255) DEFAULT NULL COMMENT '交易哈希',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_tx_hash` (`tx_hash`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='证据新闻表';

-- 区块高度表
DROP TABLE IF EXISTS `block_height`;
CREATE TABLE `block_height` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `blocknum` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `evidence_amount` bigint(20) DEFAULT NULL COMMENT '证据数量',
  `channel_name` varchar(100) DEFAULT NULL COMMENT '通道名称',
  PRIMARY KEY (`id`),
  KEY `idx_channel_name` (`channel_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区块高度表';

-- 资产信息表
DROP TABLE IF EXISTS `assets_info`;
CREATE TABLE `assets_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_code` varchar(100) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `listing_price` decimal(15,2) DEFAULT NULL COMMENT '交易挂牌价(万元)',
  `transfer_name` varchar(100) DEFAULT NULL COMMENT '转让方名称',
  `assignee_name` varchar(100) DEFAULT NULL COMMENT '受让方名称',
  `manager` varchar(100) DEFAULT NULL COMMENT '项目经理',
  `department` varchar(100) DEFAULT NULL COMMENT '项目经理部门',
  `start_date` varchar(20) DEFAULT NULL COMMENT '挂牌开始时间',
  `end_date` varchar(20) DEFAULT NULL COMMENT '挂牌结束时间',
  `flow_status` varchar(50) DEFAULT NULL COMMENT '流转状态',
  `status` varchar(50) DEFAULT NULL COMMENT '项目状态',
  `sign_price` decimal(15,2) DEFAULT NULL COMMENT '签约价格(万元)',
  `assets_type` varchar(50) DEFAULT NULL COMMENT '资产类型',
  `asset_class` varchar(50) DEFAULT NULL COMMENT '资产类别',
  `basics_type` varchar(50) DEFAULT NULL COMMENT '基础资产标的物类型',
  `vouch_mode` varchar(50) DEFAULT NULL COMMENT '资产担保方式',
  `province` varchar(50) DEFAULT NULL COMMENT '资产所在省',
  `city` varchar(50) DEFAULT NULL COMMENT '资产所在市',
  `assignee_count` bigint(20) DEFAULT NULL COMMENT '意向受让方人数',
  `bidding_count` bigint(20) DEFAULT NULL COMMENT '竞价人数',
  `trade_id` varchar(100) DEFAULT NULL COMMENT '存证文件上链ID',
  `file_path` varchar(500) DEFAULT NULL COMMENT '存证文件地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_project_code` (`project_code`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资产信息表';

-- 插入初始数据
INSERT INTO `evidence_type` (`name`, `arbitrate_type_name`, `creator`) VALUES 
('电子合同', '合同纠纷', 'admin'),
('聊天记录', '合同纠纷', 'admin'),
('转账记录', '合同纠纷', 'admin'),
('邮件记录', '合同纠纷', 'admin');

INSERT INTO `arbitrate_type` (`name`, `creator`) VALUES 
('合同纠纷', 'admin'),
('劳动争议', 'admin'),
('知识产权', 'admin'),
('房产纠纷', 'admin');

INSERT INTO `arbitral_institution` (`name`, `creator`, `remark`) VALUES 
('中国国际经济贸易仲裁委员会', 'admin', '国家级仲裁机构'),
('北京仲裁委员会', 'admin', '地方仲裁机构'),
('上海仲裁委员会', 'admin', '地方仲裁机构');

SET FOREIGN_KEY_CHECKS = 1; 