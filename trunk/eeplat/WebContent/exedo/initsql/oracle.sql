   create table DO_AUTHORIZATION
(
  OBJUID             VARCHAR2(96) not null,
  PARTERUID          VARCHAR2(96),
  OUUID              VARCHAR2(255),
  ROLEUID            VARCHAR2(96),
  WHEREDOBO          VARCHAR2(96),
  WHEREBOINSTANCEUID VARCHAR2(96),
  WHATTYPE           NUMBER(10),
  WHATUID            VARCHAR2(96),
  AUTHORITY          CHAR(1),
  ISINHERIT          CHAR(1),
  CREATOR            VARCHAR2(255),
  CREATDATE          DATE,
  MODIFIER           VARCHAR2(255),
  MODIFYDATE         DATE,
  MVERSION           VARCHAR2(150)
)
;
alter table DO_AUTHORIZATION
  add constraint AUTHORIZATION_KEY primary key (OBJUID);

create table DO_AUTH_OWNER
(
  OBJUID             VARCHAR2(96) not null,
  WHEREDOBO          VARCHAR2(96),
  WHEREBOINSTANCEUID VARCHAR2(96),
  AUTHORUID          VARCHAR2(96),
  PARTERUID          VARCHAR2(96),
  CREATOR            VARCHAR2(255),
  CREATDATE          DATE,
  MODIFIER           VARCHAR2(255),
  MODIFYDATE         DATE,
  MVERSION           VARCHAR2(150)
)
;
alter table DO_AUTH_OWNER
  add constraint AUTH_OWNER_KEY primary key (OBJUID);

create table DO_AUTH_SUITE
(
  OBJUID           VARCHAR2(96) not null,
  NAME             VARCHAR2(255),
  L10N             VARCHAR2(255),
  SUITEMAINMENUUID VARCHAR2(96),
  INCLUDEAUTHTXT   VARCHAR2(150),
  EXTENDCONFIG     VARCHAR2(255),
  INCLUDETYPE      NUMBER(10),
  INCLUDEUID       VARCHAR2(96),
  CREATOR          VARCHAR2(255),
  CREATDATE        DATE,
  MODIFIER         VARCHAR2(255),
  MODIFYDATE       DATE,
  MVERSION         VARCHAR2(150)
)
;
alter table DO_AUTH_SUITE
  add constraint AUTH_SUITE_KEY primary key (OBJUID);

create table DO_LOG
(
  OBJUID     VARCHAR2(96) not null,
  USERNAME   VARCHAR2(150),
  DEPTNAME   VARCHAR2(255),
  LOGINTIME  DATE,
  LOGOFFTIME DATE,
  IP         VARCHAR2(150),
  SESSIONID  VARCHAR2(150)
)
;
alter table DO_LOG
  add constraint LOG_KEY primary key (OBJUID);


create table DO_LOG_DATA
(
  OBJUID        VARCHAR2(96) not null,
  TABLE_NAME    VARCHAR2(255),
  COL_NAME      VARCHAR2(255),
  WHO_UID       VARCHAR2(96),
  BO_UID        VARCHAR2(96),
  COL_UID       VARCHAR2(96),
  OPER_TYPE     VARCHAR2(150),
  OPER_DATA     VARCHAR2(255),
  OPER_TIME     DATE,
  OPER_DATA_UID VARCHAR2(96),
  OPER_PANE_UID VARCHAR2(96),
  OLD_VALUE     VARCHAR2(255),
  NEW_VALUE     VARCHAR2(255)
)
;
alter table DO_LOG_DATA
  add constraint LOG_DATA_KEY primary key (OBJUID);

create table DO_ORG_DEPT
(
  OBJUID    VARCHAR2(96) not null,
  DEPT_CODE VARCHAR2(150),
  NAME      VARCHAR2(150),
  LEADER    VARCHAR2(96),
  TYPE      NUMBER(10),
  ORDER_NUM NUMBER(10),
  LOCATION  VARCHAR2(255),
  TEL       VARCHAR2(60),
  PARENTUID VARCHAR2(96),
  NOTE      VARCHAR2(255)
)
;
alter table DO_ORG_DEPT
  add constraint ORG_DEPT_KEY primary key (OBJUID);

