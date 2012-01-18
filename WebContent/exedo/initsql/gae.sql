 CREATE TABLE do_auth_owner (
	objUid varchar (96)  ,
	whereDOBO varchar (96)  ,
	whereBOInstanceUid varchar (96)  ,
	authorUid varchar (96)  ,
	parterUid varchar (96)  ,
	creator varchar (255)  ,
	creatDate timestamp NULL ,
	modifier varchar (255)  ,
	modifyDate timestamp NULL ,
	mVersion varchar (150)  
) 
;
CREATE TABLE do_auth_suite (
	objuid varchar (96)  ,
	name varchar (255)  ,
	l10n varchar (255)  ,
	suiteMainMenuUid varchar (96)  ,
	includeAuthTxt varchar (150)  ,
	extendConfig varchar (255)  ,
	includeType int NULL ,
	includeUid varchar (96)  ,
	creator varchar (255)  ,
	creatDate timestamp NULL ,
	modifier varchar (255)  ,
	modifyDate timestamp NULL ,
	mVersion varchar (150)  
) 
;
CREATE TABLE do_authorization (
	objUID varchar (96)  ,
	parterUid varchar (96)  ,
	ouUid varchar (255)  ,
	roleUid varchar (96)  ,
	whereDOBO varchar (96)  ,
	whereBOInstanceUid varchar (96)  ,
	whatType int NULL ,
	whatUid varchar (96)  ,
	authority varchar (3)  ,
	isInherit varchar (3)  ,
	creator varchar (255)  ,
	creatDate timestamp NULL ,
	modifier varchar (255)  ,
	modifyDate timestamp NULL ,
	mVersion varchar (150)  
) 
;
CREATE TABLE do_log (
	objuid varchar (96)  ,
	userName varchar (150)  ,
	deptName varchar (255)  ,
	loginTime timestamp NULL ,
	logoffTime timestamp NULL ,
	ip varchar (150)  ,
	sessionid varchar (150)  
) 
;
CREATE TABLE do_log_data (
	OBJUID varchar (96)  ,
	TABLE_NAME varchar (255)  ,
	COL_NAME varchar (255)  ,
	WHO_UID varchar (96)  ,
	BO_UID varchar (96)  ,
	COL_UID varchar (96)  ,
	OPER_TYPE varchar (150)  ,
	OPER_DATA varchar (255)  ,
	OPER_TIME timestamp NULL ,
	OPER_DATA_UID varchar (96)  ,
	OPER_PANE_UID varchar (96)  ,
	OLD_VALUE varchar (255)  ,
	NEW_VALUE varchar (255)  
) 
;
CREATE TABLE do_org_dept (
	objUid varchar (96)  ,
	dept_code varchar (150)  ,
	name varchar (150)  ,
	leader varchar (96)  ,
	type int NULL ,
	order_num int NULL ,
	location varchar (255)  ,
	tel varchar (60)  ,
	parentUid varchar (96)  ,
	note varchar (255)  
) 
;
CREATE TABLE do_org_role (
	objUid varchar (96)  ,
	name varchar (150)  ,
	roleId varchar (150)  ,
	parentUid int NULL ,
	degree int NULL ,
	description text  ,
	creator varchar (255)  ,
	creatDate timestamp NULL ,
	modifier varchar (255)  ,
	modifyDate timestamp NULL ,
	mVersion varchar (150)  
) 
;
CREATE TABLE do_org_role_conflict (
	objUid varchar (96)  ,
	roleUid varchar (96)  ,
	roleConflictUid varchar (96)  ,
	staticType varchar (3)  ,
	creator varchar (255)  ,
	creatDate timestamp NULL ,
	modifier varchar (255)  ,
	modifyDate timestamp NULL ,
	mVersion varchar (150)  
) 
;
CREATE TABLE do_org_timespan (
	objUid varchar (150)  ,
	name varchar (150)  ,
	beginTime timestamp NULL ,
	endTime timestamp NULL ,
	creator varchar (255)  ,
	creatDate timestamp NULL ,
	modifier varchar (255)  ,
	modifyDate timestamp NULL ,
	mVersion varchar (150)  
) 
;
CREATE TABLE do_org_user (
	objuid varchar (96)  ,
	name varchar (150)  ,
	user_code varchar (255)  ,
	password varchar (150)  ,
	deptuid varchar (255)  ,
	gender varchar (3)  ,
	birthday timestamp NULL ,
	email varchar (150)  ,
	telephone varchar (150)  ,
	mobile varchar (150)  ,
	address varchar (150)  ,
	creator varchar (255)  ,
	modifier varchar (255)  ,
	modifyDate timestamp NULL ,
	mVersion varchar (150)  
) 
;
CREATE TABLE do_org_user_role (
	OBJUID varchar (96)  ,
	USER_UID varchar (96)  ,
	ROLE_UID varchar (150)  
) 
;
CREATE TABLE do_wfi_his_ni_dependency (
	objuid varchar (96)  ,
	Pre_NID_UID varchar (96)  ,
	Post_NID_UID varchar (96)  ,
	do_condition varchar (255)  
) 
;
CREATE TABLE do_org_user_delegate (
  objuid varchar(32) ,
  user_uid varchar(50) ,
  delegate_uid varchar(50),
  isValid char(1) ,
  startTime datetime ,
  endTime datetime,
  note text,
  creator varchar(255) ,
  modifier varchar(255) ,
  modifyDate datetime,
  mVersion varchar(50)
);
CREATE TABLE do_wfi_his_nodeinstance (
	OBJUID varchar (96)  ,
	NodeName varchar (255)  ,
	NodeDesc varchar (255)  ,
	nodeDate timestamp NULL ,
	NodeType int NULL ,
	authType int NULL ,
	nodeStateShow varchar (255)  ,
	nodeStateShowBack varchar (255)  ,
	spec_Name varchar (255)  ,
	ExeStatus int NULL ,
	DecisionExpression varchar (255)  ,
	PI_UID varchar (150)  ,
	scheduleOUUid varchar (150)  ,
	performerUid varchar (150)  ,
	node_uid varchar (150)  ,
	backType int NULL ,
	pass_txt varchar (255)  ,
	reject_txt varchar (255)  ,
	node_ext1 varchar (255)  ,
	node_ext2 varchar (255)  ,
	retNodeUID varchar (150)  
) 
;
CREATE TABLE do_wfi_his_processinstance (
	OBJUID varchar (96)  ,
	WFI_Name varchar (255)  ,
	PT_Name varchar (255)  ,
	WFI_Desc varchar (255)  ,
	ExeStatus int NULL ,
	PT_UID varchar (150)  ,
	startUser varchar (150)  ,
	startTime timestamp NULL ,
	curState varchar (255)  ,
	curStateTime timestamp NULL ,
	curStateUser varchar (150)  ,
	rejectTxt varchar (255)  ,
	instance_uid varchar (150)  ,
	instance2_uid varchar (96)  ,
	instance3_uid varchar (96)  
) 
;
CREATE TABLE do_wfi_his_varinstance (
	objuid varchar (96)  ,
	varName varchar (255)  ,
	varValue varchar (255)  ,
	pt_var_uid varchar (96)  ,
	PI_UID varchar (96)  ,
	bo_property_uid varchar (96)  ,
	instance_uid varchar (255)  
) 
;
CREATE TABLE do_wfi_ni_dependency (
	objuid varchar (96)  ,
	Pre_NID_UID varchar (96)  ,
	Post_NID_UID varchar (96)  ,
	do_condition varchar (255)  
) 
;
CREATE TABLE do_wfi_nodeinstance (
	OBJUID varchar (96)  ,
	NodeName varchar (255)  ,
	NodeDesc varchar (255)  ,
	nodeDate timestamp NULL ,
	NodeType int NULL ,
	authType int NULL ,
	nodeStateShow varchar (255)  ,
	nodeStateShowBack varchar (255)  ,
	spec_Name varchar (255)  ,
	ExeStatus int NULL ,
	DecisionExpression varchar (255)  ,
	PI_UID varchar (150)  ,
	scheduleOUUid varchar (150)  ,
	performerUid varchar (150)  ,
	node_uid varchar (150)  ,
	backType int NULL ,
	pass_txt varchar (255)  ,
	reject_txt varchar (255)  ,
	node_ext1 varchar (255)  ,
	node_ext2 varchar (255)  ,
	retNodeUID varchar (150)  
) 
;
CREATE TABLE do_wfi_processinstance (
	OBJUID varchar (96)  ,
	WFI_Name varchar (255)  ,
	PT_Name varchar (255)  ,
	WFI_Desc varchar (255)  ,
	ExeStatus int NULL ,
	PT_UID varchar (150)  ,
	startUser varchar (150)  ,
	startTime timestamp NULL ,
	curState varchar (255)  ,
	curStateTime timestamp NULL ,
	curStateUser varchar (150)  ,
	rejectTxt varchar (255)  ,
	instance_uid varchar (150)  ,
	instance2_uid varchar (96)  ,
	instance3_uid varchar (96)  
) 
;
CREATE TABLE do_wfi_varinstance (
	objuid varchar (96)  ,
	varName varchar (255)  ,
	varValue varchar (255)  ,
	pt_var_uid varchar (96)  ,
	PI_UID varchar (96)  ,
	bo_property_uid varchar (96)  ,
	instance_uid varchar (255)  
) 
;
CREATE TABLE t_expense (
	id varchar (96)  ,
	title varchar (255)  ,
	expense_man varchar (96)  ,
	expense_date timestamp NULL ,
	expense_money float NULL ,
	note text
) 
;

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



