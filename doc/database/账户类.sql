-- 账户信息表
CREATE TABLE account(
    account_id CHAR(15) NOT NULL PRIMARY KEY COMMENT '账户ID',
    account_type VARCHAR(10) NOT NULL COMMENT '账户类型:ACCOUNT/WECHAT/QQ/SINA',
    account VARCHAR(15) UNIQUE COMMENT '账号',
    email VARCHAR(50) UNIQUE COMMENT '邮箱',
    mobile VARCHAR(20) UNIQUE COMMENT '手机号码',
    password VARCHAR(20) COMMENT '密码',
    account_status TINYINT NOT NULL DEFAULT '0' COMMENT '账户状态',
    register_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    username VARCHAR(20) NOT NULL DEFAULT '聊友' COMMENT '用户名',
    avatar VARCHAR(100) COMMENT '头像',
    gender TINYINT NOT NULL DEFAULT 0 COMMENT '性别',
    region VARCHAR(100) COMMENT '地区',
    description VARCHAR(1000) COMMENT '简介',
    birthday TIMESTAMP COMMENT '出生日期',
    education VARCHAR(100) COMMENT '学历',
    school VARCHAR(100) COMMENT '学校',
    career VARCHAR(100) COMMENT '职业',
    company VARCHAR(100) COMMENT '公司'

);
-- 已废弃账户信息表
CREATE TABLE account_invalid SELECT * FROM account WHERE 1 = 2;
ALTER TABLE account_invalid ADD invalid_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- 通讯录
CREATE TABLE contact_application(
    contact_application_id CHAR(15) NOT NULL PRIMARY KEY COMMENT '好友申请id',
    send_account_id VARCHAR(15) NOT NULL COMMENT '发送申请账户id',
    receive_account_id VARCHAR(15) NOT NULL COMMENT '接收申请账户id',
    application_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    application_description VARCHAR(1000) COMMENT '申请描述',
    application_status TINYINT NOT NULL DEFAULT 0 COMMENT '申请状态',
    application_status_time TIMESTAMP COMMENT '状态更新时间',
    contact_id CHAR(15) COMMENT '通讯录id',

    INDEX contact_application_send_index(send_account_id),
    INDEX contact_application_receive_index(receive_account_id)
)
CREATE TABLE contact_application_his SELECT * FROM contact_application WHERE 1 = 2;
ALTER TABLE contact_application_his ADD backup_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE contact(
    contact_id CHAR(15) NOT NULL PRIMARY KEY COMMENT '通讯录id',
    begin_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    contact_day INT NOT NULL DEFAULT 0 COMMENT '连续聊天天数'
);
CREATE TABLE contact_invalid SELECT * FROM contact WHERE 1 = 2;
ALTER TABLE contact_invalid ADD invalid_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE contact_detail(
    contact_detail_id CHAR(15) NOT NULL PRIMARY KEY COMMENT '通讯录详情id',
    contact_id CHAR(15) NOT NULL COMMENT '通讯录id',
    account_id CHAR(15) NOT NULL COMMENT '账户id',
    contact_account_id CHAR(15) NOT NULL COMMENT '通讯录账户id',
    nickname VARCHAR(20) COMMENT '昵称',
    category VARCHAR(20) COMMENT '分类',

    INDEX contact_detail_index(account_id)
);
CREATE TABLE contact_detail_invalid SELECT * FROM contact_detail WHERE 1 = 2;
ALTER TABLE contact_detail_invalid ADD invalid_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