create table DO_ORG_ROLE
(
  OBJUID      VARCHAR2(96) not null,
  NAME        VARCHAR2(150),
  ROLEID      VARCHAR2(150),
  PARENTUID   NUMBER(10),
  DEGREE      NUMBER(10),
  CREATOR     VARCHAR2(255),
  CREATDATE   DATE,
  MODIFIER    VARCHAR2(255),
  MODIFYDATE  DATE,
  MVERSION    VARCHAR2(150),
  DESCRIPTION VARCHAR2(1000)
)
;
alter table DO_ORG_ROLE
  add constraint ORG_ROLE_KEY primary key (OBJUID);

create table DO_ORG_ROLE_CONFLICT
(
  OBJUID          VARCHAR2(96) not null,
  ROLEUID         VARCHAR2(96),
  ROLECONFLICTUID VARCHAR2(96),
  STATICTYPE      NUMBER(2),
  CREATOR         VARCHAR2(255),
  CREATDATE       DATE,
  MODIFIER        VARCHAR2(255),
  MODIFYDATE      DATE,
  MVERSION        VARCHAR2(150)
)
;
alter table DO_ORG_ROLE_CONFLICT
  add constraint ROLE_CONFICT_KEY primary key (OBJUID);

create table DO_ORG_TIMESPAN
(
  OBJUID     VARCHAR2(150) not null,
  NAME       VARCHAR2(150),
  BEGINTIME  DATE,
  ENDTIME    DATE,
  CREATOR    VARCHAR2(255),
  CREATDATE  DATE,
  MODIFIER   VARCHAR2(255),
  MODIFYDATE DATE,
  MVERSION   VARCHAR2(150)
)
;
alter table DO_ORG_TIMESPAN
  add constraint ORG_TIMESPAN_KEY primary key (OBJUID);

create table DO_ORG_USER
(
  OBJUID     VARCHAR2(96) not null,
  NAME       VARCHAR2(150),
  USER_CODE  VARCHAR2(255),
  PASSWORD   VARCHAR2(150),
  DEPTUID    VARCHAR2(255),
  GENDER     CHAR(1),
  BIRTHDAY   DATE,
  EMAIL      VARCHAR2(150),
  TELEPHONE  VARCHAR2(150),
  MOBILE     VARCHAR2(150),
  ADDRESS    VARCHAR2(150),
  CREATOR    VARCHAR2(255),
  MODIFIER   VARCHAR2(255),
  MODIFYDATE DATE,
  MVERSION   VARCHAR2(150)
)
;
alter table DO_ORG_USER
  add constraint ORG_USER_KEY primary key (OBJUID);

create table DO_ORG_USER_ROLE
(
  OBJUID   VARCHAR2(96) not null,
  USER_UID VARCHAR2(96),
  ROLE_UID VARCHAR2(150)
)
;
CREATE TABLE do_org_user_delegate (
  objuid varchar2(32) NOT NULL,
  user_uid varchar2(50) DEFAULT NULL,
  delegate_uid varchar2(50) DEFAULT NULL,
  isValid char(1) DEFAULT NULL,
  startTime date DEFAULT NULL,
  endTime date DEFAULT NULL,
  note clob,
  creator varchar2(255) DEFAULT NULL,
  modifier varchar2(255) DEFAULT NULL,
  modifyDate date DEFAULT NULL,
  mVersion varchar2(50) DEFAULT NULL,
  PRIMARY KEY (objuid)
);
alter table DO_ORG_USER_ROLE
  add constraint USER_ROLE_KEY primary key (OBJUID);

create table DO_WFI_HIS_NI_DEPENDENCY
(
  OBJUID       VARCHAR2(96) not null,
  PRE_NID_UID  VARCHAR2(96),
  POST_NID_UID VARCHAR2(96),
  DO_CONDITION VARCHAR2(255)
)
;
alter table DO_WFI_HIS_NI_DEPENDENCY
  add constraint WF_HIS_DEP_KEY primary key (OBJUID);

