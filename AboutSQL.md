## MYSQL版本 ##
```
CREATE TABLE PM_CustomInfor 
(
	CustomUID varchar (50) ,
	CustomID varchar (50)  ,
	CustomName varchar (200)  ,
	TelePhone varchar (50)  ,
	CustomState varchar (20)  ,
	CityCode varchar (50)  ,
	Address varchar (500)  ,
	ZipCode varchar (10)  ,
	CustomDesc text  ,
	CustNotes text  
) ;

CREATE TABLE PM_ProjectInfor 
(
	ProjectUID varchar (50) ,
	ProjectID varchar (50)  ,
	ProjectName varchar (100)  ,
        CustUID varchar (50) ,
	ProjectState int NULL ,
	PlanBeginTime datetime NULL ,
	PlanFinishTime datetime NULL ,
	ActBeginTime datetime NULL ,
	ActFinishTime datetime NULL ,
	ProjectDesc text  ,
	ProjectNotes text  
) ;

```

## ORACLE版本 ##
```

CREATE TABLE PM_CustomInfor 
(
        CustomUID varchar2 (50) ,
        CustomID varchar2 (50)  ,
        CustomName varchar2 (200)  ,
        TelePhone varchar2 (50)  ,
        CustomState varchar2 (20)  ,
        CityCode varchar2 (50)  ,
        Address varchar2 (500)  ,
        ZipCode varchar2 (10)  ,
        CustomDesc clob  ,
        CustNotes clob  
) ;

CREATE TABLE PM_ProjectInfor 
(
        ProjectUID varchar2 (50) ,
        ProjectID varchar2 (50)  ,
        ProjectName varchar2 (100)  ,
        CustUID varchar2 (50) ,
        ProjectState int NULL ,
        PlanBeginTime date NULL ,
        PlanFinishTime date NULL ,
        ActBeginTime date NULL ,
        ActFinishTime date NULL ,
        ProjectDesc clob  ,
        ProjectNotes clob  
) ;


```


## MS SQLSERVER版本 ##

```
CREATE  procedure MulitiPageSP
@sqlstr text,--(16)--varchar(8000), --查询字符串
@currentpage int, --第N页
@pagesize int --每页行数
as
set nocount on
declare @P1 int, --P1是游标的id
 @rowcount int
exec sp_cursoropen @P1 output,@sqlstr,@scrollopt=1,@ccopt=1,@rowcount=@rowcount output
select ceiling(1.0*@rowcount/@pagesize) as 总页数--,@rowcount as 总行数,@currentpage as 当前页 
set @currentpage=(@currentpage-1)*@pagesize+1
exec sp_cursorfetch @P1,16,@currentpage,@pagesize 
exec sp_cursorclose @P1
set nocount off
GO


CREATE TABLE [dbo].[PM_CustomInfor] (
	[CustomUID] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[CustomID] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[CustomName] [varchar] (200) COLLATE Chinese_PRC_CI_AS NULL ,
	[TelePhone] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[CustomState] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[CityCode] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[Address] [varchar] (500) COLLATE Chinese_PRC_CI_AS NULL ,
	[ZipCode] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[CustomDesc] [text] COLLATE Chinese_PRC_CI_AS NULL ,
	[CustNotes] [text] COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

CREATE TABLE [dbo].[PM_ProjectInfor] (
	[ProjectUID] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[ProjectID] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[ProjectName] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
        [CustUID] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL,
	[ProjectState] [int] NULL ,
	[PlanBeginTime] [datetime] NULL ,
	[PlanFinishTime] [datetime] NULL ,
	[ActBeginTime] [datetime] NULL ,
	[ActFinishTime] [datetime] NULL ,
	[ProjectDesc] [text] COLLATE Chinese_PRC_CI_AS NULL ,
	[ProjectNotes] [text] COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
```