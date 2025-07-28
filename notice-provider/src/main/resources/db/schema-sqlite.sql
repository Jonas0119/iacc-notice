-- SQLite 数据库初始化脚本
-- 用于开发环境
-- 创建时间: 2025-07-22

-- 通知信息表
CREATE TABLE IF NOT EXISTS t_notice_info (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId TEXT,
    userName TEXT,
    title TEXT,
    type TEXT,
    remark TEXT,
    fileUrl TEXT,
    creator TEXT,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sceneType TEXT,
    code TEXT UNIQUE,
    source TEXT,
    noticeUrl TEXT,
    platform TEXT,
    hash TEXT,
    sourceTag TEXT,
    creatorName TEXT
);

-- 通知发布表
CREATE TABLE IF NOT EXISTS t_notice_publish (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId TEXT,
    userName TEXT,
    title TEXT,
    type TEXT,
    remark TEXT,
    fileUrl TEXT,
    fileName TEXT,
    fileHash TEXT,
    creator TEXT,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sceneType TEXT,
    code TEXT UNIQUE,
    source TEXT,
    sourceType TEXT,
    platform TEXT,
    noticeUrl TEXT,
    hash TEXT,
    sourceTag TEXT,
    creatorName TEXT,
    managerName TEXT,
    managerPhone TEXT,
    authUrl TEXT,
    payUrl TEXT,
    status TEXT DEFAULT '0',
    reason TEXT,
    updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    publishTime TIMESTAMP,
    views INTEGER DEFAULT 0,
    pay_order_id TEXT,
    pay_type TEXT,
    pay_amount DECIMAL(10,2),
    pay_status TEXT DEFAULT '0',
    pay_time TIMESTAMP,
    pay_callback_time TIMESTAMP
);

-- 通知审核日志表
CREATE TABLE IF NOT EXISTS t_notice_audit_log (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    noticeId INTEGER,
    userId TEXT,
    userName TEXT,
    role TEXT,
    reason TEXT,
    type TEXT,
    remark TEXT,
    creator TEXT,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creatorName TEXT
);

-- 合同信息表
CREATE TABLE IF NOT EXISTS t_contract_info (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId TEXT,
    userName TEXT,
    title TEXT,
    type TEXT,
    remark TEXT,
    fileUrl TEXT,
    creator TEXT,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sceneType TEXT,
    code TEXT UNIQUE,
    hash TEXT,
    sourceTag TEXT,
    creatorName TEXT,
    threeInOneFileUrl TEXT,
    idCardImageUrl TEXT,
    companyDescUrl TEXT,
    companyRuleUrl TEXT,
    resumeUrl TEXT,
    financeReportUrl TEXT,
    grayListUrl TEXT,
    creditReportUrl TEXT,
    aiRtingReportUrl TEXT,
    serviceReportUrl TEXT,
    applyReportUrl TEXT,
    standingBookReportUrl TEXT,
    promiseUrl TEXT
);

-- 证据类型表
CREATE TABLE IF NOT EXISTS evidence_type (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    arbitrate_type_name TEXT,
    creator TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modifier TEXT,
    last_modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 仲裁类型表
CREATE TABLE IF NOT EXISTS arbitrate_type (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    creator TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modifier TEXT,
    last_modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 仲裁机构表
CREATE TABLE IF NOT EXISTS arbitral_institution (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    creator TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modifier TEXT,
    last_modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    remark TEXT
);

-- 仲裁证据类型关联表
CREATE TABLE IF NOT EXISTS arbitrate_evidence_type (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    arbitrate_type_name TEXT,
    evidence_type_name TEXT,
    creator TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modifier TEXT,
    last_modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    required INTEGER DEFAULT 1
);

-- 仲裁记录表
CREATE TABLE IF NOT EXISTS arbitrate_record (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    evidence_digital_code TEXT,
    acceptance_institution TEXT,
    arbitrate_type_name TEXT,
    case_id TEXT,
    title TEXT,
    creator TEXT,
    user_platform TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status INTEGER DEFAULT 0,
    business_side TEXT
);

-- 仲裁记录文件项表
CREATE TABLE IF NOT EXISTS arbitrate_record_file_item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    record_id INTEGER,
    evidence_digital_code TEXT,
    file_name TEXT,
    hash TEXT,
    file_type TEXT,
    file_sn TEXT,
    description TEXT
);

-- 仲裁记录申请项表
CREATE TABLE IF NOT EXISTS arbitrate_record_application_item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    record_id INTEGER,
    evidence_digital_code TEXT,
    image_name TEXT,
    image_url TEXT
);

-- 证据新闻表
CREATE TABLE IF NOT EXISTS evidence_news (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tx_hash TEXT,
    remark TEXT,
    type TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 区块高度表
CREATE TABLE IF NOT EXISTS block_height (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    blocknum INTEGER,
    evidence_amount INTEGER,
    channel_name TEXT
);

-- 资产信息表
CREATE TABLE IF NOT EXISTS assets_info (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_code TEXT,
    project_name TEXT,
    listing_price DECIMAL(15,2),
    transfer_name TEXT,
    assignee_name TEXT,
    manager TEXT,
    department TEXT,
    start_date TEXT,
    end_date TEXT,
    flow_status TEXT,
    status TEXT,
    sign_price DECIMAL(15,2),
    assets_type TEXT,
    asset_class TEXT,
    basics_type TEXT,
    vouch_mode TEXT,
    province TEXT,
    city TEXT,
    assignee_count INTEGER,
    bidding_count INTEGER,
    trade_id TEXT,
    file_path TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建索引
CREATE INDEX IF NOT EXISTS idx_notice_info_code ON t_notice_info(code);
CREATE INDEX IF NOT EXISTS idx_notice_info_creator ON t_notice_info(creator);
CREATE INDEX IF NOT EXISTS idx_notice_publish_code ON t_notice_publish(code);
CREATE INDEX IF NOT EXISTS idx_notice_publish_status ON t_notice_publish(status);
CREATE INDEX IF NOT EXISTS idx_arbitrate_record_evidence_code ON arbitrate_record(evidence_digital_code);
CREATE INDEX IF NOT EXISTS idx_evidence_type_name ON evidence_type(name);
CREATE INDEX IF NOT EXISTS idx_arbitrate_type_name ON arbitrate_type(name);

-- 插入初始数据
INSERT OR IGNORE INTO evidence_type (name, arbitrate_type_name, creator) VALUES 
('电子合同', '合同纠纷', 'admin'),
('聊天记录', '合同纠纷', 'admin'),
('转账记录', '合同纠纷', 'admin'),
('邮件记录', '合同纠纷', 'admin');

INSERT OR IGNORE INTO arbitrate_type (name, creator) VALUES 
('合同纠纷', 'admin'),
('劳动争议', 'admin'),
('知识产权', 'admin'),
('房产纠纷', 'admin');

INSERT OR IGNORE INTO arbitral_institution (name, creator, remark) VALUES 
('中国国际经济贸易仲裁委员会', 'admin', '国家级仲裁机构'),
('北京仲裁委员会', 'admin', '地方仲裁机构'),
('上海仲裁委员会', 'admin', '地方仲裁机构'); 