create table DO_WFI_HIS_NODEINSTANCE
(
  OBJUID             VARCHAR2(96) not null,
  NODENAME           VARCHAR2(255),
  NODEDESC           VARCHAR2(255),
  NODEDATE           DATE,
  NODETYPE           NUMBER(10),
  AUTHTYPE           NUMBER(10),
  NODESTATESHOW      VARCHAR2(255),
  NODESTATESHOWBACK  VARCHAR2(255),
  SPEC_NAME          VARCHAR2(255),
  EXESTATUS          NUMBER(10),
  DECISIONEXPRESSION VARCHAR2(255),
  PI_UID             VARCHAR2(150),
  SCHEDULEOUUID      VARCHAR2(150),
  PERFORMERUID       VARCHAR2(150),
  NODE_UID           VARCHAR2(150),
  BACKTYPE           NUMBER(10),
  PASS_TXT           VARCHAR2(255),
  REJECT_TXT         VARCHAR2(255),
  NODE_EXT1          VARCHAR2(255),
  NODE_EXT2          VARCHAR2(255),
  RETNODEUID         VARCHAR2(150)
)
;
alter table DO_WFI_HIS_NODEINSTANCE
  add constraint WF_HIS_NODEINSTNACE primary key (OBJUID);

create table DO_WFI_HIS_PROCESSINSTANCE
(
  OBJUID        VARCHAR2(96) not null,
  WFI_NAME      VARCHAR2(255),
  PT_NAME       VARCHAR2(255),
  WFI_DESC      VARCHAR2(255),
  EXESTATUS     NUMBER(10),
  PT_UID        VARCHAR2(150),
  STARTUSER     VARCHAR2(150),
  STARTTIME     DATE,
  CURSTATE      VARCHAR2(255),
  CURSTATETIME  DATE,
  CURSTATEUSER  VARCHAR2(150),
  REJECTTXT     VARCHAR2(255),
  INSTANCE_UID  VARCHAR2(150),
  INSTANCE2_UID VARCHAR2(96),
  INSTANCE3_UID VARCHAR2(96)
)
;
alter table DO_WFI_HIS_PROCESSINSTANCE
  add constraint WF_HIS_PROCESSINSTANCE_KEY primary key (OBJUID);

create table DO_WFI_HIS_VARINSTANCE
(
  OBJUID          VARCHAR2(96) not null,
  VARNAME         VARCHAR2(255),
  VARVALUE        VARCHAR2(255),
  PT_VAR_UID      VARCHAR2(96),
  PI_UID          VARCHAR2(96),
  BO_PROPERTY_UID VARCHAR2(96),
  INSTANCE_UID    VARCHAR2(255)
)
;
alter table DO_WFI_HIS_VARINSTANCE
  add constraint WF_HIS_VARINSTANCE_KEY primary key (OBJUID);

create table DO_WFI_NI_DEPENDENCY
(
  OBJUID       VARCHAR2(96) not null,
  PRE_NID_UID  VARCHAR2(96),
  POST_NID_UID VARCHAR2(96),
  DO_CONDITION VARCHAR2(255)
)
;
alter table DO_WFI_NI_DEPENDENCY
  add constraint WF_DEN_KEY primary key (OBJUID);