INSERT INTO do_org_dept VALUES ('4028803127b6f15a0127b7294a7a0004', 'pingtaibumen', '平台部门', '40288031278ed91501278ed915b30000', '2', null, null, null, null, null);


INSERT INTO do_org_role VALUES ('4028803127b6f15a0127b725c6930003', '普通员工', 'putongyuangong', null, '1', '普通员工', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fdb80af0001', '部门经理', 'bumenjingli', null, null, '部门经理', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fdc23670002', '副总经理', 'fuzongjingli', null, null, '副总经理', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fdc70100003', '财务主管', 'caiwuzhuguan', null, null, null, null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031287fd27f01287fe80c5a0009', '总经理', 'zongjingli', null, null, '总经理', null, null, null, null, null);
INSERT INTO do_org_role VALUES ('40288031288a2b8501288a3d009d000d', '系统管理员', 'system_manager', null, null, null, null, null, null, null, null);


INSERT INTO do_org_user VALUES ('40288031278ed91501278ed915b30000', 'u', '0005', 'b59c67bf196a4758191e42f76670ceba', '4028803127b6f15a0127b7294a7a0004', null, null, 'u@u.com', null, null, null, null, null, null, null);
INSERT INTO do_org_user VALUES ('40288031287fd27f012880415d96006c', 'xz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO do_org_user VALUES ('40288031287fd27f01288045ecd3006e', 'xw', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO do_org_user VALUES ('40288031287fd27f012880469c2d006f', 'hz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO do_org_user VALUES ('40288031287fd27f012880472b4e0070', 'wz', null, 'c4ca4238a0b923820dcc509a6f75849b', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);
INSERT INTO do_org_user VALUES ('40288031288a2b8501288a3704e00005', 'lz', 'lz', 'd41d8cd98f00b204e9800998ecf8427e', '4028803127b6f15a0127b7294a7a0004', null, null, null, null, null, null, null, null, null, null);


INSERT INTO do_org_user_role VALUES ('40288031287fd27f01288047ad6e0071', '40288031287fd27f01288045ecd3006e', '4028803127b6f15a0127b725c6930003');
INSERT INTO do_org_user_role VALUES ('40288031287fd27f01288047d1cf0072', '40288031287fd27f012880469c2d006f', '40288031287fd27f01287fe80c5a0009');
INSERT INTO do_org_user_role VALUES ('40288031287fd27f01288047f3de0073', '40288031287fd27f012880472b4e0070', '40288031287fd27f01287fdc23670002');
INSERT INTO do_org_user_role VALUES ('40288031287fd27f0128804811f50074', '40288031287fd27f012880415d96006c', '40288031287fd27f01287fdc70100003');
INSERT INTO do_org_user_role VALUES ('40288031288a2b8501288a3d2a1e000e', '40288031278ed91501278ed915b30000', '40288031288a2b8501288a3d009d000d');