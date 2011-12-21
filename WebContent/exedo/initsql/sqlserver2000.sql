 if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[wf_bj]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[wf_bj]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[wf_db]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[wf_db]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[wf_yb]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[wf_yb]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_auth_owner]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_auth_owner]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_auth_suite]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_auth_suite]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_authorization]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_authorization]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_log]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_log]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_log_data]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_log_data]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_org_dept]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_org_dept]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_org_role]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_org_role]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_org_role_conflict]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_org_role_conflict]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_org_timespan]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_org_timespan]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_org_user]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_org_user]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_org_user_role]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_org_user_role]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_his_ni_dependency]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_his_ni_dependency]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_his_nodeinstance]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_his_nodeinstance]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_his_processinstance]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_his_processinstance]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_his_varinstance]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_his_varinstance]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_ni_dependency]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_ni_dependency]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_nodeinstance]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_nodeinstance]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_processinstance]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_processinstance]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[do_wfi_varinstance]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[do_wfi_varinstance]
;
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_expense]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_expense]
;
CREATE TABLE [dbo].[do_auth_owner] (
	[objUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[whereDOBO] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[whereBOInstanceUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[authorUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[parterUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[creator] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[creatDate] [smalldatetime] NULL ,
	[modifier] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [smalldatetime] NULL ,
	[mVersion] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_auth_suite] (
	[objuid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[name] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[l10n] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[suiteMainMenuUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[includeAuthTxt] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[extendConfig] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[includeType] [int] NULL ,
	[includeUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[creator] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[creatDate] [smalldatetime] NULL ,
	[modifier] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [smalldatetime] NULL ,
	[mVersion] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_authorization] (
	[objUID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[parterUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[ouUid] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[roleUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[whereDOBO] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[whereBOInstanceUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[whatType] [int] NULL ,
	[whatUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[authority] [nvarchar] (3) COLLATE Chinese_PRC_CI_AS NULL ,
	[isInherit] [nvarchar] (3) COLLATE Chinese_PRC_CI_AS NULL ,
	[creator] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[creatDate] [smalldatetime] NULL ,
	[modifier] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [smalldatetime] NULL ,
	[mVersion] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_log] (
	[objuid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[userName] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[deptName] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[loginTime] [smalldatetime] NULL ,
	[logoffTime] [smalldatetime] NULL ,
	[ip] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[sessionid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_log_data] (
	[OBJUID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[TABLE_NAME] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[COL_NAME] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[WHO_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[BO_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[COL_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[OPER_TYPE] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[OPER_DATA] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[OPER_TIME] [smalldatetime] NULL ,
	[OPER_DATA_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[OPER_PANE_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[OLD_VALUE] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[NEW_VALUE] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_org_dept] (
	[objUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[dept_code] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[name] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[leader] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[type] [int] NULL ,
	[order_num] [int] NULL ,
	[location] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[tel] [nvarchar] (60) COLLATE Chinese_PRC_CI_AS NULL ,
	[parentUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[note] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_org_role] (
	[objUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[name] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[roleId] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[parentUid] [int] NULL ,
	[degree] [int] NULL ,
	[description] [ntext] COLLATE Chinese_PRC_CI_AS NULL ,
	[creator] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[creatDate] [smalldatetime] NULL ,
	[modifier] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [smalldatetime] NULL ,
	[mVersion] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
;
CREATE TABLE [dbo].[do_org_role_conflict] (
	[objUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[roleUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[roleConflictUid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[staticType] [nvarchar] (3) COLLATE Chinese_PRC_CI_AS NULL ,
	[creator] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[creatDate] [smalldatetime] NULL ,
	[modifier] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [smalldatetime] NULL ,
	[mVersion] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_org_timespan] (
	[objUid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[name] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[beginTime] [smalldatetime] NULL ,
	[endTime] [smalldatetime] NULL ,
	[creator] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[creatDate] [smalldatetime] NULL ,
	[modifier] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [smalldatetime] NULL ,
	[mVersion] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_org_user] (
	[objuid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[name] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[user_code] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[password] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[deptuid] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[gender] [nvarchar] (3) COLLATE Chinese_PRC_CI_AS NULL ,
	[birthday] [smalldatetime] NULL ,
	[email] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[telephone] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[mobile] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[address] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[creator] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifier] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [smalldatetime] NULL ,
	[mVersion] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_org_user_role] (
	[OBJUID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[USER_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[ROLE_UID] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_his_ni_dependency] (
	[objuid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[Pre_NID_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[Post_NID_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[do_condition] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_his_nodeinstance] (
	[OBJUID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[NodeName] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[NodeDesc] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[nodeDate] [smalldatetime] NULL ,
	[NodeType] [int] NULL ,
	[authType] [int] NULL ,
	[nodeStateShow] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[nodeStateShowBack] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[spec_Name] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[ExeStatus] [int] NULL ,
	[DecisionExpression] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[PI_UID] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[scheduleOUUid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[performerUid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[node_uid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[backType] [int] NULL ,
	[pass_txt] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[reject_txt] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[node_ext1] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[node_ext2] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[retNodeUID] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_his_processinstance] (
	[OBJUID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[WFI_Name] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[PT_Name] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[WFI_Desc] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[ExeStatus] [int] NULL ,
	[PT_UID] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[startUser] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[startTime] [smalldatetime] NULL ,
	[curState] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[curStateTime] [smalldatetime] NULL ,
	[curStateUser] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[rejectTxt] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance_uid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance2_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance3_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_his_varinstance] (
	[objuid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[varName] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[varValue] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[pt_var_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[PI_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[bo_property_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance_uid] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_ni_dependency] (
	[objuid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[Pre_NID_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[Post_NID_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[do_condition] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_nodeinstance] (
	[OBJUID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[NodeName] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[NodeDesc] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[nodeDate] [smalldatetime] NULL ,
	[NodeType] [int] NULL ,
	[authType] [int] NULL ,
	[nodeStateShow] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[nodeStateShowBack] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[spec_Name] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[ExeStatus] [int] NULL ,
	[DecisionExpression] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[PI_UID] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[scheduleOUUid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[performerUid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[node_uid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[backType] [int] NULL ,
	[pass_txt] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[reject_txt] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[node_ext1] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[node_ext2] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[retNodeUID] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_processinstance] (
	[OBJUID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[WFI_Name] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[PT_Name] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[WFI_Desc] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[ExeStatus] [int] NULL ,
	[PT_UID] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[startUser] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[startTime] [smalldatetime] NULL ,
	[curState] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[curStateTime] [smalldatetime] NULL ,
	[curStateUser] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[rejectTxt] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance_uid] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance2_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance3_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE [dbo].[do_wfi_varinstance] (
	[objuid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[varName] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[varValue] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[pt_var_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[PI_UID] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[bo_property_uid] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[instance_uid] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE TABLE do_org_user_delegate (
  objuid varchar(32) NOT NULL,
  user_uid varchar(50) DEFAULT NULL,
  delegate_uid varchar(50) DEFAULT NULL,
  isValid char(1) DEFAULT NULL,
  startTime datetime DEFAULT NULL,
  endTime datetime DEFAULT NULL,
  note text,
  creator varchar(255) DEFAULT NULL,
  modifier varchar(255) DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  mVersion varchar(50) DEFAULT NULL,
  PRIMARY KEY (objuid)
);
CREATE TABLE [dbo].[t_expense] (
	[id] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[title] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[expense_man] [nvarchar] (96) COLLATE Chinese_PRC_CI_AS NULL ,
	[expense_date] [smalldatetime] NULL ,
	[expense_money] [float] NULL ,
	[note] [ntext] COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
;
CREATE TABLE [dbo].[DO_CODE_MAXSEQUENCE] (
	[OBJUID] [varchar] (32) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[SEQUENCE_NAME] [varchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[CODE_ITEMUID] [varchar] (32) COLLATE Chinese_PRC_CI_AS NULL ,
	[PROPERTYUID] [varchar] (32) COLLATE Chinese_PRC_CI_AS NULL ,
	[PROPERTYVALUE] [varchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[YEARSEQ] [decimal](10, 0) NULL ,
	[MAX_SEQUENCE] [decimal](19, 0) NULL ,
	[creator] [varchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[creatDate] [datetime] NULL ,
	[modifier] [varchar] (255) COLLATE Chinese_PRC_CI_AS NULL ,
	[modifyDate] [datetime] NULL ,
	[mVersion] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
;
CREATE VIEW dbo.wf_db AS SELECT DISTINCT 
    wfi_desc,startuser,starttime, wpi.curState, ni.node_uid, ni.nodeDate, ni.OBJUID AS contextNIUid, 
    wpi.OBJUID AS contextPIUid, wpi.instance_uid, ni.pass_txt, ni.reject_txt, 
    ur.USER_UID
 FROM dbo.do_authorization a INNER JOIN
    dbo.do_org_user_role ur ON a.ouUid = ur.ROLE_UID INNER JOIN
    dbo.do_wfi_nodeinstance ni INNER JOIN
    dbo.do_wfi_processinstance wpi ON ni.PI_UID = wpi.OBJUID ON 
    a.whatUid = ni.node_uid 
 WHERE (a.parterUid = '9') AND (ni.ExeStatus = 2) AND (wpi.ExeStatus = 2)
;
CREATE VIEW WF_YB AS SELECT DISTINCT
    wfi_desc,startuser,starttime, ni.OBJUID AS contextNIUid,
     wpi.OBJUID AS contextPIUid, wpi.curState, wpi.instance_uid, ni.performeruid as USER_UID,nodeDate
     FROM do_wfi_nodeinstance  ni INNER JOIN
    do_wfi_processinstance wpi ON ni.PI_UID = wpi.OBJUID
 WHERE  (ni.ExeStatus = 3) AND (wpi.ExeStatus = 2);

CREATE VIEW WF_BJ AS  SELECT DISTINCT   
 wfi_desc,startuser,starttime,ni.objuid as contextNiUid,wpi.OBJUID AS contextPIUid, wpi.curState, wpi.instance_uid, ni.performeruid as USER_UID,nodeDate
  FROM  do_wfi_his_nodeinstance ni INNER JOIN    do_wfi_his_processinstance wpi ON ni.PI_UID = wpi.OBJUID
 WHERE (ni.ExeStatus = 3) AND (wpi.ExeStatus = 3);

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