create table DO_WFI_NODEINSTANCE
(
  OBJUID             VARCHAR2(96) not null,
  NODENAME           VARCHAR2(255),
  NODEDESC           VARCHAR2(255),
  NODEDATE           DATE,
  NODETYPE           NUMBER(10),
  AUTHTYPE           NUMBER(10),
  NODESTATESHOW      VARCHAR2(255),
  NODESTATESHOWBACK  VARCHAR2(255),
  SPEC_NAME          VARCHAR2(255),
  EXESTATUS          NUMBER(10),
  DECISIONEXPRESSION VARCHAR2(255),
  PI_UID             VARCHAR2(150),
  SCHEDULEOUUID      VARCHAR2(150),
  PERFORMERUID       VARCHAR2(150),
  NODE_UID           VARCHAR2(150),
  BACKTYPE           NUMBER(10),
  PASS_TXT           VARCHAR2(255),
  REJECT_TXT         VARCHAR2(255),
  NODE_EXT1          VARCHAR2(255),
  NODE_EXT2          VARCHAR2(255),
  RETNODEUID         VARCHAR2(150)
)
;
alter table DO_WFI_NODEINSTANCE
  add constraint WF_NODEINSTANCE_KEY primary key (OBJUID);

create table DO_WFI_PROCESSINSTANCE
(
  OBJUID        VARCHAR2(96) not null,
  WFI_NAME      VARCHAR2(255),
  PT_NAME       VARCHAR2(255),
  WFI_DESC      VARCHAR2(255),
  EXESTATUS     NUMBER(10),
  PT_UID        VARCHAR2(150),
  STARTUSER     VARCHAR2(150),
  STARTTIME     DATE,
  CURSTATE      VARCHAR2(255),
  CURSTATETIME  DATE,
  CURSTATEUSER  VARCHAR2(150),
  REJECTTXT     VARCHAR2(255),
  INSTANCE_UID  VARCHAR2(150),
  INSTANCE2_UID VARCHAR2(96),
  INSTANCE3_UID VARCHAR2(96)
)
;
alter table DO_WFI_PROCESSINSTANCE
  add constraint WF_PROCESSINSTANCE_KEY primary key (OBJUID);

create table DO_WFI_VARINSTANCE
(
  OBJUID          VARCHAR2(96) not null,
  VARNAME         VARCHAR2(255),
  VARVALUE        VARCHAR2(255),
  PT_VAR_UID      VARCHAR2(96),
  PI_UID          VARCHAR2(96),
  BO_PROPERTY_UID VARCHAR2(96),
  INSTANCE_UID    VARCHAR2(255)
)
;
alter table DO_WFI_VARINSTANCE
  add constraint WF_VARINSTANCE_KEY primary key (OBJUID);

create table T_EXPENSE
(
  ID            VARCHAR2(96) not null,
  TITLE         VARCHAR2(255),
  EXPENSE_MAN   VARCHAR2(96),
  EXPENSE_DATE  DATE,
  EXPENSE_MONEY FLOAT,
  NOTE          VARCHAR2(1000)
)
;
alter table T_EXPENSE
  add constraint EXPENSE_KEY primary key (ID);
  
  
CREATE TABLE do_log_dev (
  id varchar(32) NOT NULL,
  threadstr varchar(255) ,
  starttime date ,
  userName varchar(256) ,
  ip varchar(128),
  msgstr clob,
  col1 varchar(255) ,
  col2 varchar(255) ,
  col3 varchar(255) 
  ) ;

alter table do_log_dev
  add constraint do_log_dev primary key (id);

CREATE TABLE DO_CODE_MAXSEQUENCE (
  OBJUID varchar2 (32)  NOT NULL ,
  SEQUENCE_NAME varchar2 (255)  NULL ,
  CODE_ITEMUID varchar2 (32)  NULL ,
  PROPERTYUID varchar2 (32)  NULL ,
  PROPERTYVALUE varchar2 (255)  NULL ,
  YEARSEQ decimal(10, 0) NULL ,
  MAX_SEQUENCE decimal(19, 0) NULL ,
  creator varchar2 (255)  NULL ,
  creatDate date NULL ,
  modifier varchar2 (255)  NULL ,
  modifyDate date NULL ,
  mVersion varchar2 (50)  NULL 
);
 alter table DO_CODE_MAXSEQUENCE
  add constraint DO_CODE_MAXSEQUENCE_KEY primary key (OBJUID);
  
  

  
