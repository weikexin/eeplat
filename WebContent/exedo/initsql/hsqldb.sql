   create table DO_AUTHORIZATION
(
  OBJUID             VARCHAR(96) not null,
  PARTERUID          VARCHAR(96),
  OUUID              VARCHAR(255),
  ROLEUID            VARCHAR(96),
  WHEREDOBO          VARCHAR(96),
  WHEREBOINSTANCEUID VARCHAR(96),
  WHATTYPE           int,
  WHATUID            VARCHAR(96),
  AUTHORITY          CHAR(1),
  ISINHERIT          CHAR(1),
  CREATOR            VARCHAR(255),
  CREATDATE          DATE,
  MODIFIER           VARCHAR(255),
  MODIFYDATE         DATE,
  MVERSION           VARCHAR(150)
)
;
create table DO_AUTH_OWNER
(
  OBJUID             VARCHAR(96) not null,
  WHEREDOBO          VARCHAR(96),
  WHEREBOINSTANCEUID VARCHAR(96),
  AUTHORUID          VARCHAR(96),
  PARTERUID          VARCHAR(96),
  CREATOR            VARCHAR(255),
  CREATDATE          DATE,
  MODIFIER           VARCHAR(255),
  MODIFYDATE         DATE,
  MVERSION           VARCHAR(150)
)
;
create table DO_AUTH_SUITE
(
  OBJUID           VARCHAR(96) not null,
  NAME             VARCHAR(255),
  L10N             VARCHAR(255),
  SUITEMAINMENUUID VARCHAR(96),
  INCLUDEAUTHTXT   VARCHAR(150),
  EXTENDCONFIG     VARCHAR(255),
  INCLUDETYPE      int,
  INCLUDEUID       VARCHAR(96),
  CREATOR          VARCHAR(255),
  CREATDATE        DATE,
  MODIFIER         VARCHAR(255),
  MODIFYDATE       DATE,
  MVERSION         VARCHAR(150)
)
;


create table DO_LOG
(
  OBJUID     VARCHAR(96) not null,
  USERNAME   VARCHAR(150),
  DEPTNAME   VARCHAR(255),
  LOGINTIME  DATE,
  LOGOFFTIME DATE,
  IP         VARCHAR(150),
  SESSIONID  VARCHAR(150)
)
;



create table DO_LOG_DATA
(
  OBJUID        VARCHAR(96) not null,
  TABLE_NAME    VARCHAR(255),
  COL_NAME      VARCHAR(255),
  WHO_UID       VARCHAR(96),
  BO_UID        VARCHAR(96),
  COL_UID       VARCHAR(96),
  OPER_TYPE     VARCHAR(150),
  OPER_DATA     VARCHAR(255),
  OPER_TIME     DATE,
  OPER_DATA_UID VARCHAR(96),
  OPER_PANE_UID VARCHAR(96),
  OLD_VALUE     VARCHAR(255),
  NEW_VALUE     VARCHAR(255)
)
;

create table DO_ORG_DEPT
(
  OBJUID    VARCHAR(96) not null,
  DEPT_CODE VARCHAR(150),
  NAME      VARCHAR(150),
  LEADER    VARCHAR(96),
  TYPE      int,
  ORDER_NUM int,
  LOCATION  VARCHAR(255),
  TEL       VARCHAR(60),
  PARENTUID VARCHAR(96),
  NOTE      VARCHAR(255)
)
;


create table DO_ORG_ROLE
(
  OBJUID      VARCHAR(96) not null,
  NAME        VARCHAR(150),
  ROLEID      VARCHAR(150),
  PARENTUID   int,
  DEGREE      int,
  CREATOR     VARCHAR(255),
  CREATDATE   DATE,
  MODIFIER    VARCHAR(255),
  MODIFYDATE  DATE,
  MVERSION    VARCHAR(150),
  DESCRIPTION VARCHAR(1000)
)
;


create table DO_ORG_ROLE_CONFLICT
(
  OBJUID          VARCHAR(96) not null,
  ROLEUID         VARCHAR(96),
  ROLECONFLICTUID VARCHAR(96),
  STATICTYPE      int,
  CREATOR         VARCHAR(255),
  CREATDATE       DATE,
  MODIFIER        VARCHAR(255),
  MODIFYDATE      DATE,
  MVERSION        VARCHAR(150)
)
;

