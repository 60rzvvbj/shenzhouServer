create database shenzhou;

use shenzhou;

-- 用户
create table user(
	account varchar(20), -- 账号
	pwd varchar(20), -- 密码
	username varchar(20), -- 用户名
	other varchar(50), -- 其它信息 
	balance int -- 余额
);

-- 添加用户
insert into user values('001', '123456', '杨超旭', '暂无', 0);

-- 文章
create table article (
	id int primary key auto_increment, -- 文章编号
	title varchar(30) not null, -- 文章标题
	placename varchar(30), -- 景点名
	province varchar(20), -- 景点所在省份
	releasetime bigint, -- 发布时间
	content varchar(100), -- 文章内容
	account varchar(20) -- 作者账号
	-- 点赞数通过其它表计算
);

-- 导游
create table guide(
	id int primary key auto_increment, -- 导游编号
	account varchar(20), -- 用户账号
	introduction varchar(100) -- 导游简介
	-- 评分由其它表计算
);

-- 导游申请
create table guideapply(
	id int primary key auto_increment, -- 申请ID
	applytime bigint, -- 申请时间
	account varchar(20), -- 用户账号
	introduction varchar(100) -- 导游简介
);

-- 管理员
create table admin(
	id int primary key auto_increment, -- 管理员ID
	account varchar(20) -- 用户账号
);

-- 用户咨询
create table consult(
	id int primary key auto_increment, -- 咨询ID
	account varchar(20), -- 用户账号
	gid int, -- 导游编号
	consulttime bigint, -- 咨询时间
	content varchar(100), -- 咨询内容
	reply varchar(100), -- 导游答复内容 
	score int, -- 用户评分
	stage int -- 咨询阶段 0: 用户发起咨询, 1: 导游回复咨询
);

-- 拼团申请
create table collage(
	id int primary key auto_increment, -- 拼团ID
	account varchar(20), -- 发起人账号
	pnumber int, -- 咨询时间
	departure varchar(30), -- 起点
	destination varchar(30), -- 终点
	dtime bigint, -- 出发时间
	describe varchar(100) -- 具体信息
);

-- 参与拼团
create table participate(
	account varchar(20), -- 参与人账号
	cid int, -- 拼团ID
);

-- 礼品
create table gift(
	id int primary key auto_increment, -- 礼品编号
	name varchar(30), -- 礼品名称
	describe varchar(100), -- 礼品描述
	price int -- 礼品价格
);

-- 兑换礼品申请
create table exchangegift(
	id int primary key auto_increment, -- 兑换ID
	account varchar(20), -- 用户账号
	gid int, -- 礼品编号
	etime bigint, -- 申请时间
	address varchar(50), -- 邮寄地址
	state int -- 申请状态 0: 未成功, 1: 邮寄中, 2: 已收货
);

-- 图片
create table picture(
	id int primary key auto_increment, -- 图片ID
	url varchar(100), -- 存放路径
	positiontype int, -- 所属位置类型 含义未定
	specificposition int -- 所属具体位置 含义未定
);

-- 经验
create table experience(
	account varchar(20), -- 用户账号
	value int, -- 经验值
	level int -- 等级
);

-- 点赞
create table thumb(
	account varchar(20), -- 用户账号
	aid int, -- 文章编号
);