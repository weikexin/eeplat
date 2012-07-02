  DROP TABLE IF EXISTS `c_comment`;

CREATE TABLE `c_comment` (
  `id` char(32) NOT NULL,
  `c_owner` char(32) default NULL,
  `c_owner_name` varchar(255) default NULL,
  `c_content` varchar(2000) default NULL,
  `c_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `subject_uid` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `c_msgbox` */
DROP TABLE IF EXISTS `c_msgbox`;

CREATE TABLE `c_msgbox` (
  `id` char(32) NOT NULL default '',
  `from_user` varchar(32) default NULL,
  `to_user` varchar(32) default NULL,
  `msg_content` varchar(32) default NULL,
  `is_read` varchar(32) default NULL,
  `from_user_name` varchar(255) default NULL,
  `send_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `to_user_name` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `c_subject` */

DROP TABLE IF EXISTS `c_subject`;

CREATE TABLE `c_subject` (
  `id` char(32) NOT NULL default '',
  `msg_owner` varchar(32) default NULL,
  `msg_title` varchar(255) default NULL,
  `msg_content` varchar(1000) default NULL,
  `msg_time` datetime default NULL,
  `parter_uid` varchar(32) default NULL,
  `msg_owner_name` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
