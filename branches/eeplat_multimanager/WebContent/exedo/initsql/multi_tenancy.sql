/*
SQLyog Ultimate v9.20 
MySQL - 5.0.24a-community-nt-log : Database - multi_tenancy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `account` */

CREATE TABLE `account` (
  `objUid` varchar(32) NOT NULL default '',
  `user_id` varchar(255) default NULL,
  `nickName` varchar(255) default NULL,
  `user_name` varchar(255) default NULL,
  `user_email` varchar(255) default NULL,
  `gender` char(1) default NULL,
  `province` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `figureurl` varchar(255) default NULL,
  `figureurl_1` varchar(255) default NULL,
  `figureurl_2` varchar(255) default NULL,
  `user_description` varchar(2000) default NULL,
  `open_site` varchar(255) default NULL,
  PRIMARY KEY  (`objUid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`objUid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402881e734c765d20134c765d2ba0000','436653530','魏可鑫','魏可鑫',NULL,'M',NULL,NULL,NULL,'http://head.xiaonei.com/photos/0/0/men_tiny.gif','http://head.xiaonei.com/photos/0/0/men_head.gif',NULL,NULL,'renren');
insert  into `account`(`objUid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402881e734c765d20134c765fd250001','61DA4B35C01B58F43B37D2F7501BDCB0','鑫鑫相容',NULL,NULL,'M',NULL,NULL,NULL,'http://qzapp.qlogo.cn/qzapp/100238553/61DA4B35C01B58F43B37D2F7501BDCB0/30',NULL,NULL,NULL,'qq');
insert  into `account`(`objUid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402881e734cad2880134cad288d60000','1752142767','魏可鑫','魏可鑫',NULL,'M','11','1000','北京','http://tp4.sinaimg.cn/1752142767/50/5596873413/1','/',NULL,'EEPlat(云鹤) PaaS创始人，http://code.google.com/p/eeplat/','weibo');
insert  into `account`(`objUid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402881e734cad2880134cad2cb0f0001','-7748407680145098641','toweikexin','toweikexin',NULL,'M',NULL,NULL,'','http://oimageb5.ydstatic.com/image?w=48&h=48&url=http%3A%2F%2Fmimg.126.net%2Fp%2Fbutter%2F1008031648%2Fimg%2Fface_big.gif','',NULL,'','163');

/*Table structure for table `do_auth_owner` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_auth_owner` */

/*Table structure for table `do_auth_suite` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_auth_suite` */

/*Table structure for table `do_authorization` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_authorization` */

insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402880312870acd1012870f1133a0001','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'test_n12','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f0128801f4bb9000a','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,110,'tt2_n11','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f0128801f849c000b','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,110,'tt2_n12','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f01287fdfd2a40005','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'tt2_n6','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402880312870acd10128712bf8ae000e','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'test_n7','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f0128801fb3f9000c','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,110,'tt2_n13','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d70128852439e90004','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d7012885249bc00006','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d701288524b1d80007','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d70128852572b70008','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d70128852588620009','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d701288525a57f000a','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d701288525bbe6000b','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d701288525d4ec000c','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d70128852615cd000e','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288512d701288529a9c2000f','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a3652c60001','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a3660920002','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a367a820003','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a3685cd0004','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a383e1c0007','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a384a220008','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a3858890009','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a386cbd000a','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a387960000b','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a3d6812000f','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788b844d10009','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a4cb701288a4ea76a0002','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788b9b77c012c','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a4cb701288a4eb8fe0003','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788ba043401ed','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a4cb701288a4ed4e20004','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278f16d001278f43e525003c','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a4cb701288a4ee4c10005','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278904920127890492720000','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a4cb701288a4ef4af0006','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278a406e01278a6bb9ce005d','1','1',NULL,NULL,NULL,NULL,NULL);
insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a4cb701288a4f02d80007','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312793120d01279318032e0260','1','1',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_code_maxsequence` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_code_maxsequence` */

/*Table structure for table `do_log` */

CREATE TABLE `do_log` (
  `objuid` varchar(32) NOT NULL default '',
  `userName` varchar(50) default NULL,
  `deptName` varchar(255) default NULL,
  `loginTime` datetime default NULL,
  `logoffTime` datetime default NULL,
  `ip` varchar(50) default NULL,
  `sessionid` varchar(50) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_log` */

insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368c562c01368c562c0f0000','u',NULL,'2012-04-07 18:25:19',NULL,'127.0.0.1','E59084CAC093373321F326D8B38BEA04');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368c562c01368c5840c60001','a',NULL,'2012-04-07 18:27:35',NULL,'127.0.0.1','E59084CAC093373321F326D8B38BEA04');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368c904d01368c904d480000','a',NULL,'2012-04-07 19:28:48','2012-04-07 20:02:35','127.0.0.1','FF573A334873AE7BE7D92ACBEA8AC532');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368c904d01368cd935870001','a',NULL,'2012-04-07 20:48:27',NULL,'127.0.0.1','060EE73B24E1470C381BF873D1AA84C5');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368ce1c401368ce1c4140000','u',NULL,'2012-04-07 20:57:47',NULL,'127.0.0.1','3933CFC80093B78F2147C716BB792380');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368d336601368d3366c30000','a',NULL,'2012-04-07 22:26:57',NULL,'127.0.0.1','19A443F3FE7EFA898F7B9BFBFB096806');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368d336601368d3494270001','a',NULL,'2012-04-07 22:28:15',NULL,'127.0.0.1','19A443F3FE7EFA898F7B9BFBFB096806');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368d535301368d5353db0000','a',NULL,'2012-04-07 23:01:50',NULL,'127.0.0.1','DA650BAA77A9138C09F8968EE097E317');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368d535301368d537c140001','a',NULL,'2012-04-07 23:02:00',NULL,'127.0.0.1','DA650BAA77A9138C09F8968EE097E317');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368d8c8601368d8c869e0000','u',NULL,'2012-04-08 00:04:18',NULL,'127.0.0.1','83B6622D78A5704F0187D0B15F910B51');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368d9e9c01368d9e9c320000','u',NULL,'2012-04-08 00:24:03',NULL,'127.0.0.1','8A8D961CF6EA0AA043F3F387B5F1A86F');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368dc03e01368dc03e2e0000','u',NULL,'2012-04-08 01:00:48',NULL,'127.0.0.1','7F46CD02B03EEAE033FD93C2AAD0079C');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368dc63001368dc630b40000','u',NULL,'2012-04-08 01:07:17',NULL,'127.0.0.1','2CFED445627D254494E334E453C81AFB');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368dc63001368dca7dc70004','u',NULL,'2012-04-08 01:11:59',NULL,'127.0.0.1','2CFED445627D254494E334E453C81AFB');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368dcfad01368dcfad1c0000','u',NULL,'2012-04-08 01:17:39',NULL,'127.0.0.1','B85F3C5452C228BD550A05774C482A5A');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368dd41801368dd418940000','u',NULL,'2012-04-08 01:22:29',NULL,'127.0.0.1','F130DA15D4C18A94CEF9011867820221');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368ddc0701368ddc07860000','u',NULL,'2012-04-08 01:31:09',NULL,'127.0.0.1','AF46DB266C0469F74D6B68F067215C85');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368de27801368de278e00000','u',NULL,'2012-04-08 01:38:11',NULL,'127.0.0.1','0EDD423489FC16DF653945292E5E4F2F');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368de79901368de799ce0000','u',NULL,'2012-04-08 01:43:47',NULL,'127.0.0.1','0CA31ABF39F13379315BF3CBEF89E068');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368ded4301368ded43c10000','u',NULL,'2012-04-08 01:49:58',NULL,'127.0.0.1','9E78B4A43C58C1F091B078BEB6BB2B92');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368df0ca01368df0cac50000','u',NULL,'2012-04-08 01:53:49','2012-04-08 02:24:11','127.0.0.1','755A55E705ABF08FDAFFE03242560ED7');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368faebc01368faebc830000','u',NULL,'2012-04-08 10:00:55',NULL,'127.0.0.1','A649EACDE6C06AC30D8F6A17295E3DF2');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368fb2fd01368fb2fd420000','u',NULL,'2012-04-08 10:05:33',NULL,'127.0.0.1','DFD6A800432E3D69E650BC5939D77085');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368fcaad01368fcaad780000','u',NULL,'2012-04-08 10:31:26',NULL,'127.0.0.1','48951DD37D3FC709B20F012404C50949');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b368fcf5301368fcf53690000','u',NULL,'2012-04-08 10:36:30',NULL,'127.0.0.1','D62ED2029925726D913BABAB34979E6C');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369045ed01369045edb70000','u',NULL,'2012-04-08 12:46:03',NULL,'127.0.0.1','3DC41F3D7FF446A36E86FC0CF5B86261');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369050420136905042eb0000','u',NULL,'2012-04-08 12:57:21','2012-04-08 13:28:13','127.0.0.1','37598ADE76D0661A9EB8B44F280D739E');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369076b601369076b6e90000','u',NULL,'2012-04-08 13:39:20',NULL,'127.0.0.1','783F078785CF94026A886D5271E3F9B8');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b36907d8e0136907d8ebe0000','u',NULL,'2012-04-08 13:46:49',NULL,'127.0.0.1','0BD206C6E5C7305ECD39C49666D10A92');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b36908e190136908e195c0000','u',NULL,'2012-04-08 14:04:53','2012-04-08 14:10:18','127.0.0.1','0CE55D80210A2AF130538BAE1C49ECF3');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369098a901369098a9090000','u',NULL,'2012-04-08 14:16:25',NULL,'127.0.0.1','C59F3C67EECDF2B53D1DA516B1C452CA');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b36909fb00136909fb0890000','u',NULL,'2012-04-08 14:24:06','2012-04-08 14:54:53','127.0.0.1','B4DBEB169B2329A0FC983414B1DEB62A');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3690fea5013690fea5400000','u',NULL,'2012-04-08 16:07:49',NULL,'127.0.0.1','318E03381E75AD75D72C73E23B1EB08F');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369118a101369118a1d70000','a',NULL,'2012-04-08 16:36:12','2012-04-08 17:12:41','127.0.0.1','9E44C585A708C1E1FE342EF517037523');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369296600136929660280000','u',NULL,'2012-04-08 23:33:10',NULL,'127.0.0.1','66E0E6EC2404DC7FF93EFF5590558916');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692a1c1013692a1c11f0000','u',NULL,'2012-04-08 23:45:36',NULL,'127.0.0.1','756748ABECF4ED2B86762BD3E4ED9293');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692a6eb013692a6eb150000','u',NULL,'2012-04-08 23:51:14',NULL,'127.0.0.1','79A2D387178D83FF0B723708D7C8F727');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692c51b013692c51b0c0000','u',NULL,'2012-04-09 00:24:12',NULL,'127.0.0.1','A99927E1B79726E7275435CAF51B6A3C');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692c951013692c9510d0000','u',NULL,'2012-04-09 00:28:48',NULL,'127.0.0.1','3442276162E286A3E010AA378A16CD98');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692c951013692c993c30001','u',NULL,'2012-04-09 00:29:05',NULL,'127.0.0.1','3442276162E286A3E010AA378A16CD98');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692cf14013692cf14c20000','u',NULL,'2012-04-09 00:35:06',NULL,'127.0.0.1','03ABFC2D96A8A527BE48E9798F8DD4ED');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692cf14013692cf38b50001','u',NULL,'2012-04-09 00:35:15',NULL,'127.0.0.1','03ABFC2D96A8A527BE48E9798F8DD4ED');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692da80013692da80190000','u',NULL,'2012-04-09 00:47:34',NULL,'127.0.0.1','82F97FCCBBAEFA31BC12DC10818D566D');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692e3f0013692e3f02d0000','u',NULL,'2012-04-09 00:57:53',NULL,'127.0.0.1','02FD45D8C1EBF191E887DB1B9B7D375E');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692edb4013692edb45c0000','u',NULL,'2012-04-09 01:08:33',NULL,'127.0.0.1','13942DE2EDC30CCF9AAE4A0226D0C7F0');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692f285013692f285b20000','u',NULL,'2012-04-09 01:13:49',NULL,'127.0.0.1','CA4E90274005D2FB0B343213FACDEB3F');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692f285013692f29e3b0001','u',NULL,'2012-04-09 01:13:55',NULL,'127.0.0.1','CA4E90274005D2FB0B343213FACDEB3F');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3692fa9a013692fa9a5c0000','u',NULL,'2012-04-09 01:22:38',NULL,'127.0.0.1','687577DC7439668FD675B075A3268F3D');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369307550136930755260000','u',NULL,'2012-04-09 01:36:33',NULL,'127.0.0.1','D496D3E678E564B6BA8359FC658023F7');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b36930a510136930a518e0000','u',NULL,'2012-04-09 01:39:48',NULL,'127.0.0.1','D1D3F85A6D3F3C32C3847F851183DCA0');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b369316b501369316b5100000','u',NULL,'2012-04-09 01:53:20',NULL,'127.0.0.1','D6F1840888673ECE8F8043ECD5409967');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b36931b250136931b256a0000','u',NULL,'2012-04-09 01:58:11',NULL,'127.0.0.1','0D513A8D9A7C24FAE5DD80380F5EB8F6');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028803b3694e791013694e791170000','a',NULL,'2012-04-09 10:21:05',NULL,'127.0.0.1','4F3A044445F5ADCF37467CEE875FC676');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('402881ec36a9882b0136a9882bbf0000','a',NULL,'2012-04-13 10:28:55',NULL,'127.0.0.1','5C3E7C2D670297D137D2A85E36103FF2');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('402881ec36a9882b0136a98ec3db0001','u',NULL,'2012-04-13 10:36:07',NULL,'127.0.0.1','5C3E7C2D670297D137D2A85E36103FF2');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('402881ec36a99e3e0136a99e3e900000','u',NULL,'2012-04-13 10:53:01','2012-04-13 11:26:30','127.0.0.1','588AA2EF1D18C44C77073DA25E9820DA');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('402881ec36aa8ada0136aa8ada190000','u',NULL,'2012-04-13 15:11:28',NULL,'127.0.0.1','217243B9B7C0D857D40A08DF134410EB');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('402881ec36aaa4550136aaa4550c0000','u',NULL,'2012-04-13 15:39:18','2012-04-13 16:35:56','127.0.0.1','4E96B1D6FA38DFDDEE4C2885C9E6C213');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('402881ec36aaa4550136aaf23ac20007','u',NULL,'2012-04-13 17:04:23','2012-04-13 17:35:25','127.0.0.1','EBAEC785CA79A8A47421FC2A43E33FA1');
insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('402880ed37d6da9f0137d6da9f570000','u',NULL,'2012-06-10 22:44:40',NULL,'127.0.0.1','3AF3758541CE51CF37EE64737F005077');

/*Table structure for table `do_log_data` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_log_data` */

insert  into `do_log_data`(`OBJUID`,`TABLE_NAME`,`COL_NAME`,`WHO_UID`,`BO_UID`,`COL_UID`,`OPER_TYPE`,`OPER_DATA`,`OPER_TIME`,`OPER_DATA_UID`,`OPER_PANE_UID`,`OLD_VALUE`,`NEW_VALUE`) values ('4028803b368c289f01368c289fa80001','multi_tenancy',NULL,NULL,NULL,NULL,'增加租户','bbb',NULL,'4028803b368c289f01368c289f1b0000',NULL,NULL,NULL);
insert  into `do_log_data`(`OBJUID`,`TABLE_NAME`,`COL_NAME`,`WHO_UID`,`BO_UID`,`COL_UID`,`OPER_TYPE`,`OPER_DATA`,`OPER_TIME`,`OPER_DATA_UID`,`OPER_PANE_UID`,`OLD_VALUE`,`NEW_VALUE`) values ('402880ed37d6da9f0137d6db26d70002','multi_tenancy',NULL,NULL,NULL,NULL,'增加租户','beijing',NULL,'402880ed37d6da9f0137d6db259e0001',NULL,NULL,NULL);

/*Table structure for table `do_org_dept` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_dept` */

insert  into `do_org_dept`(`objUid`,`dept_code`,`name`,`leader`,`type`,`order_num`,`location`,`tel`,`parentUid`,`note`) values ('4028803127b6f15a0127b7294a7a0004','pingtaibumen','平台部门','40288031278ed91501278ed915b30000',2,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_org_role` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_role` */

insert  into `do_org_role`(`objUid`,`name`,`roleId`,`parentUid`,`degree`,`description`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('4028803127b6f15a0127b725c6930003','普通员工','putongyuangong',NULL,1,'普通员工',NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_role`(`objUid`,`name`,`roleId`,`parentUid`,`degree`,`description`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f01287fdb80af0001','部门经理','bumenjingli',NULL,NULL,'部门经理',NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_role`(`objUid`,`name`,`roleId`,`parentUid`,`degree`,`description`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f01287fdc23670002','副总经理','fuzongjingli',NULL,NULL,'副总经理',NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_role`(`objUid`,`name`,`roleId`,`parentUid`,`degree`,`description`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f01287fdc70100003','财务主管','caiwuzhuguan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_role`(`objUid`,`name`,`roleId`,`parentUid`,`degree`,`description`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f01287fe80c5a0009','总经理','zongjingli',NULL,NULL,'总经理',NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_role`(`objUid`,`name`,`roleId`,`parentUid`,`degree`,`description`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a3d009d000d','系统管理员','system_manager',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_org_role_conflict` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

/*Data for the table `do_org_role_conflict` */

/*Table structure for table `do_org_timespan` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_timespan` */

/*Table structure for table `do_org_user` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

/*Data for the table `do_org_user` */

insert  into `do_org_user`(`objuid`,`name`,`user_code`,`password`,`deptuid`,`gender`,`birthday`,`email`,`telephone`,`mobile`,`address`,`creator`,`modifier`,`modifyDate`,`mVersion`) values ('40288031278ed91501278ed915b30000','u','0005','b59c67bf196a4758191e42f76670ceba','4028803127b6f15a0127b7294a7a0004',NULL,NULL,'u@u.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_user`(`objuid`,`name`,`user_code`,`password`,`deptuid`,`gender`,`birthday`,`email`,`telephone`,`mobile`,`address`,`creator`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f012880415d96006c','xz',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_user`(`objuid`,`name`,`user_code`,`password`,`deptuid`,`gender`,`birthday`,`email`,`telephone`,`mobile`,`address`,`creator`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f01288045ecd3006e','xw',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_user`(`objuid`,`name`,`user_code`,`password`,`deptuid`,`gender`,`birthday`,`email`,`telephone`,`mobile`,`address`,`creator`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f012880469c2d006f','hz',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_user`(`objuid`,`name`,`user_code`,`password`,`deptuid`,`gender`,`birthday`,`email`,`telephone`,`mobile`,`address`,`creator`,`modifier`,`modifyDate`,`mVersion`) values ('40288031287fd27f012880472b4e0070','wz',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `do_org_user`(`objuid`,`name`,`user_code`,`password`,`deptuid`,`gender`,`birthday`,`email`,`telephone`,`mobile`,`address`,`creator`,`modifier`,`modifyDate`,`mVersion`) values ('40288031288a2b8501288a3704e00005','lz','lz','d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_org_user_delegate` */

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
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_user_delegate` */

/*Table structure for table `do_org_user_role` */

CREATE TABLE `do_org_user_role` (
  `OBJUID` varchar(32) NOT NULL,
  `USER_UID` varchar(32) default NULL,
  `ROLE_UID` varchar(50) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_user_role` */

insert  into `do_org_user_role`(`OBJUID`,`USER_UID`,`ROLE_UID`) values ('40288031287fd27f01288047ad6e0071','40288031287fd27f01288045ecd3006e','4028803127b6f15a0127b725c6930003');
insert  into `do_org_user_role`(`OBJUID`,`USER_UID`,`ROLE_UID`) values ('40288031287fd27f01288047d1cf0072','40288031287fd27f012880469c2d006f','40288031287fd27f01287fe80c5a0009');
insert  into `do_org_user_role`(`OBJUID`,`USER_UID`,`ROLE_UID`) values ('40288031287fd27f01288047f3de0073','40288031287fd27f012880472b4e0070','40288031287fd27f01287fdc23670002');
insert  into `do_org_user_role`(`OBJUID`,`USER_UID`,`ROLE_UID`) values ('40288031287fd27f0128804811f50074','40288031287fd27f012880415d96006c','40288031287fd27f01287fdc70100003');
insert  into `do_org_user_role`(`OBJUID`,`USER_UID`,`ROLE_UID`) values ('40288031288a2b8501288a3d2a1e000e','40288031278ed91501278ed915b30000','40288031288a2b8501288a3d009d000d');

/*Table structure for table `do_wfi_his_ni_dependency` */

CREATE TABLE `do_wfi_his_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_ni_dependency` */

/*Table structure for table `do_wfi_his_nodeinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_nodeinstance` */

/*Table structure for table `do_wfi_his_processinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_processinstance` */

/*Table structure for table `do_wfi_his_varinstance` */

CREATE TABLE `do_wfi_his_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) default NULL,
  `pt_var_uid` varchar(32) default NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) default NULL,
  `instance_uid` varchar(100) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_varinstance` */

/*Table structure for table `do_wfi_ni_dependency` */

CREATE TABLE `do_wfi_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_ni_dependency` */

/*Table structure for table `do_wfi_nodeinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_nodeinstance` */

/*Table structure for table `do_wfi_processinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_processinstance` */

/*Table structure for table `do_wfi_varinstance` */

CREATE TABLE `do_wfi_varinstance` (
  `objuid` varchar(32) NOT NULL,
  `varName` varchar(255) NOT NULL,
  `varValue` varchar(255) default NULL,
  `pt_var_uid` varchar(32) default NULL,
  `PI_UID` varchar(32) NOT NULL,
  `bo_property_uid` varchar(32) default NULL,
  `instance_uid` varchar(100) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_varinstance` */

/*Table structure for table `multi_account` */

CREATE TABLE `multi_account` (
  `objuid` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `tenancyId` varchar(127) default NULL,
  `password` varchar(50) default NULL,
  `asrole` varchar(255) default NULL,
  `creator` varchar(255) default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  `default_app_uid` varchar(32) default NULL,
  `register_date` date default NULL,
  `end_date` date default NULL,
  `isValid` char(1) default NULL,
  `user_id` varchar(255) default NULL,
  `nickName` varchar(255) default NULL,
  `user_name` varchar(255) default NULL,
  `user_email` varchar(255) default NULL,
  `gender` char(1) default NULL,
  `province` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `location` varchar(255) default NULL,
  `figureurl` varchar(255) default NULL,
  `figureurl_1` varchar(255) default NULL,
  `figureurl_2` varchar(255) default NULL,
  `user_description` varchar(2000) default NULL,
  `open_site` varchar(255) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

/*Data for the table `multi_account` */

insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('40288012345ac6a301345ac870810001','toweikexin@126.com','eeplat','c4ca4238a0b923820dcc509a6f75849b','2',NULL,NULL,NULL,NULL,'ff80808131275dcc0131275e2fdf0001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402880363025803e0130259dcdcc0006','admin@huawei.com','carrefour','c4ca4238a0b923820dcc509a6f75849b','2',NULL,NULL,NULL,NULL,'ff80808131275dcc0131275e2fdf0001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402880e5340ee9da01340ef2326c0005','toweikexin@gmail.com','xfyl','c4ca4238a0b923820dcc509a6f75849b','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402880ed37d6da9f0137d6dbefd40006','beijing@beijing.com','beijing','c4ca4238a0b923820dcc509a6f75849b','2',NULL,NULL,NULL,NULL,'402881e631695cc701316960670c0001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402881e63436c197013436d45a980002','test@huawei.com','carrefour','c4ca4238a0b923820dcc509a6f75849b','0',NULL,NULL,NULL,NULL,'ff80808131275dcc0131275e2fdf0001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402881e7336c754d01336c78fb530004','yhs@yhs.com','yunhesu','c4ca4238a0b923820dcc509a6f75849b','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('402881ec36a99e3e0136a9a1209a0004','mypass','myPaaS','c4ca4238a0b923820dcc509a6f75849b','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_account`(`objuid`,`name`,`tenancyId`,`password`,`asrole`,`creator`,`modifier`,`modifyDate`,`mVersion`,`default_app_uid`,`register_date`,`end_date`,`isValid`,`user_id`,`nickName`,`user_name`,`user_email`,`gender`,`province`,`city`,`location`,`figureurl`,`figureurl_1`,`figureurl_2`,`user_description`,`open_site`) values ('ff8080813214c507013214c587830003','toweikexin@hotmail.com','eeplat','c4ca4238a0b923820dcc509a6f75849b','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `multi_account_fee_log` */

CREATE TABLE `multi_account_fee_log` (
  `id` varchar(32) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `multi_account_fee_log` */

/*Table structure for table `multi_account_login_log` */

CREATE TABLE `multi_account_login_log` (
  `id` varchar(32) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `multi_account_login_log` */

/*Table structure for table `multi_application` */

CREATE TABLE `multi_application` (
  `objUID` varchar(50) NOT NULL,
  `name` varchar(50) default NULL,
  `l10n` varchar(50) default NULL,
  `webAppName` varchar(50) default NULL,
  `main_title` varchar(100) default NULL,
  `main_css` varchar(100) default NULL,
  `session_overDue` int(11) default NULL,
  `upload_path` varchar(100) default NULL,
  `mode` int(11) default NULL,
  `description` varchar(50) default NULL,
  `doboUid` varchar(32) default NULL,
  `lookFeelUid` varchar(32) default NULL,
  `paneModelUid` varchar(32) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `multi_application` */

insert  into `multi_application`(`objUID`,`name`,`l10n`,`webAppName`,`main_title`,`main_css`,`session_overDue`,`upload_path`,`mode`,`description`,`doboUid`,`lookFeelUid`,`paneModelUid`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402881e631695cc701316960670c0001','OA','办公自动化','OA',NULL,NULL,NULL,NULL,NULL,NULL,'402881e631695cc7013169607b6f0003',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_application`(`objUID`,`name`,`l10n`,`webAppName`,`main_title`,`main_css`,`session_overDue`,`upload_path`,`mode`,`description`,`doboUid`,`lookFeelUid`,`paneModelUid`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('ff80808131275dcc0131275e2fdf0001','CRM','客户关系管理','CRM',NULL,NULL,NULL,NULL,NULL,NULL,'ff80808131275dcc0131275e37710003',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `multi_appshare` */

CREATE TABLE `multi_appshare` (
  `id` varchar(32) NOT NULL default '',
  `app_name` varchar(255) default NULL,
  `app_desc` varchar(1000) default NULL,
  `share_date` datetime default NULL,
  `share_app_id` varchar(32) default NULL,
  `auth_tenant_name` varchar(255) default NULL,
  `auth_tenant_id` varchar(32) default NULL,
  `xml_path` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `multi_appshare` */

insert  into `multi_appshare`(`id`,`app_name`,`app_desc`,`share_date`,`share_app_id`,`auth_tenant_name`,`auth_tenant_id`,`xml_path`) values ('40288036337934a50133793b9bbc0066','进销存',NULL,'2011-11-06 00:00:00','402880d831b4317e0131b433f8cb0001','华为技术有限公司','40288036301d2a7501301d2a75060000','carrefour/jxc.xml');
insert  into `multi_appshare`(`id`,`app_name`,`app_desc`,`share_date`,`share_app_id`,`auth_tenant_name`,`auth_tenant_id`,`xml_path`) values ('40288036337934a50133793bbf120067','财务',NULL,'2011-11-06 00:00:00','40288036337934a501337935f7370001','华为技术有限公司','40288036301d2a7501301d2a75060000','carrefour/caiwu.xml');
insert  into `multi_appshare`(`id`,`app_name`,`app_desc`,`share_date`,`share_app_id`,`auth_tenant_name`,`auth_tenant_id`,`xml_path`) values ('402880ed37d6d6510137d6dfa3180055','北京欢迎你','北京欢迎你','2012-06-10 00:00:00','402880ed37d6d6510137d6ddc08c0002','beijing','402880ed37d6da9f0137d6db259e0001','beijing/beijing.xml');

/*Table structure for table `multi_appshareinstall` */

CREATE TABLE `multi_appshareinstall` (
  `id` varchar(32) NOT NULL default '',
  `appshare_id` varchar(32) default NULL,
  `use_start_date` datetime default NULL,
  `use_tenant_id` varchar(32) default NULL,
  `use_tenant_name` varchar(255) default NULL,
  `note` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `multi_appshareinstall` */

insert  into `multi_appshareinstall`(`id`,`appshare_id`,`use_start_date`,`use_tenant_id`,`use_tenant_name`,`note`) values ('402880ed37d6d6510137d6e03ab40057','402880ed37d6d6510137d6dfa3180055','2012-06-10 00:00:00','402880e5340ee9da01340ef230970003','EEPlat PaaS',NULL);
insert  into `multi_appshareinstall`(`id`,`appshare_id`,`use_start_date`,`use_tenant_id`,`use_tenant_name`,`note`) values ('402881ea37735d31013773f3fe800017','40288036337934a50133793b9bbc0066','2012-05-22 00:00:00','402880e5340ee9da01340ef230970003','EEPlat PaaS',NULL);

/*Table structure for table `multi_datasource` */

CREATE TABLE `multi_datasource` (
  `objuid` varchar(32) NOT NULL,
  `jndiName` varchar(255) default NULL,
  `dialect` varchar(255) default NULL,
  `l10n` varchar(255) default NULL,
  `driver_class` varchar(255) default NULL,
  `driver_url` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `poolsize` int(11) default NULL,
  `poolType` int(11) default NULL,
  `otherparas` varchar(255) default NULL,
  `isInit` int(11) default NULL,
  `applicationUID` varchar(32) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `multi_datasource` */

insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('297e276a0d1f7763010d1f776e9d0003',NULL,'mysql','MYSQL_DATABASE','com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/tenancy_data?useUnicode=true&characterEncoding=utf-8','root','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402880ed37d6da9f0137d6db4aca0004',NULL,'mysql','beijing_modeldata','com.mysql.jdbc.Driver','jdbc:mysql://localhost:3306/beijing_model?useUnicode=true&characterEncoding=utf-8','wkx','123asd',NULL,NULL,'127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402880ed37d6da9f0137d6db4b760005',NULL,'mysql','beijing_busidata','com.mysql.jdbc.Driver','jdbc:mysql://localhost:3306/beijing_data?useUnicode=true&characterEncoding=utf-8','beijing','402880ed37d6da9f0137d6db4aaa0003',NULL,NULL,'127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402881ec36aaa4550136aaa47c0c0002',NULL,'mysql','mypaas_modeldata','com.mysql.jdbc.Driver','jdbc:mysql://localhost:3306/mypaas_model?useUnicode=true&characterEncoding=utf-8','wkx','123asd',NULL,NULL,'127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402881ec36aaa4550136aaa47ce70003',NULL,'mysql','mypaas_busidata','com.mysql.jdbc.Driver','jdbc:mysql://localhost:3306/mypaas_data?useUnicode=true&characterEncoding=utf-8','mypaas','402881ec36aaa4550136aaa47bdd0001',NULL,NULL,'127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402881ec36aaa4550136aaa6c65a0005',NULL,'mysql','yunhesu_modeldata','com.mysql.jdbc.Driver','jdbc:mysql://localhost:3306/yunhesu_model?useUnicode=true&characterEncoding=utf-8','wkx','123asd',NULL,NULL,'127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402881ec36aaa4550136aaa6c6a80006',NULL,'mysql','yunhesu_busidata','com.mysql.jdbc.Driver','jdbc:mysql://localhost:3306/yunhesu_data?useUnicode=true&characterEncoding=utf-8','yunhesu','402881ec36aaa4550136aaa6c64a0004',NULL,NULL,'127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('default_data',NULL,NULL,'默认应用数据','com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/default_data?useUnicode=true&characterEncoding=utf-8','kids','kids',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `multi_datasource`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('default_model',NULL,NULL,'默认应用模型','com.mysql.jdbc.Driver','jdbc:mysql://127.0.0.1:3306/default_model?useUnicode=true&characterEncoding=utf-8','root',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `multi_datasource_host` */

CREATE TABLE `multi_datasource_host` (
  `objuid` varchar(32) NOT NULL,
  `jndiName` varchar(255) default NULL,
  `dialect` varchar(255) default NULL,
  `l10n` varchar(255) default NULL,
  `driver_class` varchar(255) default NULL,
  `driver_url` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `poolsize` int(11) default NULL,
  `poolType` int(11) default NULL,
  `otherparas` varchar(255) default NULL,
  `isInit` int(11) default NULL,
  `applicationUID` varchar(32) default NULL,
  `creator` varchar(255) default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `multi_datasource_host` */

insert  into `multi_datasource_host`(`objuid`,`jndiName`,`dialect`,`l10n`,`driver_class`,`driver_url`,`username`,`password`,`poolsize`,`poolType`,`otherparas`,`isInit`,`applicationUID`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('basehost_1',NULL,'mysql','主机1','com.mysql.jdbc.Driver','jdbc:mysql://localhost:3306/information_schema?useUnicode=true&characterEncoding=utf-8','wkx','123asd',NULL,NULL,'127.0.0.1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `multi_tenancy` */

CREATE TABLE `multi_tenancy` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) default NULL,
  `l10n` varchar(255) default NULL,
  `startDate` date default NULL,
  `note` varchar(10000) default NULL,
  `multi_datasource_uid` varchar(32) default NULL,
  `model_datasource_uid` varchar(32) default NULL,
  `datasource_host_uid` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `multi_tenancy` */

insert  into `multi_tenancy`(`id`,`name`,`l10n`,`startDate`,`note`,`multi_datasource_uid`,`model_datasource_uid`,`datasource_host_uid`) values ('40288036301d2a7501301d2a75060000','carrefour','华为技术有限公司','2011-05-23','华为技术有限公司',NULL,NULL,'basehost_1');
insert  into `multi_tenancy`(`id`,`name`,`l10n`,`startDate`,`note`,`multi_datasource_uid`,`model_datasource_uid`,`datasource_host_uid`) values ('4028803b368bef5101368bef519d0000','myPaaS','我的PaaS','2012-04-18','我的PaaS','402881ec36aaa4550136aaa47ce70003','402881ec36aaa4550136aaa47c0c0002','basehost_1');
insert  into `multi_tenancy`(`id`,`name`,`l10n`,`startDate`,`note`,`multi_datasource_uid`,`model_datasource_uid`,`datasource_host_uid`) values ('402880e5340ee9da01340ef230970003','eeplat','EEPlat PaaS','2011-12-05',NULL,'default_data','default_model','basehost_1');
insert  into `multi_tenancy`(`id`,`name`,`l10n`,`startDate`,`note`,`multi_datasource_uid`,`model_datasource_uid`,`datasource_host_uid`) values ('402880ed37d6da9f0137d6db259e0001','beijing','beijing','2012-06-04','11111111111111','402880ed37d6da9f0137d6db4b760005','402880ed37d6da9f0137d6db4aca0004','basehost_1');
insert  into `multi_tenancy`(`id`,`name`,`l10n`,`startDate`,`note`,`multi_datasource_uid`,`model_datasource_uid`,`datasource_host_uid`) values ('402881e7336c754d01336c78721e0002','yunhesu','北京云荷素科技有限公司','2011-11-06',NULL,'402881ec36aaa4550136aaa6c6a80006','402881ec36aaa4550136aaa6c65a0005','basehost_1');
insert  into `multi_tenancy`(`id`,`name`,`l10n`,`startDate`,`note`,`multi_datasource_uid`,`model_datasource_uid`,`datasource_host_uid`) values ('ff8080813214c507013214c584a00001','test','test','2011-08-29','12',NULL,NULL,'basehost_1');

/*Table structure for table `t_expense` */

CREATE TABLE `t_expense` (
  `id` varchar(32) NOT NULL default '',
  `title` varchar(255) default NULL,
  `expense_man` varchar(32) NOT NULL default '',
  `expense_date` date default NULL,
  `expense_money` float NOT NULL default '0',
  `note` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_expense` */

/*Table structure for table `m_account` */

DROP TABLE IF EXISTS `m_account`;

/*!50001 CREATE TABLE  `m_account`(
 `objuid` varchar(32) ,
 `name` varchar(50) ,
 `tenancyId` varchar(127) ,
 `password` varchar(50) ,
 `asrole` varchar(255) ,
 `creator` varchar(255) ,
 `modifier` varchar(255) ,
 `modifyDate` datetime ,
 `mVersion` varchar(50) ,
 `default_app_uid` varchar(32) ,
 `register_date` date ,
 `end_date` date ,
 `isValid` char(1) ,
 `user_id` varchar(255) ,
 `nickName` varchar(255) ,
 `user_name` varchar(255) ,
 `user_email` varchar(255) ,
 `gender` char(1) ,
 `province` varchar(255) ,
 `city` varchar(255) ,
 `location` varchar(255) ,
 `figureurl` varchar(255) ,
 `figureurl_1` varchar(255) ,
 `figureurl_2` varchar(255) ,
 `user_description` varchar(2000) ,
 `open_site` varchar(255) 
)*/;

/*Table structure for table `wf_bj` */

DROP TABLE IF EXISTS `wf_bj`;

/*!50001 CREATE TABLE  `wf_bj`(
 `wfi_desc` varchar(2000) ,
 `startuser` varchar(50) ,
 `starttime` datetime ,
 `contextNiUid` varchar(32) ,
 `contextPIUid` varchar(32) ,
 `curState` varchar(255) ,
 `instance_uid` varchar(50) ,
 `USER_UID` varchar(50) ,
 `nodeDate` datetime 
)*/;

/*Table structure for table `wf_db` */

DROP TABLE IF EXISTS `wf_db`;

/*!50001 CREATE TABLE  `wf_db`(
 `curstate` varchar(255) ,
 `node_uid` varchar(50) ,
 `nodeDate` datetime ,
 `contextNIUid` varchar(32) ,
 `contextPIUid` varchar(32) ,
 `instance_uid` varchar(50) ,
 `pass_txt` varchar(500) ,
 `reject_txt` varchar(500) ,
 `user_uid` varchar(32) ,
 `WFI_Desc` varchar(2000) ,
 `startUser` varchar(50) ,
 `startTime` datetime 
)*/;

/*Table structure for table `wf_yb` */

DROP TABLE IF EXISTS `wf_yb`;

/*!50001 CREATE TABLE  `wf_yb`(
 `wfi_desc` varchar(2000) ,
 `startuser` varchar(50) ,
 `starttime` datetime ,
 `contextNIUid` varchar(32) ,
 `contextPIUid` varchar(32) ,
 `curState` varchar(255) ,
 `instance_uid` varchar(50) ,
 `USER_UID` varchar(50) ,
 `nodeDate` datetime 
)*/;

/*View structure for view m_account */

/*!50001 DROP TABLE IF EXISTS `m_account` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `m_account` AS select `multi_account`.`objuid` AS `objuid`,`multi_account`.`name` AS `name`,`multi_account`.`tenancyId` AS `tenancyId`,`multi_account`.`password` AS `password`,`multi_account`.`asrole` AS `asrole`,`multi_account`.`creator` AS `creator`,`multi_account`.`modifier` AS `modifier`,`multi_account`.`modifyDate` AS `modifyDate`,`multi_account`.`mVersion` AS `mVersion`,`multi_account`.`default_app_uid` AS `default_app_uid`,`multi_account`.`register_date` AS `register_date`,`multi_account`.`end_date` AS `end_date`,`multi_account`.`isValid` AS `isValid`,`multi_account`.`user_id` AS `user_id`,`multi_account`.`nickName` AS `nickName`,`multi_account`.`user_name` AS `user_name`,`multi_account`.`user_email` AS `user_email`,`multi_account`.`gender` AS `gender`,`multi_account`.`province` AS `province`,`multi_account`.`city` AS `city`,`multi_account`.`location` AS `location`,`multi_account`.`figureurl` AS `figureurl`,`multi_account`.`figureurl_1` AS `figureurl_1`,`multi_account`.`figureurl_2` AS `figureurl_2`,`multi_account`.`user_description` AS `user_description`,`multi_account`.`open_site` AS `open_site` from `multi_account` */;

/*View structure for view wf_bj */

/*!50001 DROP TABLE IF EXISTS `wf_bj` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_bj` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNiUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_his_nodeinstance` `ni` join `do_wfi_his_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 3)) */;

/*View structure for view wf_db */

/*!50001 DROP TABLE IF EXISTS `wf_db` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_db` AS select distinct `wpi`.`curState` AS `curstate`,`ni`.`node_uid` AS `node_uid`,`ni`.`nodeDate` AS `nodeDate`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`pass_txt` AS `pass_txt`,`ni`.`reject_txt` AS `reject_txt`,`ur`.`USER_UID` AS `user_uid`,`wpi`.`WFI_Desc` AS `WFI_Desc`,`wpi`.`startUser` AS `startUser`,`wpi`.`startTime` AS `startTime` from (((`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi`) join `do_org_user_role` `ur`) join `do_authorization` `a`) where ((`wpi`.`OBJUID` = `ni`.`PI_UID`) and (`a`.`parterUid` = _utf8'9') and (`a`.`ouUid` = `ur`.`ROLE_UID`) and (`ni`.`node_uid` = `a`.`whatUid`) and (`ni`.`ExeStatus` = 2) and (`wpi`.`ExeStatus` = 2)) */;

/*View structure for view wf_yb */

/*!50001 DROP TABLE IF EXISTS `wf_yb` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_yb` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 2)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
