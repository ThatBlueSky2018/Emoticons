drop schema if exists `emoticons`;
create schema `emoticons` default character set utf8 collate utf8_bin;
use emoticons;

-- 账号状态：0表示封禁，1表示激活
-- 是否是官方账号：0表示不是，1表示是
-- 是否公开收藏夹：1表示公开，0表示私密
create table tb_user (
    id bigint primary key auto_increment comment '主键',
    email varchar(64) default null comment '邮箱',
    username varchar(64) not null comment '用户名',
    password varchar(256) not null comment '密码',
    gender varchar(16)  default null comment '性别',
    profile_photo varchar(256) not null default 'https://emoticons-platform.oss-cn-beijing.aliyuncs.com/5d31f074-c0cd-4603-b67b-06a3738d3d85.png' comment '头像',
    signature varchar(1024) default null comment '个性签名',
    last_login datetime default current_timestamp comment'上次登录时间',

    status int not null default 1 comment '账号状态',
    is_official int not null default 0 comment '是否是官方账号',
    public_favorite int not null default 1 comment '是否公开收藏夹',

    create_time datetime default current_timestamp comment '注册时间',
    update_time datetime default current_timestamp comment '修改时间',
    update_user bigint default null comment '修改人',

    foreign key (update_user) references tb_user(id),
    unique key (username, email)
)comment '用户表';

-- 角色表（此表内容在一开始就添加好，后续不再发生变化）
create table tb_role (
    id bigint primary key auto_increment comment '主键',
    role_name varchar(16) not null default 'USER' comment '角色名称',

    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp comment '修改时间',

    unique key (role_name)
)comment '角色表';

create table user_role (
    id bigint primary key auto_increment comment '主键',
    user_id bigint not null comment '用户id',
    role_id bigint not null default 1 comment '角色id', -- 保证tb_role表中1是USER

    create_time datetime default current_timestamp comment '创建时间',
    create_user bigint default null comment '创建人',
    update_time datetime default current_timestamp comment '修改时间',
    update_user bigint default null comment '修改人',

    foreign key (user_id) references tb_user(id),
    foreign key (role_id) references tb_role(id),
    foreign key (create_user) references tb_user(id),
    foreign key (update_user) references tb_user(id)
)comment '用户——角色对应关系表';

-- 标签分组表
create table tag_group(
    id bigint primary key auto_increment comment '主键',
    name varchar(64) not null unique comment '分组名称',

    create_time datetime default current_timestamp comment '创建时间',
    create_user bigint default null comment '创建人',
    update_time datetime default current_timestamp comment '修改时间',
    update_user bigint default null comment '修改人',

    foreign key (create_user) references tb_user(id),
    foreign key (update_user) references tb_user(id)
)comment '标签分组表';

-- 审核状态：1表示通过，0表示未通过（目前暂不开发审核功能，保留此字段）
create table tb_tag (
    id bigint primary key auto_increment comment '主键',
    name varchar(64) not null comment '标签名称',
    description text default null comment '标签描述',
    group_id bigint not null comment '标签分组',
    ref_count bigint not null default 0 comment '引用数量',

    status int not null default 1 comment '审核状态',

    create_time datetime default current_timestamp comment '创建时间',
    create_user bigint default null comment '创建人',
    update_time datetime default current_timestamp comment '修改时间',
    update_user bigint default null comment '修改人',

    foreign key (group_id) references tag_group(id),
    foreign key (create_user) references tb_user(id),
    foreign key (update_user) references tb_user(id),
    unique key (name)
)comment '表情包标签表';

-- 表情包信息表
-- status：0表示未通过审核，1表示通过审核
create table tb_emoji (
    id bigint primary key auto_increment comment '主键',
    name varchar(32) not null comment '表情包名称',
    description text default null comment '表情包描述',
    url varchar(512) not null comment '存储路径',
    hits int default 0 comment '点击量',
    comments int default 0 comment '评论数量',
    downloads int default 0 comment '下载量',
    favorite int default 0 comment'收藏量',
    hot_index double not null default 0.0 comment '热门指数',

    status int not null default 1 comment '审核状态',

    create_time datetime default current_timestamp comment '上传时间',
    create_user bigint default null comment '上传人',
    update_time datetime default current_timestamp comment '修改时间',
    update_user bigint default null comment '修改人',

    foreign key (create_user) references tb_user(id),
    foreign key (update_user) references tb_user(id)
)comment '表情包信息表';

-- 表情包与标签的多对多关系表
create table emoji_tag (
    id bigint primary key auto_increment comment '主键',
    emoji_id bigint not null comment '表情包id',
    tag_id bigint not null comment '标签id',

    create_time datetime default current_timestamp comment '创建时间',
    create_user bigint default null  comment '创建人',
    update_time datetime default current_timestamp comment '修改时间',
    update_user bigint  default null comment '修改人',

    foreign key (emoji_id) references tb_emoji(id),
    foreign key (tag_id) references tb_tag(id),
    foreign key (create_user) references tb_user(id),
    foreign key (update_user) references tb_user(id)
)comment '表情包-标签的关系表';

-- 表情包评论表
create table tb_comment (
    id bigint primary key auto_increment comment '主键',
    reply_id bigint default null comment '回复id',  -- 楼主评论id
    reply_reply_id bigint default null comment '二次回复id',  -- 回复的评论id
    emoji_id bigint not null comment '表情包id',
    content text not null comment '评论内容',

    create_time datetime default current_timestamp comment '发布时间',
    create_user bigint not null comment '评论人',
    update_time datetime default current_timestamp comment '修改时间',
    update_user bigint default null comment '修改人',

    foreign key (reply_id) references tb_comment(id),
    foreign key (reply_reply_id) references tb_comment(id),
    foreign key (emoji_id) references tb_emoji(id),
    foreign key (create_user) references tb_user(id),
    foreign key (update_user) references tb_user(id)
)comment '表情包评论表';

-- 用户收藏信息表
create table tb_favorite (
    id bigint primary key auto_increment comment '主键',
    user_id bigint not null comment '用户id',
    emoji_id bigint not null comment '表情包id',

    create_time datetime default current_timestamp comment '收藏时间',

    foreign key (user_id) references tb_user(id),
    foreign key (emoji_id) references tb_emoji(id)
)comment '用户收藏信息表';

-- 消息表
-- 是否已读：0表示未读，1表示已读
-- 消息类型包括点赞、评论、私信等等，发送内容类型包括文本、链接等等，都需要严格约定，代码中通过常量直接写死
create table tb_message (
    id bigint primary key auto_increment comment '主键',
    message_type varchar(64) not null comment '消息类型',
    is_read int not null default 0 comment '是否已读',
    sender_id bigint not null comment '发送人id',
    receiver_id bigint not null comment '接收人id',
    content_type varchar(64) not null default 'TEXT' comment '发送内容类型',
    content text default null comment '消息内容',

    create_time datetime default current_timestamp comment '发送时间',
    update_time datetime default current_timestamp comment '修改时间',

    foreign key (sender_id) references tb_user(id),
    foreign key (receiver_id) references tb_user(id)
)comment '消息表';


-- TODO
-- 用户关注，好友列表？