create table DO_ORG_TIMESPAN
(
  OBJUID     VARCHAR(150) not null,
  NAME       VARCHAR(150),
  BEGINTIME  DATE,
  ENDTIME    DATE,
  CREATOR    VARCHAR(255),
  CREATDATE  DATE,
  MODIFIER   VARCHAR(255),
  MODIFYDATE DATE,
  MVERSION   VARCHAR(150)
)
;


create table DO_ORG_USER
(
  OBJUID     VARCHAR(96) not null,
  NAME       VARCHAR(150),
  USER_CODE  VARCHAR(255),
  PASSWORD   VARCHAR(150),
  DEPTUID    VARCHAR(255),
  GENDER     CHAR(1),
  BIRTHDAY   DATE,
  EMAIL      VARCHAR(150),
  TELEPHONE  VARCHAR(150),
  MOBILE     VARCHAR(150),
  ADDRESS    VARCHAR(150),
  CREATOR    VARCHAR(255),
  MODIFIER   VARCHAR(255),
  MODIFYDATE DATE,
  MVERSION   VARCHAR(150)
)
;


create table DO_ORG_USER_ROLE
(
  OBJUID   VARCHAR(96) not null,
  USER_UID VARCHAR(96),
  ROLE_UID VARCHAR(150)
)
;

CREATE TABLE do_org_user_delegate (
  objuid VARCHAR(32) NOT NULL,
  user_uid VARCHAR(50) DEFAULT NULL,
  delegate_uid VARCHAR(50) DEFAULT NULL,
  isValid char(1) DEFAULT NULL,
  startTime date DEFAULT NULL,
  endTime date DEFAULT NULL,
  note varchar(1000),
  creator VARCHAR(255) DEFAULT NULL,
  modifier VARCHAR(255) DEFAULT NULL,
  modifyDate date DEFAULT NULL,
  mVersion VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (objuid)
);


create table DO_WFI_HIS_NI_DEPENDENCY
(
  OBJUID       VARCHAR(96) not null,
  PRE_NID_UID  VARCHAR(96),
  POST_NID_UID VARCHAR(96),
  DO_CONDITION VARCHAR(255)
)
;


create table DO_WFI_HIS_NODEINSTANCE
(
  OBJUID             VARCHAR(96) not null,
  NODENAME           VARCHAR(255),
  NODEDESC           VARCHAR(255),
  NODEDATE           DATE,
  NODETYPE           int,
  AUTHTYPE           int,
  NODESTATESHOW      VARCHAR(255),
  NODESTATESHOWBACK  VARCHAR(255),
  SPEC_NAME          VARCHAR(255),
  EXESTATUS          int,
  DECISIONEXPRESSION VARCHAR(255),
  PI_UID             VARCHAR(150),
  SCHEDULEOUUID      VARCHAR(150),
  PERFORMERUID       VARCHAR(150),
  NODE_UID           VARCHAR(150),
  BACKTYPE           int,
  PASS_TXT           VARCHAR(255),
  REJECT_TXT         VARCHAR(255),
  NODE_EXT1          VARCHAR(255),
  NODE_EXT2          VARCHAR(255),
  RETNODEUID         VARCHAR(150)
)
;


create table DO_WFI_HIS_PROCESSINSTANCE
(
  OBJUID        VARCHAR(96) not null,
  WFI_NAME      VARCHAR(255),
  PT_NAME       VARCHAR(255),
  WFI_DESC      VARCHAR(255),
  EXESTATUS     int,
  PT_UID        VARCHAR(150),
  STARTUSER     VARCHAR(150),
  STARTTIME     DATE,
  CURSTATE      VARCHAR(255),
  CURSTATETIME  DATE,
  CURSTATEUSER  VARCHAR(150),
  REJECTTXT     VARCHAR(255),
  INSTANCE_UID  VARCHAR(150),
  INSTANCE2_UID VARCHAR(96),
  INSTANCE3_UID VARCHAR(96)
)
;


