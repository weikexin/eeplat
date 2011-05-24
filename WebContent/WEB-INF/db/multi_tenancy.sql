# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: multi_tenancy
# ------------------------------------------------------
# Server version 5.0.24a-community-nt-log

#
# Source for table do_authorization
#

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
  `mVersion` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_authorization
#

LOCK TABLES `do_authorization` WRITE;
/*!40000 ALTER TABLE `do_authorization` DISABLE KEYS */;
INSERT INTO `do_authorization` VALUES ('402880312870acd1012870f1133a0001','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'test_n12','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f0128801f4bb9000a','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,110,'tt2_n11','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f0128801f849c000b','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,110,'tt2_n12','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f01287fdfd2a40005','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'tt2_n6','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('402880312870acd10128712bf8ae000e','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'test_n7','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031287fd27f0128801fb3f9000c','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,110,'tt2_n13','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852439e90004','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d7012885249bc00006','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288524b1d80007','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852572b70008','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852588620009','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288525a57f000a','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288525bbe6000b','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288525d4ec000c','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d70128852615cd000e','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288512d701288529a9c2000f','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3652c60001','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3660920002','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a367a820003','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3685cd0004','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a383e1c0007','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a384a220008','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3858890009','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a386cbd000a','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a387960000b','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a2b8501288a3d6812000f','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788b844d10009','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ea76a0002','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788b9b77c012c','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4eb8fe0003','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788ba043401ed','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ed4e20004','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278f16d001278f43e525003c','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ee4c10005','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278904920127890492720000','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4ef4af0006','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278a406e01278a6bb9ce005d','1','1',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_authorization` VALUES ('40288031288a4cb701288a4f02d80007','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312793120d01279318032e0260','1','1',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `do_authorization` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_code_maxsequence
#

DROP TABLE IF EXISTS `do_code_maxsequence`;
CREATE TABLE `do_code_maxsequence` (
  `OBJUID` varchar(32) NOT NULL,
  `SEQUENCE_NAME` varchar(255) default NULL,
  `CODE_ITEMUID` varchar(32) default NULL,
  `PROPERTYUID` varchar(32) default NULL,
  `PROPERTYVALUE` varchar(255) default NULL,
  `YEARSEQ` decimal(10,0) default NULL,
  `MAX_SEQUENCE` decimal(19,0) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_code_maxsequence
#

LOCK TABLES `do_code_maxsequence` WRITE;
/*!40000 ALTER TABLE `do_code_maxsequence` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_code_maxsequence` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_log
#

DROP TABLE IF EXISTS `do_log`;
CREATE TABLE `do_log` (
  `objuid` varchar(32) NOT NULL default '',
  `userName` varchar(50) default NULL,
  `deptName` varchar(255) default NULL,
  `loginTime` datetime default NULL,
  `logoffTime` datetime default NULL,
  `ip` varchar(50) default NULL,
  `sessionid` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_log
#

LOCK TABLES `do_log` WRITE;
/*!40000 ALTER TABLE `do_log` DISABLE KEYS */;
INSERT INTO `do_log` VALUES ('2c90b186301c87f101301c87f1150000','a',NULL,'2011-05-23 19:05:29',NULL,'127.0.0.1','B3B48AB3C915A415872D889B766C136D');
INSERT INTO `do_log` VALUES ('2c90b186301c87f101301c87f24d0001','a',NULL,'2011-05-23 19:05:29',NULL,'127.0.0.1','B3B48AB3C915A415872D889B766C136D');
INSERT INTO `do_log` VALUES ('2c90b186301c895701301c89578b0000','a',NULL,'2011-05-23 19:07:00',NULL,'127.0.0.1','36AF9E3569FB4CE01537F438D2CD6B6C');
INSERT INTO `do_log` VALUES ('2c90b186301c8e8301301c8e83d20000','a',NULL,'2011-05-23 19:12:39',NULL,'127.0.0.1','8817B465FA1D94504F6A9A97483EBCF4');
INSERT INTO `do_log` VALUES ('2c90b186301c904201301c9042e70000','a',NULL,'2011-05-23 19:14:34',NULL,'127.0.0.1','7D507565BB8AE4EA7FA88AEFEE11252D');
INSERT INTO `do_log` VALUES ('40288036301d2a7501301d4be2080006','a',NULL,'2011-05-23 22:39:30',NULL,'127.0.0.1','7A3FD2428F714DA968062EC951B3507A');
INSERT INTO `do_log` VALUES ('40288036301d2a7501301d4bf3db0007','a',NULL,'2011-05-23 22:39:34',NULL,'127.0.0.1','7A3FD2428F714DA968062EC951B3507A');
INSERT INTO `do_log` VALUES ('ff808081301d670801301d6708c70000','a',NULL,'2011-05-23 23:09:09',NULL,'127.0.0.1','99BE949AE009DEDCCD635FDC1994598D');
INSERT INTO `do_log` VALUES ('ff808081301d670801301d7c285e000a','a',NULL,'2011-05-23 23:32:14',NULL,'127.0.0.1','99BE949AE009DEDCCD635FDC1994598D');
INSERT INTO `do_log` VALUES ('ff808081301d670801301d7ddeaa000b','jlf',NULL,'2011-05-23 23:34:06',NULL,'127.0.0.1','99BE949AE009DEDCCD635FDC1994598D');
INSERT INTO `do_log` VALUES ('ff808081301d670801301d7e6b59000c','jlf',NULL,'2011-05-23 23:34:42',NULL,'127.0.0.1','99BE949AE009DEDCCD635FDC1994598D');
INSERT INTO `do_log` VALUES ('40288036301f669b01301f669bda0000','a',NULL,'2011-05-24 08:27:56',NULL,'127.0.0.1','70A20A48FD7D35C91168AA60B33FD23C');
/*!40000 ALTER TABLE `do_log` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_log_data
#

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
  `NEW_VALUE` varchar(2000) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_log_data
#

LOCK TABLES `do_log_data` WRITE;
/*!40000 ALTER TABLE `do_log_data` DISABLE KEYS */;
INSERT INTO `do_log_data` VALUES ('2c90b186301b3bff01301b3c00560001','multi_tenancy',NULL,'aa',NULL,NULL,'增加租户','111',NULL,'2c90b186301b3bff01301b3bff7b0000',NULL,NULL,NULL);
INSERT INTO `do_log_data` VALUES ('40288036301d2a7501301d2a768d0001','multi_tenancy',NULL,'aa',NULL,NULL,'增加租户','家乐福',NULL,'40288036301d2a7501301d2a75060000',NULL,NULL,NULL);
INSERT INTO `do_log_data` VALUES ('40288036301d2a7501301d2ae3120003','multi_tenancy',NULL,'aa',NULL,NULL,'增加租户','wm',NULL,'40288036301d2a7501301d2ae2f30002',NULL,NULL,NULL);
INSERT INTO `do_log_data` VALUES ('40288036301d2a7501301d2c50ac0005','multi_tenancy',NULL,'aa',NULL,NULL,'增加租户','lotus',NULL,'40288036301d2a7501301d2c508d0004',NULL,NULL,NULL);
/*!40000 ALTER TABLE `do_log_data` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_org_dept
#

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
  `note` varchar(255) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_org_dept
#

LOCK TABLES `do_org_dept` WRITE;
/*!40000 ALTER TABLE `do_org_dept` DISABLE KEYS */;
INSERT INTO `do_org_dept` VALUES ('4028803127b6f15a0127b7294a7a0004','pingtaibumen','平台部门','40288031278ed91501278ed915b30000',2,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `do_org_dept` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_org_role
#

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
  `mVersion` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_org_role
#

LOCK TABLES `do_org_role` WRITE;
/*!40000 ALTER TABLE `do_org_role` DISABLE KEYS */;
INSERT INTO `do_org_role` VALUES ('4028803127b6f15a0127b725c6930003','普通员工','putongyuangong',NULL,1,'普通员工',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdb80af0001','部门经理','bumenjingli',NULL,NULL,'部门经理',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdc23670002','副总经理','fuzongjingli',NULL,NULL,'副总经理',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fdc70100003','财务主管','caiwuzhuguan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_org_role` VALUES ('40288031287fd27f01287fe80c5a0009','总经理','zongjingli',NULL,NULL,'总经理',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_org_role` VALUES ('40288031288a2b8501288a3d009d000d','系统管理员','system_manager',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `do_org_role` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_org_user
#

DROP TABLE IF EXISTS `do_org_user`;
CREATE TABLE `do_org_user` (
  `objuid` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `user_code` varchar(255) default NULL,
  `password` varchar(50) default NULL,
  `deptuid` varchar(255) default NULL,
  `tenancy_uid` varchar(32) default NULL,
  `gender` char(1) default NULL,
  `birthday` datetime default NULL,
  `email` varchar(50) default NULL,
  `telephone` varchar(50) default NULL,
  `mobile` varchar(50) default NULL,
  `address` varchar(50) default NULL,
  `creator` varchar(255) default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

#
# Dumping data for table do_org_user
#

LOCK TABLES `do_org_user` WRITE;
/*!40000 ALTER TABLE `do_org_user` DISABLE KEYS */;
INSERT INTO `do_org_user` VALUES ('40288031278ed91501278ed915b30000','jlf','0005','c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004','40288036301d2a7501301d2a75060000',NULL,NULL,'u@u.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f012880415d96006c','w2m',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004','40288036301d2a7501301d2ae2f30002',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `do_org_user` VALUES ('40288031287fd27f01288045ecd3006e','yclh',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004','40288036301d2a7501301d2c508d0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `do_org_user` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_org_user_delegate
#

DROP TABLE IF EXISTS `do_org_user_delegate`;
CREATE TABLE `do_org_user_delegate` (
  `objuid` varchar(32) NOT NULL,
  `user_uid` varchar(50) default NULL,
  `delegate_uid` varchar(50) default NULL,
  `isValid` char(1) default NULL,
  `startTime` datetime default NULL,
  `endTime` datetime default NULL,
  `note` text,
  `creator` varchar(255) default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_org_user_delegate
#

LOCK TABLES `do_org_user_delegate` WRITE;
/*!40000 ALTER TABLE `do_org_user_delegate` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_org_user_delegate` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_org_user_role
#

DROP TABLE IF EXISTS `do_org_user_role`;
CREATE TABLE `do_org_user_role` (
  `OBJUID` varchar(32) NOT NULL,
  `USER_UID` varchar(32) default NULL,
  `ROLE_UID` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_org_user_role
#

LOCK TABLES `do_org_user_role` WRITE;
/*!40000 ALTER TABLE `do_org_user_role` DISABLE KEYS */;
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047ad6e0071','40288031287fd27f01288045ecd3006e','4028803127b6f15a0127b725c6930003');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047d1cf0072','40288031287fd27f012880469c2d006f','40288031287fd27f01287fe80c5a0009');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f01288047f3de0073','40288031287fd27f012880472b4e0070','40288031287fd27f01287fdc23670002');
INSERT INTO `do_org_user_role` VALUES ('40288031287fd27f0128804811f50074','40288031287fd27f012880415d96006c','40288031287fd27f01287fdc70100003');
INSERT INTO `do_org_user_role` VALUES ('40288031288a2b8501288a3d2a1e000e','40288031278ed91501278ed915b30000','40288031288a2b8501288a3d009d000d');
/*!40000 ALTER TABLE `do_org_user_role` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_his_ni_dependency
#

DROP TABLE IF EXISTS `do_wfi_his_ni_dependency`;
CREATE TABLE `do_wfi_his_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_his_ni_dependency
#

LOCK TABLES `do_wfi_his_ni_dependency` WRITE;
/*!40000 ALTER TABLE `do_wfi_his_ni_dependency` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_his_ni_dependency` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_his_nodeinstance
#

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
  `retNodeUID` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_his_nodeinstance
#

LOCK TABLES `do_wfi_his_nodeinstance` WRITE;
/*!40000 ALTER TABLE `do_wfi_his_nodeinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_his_nodeinstance` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_his_processinstance
#

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
  `instance3_uid` varchar(32) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_his_processinstance
#

LOCK TABLES `do_wfi_his_processinstance` WRITE;
/*!40000 ALTER TABLE `do_wfi_his_processinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_his_processinstance` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_his_varinstance
#

DROP TABLE IF EXISTS `do_wfi_his_varinstance`;
CREATE TABLE `do_wfi_his_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) default NULL,
  `pt_var_uid` varchar(32) default NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) default NULL,
  `instance_uid` varchar(100) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_his_varinstance
#

LOCK TABLES `do_wfi_his_varinstance` WRITE;
/*!40000 ALTER TABLE `do_wfi_his_varinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_his_varinstance` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_ni_dependency
#

DROP TABLE IF EXISTS `do_wfi_ni_dependency`;
CREATE TABLE `do_wfi_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_ni_dependency
#

LOCK TABLES `do_wfi_ni_dependency` WRITE;
/*!40000 ALTER TABLE `do_wfi_ni_dependency` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_ni_dependency` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_nodeinstance
#

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
  `retNodeUID` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_nodeinstance
#

LOCK TABLES `do_wfi_nodeinstance` WRITE;
/*!40000 ALTER TABLE `do_wfi_nodeinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_nodeinstance` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_processinstance
#

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
  `instance3_uid` varchar(32) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_processinstance
#

LOCK TABLES `do_wfi_processinstance` WRITE;
/*!40000 ALTER TABLE `do_wfi_processinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_processinstance` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table do_wfi_varinstance
#

DROP TABLE IF EXISTS `do_wfi_varinstance`;
CREATE TABLE `do_wfi_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) default NULL,
  `pt_var_uid` varchar(32) default NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) default NULL,
  `instance_uid` varchar(100) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table do_wfi_varinstance
#

LOCK TABLES `do_wfi_varinstance` WRITE;
/*!40000 ALTER TABLE `do_wfi_varinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_wfi_varinstance` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table multi_tenancy
#

DROP TABLE IF EXISTS `multi_tenancy`;
CREATE TABLE `multi_tenancy` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(255) default NULL,
  `l10n` varchar(255) default NULL,
  `startDate` date default NULL,
  `note` varchar(10000) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table multi_tenancy
#

LOCK TABLES `multi_tenancy` WRITE;
/*!40000 ALTER TABLE `multi_tenancy` DISABLE KEYS */;
INSERT INTO `multi_tenancy` VALUES ('40288036301d2a7501301d2a75060000','carrefour','家乐福','2011-05-23','家乐福');
INSERT INTO `multi_tenancy` VALUES ('40288036301d2a7501301d2ae2f30002','wmt','沃尔玛','2011-05-03','沃尔玛');
INSERT INTO `multi_tenancy` VALUES ('40288036301d2a7501301d2c508d0004','lotus','易初莲花','2011-05-25',NULL);
/*!40000 ALTER TABLE `multi_tenancy` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table multi_tenancy_column
#

DROP TABLE IF EXISTS `multi_tenancy_column`;
CREATE TABLE `multi_tenancy_column` (
  `id` varchar(32) NOT NULL default '',
  `col_name` varchar(255) default NULL,
  `tenancy_table_uid` varchar(32) default NULL,
  `type` varchar(32) default NULL,
  `real_col` varchar(32) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table multi_tenancy_column
#

LOCK TABLES `multi_tenancy_column` WRITE;
/*!40000 ALTER TABLE `multi_tenancy_column` DISABLE KEYS */;
INSERT INTO `multi_tenancy_column` VALUES ('2c90b186301c904201301c9142370002','id','2c90b186301c904201301c9141aa0001','VARCHAR32','c011');
INSERT INTO `multi_tenancy_column` VALUES ('2c90b186301c904201301c9142940003','location','2c90b186301c904201301c9141aa0001','VARCHAR255','c061');
INSERT INTO `multi_tenancy_column` VALUES ('2c90b186301c904201301c9142a40004','name','2c90b186301c904201301c9141aa0001','VARCHAR32','c012');
/*!40000 ALTER TABLE `multi_tenancy_column` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table multi_tenancy_table
#

DROP TABLE IF EXISTS `multi_tenancy_table`;
CREATE TABLE `multi_tenancy_table` (
  `id` varchar(255) NOT NULL default '',
  `table_name` varchar(255) default NULL,
  `table_l10n` varchar(255) default NULL,
  `tenancy_uid` varchar(32) default NULL,
  `real_table` varchar(32) default NULL,
  `corr_view` varchar(255) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table multi_tenancy_table
#

LOCK TABLES `multi_tenancy_table` WRITE;
/*!40000 ALTER TABLE `multi_tenancy_table` DISABLE KEYS */;
INSERT INTO `multi_tenancy_table` VALUES ('2c90b186301c904201301c9141aa0001','ttaat',NULL,NULL,NULL,'ttaat');
/*!40000 ALTER TABLE `multi_tenancy_table` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table t_expense_a
#

DROP TABLE IF EXISTS `t_expense_a`;
CREATE TABLE `t_expense_a` (
  `id` varchar(32) NOT NULL default '',
  `title` varchar(255) default NULL,
  `expense_man` varchar(32) NOT NULL default '',
  `expense_date` date default NULL,
  `expense_money` float NOT NULL default '0',
  `note` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Dumping data for table t_expense_a
#

LOCK TABLES `t_expense_a` WRITE;
/*!40000 ALTER TABLE `t_expense_a` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_expense_a` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
