  SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `do_auth_owner`;
CREATE TABLE `do_auth_owner` (
  `objUid` varchar(32) NOT NULL default '',
  `whereDOBO` varchar(32) default NULL,
  `whereBOInstanceUid` varchar(32) default NULL,
  `authorUid` varchar(32) default NULL,
  `parterUid` varchar(32) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objUid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_auth_suite`;
CREATE TABLE `do_auth_suite` (
  `objuid` varchar(32) NOT NULL default '',
  `name` varchar(100) default NULL,
  `l10n` varchar(300) default NULL,
  `suiteMainMenuUid` varchar(32) default NULL,
  `includeAuthTxt` varchar(50) default NULL,
  `extendConfig` varchar(500) default NULL,
  `includeType` int(11) default NULL,
  `includeUid` varchar(32) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_authorization`;
CREATE TABLE `do_authorization` (
  `objUID` varchar(32) NOT NULL default '',
  `parterUid` varchar(32) default NULL,
  `ouUid` varchar(100) default NULL,
  `roleUid` varchar(32) default NULL,
  `whereDOBO` varchar(32) default NULL,
  `whereBOInstanceUid` varchar(32) default NULL,
  `whatType` int(11) default NULL,
  `whatUid` varchar(32) default NULL,
  `authority` char(1) default NULL,
  `isInherit` char(1) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

DROP TABLE IF EXISTS `do_log`;
CREATE TABLE `do_log` (
  `objuid` varchar(32) NOT NULL default '',
  `userName` varchar(50) default NULL,
  `deptName` varchar(255) default NULL,
  `loginTime` datetime default NULL,
  `logoffTime` datetime default NULL,
  `ip` varchar(50) default NULL,
  `sessionid` varchar(50) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_log_data`;
CREATE TABLE `do_log_data` (
  `OBJUID` varchar(32) NOT NULL default '',
  `TABLE_NAME` varchar(100) default NULL,
  `COL_NAME` varchar(100) default NULL,
  `WHO_UID` varchar(32) default NULL,
  `BO_UID` varchar(32) default NULL,
  `COL_UID` varchar(32) default NULL,
  `OPER_TYPE` varchar(50) default NULL,
  `OPER_DATA` varchar(100) default NULL,
  `OPER_TIME` datetime default NULL,
  `OPER_DATA_UID` varchar(32) default NULL,
  `OPER_PANE_UID` varchar(32) default NULL,
  `OLD_VALUE` varchar(2000) default NULL,
  `NEW_VALUE` varchar(2000) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_org_dept`;
CREATE TABLE `do_org_dept` (
  `objUid` varchar(32) NOT NULL,
  `dept_code` varchar(50) default NULL,
  `name` varchar(50) default NULL,
  `leader` varchar(32) default NULL,
  `type` int(11) default NULL,
  `order_num` int(11) default NULL,
  `location` varchar(100) default NULL,
  `tel` varchar(20) default NULL,
  `parentUid` varchar(32) default NULL,
  `note` varchar(255) default NULL,
  PRIMARY KEY  (`objUid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `do_org_dept` VALUES ('4028803127b6f15a0127b7294a7a0004', 'pingtaibumen', '平台部门', '40288031278ed91501278ed915b30000', '2', null, null, null, null, null);

DROP TABLE IF EXISTS `do_org_role`;
CREATE TABLE `do_org_role` (
  `objUid` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `roleId` varchar(50) default NULL,
  `parentUid` bigint(20) default NULL,
  `degree` int(11) default NULL,
  `description` text,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objUid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `do_org_role` VALUES ('4028803127b6f15a0127b725c6930003', '普通员工', 'putongyuangong', null, '1', '普通员工', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdb80af0001', '部门经理', 'bumenjingli', null, null, '部门经理', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdc23670002', '副总经理', 'fuzongjingli', null, null, '副总经理', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdc70100003', '财务主管', 'caiwuzhuguan', null, null, null, null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fe80c5a0009', '总经理', 'zongjingli', null, null, '总经理', null, null, null, null, null);
INSERT INTO `do_org_role` VALUES ('40288031288a2b8501288a3d009d000d', '系统管理员', 'system_manager', null, null, null, null, null, null, null, null);


DROP TABLE IF EXISTS `do_org_role_conflict`;
CREATE TABLE `do_org_role_conflict` (
  `objUid` varchar(32) NOT NULL default '',
  `roleUid` varchar(32) default NULL,
  `roleConflictUid` varchar(32) default NULL,
  `staticType` char(1) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objUid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;


DROP TABLE IF EXISTS `do_org_timespan`;
CREATE TABLE `do_org_timespan` (
  `objUid` varchar(50) NOT NULL,
  `name` varchar(50) default NULL,
  `beginTime` datetime default NULL,
  `endTime` datetime default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objUid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_org_user`;
CREATE TABLE `do_org_user` (
  `objuid` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `user_code` varchar(255) default NULL,
  `password` varchar(50) default NULL,
  `deptuid` varchar(255) default NULL,
  `gender` char(1) default NULL,
  `birthday` datetime default NULL,
  `email` varchar(50) default NULL,
  `telephone` varchar(50) default NULL,
  `mobile` varchar(50) default NULL,
  `address` varchar(50) default NULL,
  `creator` varchar(255) default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;


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
);

INSERT INTO `do_org_user` VALUES ('40288031278ed91501278ed915b30000', 'u', '0005', 'b59c67bf196a4758191e42f76670ceba', '4028803127b6f15a0127b7294a7a0004', null, null, 'u@u.com', null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f012880415d96006c', 'xz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f01288045ecd3006e', 'xw', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f012880469c2d006f', 'hz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f012880472b4e0070', 'wz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `do_org_user` VALUES ('40288031288a2b8501288a3704e00005', 'lz', 'lz', 'd41d8cd98f00b204e9800998ecf8427e', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);


DROP TABLE IF EXISTS `do_org_user_role`;
CREATE TABLE `do_org_user_role` (
  `OBJUID` varchar(32) NOT NULL,
  `USER_UID` varchar(32) default NULL,
  `ROLE_UID` varchar(50) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047ad6e0071', '40288031287fd27f01288045ecd3006e', '4028803127b6f15a0127b725c6930003');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047d1cf0072', '40288031287fd27f012880469c2d006f', '40288031287fd27f01287fe80c5a0009');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047f3de0073', '40288031287fd27f012880472b4e0070', '40288031287fd27f01287fdc23670002');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f0128804811f50074', '40288031287fd27f012880415d96006c', '40288031287fd27f01287fdc70100003');
INSERT INTO `do_org_user_role` VALUES ('40288031288a2b8501288a3d2a1e000e', '40288031278ed91501278ed915b30000', '40288031288a2b8501288a3d009d000d');


DROP TABLE IF EXISTS `do_wfi_his_ni_dependency`;
CREATE TABLE `do_wfi_his_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_wfi_his_nodeinstance`;
CREATE TABLE `do_wfi_his_nodeinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `NodeName` varchar(255) NOT NULL,
  `NodeDesc` varchar(1000) default NULL,
  `nodeDate` datetime default NULL,
  `NodeType` tinyint(4) default NULL,
  `authType` int(11) default NULL,
  `nodeStateShow` varchar(255) default NULL,
  `nodeStateShowBack` varchar(255) default NULL,
  `spec_Name` varchar(255) default NULL,
  `ExeStatus` tinyint(4) default NULL,
  `DecisionExpression` varchar(500) default NULL,
  `PI_UID` varchar(50) NOT NULL,
  `scheduleOUUid` varchar(50) default NULL,
  `performerUid` varchar(50) default NULL,
  `node_uid` varchar(50) default NULL,
  `backType` tinyint(4) default NULL,
  `pass_txt` varchar(500) default NULL,
  `reject_txt` varchar(500) default NULL,
  `node_ext1` varchar(500) default NULL,
  `node_ext2` varchar(500) default NULL,
  `retNodeUID` varchar(50) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_wfi_his_processinstance`;
CREATE TABLE `do_wfi_his_processinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `WFI_Name` varchar(255) NOT NULL,
  `PT_Name` varchar(255) NOT NULL,
  `WFI_Desc` varchar(2000) default NULL,
  `ExeStatus` tinyint(4) default NULL,
  `PT_UID` varchar(50) NOT NULL,
  `startUser` varchar(50) default NULL,
  `startTime` datetime default NULL,
  `curState` varchar(255) default NULL,
  `curStateTime` datetime default NULL,
  `curStateUser` varchar(50) default NULL,
  `rejectTxt` varchar(2000) default NULL,
  `instance_uid` varchar(50) default NULL,
  `instance2_uid` varchar(32) default NULL,
  `instance3_uid` varchar(32) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_wfi_his_varinstance`;
CREATE TABLE `do_wfi_his_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) default NULL,
  `pt_var_uid` varchar(32) default NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) default NULL,
  `instance_uid` varchar(100) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_wfi_ni_dependency`;
CREATE TABLE `do_wfi_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_wfi_nodeinstance`;
CREATE TABLE `do_wfi_nodeinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `NodeName` varchar(255) NOT NULL,
  `NodeDesc` varchar(1000) default NULL,
  `nodeDate` datetime default NULL,
  `NodeType` tinyint(4) default NULL,
  `authType` int(11) default NULL,
  `nodeStateShow` varchar(255) default NULL,
  `nodeStateShowBack` varchar(255) default NULL,
  `spec_Name` varchar(255) default NULL,
  `ExeStatus` tinyint(4) default NULL,
  `DecisionExpression` varchar(500) default NULL,
  `PI_UID` varchar(50) NOT NULL,
  `scheduleOUUid` varchar(50) default NULL,
  `performerUid` varchar(50) default NULL,
  `node_uid` varchar(50) default NULL,
  `backType` tinyint(4) default NULL,
  `pass_txt` varchar(500) default NULL,
  `reject_txt` varchar(500) default NULL,
  `node_ext1` varchar(500) default NULL,
  `node_ext2` varchar(500) default NULL,
  `retNodeUID` varchar(50) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_wfi_processinstance`;
CREATE TABLE `do_wfi_processinstance` (
  `OBJUID` varchar(32) NOT NULL,
  `WFI_Name` varchar(255) NOT NULL,
  `PT_Name` varchar(255) NOT NULL,
  `WFI_Desc` varchar(2000) default NULL,
  `ExeStatus` tinyint(4) default NULL,
  `PT_UID` varchar(50) NOT NULL,
  `startUser` varchar(50) default NULL,
  `startTime` datetime default NULL,
  `curState` varchar(255) default NULL,
  `curStateTime` datetime default NULL,
  `curStateUser` varchar(50) default NULL,
  `rejectTxt` varchar(2000) default NULL,
  `instance_uid` varchar(50) default NULL,
  `instance2_uid` varchar(32) default NULL,
  `instance3_uid` varchar(32) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `do_wfi_varinstance`;
CREATE TABLE `do_wfi_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) default NULL,
  `pt_var_uid` varchar(32) default NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) default NULL,
  `instance_uid` varchar(100) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE DO_CODE_MAXSEQUENCE (
	OBJUID varchar (32)  NOT NULL ,
	SEQUENCE_NAME varchar (255)  NULL ,
	CODE_ITEMUID varchar (32)  NULL ,
	PROPERTYUID varchar (32)  NULL ,
	PROPERTYVALUE varchar (255)  NULL ,
	YEARSEQ decimal(10, 0) NULL ,
	MAX_SEQUENCE decimal(19, 0) NULL ,
	creator varchar (255)  NULL ,
	creatDate datetime NULL ,
	modifier varchar (255)  NULL ,
	modifyDate datetime NULL ,
	mVersion varchar (50)  NULL 
);


CREATE TABLE do_log_dev (
  id varchar(32) NOT NULL,
  threadstr varchar(255) default NULL,
  starttime datetime default NULL,
  userName varchar(256) default NULL,
  ip varchar(128) default NULL,
  msgstr text,
  col1 varchar(255) default NULL,
  col2 varchar(255) default NULL,
  col3 varchar(255) default NULL,
  PRIMARY KEY  (id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_expense`;
CREATE TABLE `t_expense` (
  `id` varchar(32) NOT NULL default '',
  `title` varchar(255) default NULL,
  `expense_man` varchar(32) NOT NULL default '',
  `expense_date` date default NULL,
  `expense_money` float NOT NULL default '0',
  `note` text default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP VIEW IF EXISTS `wf_db`;
CREATE  VIEW `wf_db` AS select distinct `wpi`.`curState` AS `curstate`,`ni`.`node_uid` AS `node_uid`,`ni`.`nodeDate` AS `nodeDate`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`pass_txt` AS `pass_txt`,`ni`.`reject_txt` AS `reject_txt`,`ur`.`USER_UID` AS `user_uid`,`wpi`.`WFI_Desc` AS `WFI_Desc`,`wpi`.`startUser` AS `startUser`,`wpi`.`startTime` AS `startTime` from (((`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi`) join `do_org_user_role` `ur`) join `do_authorization` `a`) where ((`wpi`.`OBJUID` = `ni`.`PI_UID`) and (`a`.`parterUid` = _utf8'9') and (`a`.`ouUid` = `ur`.`ROLE_UID`) and (`ni`.`node_uid` = `a`.`whatUid`) and (`ni`.`ExeStatus` = 2) and (`wpi`.`ExeStatus` = 2));
CREATE  VIEW `wf_yb` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 2));
CREATE  VIEW `wf_bj` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNiUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_his_nodeinstance` `ni` join `do_wfi_his_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 3));
CREATE VIEW  wf_db_schedule     AS SELECT DISTINCT
  `wpi`.`curState`     AS `curstate`,
  `ni`.`node_uid`      AS `node_uid`,
  `ni`.`nodeDate`      AS `nodeDate`,
  `ni`.`OBJUID`        AS `contextNIUid`,
  `wpi`.`OBJUID`       AS `contextPIUid`,
  `wpi`.`instance_uid` AS `instance_uid`,
  `ni`.`pass_txt`      AS `pass_txt`,
  `ni`.`reject_txt`    AS `reject_txt`,
  `ur`.`USER_UID`      AS `user_uid`,
  `wpi`.`WFI_Desc`     AS `WFI_Desc`,
  `wpi`.`startUser`    AS `startUser`,
  `wpi`.`startTime`    AS `startTime`
FROM (((`do_wfi_nodeinstance` `ni`
     JOIN `do_wfi_processinstance` `wpi`)
    JOIN `do_org_user_role` `ur`)
   JOIN `do_authorization` `a`)
WHERE ((`wpi`.`OBJUID` = `ni`.`PI_UID`)
       AND (`a`.`parterUid` = '9')
       AND (`a`.`ouUid` = `ur`.`ROLE_UID`)
       AND (`ni`.`node_uid` = `a`.`whatUid`)
       AND (`ni`.`ExeStatus` = 2)
       AND (`wpi`.`ExeStatus` = 2))
       
UNION   SELECT DISTINCT
  `wpi`.`curState`     AS `curstate`,
  `ni`.`node_uid`      AS `node_uid`,
  `ni`.`nodeDate`      AS `nodeDate`,
  `ni`.`OBJUID`        AS `contextNIUid`,
  `wpi`.`OBJUID`       AS `contextPIUid`,
  `wpi`.`instance_uid` AS `instance_uid`,
  `ni`.`pass_txt`      AS `pass_txt`,
  `ni`.`reject_txt`    AS `reject_txt`,
  `a`.`ouUid`          AS `user_uid`,
  `wpi`.`WFI_Desc`     AS `WFI_Desc`,
  `wpi`.`startUser`    AS `startUser`,
  `wpi`.`startTime`    AS `startTime`
FROM ((`do_wfi_nodeinstance` `ni`
     JOIN `do_wfi_processinstance` `wpi`)
      JOIN `do_authorization` `a`)
WHERE ((`wpi`.`OBJUID` = `ni`.`PI_UID`)
       AND (`a`.`parterUid` = 1)
       AND (`ni`.`objuid` = `a`.`whatUid`)
       AND (`ni`.`ExeStatus` = 2)
       AND (`wpi`.`ExeStatus` = 2));