create table DO_WFI_HIS_VARINSTANCE
(
  OBJUID          VARCHAR(96) not null,
  VARNAME         VARCHAR(255),
  VARVALUE        VARCHAR(255),
  PT_VAR_UID      VARCHAR(96),
  PI_UID          VARCHAR(96),
  BO_PROPERTY_UID VARCHAR(96),
  INSTANCE_UID    VARCHAR(255)
)
;


create table DO_WFI_NI_DEPENDENCY
(
  OBJUID       VARCHAR(96) not null,
  PRE_NID_UID  VARCHAR(96),
  POST_NID_UID VARCHAR(96),
  DO_CONDITION VARCHAR(255)
)
;

create table DO_WFI_NODEINSTANCE
(
  OBJUID             VARCHAR(96) not null,
  NODENAME           VARCHAR(255),
  NODEDESC           VARCHAR(255),
  NODEDATE           DATE,
  NODETYPE           int,
  AUTHTYPE           int,
  NODESTATESHOW      VARCHAR(255),
  NODESTATESHOWBACK  VARCHAR(255),
  SPEC_NAME          VARCHAR(255),
  EXESTATUS          int,
  DECISIONEXPRESSION VARCHAR(255),
  PI_UID             VARCHAR(150),
  SCHEDULEOUUID      VARCHAR(150),
  PERFORMERUID       VARCHAR(150),
  NODE_UID           VARCHAR(150),
  BACKTYPE           int,
  PASS_TXT           VARCHAR(255),
  REJECT_TXT         VARCHAR(255),
  NODE_EXT1          VARCHAR(255),
  NODE_EXT2          VARCHAR(255),
  RETNODEUID         VARCHAR(150)
)
;


create table DO_WFI_PROCESSINSTANCE
(
  OBJUID        VARCHAR(96) not null,
  WFI_NAME      VARCHAR(255),
  PT_NAME       VARCHAR(255),
  WFI_DESC      VARCHAR(255),
  EXESTATUS     int,
  PT_UID        VARCHAR(150),
  STARTUSER     VARCHAR(150),
  STARTTIME     DATE,
  CURSTATE      VARCHAR(255),
  CURSTATETIME  DATE,
  CURSTATEUSER  VARCHAR(150),
  REJECTTXT     VARCHAR(255),
  INSTANCE_UID  VARCHAR(150),
  INSTANCE2_UID VARCHAR(96),
  INSTANCE3_UID VARCHAR(96)
)
;


create table DO_WFI_VARINSTANCE
(
  OBJUID          VARCHAR(96) not null,
  VARNAME         VARCHAR(255),
  VARVALUE        VARCHAR(255),
  PT_VAR_UID      VARCHAR(96),
  PI_UID          VARCHAR(96),
  BO_PROPERTY_UID VARCHAR(96),
  INSTANCE_UID    VARCHAR(255)
)
;

create table T_EXPENSE
(
  ID            VARCHAR(96) not null,
  TITLE         VARCHAR(255),
  EXPENSE_MAN   VARCHAR(96),
  EXPENSE_DATE  DATE,
  EXPENSE_MONEY FLOAT,
  NOTE          VARCHAR(1000)
)
;


CREATE TABLE DO_CODE_MAXSEQUENCE (
  OBJUID VARCHAR (32)  NOT NULL ,
  SEQUENCE_NAME VARCHAR (255)  NULL ,
  CODE_ITEMUID VARCHAR (32)  NULL ,
  PROPERTYUID VARCHAR (32)  NULL ,
  PROPERTYVALUE VARCHAR (255)  NULL ,
  YEARSEQ int NULL ,
  MAX_SEQUENCE int NULL ,
  creator VARCHAR (255)  NULL ,
  creatDate date NULL ,
  modifier VARCHAR (255)  NULL ,
  modifyDate date NULL ,
  mVersion VARCHAR (50)  NULL 
);


  
CREATE  VIEW wf_db AS SELECT DISTINCT 
    wfi_desc,startuser,starttime, wpi.curState, ni.node_uid, ni.nodeDate, ni.OBJUID AS contextNIUid, 
    wpi.OBJUID AS contextPIUid, wpi.instance_uid, ni.pass_txt, ni.reject_txt, 
    ur.USER_UID
 FROM do_authorization a  JOIN
    do_org_user_role ur ON a.ouUid = ur.ROLE_UID  JOIN
    do_wfi_nodeinstance ni  JOIN
    do_wfi_processinstance wpi ON ni.PI_UID = wpi.OBJUID ON 
    a.whatUid = ni.node_uid 
 WHERE (a.parterUid = '9') AND (ni.ExeStatus = 2) AND (wpi.ExeStatus = 2);
 
