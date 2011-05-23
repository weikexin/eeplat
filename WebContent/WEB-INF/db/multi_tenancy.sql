/*
MySQL Data Transfer
Source Host: localhost
Source Database: multi_tenancy
Target Host: localhost
Target Database: multi_tenancy
Date: 2011-5-23 19:17:08
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for do_authorization
-- ----------------------------
DROP TABLE IF EXISTS `do_authorization`;
CREATE TABLE `do_authorization` (
  `objUID` varchar(32) NOT NULL DEFAULT '',
  `parterUid` varchar(32) DEFAULT NULL,
  `ouUid` varchar(100) DEFAULT NULL,
  `roleUid` varchar(32) DEFAULT NULL,
  `whereDOBO` varchar(32) DEFAULT NULL,
  `whereBOInstanceUid` varchar(32) DEFAULT NULL,
  `whatType` int(11) DEFAULT NULL,
  `whatUid` varchar(32) DEFAULT NULL,
  `authority` char(1) DEFAULT NULL,
  `isInherit` char(1) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `creatDate` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `mVersion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`objUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_code_maxsequence
-- ----------------------------
DROP TABLE IF EXISTS `do_code_maxsequence`;
CREATE TABLE `do_code_maxsequence` (
  `OBJUID` varchar(32) NOT NULL,
  `SEQUENCE_NAME` varchar(255) DEFAULT NULL,
  `CODE_ITEMUID` varchar(32) DEFAULT NULL,
  `PROPERTYUID` varchar(32) DEFAULT NULL,
  `PROPERTYVALUE` varchar(255) DEFAULT NULL,
  `YEARSEQ` decimal(10,0) DEFAULT NULL,
  `MAX_SEQUENCE` decimal(19,0) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `creatDate` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `mVersion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_log
-- ----------------------------
DROP TABLE IF EXISTS `do_log`;
CREATE TABLE `do_log` (
  `objuid` varchar(32) NOT NULL DEFAULT '',
  `userName` varchar(50) DEFAULT NULL,
  `deptName` varchar(255) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `logoffTime` datetime DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `sessionid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_log_data
-- ----------------------------
DROP TABLE IF EXISTS `do_log_data`;
CREATE TABLE `do_log_data` (
  `OBJUID` varchar(32) NOT NULL DEFAULT '',
  `TABLE_NAME` varchar(100) DEFAULT NULL,
  `COL_NAME` varchar(100) DEFAULT NULL,
  `WHO_UID` varchar(32) DEFAULT NULL,
  `BO_UID` varchar(32) DEFAULT NULL,
  `COL_UID` varchar(32) DEFAULT NULL,
  `OPER_TYPE` varchar(50) DEFAULT NULL,
  `OPER_DATA` varchar(100) DEFAULT NULL,
  `OPER_TIME` datetime DEFAULT NULL,
  `OPER_DATA_UID` varchar(32) DEFAULT NULL,
  `OPER_PANE_UID` varchar(32) DEFAULT NULL,
  `OLD_VALUE` varchar(2000) DEFAULT NULL,
  `NEW_VALUE` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_org_dept
-- ----------------------------
DROP TABLE IF EXISTS `do_org_dept`;
CREATE TABLE `do_org_dept` (
  `objUid` varchar(32) NOT NULL,
  `dept_code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `leader` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `parentUid` varchar(32) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`objUid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_org_role
-- ----------------------------
DROP TABLE IF EXISTS `do_org_role`;
CREATE TABLE `do_org_role` (
  `objUid` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `roleId` varchar(50) DEFAULT NULL,
  `parentUid` bigint(20) DEFAULT NULL,
  `degree` int(11) DEFAULT NULL,
  `description` text,
  `creator` varchar(255) DEFAULT NULL,
  `creatDate` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `mVersion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`objUid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_org_user
-- ----------------------------
DROP TABLE IF EXISTS `do_org_user`;
CREATE TABLE `do_org_user` (
  `objuid` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `user_code` varchar(255) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `deptuid` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `mVersion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
-- Table structure for do_org_user_delegate
-- ----------------------------
DROP TABLE IF EXISTS `do_org_user_delegate`;
CREATE TABLE `do_org_user_delegate` (
  `objuid` varchar(32) NOT NULL,
  `user_uid` varchar(50) DEFAULT NULL,
  `delegate_uid` varchar(50) DEFAULT NULL,
  `isValid` char(1) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `note` text,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `mVersion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_org_user_role
-- ----------------------------
DROP TABLE IF EXISTS `do_org_user_role`;
CREATE TABLE `do_org_user_role` (
  `OBJUID` varchar(32) NOT NULL,
  `USER_UID` varchar(32) DEFAULT NULL,
  `ROLE_UID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_his_ni_dependency
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_his_ni_dependency`;
CREATE TABLE `do_wfi_his_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) DEFAULT NULL,
  `Post_NID_UID` varchar(32) DEFAULT NULL,
  `do_condition` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_his_nodeinstance
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_his_nodeinstance`;
CREATE TABLE `do_wfi_his_nodeinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `NodeName` varchar(255) NOT NULL,
  `NodeDesc` varchar(1000) DEFAULT NULL,
  `nodeDate` datetime DEFAULT NULL,
  `NodeType` tinyint(4) DEFAULT NULL,
  `authType` int(11) DEFAULT NULL,
  `nodeStateShow` varchar(255) DEFAULT NULL,
  `nodeStateShowBack` varchar(255) DEFAULT NULL,
  `spec_Name` varchar(255) DEFAULT NULL,
  `ExeStatus` tinyint(4) DEFAULT NULL,
  `DecisionExpression` varchar(500) DEFAULT NULL,
  `PI_UID` varchar(50) NOT NULL,
  `scheduleOUUid` varchar(50) DEFAULT NULL,
  `performerUid` varchar(50) DEFAULT NULL,
  `node_uid` varchar(50) DEFAULT NULL,
  `backType` tinyint(4) DEFAULT NULL,
  `pass_txt` varchar(500) DEFAULT NULL,
  `reject_txt` varchar(500) DEFAULT NULL,
  `node_ext1` varchar(500) DEFAULT NULL,
  `node_ext2` varchar(500) DEFAULT NULL,
  `retNodeUID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_his_processinstance
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_his_processinstance`;
CREATE TABLE `do_wfi_his_processinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `WFI_Name` varchar(255) NOT NULL,
  `PT_Name` varchar(255) NOT NULL,
  `WFI_Desc` varchar(2000) DEFAULT NULL,
  `ExeStatus` tinyint(4) DEFAULT NULL,
  `PT_UID` varchar(50) NOT NULL,
  `startUser` varchar(50) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `curState` varchar(255) DEFAULT NULL,
  `curStateTime` datetime DEFAULT NULL,
  `curStateUser` varchar(50) DEFAULT NULL,
  `rejectTxt` varchar(2000) DEFAULT NULL,
  `instance_uid` varchar(50) DEFAULT NULL,
  `instance2_uid` varchar(32) DEFAULT NULL,
  `instance3_uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_his_varinstance
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_his_varinstance`;
CREATE TABLE `do_wfi_his_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) DEFAULT NULL,
  `pt_var_uid` varchar(32) DEFAULT NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) DEFAULT NULL,
  `instance_uid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_ni_dependency
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_ni_dependency`;
CREATE TABLE `do_wfi_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) DEFAULT NULL,
  `Post_NID_UID` varchar(32) DEFAULT NULL,
  `do_condition` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_nodeinstance
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_nodeinstance`;
CREATE TABLE `do_wfi_nodeinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `NodeName` varchar(255) NOT NULL,
  `NodeDesc` varchar(1000) DEFAULT NULL,
  `nodeDate` datetime DEFAULT NULL,
  `NodeType` tinyint(4) DEFAULT NULL,
  `authType` int(11) DEFAULT NULL,
  `nodeStateShow` varchar(255) DEFAULT NULL,
  `nodeStateShowBack` varchar(255) DEFAULT NULL,
  `spec_Name` varchar(255) DEFAULT NULL,
  `ExeStatus` tinyint(4) DEFAULT NULL,
  `DecisionExpression` varchar(500) DEFAULT NULL,
  `PI_UID` varchar(50) NOT NULL,
  `scheduleOUUid` varchar(50) DEFAULT NULL,
  `performerUid` varchar(50) DEFAULT NULL,
  `node_uid` varchar(50) DEFAULT NULL,
  `backType` tinyint(4) DEFAULT NULL,
  `pass_txt` varchar(500) DEFAULT NULL,
  `reject_txt` varchar(500) DEFAULT NULL,
  `node_ext1` varchar(500) DEFAULT NULL,
  `node_ext2` varchar(500) DEFAULT NULL,
  `retNodeUID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_processinstance
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_processinstance`;
CREATE TABLE `do_wfi_processinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `WFI_Name` varchar(255) NOT NULL,
  `PT_Name` varchar(255) NOT NULL,
  `WFI_Desc` varchar(2000) DEFAULT NULL,
  `ExeStatus` tinyint(4) DEFAULT NULL,
  `PT_UID` varchar(50) NOT NULL,
  `startUser` varchar(50) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `curState` varchar(255) DEFAULT NULL,
  `curStateTime` datetime DEFAULT NULL,
  `curStateUser` varchar(50) DEFAULT NULL,
  `rejectTxt` varchar(2000) DEFAULT NULL,
  `instance_uid` varchar(50) DEFAULT NULL,
  `instance2_uid` varchar(32) DEFAULT NULL,
  `instance3_uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for do_wfi_varinstance
-- ----------------------------
DROP TABLE IF EXISTS `do_wfi_varinstance`;
CREATE TABLE `do_wfi_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) DEFAULT NULL,
  `pt_var_uid` varchar(32) DEFAULT NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) DEFAULT NULL,
  `instance_uid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for multi_tenancy
-- ----------------------------
DROP TABLE IF EXISTS `multi_tenancy`;
CREATE TABLE `multi_tenancy` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL,
  `l10n` varchar(255) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `note` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for multi_tenancy_column
-- ----------------------------
DROP TABLE IF EXISTS `multi_tenancy_column`;
CREATE TABLE `multi_tenancy_column` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `col_name` varchar(255) DEFAULT NULL,
  `tenancy_table_uid` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `real_col` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for multi_tenancy_table
-- ----------------------------
DROP TABLE IF EXISTS `multi_tenancy_table`;
CREATE TABLE `multi_tenancy_table` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `table_name` varchar(255) DEFAULT NULL,
  `table_l10n` varchar(255) DEFAULT NULL,
  `tenancy_uid` varchar(32) DEFAULT NULL,
  `real_table` varchar(32) DEFAULT NULL,
  `corr_view` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_expense_a
-- ----------------------------
DROP TABLE IF EXISTS `t_expense_a`;
CREATE TABLE `t_expense_a` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(255) DEFAULT NULL,
  `expense_man` varchar(32) NOT NULL DEFAULT '',
  `expense_date` date DEFAULT NULL,
  `expense_money` float NOT NULL DEFAULT '0',
  `note` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for wf_bj
-- ----------------------------
DROP VIEW IF EXISTS `wf_bj`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_bj` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNiUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_his_nodeinstance` `ni` join `do_wfi_his_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 3));

-- ----------------------------
-- View structure for wf_db
-- ----------------------------
DROP VIEW IF EXISTS `wf_db`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_db` AS select distinct `wpi`.`curState` AS `curstate`,`ni`.`node_uid` AS `node_uid`,`ni`.`nodeDate` AS `nodeDate`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`pass_txt` AS `pass_txt`,`ni`.`reject_txt` AS `reject_txt`,`ur`.`USER_UID` AS `user_uid`,`wpi`.`WFI_Desc` AS `WFI_Desc`,`wpi`.`startUser` AS `startUser`,`wpi`.`startTime` AS `startTime` from (((`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi`) join `do_org_user_role` `ur`) join `do_authorization` `a`) where ((`wpi`.`OBJUID` = `ni`.`PI_UID`) and (`a`.`parterUid` = _utf8'9') and (`a`.`ouUid` = `ur`.`ROLE_UID`) and (`ni`.`node_uid` = `a`.`whatUid`) and (`ni`.`ExeStatus` = 2) and (`wpi`.`ExeStatus` = 2));

-- ----------------------------
-- View structure for wf_yb
-- ----------------------------
DROP VIEW IF EXISTS `wf_yb`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_yb` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 2));

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `do_authorization` VALUES ('402880312870acd1012870f1133a0001', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '110', 'test_n12', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f0128801f4bb9000a', '9', '40288031287fd27f01287fdc23670002', null, null, null, '110', 'tt2_n11', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f0128801f849c000b', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '110', 'tt2_n12', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f01287fdfd2a40005', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '110', 'tt2_n6', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('402880312870acd10128712bf8ae000e', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '110', 'test_n7', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f0128801fb3f9000c', '9', '40288031287fd27f01287fdc70100003', null, null, null, '110', 'tt2_n13', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852439e90004', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d7012885249bc00006', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288524b1d80007', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852572b70008', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852588620009', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288525a57f000a', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288525bbe6000b', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288525d4ec000c', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852615cd000e', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288529a9c2000f', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3652c60001', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3660920002', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a367a820003', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3685cd0004', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a383e1c0007', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a384a220008', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3858890009', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a386cbd000a', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a387960000b', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3d6812000f', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312788b836012788b844d10009', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ea76a0002', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312788b836012788b9b77c012c', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4eb8fe0003', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312788b836012788ba043401ed', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ed4e20004', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '40288031278f16d001278f43e525003c', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ee4c10005', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '40288031278904920127890492720000', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ef4af0006', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '40288031278a406e01278a6bb9ce005d', '1', '1', null, null, null, null, null);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4f02d80007', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312793120d01279318032e0260', '1', '1', null, null, null, null, null);
INSERT INTO `do_log` VALUES ('2c90b186301c87f101301c87f1150000', 'a', null, '2011-05-23 19:05:29', null, '127.0.0.1', 'B3B48AB3C915A415872D889B766C136D');
INSERT INTO `do_log` VALUES ('2c90b186301c87f101301c87f24d0001', 'a', null, '2011-05-23 19:05:29', null, '127.0.0.1', 'B3B48AB3C915A415872D889B766C136D');
INSERT INTO `do_log` VALUES ('2c90b186301c895701301c89578b0000', 'a', null, '2011-05-23 19:07:00', null, '127.0.0.1', '36AF9E3569FB4CE01537F438D2CD6B6C');
INSERT INTO `do_log` VALUES ('2c90b186301c8e8301301c8e83d20000', 'a', null, '2011-05-23 19:12:39', null, '127.0.0.1', '8817B465FA1D94504F6A9A97483EBCF4');
INSERT INTO `do_log` VALUES ('2c90b186301c904201301c9042e70000', 'a', null, '2011-05-23 19:14:34', null, '127.0.0.1', '7D507565BB8AE4EA7FA88AEFEE11252D');
INSERT INTO `do_log_data` VALUES ('2c90b186301b3bff01301b3c00560001', 'multi_tenancy', null, 'aa', null, null, '增加租户', '111', null, '2c90b186301b3bff01301b3bff7b0000', null, null, null);
INSERT INTO `do_org_dept` VALUES ('4028803127b6f15a0127b7294a7a0004', 'pingtaibumen', '平台部门', '40288031278ed91501278ed915b30000', '2', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('4028803127b6f15a0127b725c6930003', '普通员工', 'putongyuangong', null, '1', '普通员工', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdb80af0001', '部门经理', 'bumenjingli', null, null, '部门经理', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdc23670002', '副总经理', 'fuzongjingli', null, null, '副总经理', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdc70100003', '财务主管', 'caiwuzhuguan', null, null, null, null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fe80c5a0009', '总经理', 'zongjingli', null, null, '总经理', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031288a2b8501288a3d009d000d', '系统管理员', 'system_manager', null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031278ed91501278ed915b30000', 'u', '0005', 'b59c67bf196a4758191e42f76670ceba', '4028803127b6f15a0127b7294a7a0004', null, null, 'u@u.com', null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f012880415d96006c', 'xz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f01288045ecd3006e', 'xw', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f012880469c2d006f', 'hz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f012880472b4e0070', 'wz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031288a2b8501288a3704e00005', 'lz', 'lz', 'd41d8cd98f00b204e9800998ecf8427e', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047ad6e0071', '40288031287fd27f01288045ecd3006e', '4028803127b6f15a0127b725c6930003');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047d1cf0072', '40288031287fd27f012880469c2d006f', '40288031287fd27f01287fe80c5a0009');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047f3de0073', '40288031287fd27f012880472b4e0070', '40288031287fd27f01287fdc23670002');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f0128804811f50074', '40288031287fd27f012880415d96006c', '40288031287fd27f01287fdc70100003');
INSERT INTO `do_org_user_role` VALUES ('40288031288a2b8501288a3d2a1e000e', '40288031278ed91501278ed915b30000', '40288031288a2b8501288a3d009d000d');
INSERT INTO `multi_tenancy_column` VALUES ('2c90b186301c904201301c9142370002', 'id', '2c90b186301c904201301c9141aa0001', 'VARCHAR32', 'c011');
INSERT INTO `multi_tenancy_column` VALUES ('2c90b186301c904201301c9142940003', 'location', '2c90b186301c904201301c9141aa0001', 'VARCHAR255', 'c061');
INSERT INTO `multi_tenancy_column` VALUES ('2c90b186301c904201301c9142a40004', 'name', '2c90b186301c904201301c9141aa0001', 'VARCHAR32', 'c012');
INSERT INTO `multi_tenancy_table` VALUES ('2c90b186301c904201301c9141aa0001', 'ttaat', null, null, null, 'ttaat');