CREATE OR REPLACE  VIEW wf_db AS SELECT DISTINCT 
    wfi_desc,startuser,starttime, wpi.curState, ni.node_uid, ni.nodeDate, ni.OBJUID AS contextNIUid, 
    wpi.OBJUID AS contextPIUid, wpi.instance_uid, ni.pass_txt, ni.reject_txt, 
    ur.USER_UID
 FROM do_authorization a INNER JOIN
    do_org_user_role ur ON a.ouUid = ur.ROLE_UID INNER JOIN
    do_wfi_nodeinstance ni INNER JOIN
    do_wfi_processinstance wpi ON ni.PI_UID = wpi.OBJUID ON 
    a.whatUid = ni.node_uid 
 WHERE (a.parterUid = '9') AND (ni.ExeStatus = 2) AND (wpi.ExeStatus = 2);
 
CREATE OR REPLACE VIEW WF_YB AS SELECT DISTINCT
    wfi_desc,startuser,starttime, ni.OBJUID AS contextNIUid,
     wpi.OBJUID AS contextPIUid, wpi.curState, wpi.instance_uid, ni.performeruid as USER_UID,nodeDate
     FROM do_wfi_nodeinstance  ni INNER JOIN
    do_wfi_processinstance wpi ON ni.PI_UID = wpi.OBJUID
 WHERE  (ni.ExeStatus = 3) AND (wpi.ExeStatus = 2);
 
 CREATE OR REPLACE VIEW WF_BJ AS SELECT DISTINCT   
  wfi_desc,startuser,starttime,ni.objuid as contextNiUid,wpi.OBJUID AS contextPIUid, wpi.curState, wpi.instance_uid, ni.performeruid as USER_UID,nodeDate
 FROM  do_wfi_his_nodeinstance ni INNER JOIN    do_wfi_his_processinstance wpi ON ni.PI_UID = wpi.OBJUID
  WHERE (ni.ExeStatus = 3) AND (wpi.ExeStatus = 3);
  
 CREATE OR REPLACE VIEW  wf_db_schedule     AS  (SELECT DISTINCT
  wpi.curState     AS curstate,
  ni.node_uid      AS node_uid,
  ni.nodeDate      AS nodeDate,
  ni.OBJUID        AS contextNIUid,
  wpi.OBJUID       AS contextPIUid,
  wpi.instance_uid AS instance_uid,
  ni.pass_txt      AS pass_txt,
  ni.reject_txt    AS reject_txt,
  ur.USER_UID      AS user_uid,
  wpi.WFI_Desc     AS WFI_Desc,
  wpi.startUser    AS startUser,
  wpi.startTime    AS startTime
FROM (((do_wfi_nodeinstance ni
     JOIN do_wfi_processinstance wpi)
    JOIN do_org_user_role ur)
   JOIN do_authorization a)
WHERE ((wpi.OBJUID = ni.PI_UID)
       AND (a.parterUid = '9')
       AND (a.ouUid = ur.ROLE_UID)
       AND (ni.node_uid = a.whatUid)
       AND (ni.ExeStatus = 2)
       AND (wpi.ExeStatus = 2))
       
UNION   SELECT DISTINCT
  wpi.curState     AS curstate,
  ni.node_uid      AS node_uid,
  ni.nodeDate      AS nodeDate,
  ni.OBJUID        AS contextNIUid,
  wpi.OBJUID       AS contextPIUid,
  wpi.instance_uid AS instance_uid,
  ni.pass_txt      AS pass_txt,
  ni.reject_txt    AS reject_txt,
  a.ouUid          AS user_uid,
  wpi.WFI_Desc     AS WFI_Desc,
  wpi.startUser    AS startUser,
  wpi.startTime    AS startTime
FROM ((do_wfi_nodeinstance ni
     JOIN do_wfi_processinstance wpi)
      JOIN do_authorization a)
WHERE ((wpi.OBJUID = ni.PI_UID)
       AND (a.parterUid = 1)
       AND (ni.objuid = a.whatUid)
       AND (ni.ExeStatus = 2)
       AND (wpi.ExeStatus = 2))); 

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