CREATE  VIEW WF_YB AS SELECT DISTINCT
    wfi_desc,startuser,starttime, ni.OBJUID AS contextNIUid,
     wpi.OBJUID AS contextPIUid, wpi.curState, wpi.instance_uid, ni.performeruid as USER_UID,nodeDate
     FROM do_wfi_nodeinstance  ni  JOIN
    do_wfi_processinstance wpi ON ni.PI_UID = wpi.OBJUID
 WHERE  (ni.ExeStatus = 3) AND (wpi.ExeStatus = 2);
 
 CREATE  VIEW WF_BJ AS SELECT DISTINCT   
  wfi_desc,startuser,starttime,ni.objuid as contextNiUid,wpi.OBJUID AS contextPIUid, wpi.curState, wpi.instance_uid, ni.performeruid as USER_UID,nodeDate
 FROM  do_wfi_his_nodeinstance ni  JOIN    do_wfi_his_processinstance wpi ON ni.PI_UID = wpi.OBJUID
  WHERE (ni.ExeStatus = 3) AND (wpi.ExeStatus = 3);

insert into DO_ORG_DEPT (OBJUID, DEPT_CODE, NAME, LEADER, TYPE, ORDER_NUM, LOCATION, TEL, PARENTUID, NOTE)
values ('4028803127b6f15a0127b7294a7a0004', 'pingtaibumen', '平台部门', '40288031278ed91501278ed915b30000', 2, null, null, null, null, null);

