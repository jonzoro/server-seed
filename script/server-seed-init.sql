-- ----------------------------
-- Table structure for sys_banner
-- ----------------------------
DROP TABLE IF EXISTS "sys_banner";
CREATE TABLE "sys_banner" (
  "id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "title" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "img_path" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "link_url" text COLLATE "pg_catalog"."default",
  "type" varchar(10) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "status" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT '0'::bpchar,
  "create_user" varchar(36) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_user" varchar(36) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "sort" int4 DEFAULT 0,
  "lang" varchar(10) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "app_id" varchar(40) COLLATE "pg_catalog"."default" DEFAULT 'owner'::character varying,
  "remarks" text COLLATE "pg_catalog"."default",
  "remarks_en" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "sys_banner"."id" IS 'id';
COMMENT ON COLUMN "sys_banner"."title" IS '标题';
COMMENT ON COLUMN "sys_banner"."img_path" IS '路径';
COMMENT ON COLUMN "sys_banner"."link_url" IS '跳转地址';
COMMENT ON COLUMN "sys_banner"."type" IS '业务类型，tz:通证';
COMMENT ON COLUMN "sys_banner"."status" IS '状态，1启用，0禁用';
COMMENT ON COLUMN "sys_banner"."create_user" IS '创建人';
COMMENT ON COLUMN "sys_banner"."create_date" IS '创建时间';
COMMENT ON COLUMN "sys_banner"."update_user" IS '更新人';
COMMENT ON COLUMN "sys_banner"."update_date" IS '更新时间';
COMMENT ON COLUMN "sys_banner"."lang" IS '语言版本，ch中文，en英文';
COMMENT ON COLUMN "sys_banner"."remarks" IS '备注';
COMMENT ON COLUMN "sys_banner"."remarks_en" IS '备注en';

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS "sys_dict_item";
CREATE TABLE "sys_dict_item" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "value" varchar(200) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "show_val" varchar(510) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "remark" varchar(200) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "type_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "sort" int4 NOT NULL,
  "create_user" varchar(40) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_user" varchar(40) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "del_flag" varchar(2) COLLATE "pg_catalog"."default" NOT NULL DEFAULT '0'::character varying
)
;
COMMENT ON COLUMN "sys_dict_item"."value" IS '真实值';
COMMENT ON COLUMN "sys_dict_item"."show_val" IS '显示值';
COMMENT ON COLUMN "sys_dict_item"."remark" IS '描述';
COMMENT ON COLUMN "sys_dict_item"."type_id" IS '字典id';
COMMENT ON COLUMN "sys_dict_item"."sort" IS '项目排序';
COMMENT ON COLUMN "sys_dict_item"."del_flag" IS '删除标识(0为未删除1为已删除)';
COMMENT ON TABLE "sys_dict_item" IS '数据字典项目';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict_item" VALUES ('c330b67f565d4d188e1fdb14871b5251', '0', '代持转入', '代持类型 - 代持转入', 'cfce281fb1b44fd295df08bdb987ccfc', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:35:42.107163+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:35:42.107163+00', '0');
INSERT INTO "sys_dict_item" VALUES ('f3d657d4690544a290b81a5afcda167b', '1', '代持转出', '代持类型- 代持转出', 'cfce281fb1b44fd295df08bdb987ccfc', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:35:53.948963+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:35:53.948963+00', '0');
INSERT INTO "sys_dict_item" VALUES ('660204f07dbf485bb028c20bc2c3b650', '1', 'GF管理', '管理员功能模块 - GF管理', '3184dd7f145e4b16b8dd10c1e0f8f052', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:41:49.286317+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:41:49.286317+00', '0');
INSERT INTO "sys_dict_item" VALUES ('5127c51425984a15a0dbdb35eb988352', '2', '收益管理', '管理员功能模块 - 收益管理', '3184dd7f145e4b16b8dd10c1e0f8f052', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:42:06.30707+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:42:06.30707+00', '0');
INSERT INTO "sys_dict_item" VALUES ('012673b78a5e48eea1b245e720d6c975', '2', '高级转账', '管理员功能模块 - 高级转账', '3184dd7f145e4b16b8dd10c1e0f8f052', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:42:18.898878+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:42:18.898878+00', '0');
INSERT INTO "sys_dict_item" VALUES ('5bd2766dd80f4fc6b231201d531ff014', '0', '超级管理员', '', 'ddd944cf9b9c4e0ba3d06d4c747df72a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('8fa8c794db7c46febd21cf1b9c73e61c', '001', '公告', '公告类型', 'bc600652bc354bfeae0cdb9d4686efd3', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 02:54:26.154712+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 02:54:26.154712+00', '0');
INSERT INTO "sys_dict_item" VALUES ('26236c8e103f4744891a499d29b64d3b', '2', '买出', '投资信息类型 - 卖出', '1cb1827fa1244937a89adc3ddd11671b', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:42:30.393573+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:42:30.393573+00', '0');
INSERT INTO "sys_dict_item" VALUES ('ffa80867d95745a9bd06b3e8b7dd70be', '3', '回购', '投资信息类型 - 回购', '1cb1827fa1244937a89adc3ddd11671b', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:42:48.662901+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:42:48.662901+00', '0');
INSERT INTO "sys_dict_item" VALUES ('d60f5462d7d54a0ea79dbf270837f3da', '1', '买入', '投资信息类型 - 买入', '1cb1827fa1244937a89adc3ddd11671b', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:37:28.388475+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:37:28.388475+00', '0');
INSERT INTO "sys_dict_item" VALUES ('dd90f4a7519641db841f76684588ad66', '1', '买入申请', '投资信息状态 - 买入申请', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:46:07.127057+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:46:07.127057+00', '0');
INSERT INTO "sys_dict_item" VALUES ('1bcc40ae65d247828d9b995de025f13c', '2', '买入成功', '投资信息状态 - 买入成功', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:46:27.436604+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:46:27.436604+00', '0');
INSERT INTO "sys_dict_item" VALUES ('544a21a9714340dcb027a21a285ae6d9', '3', '买入失败', '投资信息状态 - 买入失败', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:46:39.029304+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:46:39.029304+00', '0');
INSERT INTO "sys_dict_item" VALUES ('fafc64d944c64853a9ddfd8d099037d8', '4', '卖出申请', '投资信息状态 - 卖出申请', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:47:11.766935+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:47:11.766935+00', '0');
INSERT INTO "sys_dict_item" VALUES ('9aad707ca4ca478ab638c89d35e270b7', '5', '卖出成功', '投资信息状态 - 卖出成功', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:47:21.532989+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:47:21.532989+00', '0');
INSERT INTO "sys_dict_item" VALUES ('9145af97acdb4300ae8313fab5702715', '6', '卖出失败', '投资信息状态 - 卖出失败', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:47:29.652665+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:47:29.652665+00', '0');
INSERT INTO "sys_dict_item" VALUES ('e23a4a7e1928435c8c427afe2fe4ac83', '7', '回购申请', '投资信息状态 - 回购申请', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:48:55.645065+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:48:55.645065+00', '0');
INSERT INTO "sys_dict_item" VALUES ('30f6349fdc7140348a6ff92886278831', '8', '回购成功', '投资信息状态 - 回购成功', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:27.026949+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:27.026949+00', '0');
INSERT INTO "sys_dict_item" VALUES ('5bd2766fd80f4fc6b231201d531ff014', '9', '回购失败', '投资信息状态 - 回购失败', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('5bd2766dd80f4fc6b231201d531ff015', '1', '合作方管理员', '', 'ddd944cf9b9c4e0ba3d06d4c747df72a', 1, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('5bd2766dd80f4fc6b231201d531ff016', '2', '普通用户', '', 'ddd944cf9b9c4e0ba3d06d4c747df72a', 2, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('ddd944cf9b9c4ccba3d06d4c747df001', '0', '关闭', '', 'ddd944cf9b9c4ccba3d06d4c747df72a', 2, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('ddd944cf9b9c4ccba3d06d4c747df002', '1', '开启', '', 'ddd944cf9b9c4ccba3d06d4c747df72a', 2, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('666944cf9b9c4ccba3d06d4c747dfddd', '0', '内部合作方', '', 'ddd944cf9b9c4ccba3d06d4c747dfddd', 2, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('666944cf9b9c4ccba3d06d4c747dfdbb', '1', '外部合作方', '', 'ddd944cf9b9c4ccba3d06d4c747dfddd', 2, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('3226cd3a081011e9bd39fa163e1682c1', '0', '租金分红', '', '3226cd3a081011e9bd39fa163e168207', 2, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('3226cd3a081011e9bd39fa163e1682c2', '1', '出售分红', '', '3226cd3a081011e9bd39fa163e168207', 2, '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:49:44.703202+00', '0');
INSERT INTO "sys_dict_item" VALUES ('e11af81b061441eabf07186cf66dde21', '001', '简体中文', '简体中文', 'b02944cf9b9c4e0ba3d06d4c747df72a', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:33:34.694483+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:33:34.694483+00', '0');
INSERT INTO "sys_dict_item" VALUES ('7c0e428801654107987a28ead914ceaa', '001', '贷款申请', '用户提交申请贷款的后', 'fec08f87df0546aa9889c9899b943cff', 0, '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:36:27.382481+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:36:27.382481+00', '0');
INSERT INTO "sys_dict_item" VALUES ('1d4e34a5ac5d4b99b40718a2d87087c7', 'SysBanner', '系统横幅', '系统横幅', '7c318675f1dd4f3fa45326b068b07782', 0, '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 06:40:35.613472+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 06:40:35.613472+00', '0');
INSERT INTO "sys_dict_item" VALUES ('f00ab042b46445339a0fee7fc760eee9', '0', '订单取消', '投资信息状态 - 订单取消', 'ccba7e6bc58a4a95802275b5c520e82a', 0, '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-18 02:31:32.537857+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-18 02:31:52.864987+00', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS "sys_dict_type";
CREATE TABLE "sys_dict_type" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "code" varchar(510) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "name" varchar(510) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "create_user" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_user" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "del_flag" varchar(2) COLLATE "pg_catalog"."default" NOT NULL DEFAULT '0'::character varying,
  "remark" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "sys_dict_type"."code" IS '编码';
COMMENT ON COLUMN "sys_dict_type"."name" IS '字典名称';
COMMENT ON COLUMN "sys_dict_type"."del_flag" IS '删除标识';
COMMENT ON COLUMN "sys_dict_type"."remark" IS '描述';
COMMENT ON TABLE "sys_dict_type" IS '数据字典类型';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO "sys_dict_type" VALUES ('bc600652bc354bfeae0cdb9d4686efd3', 'noticeType', '公告类型', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 02:50:43.157581+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:39:12.38826+00', '1', '公告类型');
INSERT INTO "sys_dict_type" VALUES ('1cb1827fa1244937a89adc3ddd11671b', 'investInfoType', '投资信息类型', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:34:27.621416+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:34:27.621416+00', '0', NULL);
INSERT INTO "sys_dict_type" VALUES ('ccba7e6bc58a4a95802275b5c520e82a', 'investInfoStatus', '投资信息状态', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:44:06.669553+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 03:44:06.669553+00', '0', NULL);
INSERT INTO "sys_dict_type" VALUES ('cfce281fb1b44fd295df08bdb987ccfc', 'agencyHoldType', '代持状态', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:34:18.617863+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:34:18.617863+00', '0', NULL);
INSERT INTO "sys_dict_type" VALUES ('ddd944cf9b9c4ccba3d06d4c747dfddd', 'partnerType', '合作方类型', '2776cd3a081011e9bd39fa163e168207', '2019-01-03 12:23:49.053596+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-07 03:10:03.305565+00', '0', NULL);
INSERT INTO "sys_dict_type" VALUES ('3226cd3a081011e9bd39fa163e168207', 'revenueType', '分红类型', '2776cd3a081011e9bd39fa163e168207', '2019-01-03 12:23:49.053596+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:32:49.268863+00', '1', NULL);
INSERT INTO "sys_dict_type" VALUES ('ddd944cf9b9c4ccba3d06d4c747df72a', 'openFlag', '开关状态', '2776cd3a081011e9bd39fa163e168207', '2019-01-03 12:23:49.053596+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:32:50.860841+00', '1', NULL);
INSERT INTO "sys_dict_type" VALUES ('ddd944cf9b9c4e0ba3d06d4c747df72a', 'userType', '用户类型', '2776cd3a081011e9bd39fa163e168207', '2019-01-03 12:23:49.053596+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:32:52.014434+00', '1', NULL);
INSERT INTO "sys_dict_type" VALUES ('3184dd7f145e4b16b8dd10c1e0f8f052', 'gfMagMiseFuncMod', '管理员功能模块', '2776cd3a081011e9bd39fa163e168207', '2019-01-04 05:39:48.7077+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:33:00.439208+00', '1', NULL);
INSERT INTO "sys_dict_type" VALUES ('fec08f87df0546aa9889c9899b943cff', '001', '贷款状态', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:35:38.664158+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:35:43.119025+00', '0', '本类型用于描述贷款的各种状态');
INSERT INTO "sys_dict_type" VALUES ('b02944cf9b9c4e0ba3d06d4c747df72a', 'language', '语言类型', '2776cd3a081011e9bd39fa163e168207', '2019-01-03 12:23:49.053596+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:38:17.403416+00', '1', NULL);
INSERT INTO "sys_dict_type" VALUES ('90abc3d7798a4e9da59c2d37f9ad9001', 'type', '公告类型', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:38:45.698624+00', '2776cd3a081011e9bd39fa163e168207', '2019-01-08 12:39:08.887855+00', '0', '公告类型');
INSERT INTO "sys_dict_type" VALUES ('7c318675f1dd4f3fa45326b068b07782', 'modelName', '数据类名称', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 06:39:34.259817+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 06:39:34.259817+00', '0', '记录类名与实际名称');
INSERT INTO "sys_dict_type" VALUES ('050e7327e81e447ea88b9ae8a8d8a40b', 'ewq', '小测试咯', '2776cd3a081011e9bd39fa163e168207', '2019-01-10 10:02:25.984443+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:06:31.289257+00', '1', '呵呵');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "sys_menu";
CREATE TABLE "sys_menu" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "parent_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "menu_name" varchar(60) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "url" varchar(600) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sort" int4,
  "flag" int4,
  "icon" varchar(400) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "type" int4,
  "partner_types" varchar(40) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "create_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "menu_id" int4,
  "menu_as" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "sys_menu"."id" IS '主键id';
COMMENT ON COLUMN "sys_menu"."parent_id" IS '父菜单ID，一级菜单为0';
COMMENT ON COLUMN "sys_menu"."menu_name" IS '菜单名称';
COMMENT ON COLUMN "sys_menu"."url" IS '菜单对应的页面url';
COMMENT ON COLUMN "sys_menu"."sort" IS '显示顺序';
COMMENT ON COLUMN "sys_menu"."flag" IS '是否有效';
COMMENT ON COLUMN "sys_menu"."icon" IS '菜单对应的图标';
COMMENT ON COLUMN "sys_menu"."type" IS '类型,0模块1菜单2按钮';
COMMENT ON COLUMN "sys_menu"."partner_types" IS '根据合作方类型过滤菜单（如：1,2）';
COMMENT ON COLUMN "sys_menu"."create_date" IS '创建时间';
COMMENT ON COLUMN "sys_menu"."create_user" IS '创建人';
COMMENT ON COLUMN "sys_menu"."update_date" IS '修改时间';
COMMENT ON COLUMN "sys_menu"."update_user" IS '修改人';
COMMENT ON COLUMN "sys_menu"."menu_id" IS '菜单id';
COMMENT ON COLUMN "sys_menu"."menu_as" IS '模块名称';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac02', '9ad5de8c8cf24d8babea2fae3797ac1f', '融资管理', '/financemanage', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 102, 'financeManage');
INSERT INTO "sys_menu" VALUES ('525abc902af840b984173cdefe6f2544', '0', '首页', '/', 0, 1, '', 0, '0,1', '2019-01-04 02:06:11.699159+00', NULL, '2019-01-04 02:06:11.699159+00', NULL, 0, 'home');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac1f', '0', '链上数据', '/datachain', 1, 1, '', 0, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 1, 'dataChain');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac23', '9ad5de8c8cf24d8babea2fae3797ac02', '投资信息', '/datachain/financemanage/riskmanage', 2, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10203, 'riskManage');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac01', '9ad5de8c8cf24d8babea2fae3797ac1f', '客户管理', '/clientmanage', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 101, 'clientManage');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac03', '9ad5de8c8cf24d8babea2fae3797ac1f', '数据验证', '/datavalidation', 2, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 103, 'dataValidation');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac04', '9ad5de8c8cf24d8babea2fae3797ac1f', '统计分析', '/statistics', 3, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 104, 'statistics');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae11', '4c0872cfeabc4dbc849d06dd6abcbfae01', '项目信息', '/financeinvestment/project/index', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 20101, 'projectIndex');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae01', '3c0872cfeabc4dbc849d06dd0abcbfae', '项目管理', '/project', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 201, 'project');
INSERT INTO "sys_menu" VALUES ('3c0872cfeabc4dbc849d06dd0abcbfae', '0', '投资理财', '/financeinvestment', 2, 1, '', 0, '0,1', '2019-01-04 02:06:11.705369+00', NULL, '2019-01-04 02:06:11.705369+00', NULL, 2, 'financeInvestment');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae21', '4c0872cfeabc4dbc849d06dd6abcbfae02', '用户投资信息', '/financeinvestment/investment/customer', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 20201, 'customer');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae22', '4c0872cfeabc4dbc849d06dd6abcbfae02', '用户代持管理', '/financeinvestment/investment/dai', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 20202, 'dai');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae12', '4c0872cfeabc4dbc849d06dd6abcbfae01', '收益管理（token总收益）', '/financeinvestment/project/all', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 20102, 'projectAll');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae02', '3c0872cfeabc4dbc849d06dd0abcbfae', '用户管理', '/investment', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 202, 'investment');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae23', '4c0872cfeabc4dbc849d06dd6abcbfae02', '管理员列表', '/financeinvestment/investment/gfadmin', 2, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 20203, 'gfadmin');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff21', '0ab8d1e7bb60496b8c0ca62fb102ff02', '用户管理', '/system/management/user', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 90201, 'partnerUser');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae03', '3c0872cfeabc4dbc849d06dd0abcbfae', '内容管理', '/contentManagement', 2, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 203, 'contentManagement');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff03', '0ab8d1e7bb60496b8c0ca62fb102fff4', '系统管理(合作方管理员)', '/cooperation', 2, 0, NULL, 1, '0', '2019-01-08 03:32:06+00', NULL, '2019-01-08 03:32:04+00', NULL, 903, 'cooperation');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff01', '0ab8d1e7bb60496b8c0ca62fb102fff4', '我的信息', '/my', 0, 1, '', 1, NULL, '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 901, 'my');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102fff4', '0', '系统设置', '/system', 9, 1, '', 0, '0,1', '2019-01-04 02:06:11.707459+00', NULL, '2019-01-04 02:06:11.707459+00', NULL, 9, 'system');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac21', '9ad5de8c8cf24d8babea2fae3797ac02', '融资信息', '/datachain/financemanage/finance', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10201, 'finance');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff02', '0ab8d1e7bb60496b8c0ca62fb102fff4', '系统管理', '/management', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 902, 'management');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff23', '0ab8d1e7bb60496b8c0ca62fb102ff02', '角色管理', '/system/management/role', 2, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 90203, 'role');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac22', '9ad5de8c8cf24d8babea2fae3797ac02', '逾期信息', '/datachain/financemanage/overdue', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10202, 'overdue');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff11', '0ab8d1e7bb60496b8c0ca62fb102ff01', '个人信息', '/system/my/userinfo', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 90101, 'userinfo');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac31', '9ad5de8c8cf24d8babea2fae3797ac03', '数据验证', '/datachain/datavalidation/validation', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10301, 'validation');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac32', '9ad5de8c8cf24d8babea2fae3797ac03', '数据明细验证', '/datachain/datavalidation/detail', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10302, 'detail');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac41', '9ad5de8c8cf24d8babea2fae3797ac04', '平台运营统计', '/datachain/statistics/platform', 0, 1, '', 1, '0,1', '2019-01-08 03:10:47.011657+00', '', '2019-01-08 03:10:47.011657+00', '', 10401, 'platform');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac42', '9ad5de8c8cf24d8babea2fae3797ac04', '投资人收益统计', '/datachain/statistics/earnings', 1, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10402, 'earnings');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac43', '9ad5de8c8cf24d8babea2fae3797ac04', '平台运营统计（内部）', '/datachain/statistics/inside\bearnings', 2, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10403, 'insideEarnings');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac44', '9ad5de8c8cf24d8babea2fae3797ac04', '投资人收益统计（内部）', '/datachain/statistics/insideplatform', 3, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 10404, 'insidePlatform');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae31', '4c0872cfeabc4dbc849d06dd6abcbfae03', '公告管理', '/financeinvestment/contentmanagement/index', 0, 1, '', 1, '0,1', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 20301, 'notice');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff22', '0ab8d1e7bb60496b8c0ca62fb102ff02', '合作方管理', '/system/management/partners', 1, 1, '', 1, '0', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 90202, 'partners');
INSERT INTO "sys_menu" VALUES ('4c0872cfeabc4dbc849d06dd6abcbfae32', '4c0872cfeabc4dbc849d06dd6abcbfae03', '横幅管理', '/financeinvestment/contentmanagement/banner', 1, 1, '', 1, '0', '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 20302, 'banner');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff12', '0ab8d1e7bb60496b8c0ca62fb102ff01', '密码修改', '/system/my/pws', 1, 1, '', 1, NULL, '2019-01-04 02:06:11.702517+00', NULL, '2019-01-04 02:06:11.702517+00', NULL, 90102, 'pws');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff31', '0ab8d1e7bb60496b8c0ca62fb102ff03', '用户管理', '/system/cooperation/user', 0, 0, NULL, 1, '0', '2019-01-08 03:34:12+00', NULL, '2019-01-08 03:34:14+00', NULL, 90301, 'user');
INSERT INTO "sys_menu" VALUES ('9ad5de8c8cf24d8babea2fae3797ac11', '9ad5de8c8cf24d8babea2fae3797ac01', '客户信息', '/datachain/clientmanage/basicinfo', 0, 1, '', 1, '0,1', '2019-01-08 02:51:43.155492+00', '', '2019-01-08 02:51:43.155492+00', '', 10101, 'basicinfo');
INSERT INTO "sys_menu" VALUES ('f241f3152cb34f439e8eec86cdc3b3f4', '525abc902af840b984173cdefe6f2544', '首页', '/dashboard', 3, 1, '', 0, '0,1', '2019-01-08 02:37:25.044425+00', '', '2019-01-08 02:37:25.044425+00', '', 3, 'dashboard');
INSERT INTO "sys_menu" VALUES ('0ab8d1e7bb60496b8c0ca62fb102ff24', '0ab8d1e7bb60496b8c0ca62fb102ff02', '数据字典管理', '/system/management/dictionary', 3, 1, NULL, 1, '0', '2019-01-08 03:30:56+00', NULL, '2019-01-08 03:30:59+00', NULL, 90204, 'dictionary');
COMMIT;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS "sys_operation_log";
CREATE TABLE "sys_operation_log" (
  "id" varchar(64) COLLATE "pg_catalog"."default",
  "op_user" varchar(64) COLLATE "pg_catalog"."default",
  "op_type" varchar(64) COLLATE "pg_catalog"."default",
  "effect_table_id" varchar(64) COLLATE "pg_catalog"."default",
  "business_key" varchar(64) COLLATE "pg_catalog"."default",
  "effect_table" varchar(64) COLLATE "pg_catalog"."default",
  "effect_column" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "create_date" timestamp(6),
  "create_user" varchar(64) COLLATE "pg_catalog"."default",
  "update_date" timestamp(6),
  "update_user" varchar(64) COLLATE "pg_catalog"."default"
)
;
COMMENT ON TABLE "sys_operation_log" IS '系统操作日志';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
BEGIN;
INSERT INTO "sys_operation_log" VALUES ('aaeba7339fd94065b86507034adec550', 'unknown', '新增', '51c4933d305249269cb09c478db4eb96', '', 'SysBanner', '', '', '2019-02-18 14:19:08.309551', NULL, '2019-02-18 14:19:08.309551', NULL);
INSERT INTO "sys_operation_log" VALUES ('2fd31285adab408187c51c0eac5510ba', 'unknown', '新增', '9e520ea534bb495ea1dd5621fa831813', '', 'SysBanner', '', '', '2019-02-18 14:20:33.690513', NULL, '2019-02-18 14:20:33.690513', NULL);
INSERT INTO "sys_operation_log" VALUES ('43148e16aac84522a6be410fd5868ffc', 'unknown', '新增', '6b092778eefc4060b760a494d656562f', '', 'SysBanner', '', '', '2019-02-18 14:22:32.44266', NULL, '2019-02-18 14:22:32.44266', NULL);
INSERT INTO "sys_operation_log" VALUES ('0bfa5d2bd5f744d6ba85bdf92699a798', 'unknown', '新增', '84b49821497f40078050903a8e06b7d4', '标题', 'SysBanner', '', '', '2019-02-18 14:23:55.812952', NULL, '2019-02-18 14:23:55.812952', NULL);
INSERT INTO "sys_operation_log" VALUES ('da81fbce99ae45f8b8d490b7c6dfed81', 'unknown', '新增', '19ad2640e73641c5b1fd1d6808405b84', '标题', 'SysBanner', '', '', '2019-02-18 14:29:04.794088', NULL, '2019-02-18 14:29:04.794088', NULL);
INSERT INTO "sys_operation_log" VALUES ('1f2b4670361444d985e24eb87883083c', 'unknown', '新增', '5ac15cd613634a848953da937e4dcecc', '标题:001', 'SysBanner', '', '', '2019-02-18 14:35:44.432705', NULL, '2019-02-18 14:35:44.432705', NULL);
INSERT INTO "sys_operation_log" VALUES ('60f712a46bff46f195a827dbb5eae0f8', 'unknown', '创建', '8ddc5cb9c8f74a768c492994f9e094f0', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 10:39:15.64788', NULL, '2019-02-19 10:39:15.64788', NULL);
INSERT INTO "sys_operation_log" VALUES ('fd818cb6233346efa744727038c074af', 'unknown', '创建', '133b08142f474c359f18f230477b5a9a', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 10:40:09.247797', NULL, '2019-02-19 10:40:09.247797', NULL);
INSERT INTO "sys_operation_log" VALUES ('c58d3bb59a244647abaa31383f225282', 'unknown', '创建', '3a236a17f1b34da4b4690f681603fd97', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 10:41:24.518595', NULL, '2019-02-19 10:41:24.518595', NULL);
INSERT INTO "sys_operation_log" VALUES ('491d24891806433f9f08ce8c3d95a54e', 'unknown', '创建', '8619456eecc84ecab0a29d003ce69512', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 10:55:52.802384', NULL, '2019-02-19 10:55:52.802384', NULL);
INSERT INTO "sys_operation_log" VALUES ('7fff097c4fca4d15ba400e1753e06322', 'unknown', '创建', '2c6e4daf90164d189932a30985a0d0a9', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:04:21.440523', NULL, '2019-02-19 11:04:21.440523', NULL);
INSERT INTO "sys_operation_log" VALUES ('a577266b4981410ca543a9a7f4bd17bc', 'unknown', '创建', 'c1626772dee4434e827d72e7672846b3', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:04:21.480815', NULL, '2019-02-19 11:04:21.480815', NULL);
INSERT INTO "sys_operation_log" VALUES ('79c09a4504e543cfa040c2531e6f535e', 'unknown', '创建', '2d62b1a571194debac4492a9b733cc29', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:04:21.542694', NULL, '2019-02-19 11:04:21.542694', NULL);
INSERT INTO "sys_operation_log" VALUES ('e21c444b04924aeeb43185d9f30519da', 'unknown', '编辑', '2c6e4daf90164d189932a30985a0d0a9', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:06:14.929895', NULL, '2019-02-19 11:06:14.929895', NULL);
INSERT INTO "sys_operation_log" VALUES ('caf7f04b730641b298932bbffa7a4d57', 'unknown', '删除', '2c6e4daf90164d189932a30985a0d0a9', '2c6e4daf90164d189932a30985a0d0a9', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:06:15.070871', NULL, '2019-02-19 11:06:15.070871', NULL);
INSERT INTO "sys_operation_log" VALUES ('32be4e44343a4fe2988980b07a824d8d', 'unknown', '创建', '6309d947a0594b75a32679c85563b25e', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:06:15.21997', NULL, '2019-02-19 11:06:15.21997', NULL);
INSERT INTO "sys_operation_log" VALUES ('8a06125c5eae4eabb30319f0470166a5', 'unknown', '创建', 'ce511c310e3946dbb9585ac6923f2bfe', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:06:15.240208', NULL, '2019-02-19 11:06:15.240208', NULL);
INSERT INTO "sys_operation_log" VALUES ('946d512cb1864ad68d4ed2a8060df884', 'unknown', '编辑', '2c6e4daf90164d189932a30985a0d0a9', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:07:18.223951', NULL, '2019-02-19 11:07:18.223951', NULL);
INSERT INTO "sys_operation_log" VALUES ('d45ff465bd1f4e058c79a98997d3c9af', 'unknown', '删除', '2c6e4daf90164d189932a30985a0d0a9', '2c6e4daf90164d189932a30985a0d0a9', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:07:21.736414', NULL, '2019-02-19 11:07:21.736414', NULL);
INSERT INTO "sys_operation_log" VALUES ('52d166b056da4bffbbd56578c6cc27f1', 'unknown', '创建', '4bc10d6a790e42b89805697bda16a38c', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:07:43.850864', NULL, '2019-02-19 11:07:43.850864', NULL);
INSERT INTO "sys_operation_log" VALUES ('fed7b1513105439b9d20bd38c52943d8', 'unknown', '创建', '3e2f4cea811c4ac9ba9670733cbcb662', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:07:46.55173', NULL, '2019-02-19 11:07:46.55173', NULL);
INSERT INTO "sys_operation_log" VALUES ('a23db1df683f4d3f984c7e51883c6b8a', 'unknown', '编辑', '2c6e4daf90164d189932a30985a0d0a9', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:09:02.762601', NULL, '2019-02-19 11:09:02.762601', NULL);
INSERT INTO "sys_operation_log" VALUES ('acca6c31e4584578852ce478f85355c6', 'unknown', '删除', '2c6e4daf90164d189932a30985a0d0a9', '2c6e4daf90164d189932a30985a0d0a9', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:09:03.605414', NULL, '2019-02-19 11:09:03.605414', NULL);
INSERT INTO "sys_operation_log" VALUES ('48a47c7246154e05b6b23160221a9c1d', 'unknown', '创建', 'edebe19191b04f18b89568bd15e58440', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:15:30.047849', NULL, '2019-02-19 11:15:30.047849', NULL);
INSERT INTO "sys_operation_log" VALUES ('5b51dbe3db3845babc2fb0bb0c92b5d8', 'unknown', '创建', 'bfead343166e4f8b8448e89af878c382', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:15:30.067568', NULL, '2019-02-19 11:15:30.067568', NULL);
INSERT INTO "sys_operation_log" VALUES ('bfb0e2f1a4644358a1b014432f71da47', 'unknown', '创建', 'cbd8ba72d50f4ae6aff01eee33e177a9', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:15:30.193828', NULL, '2019-02-19 11:15:30.193828', NULL);
INSERT INTO "sys_operation_log" VALUES ('954ca5e6b90b4b7baea3c80c167824d9', 'unknown', '编辑', 'edebe19191b04f18b89568bd15e58440', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:16:05.168921', NULL, '2019-02-19 11:16:05.168921', NULL);
INSERT INTO "sys_operation_log" VALUES ('325f6d1909e042bd96f310a7f4eaf813', 'unknown', '删除', 'edebe19191b04f18b89568bd15e58440', 'edebe19191b04f18b89568bd15e58440', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:16:05.188416', NULL, '2019-02-19 11:16:05.188416', NULL);
INSERT INTO "sys_operation_log" VALUES ('d97b83761c3841b5bcf21b66a590b660', 'unknown', '创建', '32c5cab54c0b4fa2ad840df91d62ec35', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:16:52.704918', NULL, '2019-02-19 11:16:52.704918', NULL);
INSERT INTO "sys_operation_log" VALUES ('9a4e15278689477fb57f12c494925f7b', 'unknown', '创建', '8022fd8236f447279018e4719cd224ce', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:16:52.716846', NULL, '2019-02-19 11:16:52.716846', NULL);
INSERT INTO "sys_operation_log" VALUES ('a51c8c4543434f60922fc42ec78e73b8', 'unknown', '创建', '23a803d0147e491ebf8178d1bf9be017', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:16:52.724169', NULL, '2019-02-19 11:16:52.724169', NULL);
INSERT INTO "sys_operation_log" VALUES ('af4dcfa8c497453b92f992d54d0a7570', 'unknown', '编辑', '32c5cab54c0b4fa2ad840df91d62ec35', 'token编号:PH2006', 'TzInvestItem', '', '', '2019-02-19 11:17:37.212993', NULL, '2019-02-19 11:17:37.212993', NULL);
INSERT INTO "sys_operation_log" VALUES ('8f8a2ad8ff6145618dd63d4b35415508', 'unknown', '删除', '32c5cab54c0b4fa2ad840df91d62ec35', '32c5cab54c0b4fa2ad840df91d62ec35', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:17:37.227149', NULL, '2019-02-19 11:17:37.227149', NULL);
INSERT INTO "sys_operation_log" VALUES ('2f03aef8ce024fdf806d123955122548', 'unknown', '创建', '5e37c3481e80403b96913d68ae99a45e', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:17:37.240853', NULL, '2019-02-19 11:17:37.240853', NULL);
INSERT INTO "sys_operation_log" VALUES ('4e0b54d0be3540f19c8294e9b8d741aa', 'unknown', '创建', '677db589f06a49508e7cf988d4458ad2', '', 'TzInvestItemAgreementRel', '', '', '2019-02-19 11:17:37.255225', NULL, '2019-02-19 11:17:37.255225', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_partner
-- ----------------------------
DROP TABLE IF EXISTS "sys_partner";
CREATE TABLE "sys_partner" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "channel_mark" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "partner_name" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "user_no" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "wallet_addr" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "open_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "create_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "remark" varchar(1000) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "partner_type" varchar(2) COLLATE "pg_catalog"."default" DEFAULT '1'::character varying
)
;
COMMENT ON COLUMN "sys_partner"."id" IS '主键id';
COMMENT ON COLUMN "sys_partner"."channel_mark" IS '渠道标识，字典表';
COMMENT ON COLUMN "sys_partner"."partner_name" IS '合作方名称';
COMMENT ON COLUMN "sys_partner"."user_no" IS '用户标识';
COMMENT ON COLUMN "sys_partner"."wallet_addr" IS '钱包地址';
COMMENT ON COLUMN "sys_partner"."open_flag" IS '是否开启';
COMMENT ON COLUMN "sys_partner"."create_date" IS '创建时间';
COMMENT ON COLUMN "sys_partner"."create_user" IS '创建人';
COMMENT ON COLUMN "sys_partner"."update_date" IS '修改时间';
COMMENT ON COLUMN "sys_partner"."update_user" IS '修改人';
COMMENT ON COLUMN "sys_partner"."remark" IS '备注';
COMMENT ON COLUMN "sys_partner"."partner_type" IS '合作类型，0为最高级别（owner）';

-- ----------------------------
-- Records of sys_partner
-- ----------------------------
BEGIN;
INSERT INTO "sys_partner" VALUES ('2988e415237849d0b523d2a208fe1993', 'owner', 'owner合作方名称', NULL, '222', '1', '2018-12-25 02:15:17+00', NULL, '2019-02-15 08:22:38.732+00', '88ae146c7f374b73a5ecf51173eae7ec', '2222', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "sys_role";
CREATE TABLE "sys_role" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "role_name" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "role_desc" text COLLATE "pg_catalog"."default",
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "create_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "channel_mark" varchar(40) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "sys_role"."id" IS '主键id';
COMMENT ON COLUMN "sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "sys_role"."role_desc" IS '角色描述';
COMMENT ON COLUMN "sys_role"."create_date" IS '创建时间';
COMMENT ON COLUMN "sys_role"."create_user" IS '创建人';
COMMENT ON COLUMN "sys_role"."update_date" IS '修改时间';
COMMENT ON COLUMN "sys_role"."update_user" IS '修改人';
COMMENT ON COLUMN "sys_role"."channel_mark" IS '合作方表示';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO "sys_role" VALUES ('3aa2120bf27249a885fd8b66332476ec', 'mini角色', 'mini角色测试', '2019-02-15 08:10:00.011496+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:10:00.011496+00', '88ae146c7f374b73a5ecf51173eae7ec', 'owner');
INSERT INTO "sys_role" VALUES ('c94787932f6c40bf8d037c6ba6e507e8', 'owner钱包功能', 'owner哦', '2019-02-15 08:09:06.922269+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:41:49.977082+00', '88ae146c7f374b73a5ecf51173eae7ec', 'owner');
INSERT INTO "sys_role" VALUES ('4c3ee72e832349b5885a5b0384c99ab0', 'owner钱包+系统', 'owner钱包+系统菜单 权限', '2019-02-15 08:15:58.250987+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:41:58.617292+00', '88ae146c7f374b73a5ecf51173eae7ec', 'owner');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "sys_role_menu";
CREATE TABLE "sys_role_menu" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "role_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "menu_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "sys_role_menu"."role_id" IS '角色id';
COMMENT ON COLUMN "sys_role_menu"."menu_id" IS '菜单id';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO "sys_role_menu" VALUES ('0ac13ffc5ed349d8be79f5466f09cb66', 'c94787932f6c40bf8d037c6ba6e507e8', '3c0872cfeabc4dbc849d06dd0abcbfae');
INSERT INTO "sys_role_menu" VALUES ('e93c2133e60841dc836ddb153732a9b5', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae01');
INSERT INTO "sys_role_menu" VALUES ('e3178f12a007433aa4d1af6cfb7e09ee', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae11');
INSERT INTO "sys_role_menu" VALUES ('6f5886c42d2745b193da5e86d64959b4', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae12');
INSERT INTO "sys_role_menu" VALUES ('6270027d720b49858a8a771bb3b908c3', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae02');
INSERT INTO "sys_role_menu" VALUES ('954b6a9342e742678b68b96068a19a76', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae21');
INSERT INTO "sys_role_menu" VALUES ('26a09f9a33204fb7aa6f0da7fa31c033', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae22');
INSERT INTO "sys_role_menu" VALUES ('3b59ac0dc52d47afa8b02de03bcb8a8e', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae23');
INSERT INTO "sys_role_menu" VALUES ('6c53c9e75ad6496bb6c7fea192b0b94a', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae03');
INSERT INTO "sys_role_menu" VALUES ('12988e51a7274fd5bd7c32d51ff90c47', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae31');
INSERT INTO "sys_role_menu" VALUES ('bbc9f06ab805471998bd8460c16e13aa', 'c94787932f6c40bf8d037c6ba6e507e8', '4c0872cfeabc4dbc849d06dd6abcbfae32');
INSERT INTO "sys_role_menu" VALUES ('53ed7f3a2d4a45e29bd2ada0e0576438', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac1f');
INSERT INTO "sys_role_menu" VALUES ('88dc14d46de24c32b6e227ffc7ff6341', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac02');
INSERT INTO "sys_role_menu" VALUES ('284f251558844af0b1eea749366a498e', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac23');
INSERT INTO "sys_role_menu" VALUES ('18cf6610ee134154a72a31053e82fd06', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac21');
INSERT INTO "sys_role_menu" VALUES ('46f61d1c115e48fab439e710ec129cef', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac22');
INSERT INTO "sys_role_menu" VALUES ('28884a4595b94e94b8bcb3c940339e6f', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac01');
INSERT INTO "sys_role_menu" VALUES ('6e134312dd144c61880351bbe58e4fb6', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac11');
INSERT INTO "sys_role_menu" VALUES ('3bcd3556301644079543246223860d2c', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac03');
INSERT INTO "sys_role_menu" VALUES ('7b024ebdbc39411c9573d359de04dc13', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac31');
INSERT INTO "sys_role_menu" VALUES ('d792c115e56444e1a798f084039e104f', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac32');
INSERT INTO "sys_role_menu" VALUES ('67ec427606d24a5a93fe543cb8e34b49', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac04');
INSERT INTO "sys_role_menu" VALUES ('cedaab3317a8409a861bb95892624742', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac41');
INSERT INTO "sys_role_menu" VALUES ('d2303fb6638747c4bf4b8272250fe1c7', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac42');
INSERT INTO "sys_role_menu" VALUES ('eeeb4ede491a4aa38d445f7b5f09f3b2', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac43');
INSERT INTO "sys_role_menu" VALUES ('a3d0d404a7124d798cbfaf56cf060ded', '3aa2120bf27249a885fd8b66332476ec', '9ad5de8c8cf24d8babea2fae3797ac44');
INSERT INTO "sys_role_menu" VALUES ('08b923528d2848a19745c318fc58865f', '4c3ee72e832349b5885a5b0384c99ab0', '3c0872cfeabc4dbc849d06dd0abcbfae');
INSERT INTO "sys_role_menu" VALUES ('2e07dd1649c14f3b8b044d9f68712acd', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae01');
INSERT INTO "sys_role_menu" VALUES ('9f66c908c4c6475cba0864ea9d019d13', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae11');
INSERT INTO "sys_role_menu" VALUES ('1b1f30beaa914ae49a7488e29407e220', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae12');
INSERT INTO "sys_role_menu" VALUES ('371acbe47cf5443a8b91c5ed3839fbfe', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae02');
INSERT INTO "sys_role_menu" VALUES ('0907b4a927954a8b8ba92c78eea1fe6a', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae21');
INSERT INTO "sys_role_menu" VALUES ('c2d173bb78b141f3991fd1b887af5146', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae22');
INSERT INTO "sys_role_menu" VALUES ('6625948bd7fa4b6893d58789fe5fd528', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae23');
INSERT INTO "sys_role_menu" VALUES ('f0060535ae31425eae674af91a3ed725', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae03');
INSERT INTO "sys_role_menu" VALUES ('a5bf3d47614d4b73af7b7df242bde52b', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae31');
INSERT INTO "sys_role_menu" VALUES ('3a001e4aa7604d1f8e64a739de1a81bc', '4c3ee72e832349b5885a5b0384c99ab0', '4c0872cfeabc4dbc849d06dd6abcbfae32');
INSERT INTO "sys_role_menu" VALUES ('983491eeec634191a638bd95b03df3a8', '4c3ee72e832349b5885a5b0384c99ab0', '0ab8d1e7bb60496b8c0ca62fb102fff4');
INSERT INTO "sys_role_menu" VALUES ('5decb77deee74f92a3c0fce673d0759d', '4c3ee72e832349b5885a5b0384c99ab0', '0ab8d1e7bb60496b8c0ca62fb102ff02');
INSERT INTO "sys_role_menu" VALUES ('cc4957378bae429498c171dd34d4d876', '4c3ee72e832349b5885a5b0384c99ab0', '0ab8d1e7bb60496b8c0ca62fb102ff21');
INSERT INTO "sys_role_menu" VALUES ('f9a20a060edf4137ae3ad2bd27a17398', '4c3ee72e832349b5885a5b0384c99ab0', '0ab8d1e7bb60496b8c0ca62fb102ff23');
INSERT INTO "sys_role_menu" VALUES ('dac4044cf71042319a26b362c27d2786', '4c3ee72e832349b5885a5b0384c99ab0', '0ab8d1e7bb60496b8c0ca62fb102ff22');
INSERT INTO "sys_role_menu" VALUES ('d558d03d2eeb4ae683e1f571ce1d25f0', '4c3ee72e832349b5885a5b0384c99ab0', '0ab8d1e7bb60496b8c0ca62fb102ff24');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "sys_user";
CREATE TABLE "sys_user" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "username" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "init_pass" varchar(200) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "password" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "channel_mark" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "name" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "phone" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "email" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "open_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "user_type" varchar(2) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "create_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "sys_user"."id" IS '主键id';
COMMENT ON COLUMN "sys_user"."username" IS '用户名';
COMMENT ON COLUMN "sys_user"."init_pass" IS '初始明文密码';
COMMENT ON COLUMN "sys_user"."password" IS '密文密码';
COMMENT ON COLUMN "sys_user"."channel_mark" IS '渠道标识';
COMMENT ON COLUMN "sys_user"."name" IS '姓名';
COMMENT ON COLUMN "sys_user"."phone" IS '手机号';
COMMENT ON COLUMN "sys_user"."email" IS '邮箱';
COMMENT ON COLUMN "sys_user"."open_flag" IS '是否开启   0为是 1为否';
COMMENT ON COLUMN "sys_user"."user_type" IS '用户类型，0超级管理员，1普通管理员，2普通用户';
COMMENT ON COLUMN "sys_user"."create_date" IS '创建时间';
COMMENT ON COLUMN "sys_user"."create_user" IS '创建人';
COMMENT ON COLUMN "sys_user"."update_date" IS '修改时间';
COMMENT ON COLUMN "sys_user"."update_user" IS '修改人';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO "sys_user" VALUES ('88ae146c7f374b73a5ecf51173eae7ec', 'admin', 'owner@2018', 'f2c226ac61c0d1c54a0c3601b8d41677', 'owner', '超级管理员', '', '', '1', '0', '2019-01-16 07:42:40.739576+00', '', '2019-01-16 07:42:40.739576+00', '');
INSERT INTO "sys_user" VALUES ('ac22fcad878541dfab027b2bd65b241c', 'wucm', '123456', '31f3e2260e6cb85f89c648130b2b7a49', 'owner', '测试', '13459010000', '123456@qq.com', '1', '2', '2019-02-15 08:04:47.22673+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:04:47.22673+00', '88ae146c7f374b73a5ecf51173eae7ec');
INSERT INTO "sys_user" VALUES ('5d9e7b3ce160487e84ca8809bfb8127b', 'admin', 'mini@2018', '96ce51eb92aec20d4263a5c0a4f3c509', 'mini', '管理员', '', '', '1', '1', '2019-02-15 08:06:03.381653+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:06:03.381653+00', '88ae146c7f374b73a5ecf51173eae7ec');
INSERT INTO "sys_user" VALUES ('bf8fd861ea944ef797ef0b71552af050', 'mini', '123456', '31f3e2260e6cb85f89c648130b2b7a49', 'mini', '小测试', '13459020000', '4321@qq.com', '1', '2', '2019-02-15 08:08:38.001306+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:08:38.001306+00', '88ae146c7f374b73a5ecf51173eae7ec');
INSERT INTO "sys_user" VALUES ('54eca01da7a343b9aef0e969268132c2', 'owner', '123456', '31f3e2260e6cb85f89c648130b2b7a49', 'owner', '测试哦', '15588800001', '999@qq.com', '1', '2', '2019-02-15 08:14:23.728562+00', '88ae146c7f374b73a5ecf51173eae7ec', '2019-02-15 08:14:23.728562+00', '88ae146c7f374b73a5ecf51173eae7ec');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "sys_user_role";
CREATE TABLE "sys_user_role" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL::character varying,
  "partner_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "user_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "role_id" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "role_code" varchar(100) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "update_date" timestamptz(6) DEFAULT NULL::timestamp with time zone,
  "create_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "update_user" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying
)
;
COMMENT ON COLUMN "sys_user_role"."partner_id" IS '合作方id';
COMMENT ON COLUMN "sys_user_role"."user_id" IS '用户ID';
COMMENT ON COLUMN "sys_user_role"."role_id" IS '角色ID';
COMMENT ON COLUMN "sys_user_role"."role_code" IS '角色编码';
COMMENT ON COLUMN "sys_user_role"."create_date" IS '创建时间';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO "sys_user_role" VALUES ('44ac8d2b8e9640ff9d58eb6fd9d9ed06', '', 'ac22fcad878541dfab027b2bd65b241c', '4c3ee72e832349b5885a5b0384c99ab0', '', '2019-02-15 08:42:16.441784+00', '2019-02-15 08:42:16.441784+00', NULL, NULL);
INSERT INTO "sys_user_role" VALUES ('a5f472e95c914f339023a848ab86f930', '', '54eca01da7a343b9aef0e969268132c2', 'c94787932f6c40bf8d037c6ba6e507e8', '', '2019-02-15 08:42:24.102784+00', '2019-02-15 08:42:24.102784+00', NULL, NULL);
COMMIT;

-- ----------------------------
-- Primary Key structure for table sys_banner
-- ----------------------------
ALTER TABLE "sys_banner" ADD CONSTRAINT "sys_banner_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_dict_item
-- ----------------------------
ALTER TABLE "sys_dict_item" ADD CONSTRAINT "sys_dict_item_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_dict_type
-- ----------------------------
ALTER TABLE "sys_dict_type" ADD CONSTRAINT "sys_dict_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_menu
-- ----------------------------
ALTER TABLE "sys_menu" ADD CONSTRAINT "sys_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table sys_partner
-- ----------------------------
ALTER TABLE "sys_partner" ADD CONSTRAINT "sys_partner_channel_mark_key" UNIQUE ("channel_mark");

-- ----------------------------
-- Primary Key structure for table sys_partner
-- ----------------------------
ALTER TABLE "sys_partner" ADD CONSTRAINT "sys_partner_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "sys_role" ADD CONSTRAINT "sys_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_menu
-- ----------------------------
ALTER TABLE "sys_role_menu" ADD CONSTRAINT "sys_role_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "sys_user" ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "sys_user_role" ADD CONSTRAINT "sys_user_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table sys_dict_item
-- ----------------------------
ALTER TABLE "sys_dict_item" ADD CONSTRAINT "fk_type_id" FOREIGN KEY ("type_id") REFERENCES "sys_dict_type" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table sys_role_menu
-- ----------------------------
ALTER TABLE "sys_role_menu" ADD CONSTRAINT "fk_menu_id" FOREIGN KEY ("menu_id") REFERENCES "sys_menu" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "sys_role_menu" ADD CONSTRAINT "fk_role_id" FOREIGN KEY ("role_id") REFERENCES "sys_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table sys_user
-- ----------------------------
ALTER TABLE "sys_user" ADD CONSTRAINT "fk_channel_mark" FOREIGN KEY ("channel_mark") REFERENCES "sys_partner" ("channel_mark") ON DELETE NO ACTION ON UPDATE NO ACTION;