insert into DO_ORG_USER (OBJUID, NAME, USER_CODE, PASSWORD, DEPTUID, GENDER, BIRTHDAY, EMAIL, TELEPHONE, MOBILE, ADDRESS, CREATOR, MODIFIER, MODIFYDATE, MVERSION)
values ('40288031278ed91501278ed915b30000', 'u', '0005', 'b59c67bf196a4758191e42f76670ceba', '4028803127b6f15a0127b7294a7a0004', null, null, 'u@u.com', null, null, null, null, null, null, null);
insert into DO_ORG_USER (OBJUID, NAME, USER_CODE, PASSWORD, DEPTUID, GENDER, BIRTHDAY, EMAIL, TELEPHONE, MOBILE, ADDRESS, CREATOR, MODIFIER, MODIFYDATE, MVERSION)
values ('40288031287fd27f012880415d96006c', 'xz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
insert into DO_ORG_USER (OBJUID, NAME, USER_CODE, PASSWORD, DEPTUID, GENDER, BIRTHDAY, EMAIL, TELEPHONE, MOBILE, ADDRESS, CREATOR, MODIFIER, MODIFYDATE, MVERSION)
values ('40288031287fd27f01288045ecd3006e', 'xw', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
insert into DO_ORG_USER (OBJUID, NAME, USER_CODE, PASSWORD, DEPTUID, GENDER, BIRTHDAY, EMAIL, TELEPHONE, MOBILE, ADDRESS, CREATOR, MODIFIER, MODIFYDATE, MVERSION)
values ('40288031287fd27f012880469c2d006f', 'hz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
insert into DO_ORG_USER (OBJUID, NAME, USER_CODE, PASSWORD, DEPTUID, GENDER, BIRTHDAY, EMAIL, TELEPHONE, MOBILE, ADDRESS, CREATOR, MODIFIER, MODIFYDATE, MVERSION)
values ('40288031287fd27f012880472b4e0070', 'wz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
insert into DO_ORG_USER (OBJUID, NAME, USER_CODE, PASSWORD, DEPTUID, GENDER, BIRTHDAY, EMAIL, TELEPHONE, MOBILE, ADDRESS, CREATOR, MODIFIER, MODIFYDATE, MVERSION)
values ('40288031288a2b8501288a3704e00005', 'lz', 'lz', 'd41d8cd98f00b204e9800998ecf8427e', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);


INSERT INTO do_org_role VALUES ('4028803127b6f15a0127b725c6930003', '普通员工', 'putongyuangong', null, '1', '普通员工', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fdb80af0001', '部门经理', 'bumenjingli', null, null, '部门经理', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fdc23670002', '副总经理', 'fuzongjingli', null, null, '副总经理', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fdc70100003', '财务主管', 'caiwuzhuguan', null, null, null, null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fe80c5a0009', '总经理', 'zongjingli', null, null, '总经理', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031288a2b8501288a3d009d000d', '系统管理员', 'system_manager', null, null, null, null, null, null, null, null);



insert into DO_ORG_USER_ROLE (OBJUID, USER_UID, ROLE_UID)
values ('40288031287fd27f01288047ad6e0071', '40288031287fd27f01288045ecd3006e', '4028803127b6f15a0127b725c6930003');
insert into DO_ORG_USER_ROLE (OBJUID, USER_UID, ROLE_UID)
values ('40288031287fd27f01288047d1cf0072', '40288031287fd27f012880469c2d006f', '40288031287fd27f01287fe80c5a0009');
insert into DO_ORG_USER_ROLE (OBJUID, USER_UID, ROLE_UID)
values ('40288031287fd27f01288047f3de0073', '40288031287fd27f012880472b4e0070', '40288031287fd27f01287fdc23670002');
insert into DO_ORG_USER_ROLE (OBJUID, USER_UID, ROLE_UID)
values ('40288031287fd27f0128804811f50074', '40288031287fd27f012880415d96006c', '40288031287fd27f01287fdc70100003');
insert into DO_ORG_USER_ROLE (OBJUID, USER_UID, ROLE_UID)
values ('40288031288a2b8501288a3d2a1e000e', '40288031278ed91501278ed915b30000', '40288031288a2b8501288a3d009d000d');



INSERT INTO do_authorization VALUES ('402880312870acd1012870f1133a0001', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '110', 'test_n12', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031287fd27f0128801f4bb9000a', '9', '40288031287fd27f01287fdc23670002', null, null, null, '110', 'tt2_n11', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031287fd27f0128801f849c000b', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '110', 'tt2_n12', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031287fd27f01287fdfd2a40005', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '110', 'tt2_n6', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('402880312870acd10128712bf8ae000e', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '110', 'test_n7', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031287fd27f0128801fb3f9000c', '9', '40288031287fd27f01287fdc70100003', null, null, null, '110', 'tt2_n13', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d70128852439e90004', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d7012885249bc00006', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d701288524b1d80007', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d70128852572b70008', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d70128852588620009', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '40288031287fd27f0128804a71510076', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d701288525a57f000a', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d701288525bbe6000b', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d701288525d4ec000c', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d70128852615cd000e', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '40288031287fd27f0128804c9b550077', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288512d701288529a9c2000f', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a3652c60001', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a3660920002', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a367a820003', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a3685cd0004', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '402880312880f0bc012880f3b4cc0001', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a383e1c0007', '9', '4028803127b6f15a0127b725c6930003', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a384a220008', '9', '40288031287fd27f01287fdb80af0001', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a3858890009', '9', '40288031287fd27f01287fdc23670002', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a386cbd000a', '9', '40288031287fd27f01287fdc70100003', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a387960000b', '9', '40288031287fd27f01287fe80c5a0009', null, null, null, '16', '402880312880f0bc012880f413290002', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a2b8501288a3d6812000f', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312788b836012788b844d10009', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a4cb701288a4ea76a0002', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312788b836012788b9b77c012c', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a4cb701288a4eb8fe0003', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312788b836012788ba043401ed', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a4cb701288a4ed4e20004', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '40288031278f16d001278f43e525003c', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a4cb701288a4ee4c10005', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '40288031278904920127890492720000', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a4cb701288a4ef4af0006', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '40288031278a406e01278a6bb9ce005d', '1', '1', null, null, null, null, null);
INSERT INTO do_authorization VALUES ('40288031288a4cb701288a4f02d80007', '9', '40288031288a2b8501288a3d009d000d', null, null, null, '16', '402880312793120d01279318032e0260', '1', '1', null, null, null, null, null);



