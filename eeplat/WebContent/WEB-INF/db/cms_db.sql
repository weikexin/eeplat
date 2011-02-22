/*
SQLyog Ultimate v8.32 
MySQL - 5.0.67-community-nt : Database - cms_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cms_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `cms_db`;

/*Table structure for table `cms_category` */

DROP TABLE IF EXISTS `cms_category`;

CREATE TABLE `cms_category` (
  `cat_id` varchar(32) collate utf8_bin NOT NULL,
  `cat_name` varchar(20) collate utf8_bin default NULL,
  `cat_description` text collate utf8_bin,
  `cat_parent` varchar(32) collate utf8_bin default NULL,
  PRIMARY KEY  (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cms_category` */

insert  into `cms_category`(`cat_id`,`cat_name`,`cat_description`,`cat_parent`) values ('4028800f2d7926fb012d792a27780004','时事动态','时事动态',NULL),('4028800f2d7926fb012d792bf3310005','通知公告','通知公告',NULL),('4028800f2d7926fb012d792d5fcb0008','军事动态',NULL,NULL),('4028800f2d7926fb012d792d80fc0009','财经信息','财经信息',NULL),('4028800f2d7926fb012d792df988000a','读书频道',NULL,NULL),('4028800f2d8ec3a5012d8ed58feb000d','娱乐新闻','娱乐新闻',NULL);

/*Table structure for table `cms_category_relation` */

DROP TABLE IF EXISTS `cms_category_relation`;

CREATE TABLE `cms_category_relation` (
  `id` varchar(32) collate utf8_bin NOT NULL,
  `cat_id` varchar(32) collate utf8_bin default NULL,
  `posts_id` varchar(32) collate utf8_bin default NULL,
  `order` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cms_category_relation` */

/*Table structure for table `cms_comment` */

DROP TABLE IF EXISTS `cms_comment`;

CREATE TABLE `cms_comment` (
  `comment_id` varchar(32) collate utf8_bin NOT NULL,
  `comment_posts_id` varchar(32) collate utf8_bin default NULL,
  `comment_author` varchar(60) collate utf8_bin default NULL,
  `comment_author_email` text collate utf8_bin,
  `comment_author_ip` varchar(32) collate utf8_bin default NULL,
  `comment_author_date` timestamp NULL default CURRENT_TIMESTAMP,
  `comment_author_content` longtext collate utf8_bin,
  PRIMARY KEY  (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cms_comment` */

insert  into `cms_comment`(`comment_id`,`comment_posts_id`,`comment_author`,`comment_author_email`,`comment_author_ip`,`comment_author_date`,`comment_author_content`) values ('4028800f2d9ea2d8012d9ebab7f60025','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'44444'),('4028800f2d9ebd3b012d9ebd3bc70000','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'eeee'),('4028800f2d9ebd3b012d9ebe9ec80001','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'eererer'),('4028800f2d9ebd3b012d9ebeac160002','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'ererer'),('4028800f2d9ebd3b012d9ebebfed0003','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'erererere'),('4028800f2d9ebd3b012d9ebee5ad0004','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'555'),('4028800f2d9ebd3b012d9ebef8650005','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'55555'),('4028800f2d9ebd3b012d9ebf655b0006','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'5454545'),('4028800f2d9eccb3012d9eccb3f70000','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'r4444'),('4028800f2d9eccb3012d9eccbcc90001','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'44r4r'),('4028800f2d9eccb3012d9eccc61d0002','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'r4r4r'),('4028800f2d9eccb3012d9eccd2750003','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'rr4r'),('4028800f2d9eccb3012d9eccde750004','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'r4r4'),('4028800f2d9eccb3012d9ecce7550005','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'r4r4r4'),('4028800f2d9eccb3012d9eccf83e0006','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'5t5t5t'),('4028800f2da11f59012da11f59b80000','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'hh'),('4028800f2da11f59012da121e9f90001','4028800f2d8f144b012d8f150cf30001','xbwolf',NULL,NULL,NULL,'ffgfgf'),('4028800f2da11f59012da121f7130002','4028800f2d8f144b012d8f150cf30001','xbwolf',NULL,NULL,NULL,'ff'),('4028800f2da13b29012da13b29a00000','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'ppp'),('4028800f2da14f0c012da14fd1dd0003','4028800f2da14f0c012da14f98440002','xbwolf',NULL,NULL,NULL,'rewrwerwerewr'),('4028800f2da14f0c012da1522b200004','4028800f2da14f0c012da14f98440002','xbwolf',NULL,NULL,NULL,'3232323'),('4028800f2da763a2012da7824b2d0002','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'éå¤©å¡'),('4028800f2da763a2012da782bda50003','4028800f2d8f144b012d8f150cf30001','xbwolf',NULL,NULL,NULL,'33'),('4028800f2da763a2012da784881e0004','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'å°åº'),('4028800f2da763a2012da784b0960005','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'33'),('4028800f2da763a2012da7854b3a0006','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'33'),('4028800f2da763a2012da78560270007','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'33'),('4028800f2da763a2012da7856de00008','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'33'),('4028800f2da763a2012da7857ae10009','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'33'),('4028800f2da79501012da79501970000','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'55'),('4028800f2da79501012da7954d550001','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'444'),('4028800f2da79501012da795b7400002','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'444'),('4028800f2da79501012da79601410003','4028800f2d7fb895012d7fd1be880003','xbwolf',NULL,NULL,NULL,'666'),('4028800f2da79501012da797801d0004','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'333'),('4028800f2da79501012da79899f10005','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'rrrrrr'),('4028800f2da79501012da798adfe0006','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'rrrr'),('4028800f2da79501012da798b94d0007','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'4444'),('4028800f2da79501012da798c5cd0008','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'333'),('4028800f2da7a9f1012da7a9f1190000','4028800f2d8f144b012d8f150cf30001','xbwolf',NULL,NULL,NULL,'rrrrrrr'),('4028800f2da7a9f1012da7b10ded0001','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'8888'),('4028800f2da7a9f1012da7c264eb0004','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'666'),('4028800f2da7a9f1012da7c2a37f0005','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'777'),('4028800f2da7a9f1012da7c3438f0006','4028800f2d8f144b012d8f150cf30001','xbwolf',NULL,NULL,NULL,'66666'),('4028800f2da7a9f1012da7c364560007','4028800f2d8f144b012d8f150cf30001','xbwolf',NULL,NULL,NULL,'77777'),('4028800f2da7a9f1012da7c5ec360008','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'33333333'),('4028800f2db7126a012db7126ae50000','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'ffff'),('4028800f2db7126a012db7129d270001','4028800f2d7fb895012d7fbb85370001','xbwolf',NULL,NULL,NULL,'fffff'),('4028800f2db7126a012db712d53e0002','4028800f2da763a2012da7640cb90001','xbwolf',NULL,NULL,NULL,'rrr'),('4028800f2dbb237b012dbb24e7cc0001','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'澶╁ぉ澶╁ぉ澶╁ぉ澶╁ぉ澶╁ぉ澶╁ぉ澶╁ぉ澶╁ぉ'),('4028800f2dbb237b012dbb2504090002','4028800f2d8f144b012d8f182fae0002','xbwolf',NULL,NULL,NULL,'鎸烘尯鎸烘尯鎸�'),('4028800f2dbb237b012dbb2542a90003','4028800f2da763a2012da7640cb90001','xbwolf',NULL,NULL,NULL,'鎸烘尯 ');

/*Table structure for table `cms_options` */

DROP TABLE IF EXISTS `cms_options`;

CREATE TABLE `cms_options` (
  `opt_id` varchar(32) collate utf8_bin NOT NULL,
  `opt_key` varchar(20) collate utf8_bin default NULL,
  `opt_value` text collate utf8_bin,
  PRIMARY KEY  (`opt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cms_options` */

insert  into `cms_options`(`opt_id`,`opt_key`,`opt_value`) values ('4028800f2d8dde29012d8dff3e620003','template_path','exedo/webv3/template/cms/theme/test/'),('4028800f2da89c4b012da8baf94c0007','themes_dir','bbcms');

/*Table structure for table `cms_posts` */

DROP TABLE IF EXISTS `cms_posts`;

CREATE TABLE `cms_posts` (
  `posts_id` varchar(32) collate utf8_bin NOT NULL,
  `posts_author` varchar(32) collate utf8_bin default NULL,
  `posts_content` longtext collate utf8_bin,
  `posts_title` varchar(200) collate utf8_bin default NULL,
  `posts_category` varchar(32) collate utf8_bin default NULL,
  `posts_except` text collate utf8_bin,
  `posts_status` int(2) default NULL,
  `comment_status` int(2) default NULL,
  `posts_modified_date` timestamp NULL default CURRENT_TIMESTAMP,
  `posts_url` text collate utf8_bin,
  `posts_comment_count` int(10) default '0',
  `posts_order_num` int(10) NOT NULL auto_increment,
  PRIMARY KEY  (`posts_id`),
  UNIQUE KEY `posts_order_num` (`posts_order_num`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cms_posts` */

insert  into `cms_posts`(`posts_id`,`posts_author`,`posts_content`,`posts_title`,`posts_category`,`posts_except`,`posts_status`,`comment_status`,`posts_modified_date`,`posts_url`,`posts_comment_count`,`posts_order_num`) values ('4028800f2d7fb895012d7fbb85370001','admin','<p><a href=\"http://i1.sinaimg.cn/cj/china/hgjj/20110113/U1633P31T1D9247184F46DT20110113101347.jpg\"><img src=\"http://i1.sinaimg.cn/cj/china/hgjj/20110113/U1633P31T1D9247184F46DT20110113101347.jpg\" alt=\"\" /></a></p>\n<p>　　近期欧美股市持续走强，令市场风险偏好不断改善。受此影响，国际汇市美元延续回落整理，人民币对美元汇率中间价则大幅走高，再创新高纪录</p>\n<p><strong>&nbsp;&nbsp;&nbsp;&nbsp; 人民币对美元中间价首次突破6.60元</strong></p>\n<p>　　中国外汇交易中心1月13日受权公布人民币对美元、欧元、日元、港币、英镑、林吉特及卢布的市场汇价。</p>\n<p>　　1月13日人民币汇率中间价如下：</p>\n<p>　　100美元659.97人民币</p>\n<p>　　100欧元866.84人民币</p>\n<p>　　100日元7.9438人民币</p>\n<p>　　100港币84.907人民币</p>\n<p>　　100英镑1039.78人民币</p>\n<p>　　100人民币46.295林吉特</p>\n<p>　　100人民币457.24卢布</p>','人民币对美元汇率中间价破6.6 再创汇改以来新高','4028800f2d7926fb012d792bf3310005','新华网上海1月13日电(记者 潘清)来自中国外汇交易中心的最新数据显示，1月13日人民币对美元汇率中间价升破6.6，以6.5997再度创下汇改以来新高。',NULL,NULL,'2010-11-11 00:00:00','http://127.0.0.1:8080/yiyi/exedo/webv3/template/cms/theme/test/single.ftl?page_id=',0,1),('4028800f2d7fb895012d7fd1be880003','admin','<p><img src=\"http://i3.sinaimg.cn/dy/c/2011-01-13/1294919764_KEAnbE.jpg\" alt=\"\" /></p>\n<p>人民网1月13日电  据中国天气网消息，北京连续3、4个月滴雨未现，冬小麦缺水严重，人畜饮水困难突出，在南方诸省遭遇多轮雨雪侵袭时，山东、河南、河北等9省市降水则持续 偏少，旱情迅速发展，气象专家表示，上述地区水汽条件不足是主因，预计未来十天，华北、黄淮地区仍无明显降水过程，旱情将持续。</p>\n<p>　　<strong>旱情直击：山东河南部分地区达特旱等级</strong></p>\n<p>　　山东：全省大部旱情超50年一遇</p>\n<p>　　&ldquo;从去年到现在，4个月没有降一滴雨，水库也见底了，现在想浇地连水都没有。我活了70多岁，这样的干旱经历过三次，但这次是最旱的。&rdquo;山东曲 阜白塔村村民刘兴德接受当地媒体采访时面露愁云，70多岁的老汉经历过三次大旱，但这次最旱。每天去自己的麦地里看一看已是必修课，麦苗一天天在枯萎，刘 老汉只能干着急：&ldquo;要是过年前，能下场雪，这些苗苗还有救，要是下不了雨，再没水浇，可就难说了。&rdquo;</p>\n<p>　　这样的情形在齐鲁大地随处可见。据山东省水利厅介绍，目前除半岛地区北部以外，全省大部旱情已超过50年一遇，先后有5973万亩农田受旱，其 中已浇灌3183万亩，目前仍有2781万亩冬小麦受旱，约占全省小麦播种面积的51%，其中重旱373万亩。临沂、菏泽、枣庄、济宁、潍坊已达严重干旱 等级。</p>\n<p>　　河南：受旱面积1586万亩</p>\n<p>　　从麦播以来，河南省全省就持续干旱少雨，从去年11月份至今，全省各地降水量比常年同期偏少86%，为1961年以来同期最少。对于小麦产量占到全国四分之一的&ldquo;中国粮仓&rdquo;河南来说，这样的形势显然不利。</p>\n<p>　　目前河南省全省受旱面积1586万亩，重旱为167万亩。为确保粮食安全，河南省已累计投入抗旱资金2.1亿元人民币，出动劳动力33万人，累计抗旱浇麦1725万亩次。河南省还出台了首部有关抗旱的规范性法规，规定抗旱工作实行行政首长负责制。</p>\n<p>　　河北：377万人饮水困难</p>\n<p>　　目前河北全省受旱面积已达1615万亩，其中冬小麦受旱面积为515万亩。不仅作物缺水严重，人畜饮水困难也十分突出。据调查，全省目前有377万人、12.95万头(只)大牲畜因旱出现饮水困难。</p>\n<p>　　据河北省防汛抗旱指挥部介绍，河北抗旱问题突出表现在，蓄水不足致使抗旱水资源紧张。全省大中型水库蓄水分布很不均匀，仅仅集中在岗南、黄壁 庄、西大洋等少数大型水库，大部分灌区供水不足甚至无水可供。另外，省内地下水位持续下降，全省有近10万眼机井出水不足或者抽不上水。为此，河北省水利 厅结合冬季农田水利基本建设，增加投入，加强应急水源工程建设。</p>\n<p>　　<strong>-专家释疑：降水偏少缘于水汽条件不足</strong></p>\n<p>　　据国家气候中心1月12日监测，目前，河北、北京、天津、河南、山西、山东、安徽、江苏、湖北等地均出现不同程度的气象干旱，其中，山东、河南等省大部出现中到重旱，河南中部部分地区和山东局地达特旱等级。冬季抗旱形势较为严峻。</p>\n<p>　　在南方连遭雨水侵袭之时，华北、黄淮等地为何却持续干旱少雨？这样的天气是否异常？</p>\n<p>　　中国天气网首席气象专家李小泉解释说，南方近期降水较多缘于从孟加拉湾过来的西南暖湿气流输送了充沛的水汽，而北方两块相对多雨的区域东北和新疆的水汽来源分别为日本海和中亚地区。</p>\n<p>　　&ldquo;华北平原、黄河中下游和淮河流域，两边都挨不着。这些地区在冷空气入侵时受到西北气流控制，比较干燥，水汽条件很差，而南方的西南暖湿气流势力还不足以深入到更北的区域。这样的大气环流形势长期维持，导致华北、黄淮地区干旱少雨。&rdquo;李小泉分析说。</p>\n<p>　　专家表示，华北、黄淮地区是季风气候区，冬季降水少，是正常的气候现象，不过今年相比常年平均来说偏少较多，而且长时间没有降水，这种情况还是比较少见的。</p>\n<p>　　<strong>-未来发展：华北黄淮等地仍无明显降水</strong></p>\n<p>　　中央气象台预计，受冷空气影响，未来十天，南方地区还将迎来两次比较明显的降水过程。</p>\n<p>　　然而，在北方由于缺乏暖湿气流的配合，冷空气给北方大部带来的依旧是大风降温天气。预计未来十天，北方大部天气干冷，气温偏低，华北、黄淮气象干旱区仍无明显降水过程，旱情将持续。</p>\n<p>　　农气专家建议，气象干旱区要加强冬小麦田间管理。冬麦区要及时查看墒情、苗情，针对具体情况进行分类管理。对已浇越冬水的麦田，及时划锄松土， 破除板结，以通气保墒，提高地温；有灌溉条件的地区应趁晴暖午后采取小水细灌或喷灌；没有灌溉条件或天气寒冷不适宜灌溉的地区，可采取镇压、施肥及覆盖等 措施进行保墒抗旱；对于底墒充足、生长正常或偏旺的麦田，要推迟浇水，适时进行中耕或镇压，抑制地上部分过快生长，确保冬小麦形成壮苗、安全越冬。</p>\n<p>　　与此同时，要做好蓄水防旱和农田水利基础设施建设。华北、黄淮及江淮北部等地要采取保墒蓄墒措施，以防御冬、春旱的发生。各地还要做好农田水利基础设施建设，修复、完善、配套水利设施，以增强农业防灾抗灾能力。</p>','我国北方大部降水稀少 九省市遭遇冬旱考验','4028800f2d7926fb012d792d5fcb0008','　　人民网1月13日电 据中国天气网消息，北京连续3、4个月滴雨未现，冬小麦缺水严重，人畜饮水困难突出，在南方诸省遭遇多轮雨雪侵袭时，山东、河南、河北等9省市降水则持续偏少，旱情迅速发展，气象专家表示，上述地区水汽条件不足是主因，预计未来十天，华北、黄淮地区仍无明显降水过程，旱情将持续。',NULL,NULL,'2011-11-11 00:00:00',NULL,0,2),('4028800f2d8f144b012d8f150cf30001','admin','<p>测试文章内容</p>','测试文章标题','4028800f2d7926fb012d792a27780004','测试文章内容',NULL,NULL,'2011-11-11 00:00:00',NULL,0,3),('4028800f2d8f144b012d8f182fae0002','admin','<p>测试文章内容2</p>','测试文章标题2','4028800f2d7926fb012d792bf3310005','测试文章内容2',NULL,NULL,'2011-11-11 00:00:00',NULL,0,4),('4028800f2da128fc012da1295b330001','admin','<p>678678678678768678</p>','6786786','4028800f2d7926fb012d792a27780004','678678678',NULL,NULL,'2011-01-20 10:03:18',NULL,0,5),('4028800f2da14f0c012da14f98440002','admin','<p>234234324</p>','3234','4028800f2d7926fb012d792bf3310005','34343',NULL,NULL,'2011-01-20 10:45:04',NULL,0,6),('4028800f2da763a2012da7640cb90001','admin','<p>老范你好</p>','老范你好','4028800f2d7926fb012d792a27780004','老范你好',NULL,NULL,'2011-01-21 15:05:08',NULL,0,7),('4028800f2dbbd620012dbbd92ed80002',NULL,'<p>主题测试主题测试主题测试主题测试主题测试主题测试主题测试主题测试主题测试主题测试主题测试</p>\n<p>&nbsp;</p>\n<p>主题测试主题测试主题测试主题测试主题测试主题测试v主题测试主题测试</p>\n<p>&nbsp;</p>\n<p>主题测试主题测试主题测试</p>','主题测试','4028800f2d7926fb012d792d80fc0009','所的发生法',NULL,NULL,'2011-01-25 14:25:28',NULL,0,8);

/*Table structure for table `cms_user` */

DROP TABLE IF EXISTS `cms_user`;

CREATE TABLE `cms_user` (
  `user_id` varchar(32) collate utf8_bin NOT NULL,
  `user_login_id` varchar(32) collate utf8_bin default NULL,
  `user_password` varchar(32) collate utf8_bin default NULL,
  `user_nicename` text collate utf8_bin,
  `user_email` text collate utf8_bin,
  `user_url` text collate utf8_bin,
  `user_registered_date` datetime default NULL,
  `user_level` int(2) default NULL,
  `user_status` int(2) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cms_user` */

insert  into `cms_user`(`user_id`,`user_login_id`,`user_password`,`user_nicename`,`user_email`,`user_url`,`user_registered_date`,`user_level`,`user_status`) values ('4028800f2d97dbe5012d97de6b030004','wanghongliang','96e79218965eb72c92a549dd5a330112','xbwolf','wanghongliang@zephyr.com.cn',NULL,NULL,NULL,NULL);

/*Table structure for table `cms_usermeta` */

DROP TABLE IF EXISTS `cms_usermeta`;

CREATE TABLE `cms_usermeta` (
  `usermeta_id` varchar(32) collate utf8_bin NOT NULL,
  `user_id` varchar(32) collate utf8_bin default NULL,
  `usermeta_key` varchar(32) collate utf8_bin default NULL,
  `usermeta_value` text collate utf8_bin,
  PRIMARY KEY  (`usermeta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `cms_usermeta` */

/*Table structure for table `do_auth_owner` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_auth_owner` */

/*Table structure for table `do_auth_suite` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_auth_suite` */

/*Table structure for table `do_authorization` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_authorization` */

insert  into `do_authorization`(`objUID`,`parterUid`,`ouUid`,`roleUid`,`whereDOBO`,`whereBOInstanceUid`,`whatType`,`whatUid`,`authority`,`isInherit`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('402880312870acd1012870f1133a0001','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'test_n12','1','1',NULL,NULL,NULL,NULL,NULL),('40288031287fd27f0128801f4bb9000a','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,110,'tt2_n11','1','1',NULL,NULL,NULL,NULL,NULL),('40288031287fd27f0128801f849c000b','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,110,'tt2_n12','1','1',NULL,NULL,NULL,NULL,NULL),('40288031287fd27f01287fdfd2a40005','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'tt2_n6','1','1',NULL,NULL,NULL,NULL,NULL),('402880312870acd10128712bf8ae000e','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,110,'test_n7','1','1',NULL,NULL,NULL,NULL,NULL),('40288031287fd27f0128801fb3f9000c','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,110,'tt2_n13','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d70128852439e90004','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d7012885249bc00006','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d701288524b1d80007','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d70128852572b70008','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d70128852588620009','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'40288031287fd27f0128804a71510076','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d701288525a57f000a','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d701288525bbe6000b','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d701288525d4ec000c','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d70128852615cd000e','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'40288031287fd27f0128804c9b550077','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288512d701288529a9c2000f','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a3652c60001','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a3660920002','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a367a820003','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a3685cd0004','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'402880312880f0bc012880f3b4cc0001','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a383e1c0007','9','4028803127b6f15a0127b725c6930003',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a384a220008','9','40288031287fd27f01287fdb80af0001',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a3858890009','9','40288031287fd27f01287fdc23670002',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a386cbd000a','9','40288031287fd27f01287fdc70100003',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a387960000b','9','40288031287fd27f01287fe80c5a0009',NULL,NULL,NULL,16,'402880312880f0bc012880f413290002','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a3d6812000f','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788b844d10009','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a4cb701288a4ea76a0002','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788b9b77c012c','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a4cb701288a4eb8fe0003','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312788b836012788ba043401ed','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a4cb701288a4ed4e20004','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278f16d001278f43e525003c','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a4cb701288a4ee4c10005','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278904920127890492720000','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a4cb701288a4ef4af0006','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'40288031278a406e01278a6bb9ce005d','1','1',NULL,NULL,NULL,NULL,NULL),('40288031288a4cb701288a4f02d80007','9','40288031288a2b8501288a3d009d000d',NULL,NULL,NULL,16,'402880312793120d01279318032e0260','1','1',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_code_maxsequence` */

DROP TABLE IF EXISTS `do_code_maxsequence`;

CREATE TABLE `do_code_maxsequence` (
  `OBJUID` varchar(32) collate utf8_bin NOT NULL,
  `SEQUENCE_NAME` varchar(255) collate utf8_bin default NULL,
  `CODE_ITEMUID` varchar(32) collate utf8_bin default NULL,
  `PROPERTYUID` varchar(32) collate utf8_bin default NULL,
  `PROPERTYVALUE` varchar(255) collate utf8_bin default NULL,
  `YEARSEQ` decimal(10,0) default NULL,
  `MAX_SEQUENCE` decimal(19,0) default NULL,
  `creator` varchar(255) collate utf8_bin default NULL,
  `creatDate` datetime default NULL,
  `modifier` varchar(255) collate utf8_bin default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) collate utf8_bin default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `do_code_maxsequence` */

/*Table structure for table `do_log` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_log` */

insert  into `do_log`(`objuid`,`userName`,`deptName`,`loginTime`,`logoffTime`,`ip`,`sessionid`) values ('4028800f2d739581012d739581c50000','a',NULL,'2011-01-11 13:38:54',NULL,'127.0.0.1','CAC555816515A8CCB06E474E072243DA'),('4028800f2d739581012d73966cb80001','u',NULL,'2011-01-11 13:39:54',NULL,'127.0.0.1','CAC555816515A8CCB06E474E072243DA'),('4028800f2d73af3c012d73af3cd40000','a',NULL,'2011-01-11 14:07:00',NULL,'127.0.0.1','D75BCA2C30BE78DCF5AB74702B4C308D'),('4028800f2d7926fb012d7926fb8b0000','a',NULL,'2011-01-12 15:35:54','2011-01-12 16:14:17','127.0.0.1','B1390A0A047307962C942B75DC90953B'),('4028800f2d7abb0f012d7abb0feb0000','a',NULL,'2011-01-12 22:57:15',NULL,'127.0.0.1','7339E9ECDCCF1BA8D94533630EBB06E6'),('4028800f2d7d4a36012d7d4a36d30000','a',NULL,'2011-01-13 10:52:51','2011-01-13 12:59:12','127.0.0.1','636538CBF24816077202A028E98B8693'),('4028800f2d7d4a36012d7de140690005','a',NULL,'2011-01-13 13:37:50','2011-01-13 15:35:14','127.0.0.1','204EFFD73888B26F1BF51D83F9F87940'),('4028800f2d7d4a36012d7de1431d0006','a',NULL,'2011-01-13 13:37:50','2011-01-13 15:35:14','127.0.0.1','204EFFD73888B26F1BF51D83F9F87940'),('4028800f2d7d4a36012d7e5a92fc0021','a',NULL,'2011-01-13 15:50:21',NULL,'127.0.0.1','91DC415646A65E05A10AC0C25ECB0A85'),('4028800f2d7fb895012d7fb895b50000','a',NULL,'2011-01-13 22:12:39',NULL,'127.0.0.1','0F14837A1BD301CC43CA246416959DF0'),('4028800f2d826611012d826611fe0000','a',NULL,'2011-01-14 10:41:23',NULL,'127.0.0.1','28DC4925260957D86EC5381254DBC2AE'),('4028800f2d8291c1012d8291c1bd0000','a',NULL,'2011-01-14 11:29:06',NULL,'127.0.0.1','54C5DD445FFE7F7D695AC77987723FF2'),('4028800f2d82a047012d82a0477d0000','a',NULL,'2011-01-14 11:44:58','2011-01-14 12:32:46','127.0.0.1','4C1E7646560B2FB4C2AB812D8DDEC7FA'),('402880e52d8a5ba1012d8a5ba1d10000','a',NULL,'2011-01-15 23:46:57',NULL,'127.0.0.1','23DAF10F453097BB7A16EDBA79A27BCD'),('4028800f2d8dde29012d8dde29330000','a',NULL,'2011-01-16 16:08:23',NULL,'127.0.0.1','36DD6FD545B85133D491BAB3EEE5D616'),('4028800f2d8dde29012d8dfe3b020001','a',NULL,'2011-01-16 16:43:24',NULL,'127.0.0.1','36DD6FD545B85133D491BAB3EEE5D616'),('4028800f2d8dde29012d8dfe51610002','a',NULL,'2011-01-16 16:43:30',NULL,'127.0.0.1','36DD6FD545B85133D491BAB3EEE5D616'),('4028800f2d8e2b77012d8e2b77390000','a',NULL,'2011-01-16 17:32:49','2011-01-16 18:09:31','127.0.0.1','AB5945B1343CCF32E349E0DA6A956A64'),('4028800f2d8e2b77012d8e38f2320003','a',NULL,'2011-01-16 17:47:32','2011-01-16 19:50:31','127.0.0.1','B1EA8BFFE21C5100613C1B29C1FD4D49'),('4028800f2d8e2b77012d8ebec0b60005','a',NULL,'2011-01-16 20:13:42',NULL,'127.0.0.1','41AFAB34A067496CEB8CD6081ECD9607'),('4028800f2d8e2b77012d8ebec0900004','a',NULL,'2011-01-16 20:13:42',NULL,'127.0.0.1','41AFAB34A067496CEB8CD6081ECD9607'),('4028800f2d8ec3a5012d8ec3a5e70000','a',NULL,'2011-01-16 20:19:02',NULL,'127.0.0.1','FFFEDE89409D93EE6238970132979D78'),('4028800f2d8f0362012d8f0362750000','a',NULL,'2011-01-16 21:28:39',NULL,'127.0.0.1','5D6E5C7BFC7E15CBD66DD176974EFA24'),('4028800f2d8f144b012d8f144bf50000','a',NULL,'2011-01-16 21:47:08',NULL,'127.0.0.1','F6698B86C3E80D80B352ECFBDAA9B66F'),('4028800f2d91a711012d91a711290000','a',NULL,'2011-01-17 09:46:41',NULL,'127.0.0.1','E5409B28AAD88A7409A37479937FD0A3'),('4028800f2d91afb9012d91afb96f0000','a',NULL,'2011-01-17 09:56:08',NULL,'127.0.0.1','B389A0A78F23B3406D2D9A721478F9CD'),('4028800f2d91ce15012d91ce15ba0000','a',NULL,'2011-01-17 10:29:18',NULL,'127.0.0.1','A6441DEF67CC26DC9B319E8D3C9ECBF1'),('4028800f2d91d411012d91d4113c0000','a',NULL,'2011-01-17 10:35:50',NULL,'127.0.0.1','7DB043249C145C7234FA60736CCB3649'),('4028800f2d91e4bc012d91e4bcd80000','a',NULL,'2011-01-17 10:54:03',NULL,'127.0.0.1','E88F9178A8DB9F28425C63736DD49688'),('4028800f2d91f13d012d91f13d210000','a',NULL,'2011-01-17 11:07:42',NULL,'127.0.0.1','0245EBB8435E805089D15B7536AD307B'),('4028800f2d9203ba012d9203baeb0000','a',NULL,'2011-01-17 11:27:54','2011-01-17 12:25:37','127.0.0.1','D6FC4903D65D5DDFBAF748B8B12BE973'),('4028800f2d941f98012d941f98dc0000','a',NULL,'2011-01-17 21:17:34',NULL,'127.0.0.1','402773506AD0DAC03A19F04AD5763985'),('4028800f2d941f98012d941fb3f70001','a',NULL,'2011-01-17 21:17:41',NULL,'127.0.0.1','402773506AD0DAC03A19F04AD5763985'),('4028800f2d943da6012d943da6680000','a',NULL,'2011-01-17 21:50:24',NULL,'127.0.0.1','FC21ABB6B91AA9F67E8E6D0FF0E6D9AB'),('4028800f2d9442ef012d9442efec0000','a',NULL,'2011-01-17 21:56:10',NULL,'127.0.0.1','00A46D24187C4C4A7C20700C5280E859'),('4028800f2d97dbe5012d97dbe52b0000','a',NULL,'2011-01-18 14:42:06',NULL,'127.0.0.1','32EC12BFABC9F8C1202919FFA68BA0AF'),('4028800f2d97f0b6012d97f0b6af0000','a',NULL,'2011-01-18 15:04:51',NULL,'127.0.0.1','7936F0E4E8478EEB15D03DB5DDAD15ED'),('4028800f2d97f6eb012d97f6eb070000','a',NULL,'2011-01-18 15:11:37',NULL,'127.0.0.1','7757EC41709678CBF37DB64AE97A3CE0'),('297e65bf2d9c1f31012d9c1f31ea0000','a',NULL,'2011-01-19 10:34:06','2011-01-19 11:22:02','127.0.0.1','C2F3F7710817761852B582109C6CEE5F'),('297e65bf2d9c1f31012d9c1f7b300001','u',NULL,'2011-01-19 10:34:24','2011-01-19 11:22:02','127.0.0.1','C2F3F7710817761852B582109C6CEE5F'),('297e65bf2d9c1f31012d9c30006e0003','a',NULL,'2011-01-19 10:52:27','2011-01-19 11:22:02','127.0.0.1','C2F3F7710817761852B582109C6CEE5F'),('297e65bf2d9c1f31012d9c3091180004','u',NULL,'2011-01-19 10:53:04','2011-01-19 11:22:02','127.0.0.1','C2F3F7710817761852B582109C6CEE5F'),('297e65bf2d9c1f31012d9c4b5113000d','a',NULL,'2011-01-19 11:22:17','2011-01-19 11:27:08','127.0.0.1','690FE20194742E5104C42B0F0CCF6433'),('297e65bf2d9c1f31012d9c4cbcbe000e','u',NULL,'2011-01-19 11:23:50','2011-01-19 11:27:08','127.0.0.1','690FE20194742E5104C42B0F0CCF6433'),('4028800f2d9cfe06012d9cfe062e0000','a',NULL,'2011-01-19 14:37:29',NULL,'127.0.0.1','E904A180C19AC872507A7010CBFEB286'),('4028800f2d9d366c012d9d39c63b0001','a',NULL,'2011-01-19 15:42:45',NULL,'127.0.0.1','E11EB24F0559D668BB67937648D1F6D6'),('4028800f2d9e674d012d9e674d0b0000','a',NULL,'2011-01-19 21:12:06',NULL,'127.0.0.1','208B42718B72122A49CFD1AB72511353'),('4028800f2d9e674d012d9e6cbfdf0001','a',NULL,'2011-01-19 21:18:07',NULL,'127.0.0.1','208B42718B72122A49CFD1AB72511353'),('4028800f2d9e674d012d9e6dacb10002','a',NULL,'2011-01-19 21:19:03',NULL,'127.0.0.1','208B42718B72122A49CFD1AB72511353'),('4028800f2d9e7bfb012d9e7bfbc00000','a',NULL,'2011-01-19 21:34:41',NULL,'127.0.0.1','53D3E34DF6C01641E9A0ADB7D63F75F9'),('4028800f2d9e8003012d9e8003fe0000','a',NULL,'2011-01-19 21:39:05',NULL,'127.0.0.1','BE31521179B7239DB5CC28D778361D43'),('4028800f2d9ea2d8012d9eb6c2b7001f','a',NULL,'2011-01-19 22:38:53',NULL,'127.0.0.1','3C6F67DAB89BC490B7F9E220B7AA28F2'),('4028800f2da128fc012da128fc990000','a',NULL,'2011-01-20 10:02:54',NULL,'127.0.0.1','017E7AFF060651EA673807A6E22F63C1'),('4028800f2da13b29012da13bb7f10001','a',NULL,'2011-01-20 10:23:21',NULL,'127.0.0.1','EEA4985B34AF5C77FBDFC5E458EEA3C3'),('4028800f2da14f0c012da14f0c620000','a',NULL,'2011-01-20 10:44:28','2011-01-20 11:42:10','127.0.0.1','431FB933D782E60C858C5CC0FF1B4428'),('4028800f2da763a2012da763a27f0000','a',NULL,'2011-01-21 15:04:40',NULL,'127.0.0.1','E5902B4683C3834CA54E9E5153A66EA8'),('4028800f2da79e04012da79e04b10000','a',NULL,'2011-01-21 16:08:27',NULL,'127.0.0.1','43DE7461B9E3D369792ED5DA601FFE90'),('4028800f2da7a9f1012da7ba9a9c0003','a',NULL,'2011-01-21 16:39:40',NULL,'127.0.0.1','98A4F7621A46F45D8A8F9BAB1031EECD'),('4028800f2da7f950012da7f950e10000','a',NULL,'2011-01-21 17:48:10',NULL,'127.0.0.1','14FA10439B3D66E33746E515673EB362'),('4028800f2da7f950012da7f966260001','a',NULL,'2011-01-21 17:48:15',NULL,'127.0.0.1','14FA10439B3D66E33746E515673EB362'),('4028800f2da85d8c012da85d8ce90000','a',NULL,'2011-01-21 19:37:39',NULL,'127.0.0.1','90038D92643BF09CB161CA62C5859CDA'),('4028800f2da8807d012da8807d7a0000','a',NULL,'2011-01-21 20:15:49',NULL,'127.0.0.1','33C0E54C647AA8A9896C34605B1D8336'),('4028800f2da8948e012da8948ef90000','a',NULL,'2011-01-21 20:37:44',NULL,'127.0.0.1','9F53F90390E111730FEE9DDA766794A2'),('4028800f2da89c4b012da89c4b300000','a',NULL,'2011-01-21 20:46:11',NULL,'127.0.0.1','3AC1FEA93143A34749587889C981B5A6'),('4028800f2da8c785012da8c785bb0000','a',NULL,'2011-01-21 21:33:24',NULL,'127.0.0.1','BF40BAF5BED5A8F886A1D03E411F08D6'),('4028800f2db607ce012db607ce6a0000','a',NULL,'2011-01-24 11:18:41','2011-01-24 11:57:26','127.0.0.1','F263B85242189253F8467F5D222C73B0'),('4028800f2db607ce012db6301d850001','a',NULL,'2011-01-24 12:02:42','2011-01-24 13:00:26','127.0.0.1','C86902588B479407CFE5243BAB64CBDE'),('4028800f2db607ce012db677d3290002','a',NULL,'2011-01-24 13:21:02','2011-01-24 15:12:27','127.0.0.1','F1ED383716FCBB98510B68F60EA457C0'),('4028800f2db7126a012db72880d40003','a',NULL,'2011-01-24 16:34:01',NULL,'127.0.0.1','C8A7F431F23DA390DC80168572DC7CB2'),('4028800f2db7371a012db7371ac80000','a',NULL,'2011-01-24 16:49:57','2011-01-24 17:34:23','127.0.0.1','8D545700727C623FFBA502A43DA27BDD'),('4028800f2dbb21ef012dbb21ef760000','a',NULL,'2011-01-25 11:05:19',NULL,'127.0.0.1','23B1DF97E73231ECBF77A956FC9DC4F4'),('4028800f2dbb237b012dbb237b9d0000','a',NULL,'2011-01-25 11:07:00','2011-01-25 11:38:52','127.0.0.1','54E6BBD37C221CD2321B84E756829915'),('4028800f2dbb237b012dbb481ab60004','a',NULL,'2011-01-25 11:47:00','2011-01-25 12:18:52','127.0.0.1','B3DE7FE99C9438752E033BE2CB83C446'),('4028800f2dbb237b012dbbb654c50005','a',NULL,'2011-01-25 13:47:24',NULL,'127.0.0.1','D3BA4400FF4C6C5DC0980E092875949E'),('4028800f2dbbd620012dbbd620280000','a',NULL,'2011-01-25 14:22:08','2011-01-25 16:21:59','127.0.0.1','8B4CC1771E857C0556CC590B076468C2'),('4028800f2dbbd620012dbbd638510001','a',NULL,'2011-01-25 14:22:14','2011-01-25 16:21:59','127.0.0.1','8B4CC1771E857C0556CC590B076468C2'),('4028800f2dbbd620012dbc49da4d0003','a',NULL,'2011-01-25 16:28:32',NULL,'127.0.0.1','D9033661C10DC40B90F2221DBB1F4F5A'),('4028800f2e2317e2012e2317e2b30000','a',NULL,'2011-02-14 15:34:51','2011-02-14 16:07:10','127.0.0.1','BA2121E884ABDB4E9AD5EF1E1FDC4AF4'),('4028800f2e368da2012e368da2640000','a',NULL,'2011-02-18 10:16:15','2011-02-18 11:03:40','127.0.0.1','FB68C4A3551FCC05E5EACAD6150CD320'),('4028800f2e471787012e471787620000','a',NULL,'2011-02-21 15:20:47',NULL,'127.0.0.1','B3F75013FCA7F81352FA10014029A179'),('4028800f2e474092012e474092320000','a',NULL,'2011-02-21 16:05:37',NULL,'127.0.0.1','646F64E31F39C0B1070B3AC927D4A334'),('4028800f2e4745e2012e4745e22b0000','a',NULL,'2011-02-21 16:11:25',NULL,'127.0.0.1','54E0F75C0A673F30AB203C01167C076B'),('4028800f2e474801012e4748018d0000','a',NULL,'2011-02-21 16:13:44',NULL,'127.0.0.1','DF32D2F0A4688B68B2A63BCD08FC48D4'),('4028800f2e474b63012e474b637e0000','a',NULL,'2011-02-21 16:17:26',NULL,'127.0.0.1','F41FE84B028DB0EFD7C229F7006F5A41');

/*Table structure for table `do_log_data` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_log_data` */

/*Table structure for table `do_org_dept` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_dept` */

insert  into `do_org_dept`(`objUid`,`dept_code`,`name`,`leader`,`type`,`order_num`,`location`,`tel`,`parentUid`,`note`) values ('4028803127b6f15a0127b7294a7a0004','pingtaibumen','平台部门','40288031278ed91501278ed915b30000',2,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_org_role` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_role` */

insert  into `do_org_role`(`objUid`,`name`,`roleId`,`parentUid`,`degree`,`description`,`creator`,`creatDate`,`modifier`,`modifyDate`,`mVersion`) values ('4028803127b6f15a0127b725c6930003','普通员工','putongyuangong',NULL,1,'普通员工',NULL,NULL,NULL,NULL,NULL),('40288031287fd27f01287fdb80af0001','部门经理','bumenjingli',NULL,NULL,'部门经理',NULL,NULL,NULL,NULL,NULL),('40288031287fd27f01287fdc23670002','副总经理','fuzongjingli',NULL,NULL,'副总经理',NULL,NULL,NULL,NULL,NULL),('40288031287fd27f01287fdc70100003','财务主管','caiwuzhuguan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('40288031287fd27f01287fe80c5a0009','总经理','zongjingli',NULL,NULL,'总经理',NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a3d009d000d','系统管理员','system_manager',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_org_role_conflict` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

/*Data for the table `do_org_role_conflict` */

/*Table structure for table `do_org_timespan` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_timespan` */

/*Table structure for table `do_org_user` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

/*Data for the table `do_org_user` */

insert  into `do_org_user`(`objuid`,`name`,`user_code`,`password`,`deptuid`,`gender`,`birthday`,`email`,`telephone`,`mobile`,`address`,`creator`,`modifier`,`modifyDate`,`mVersion`) values ('40288031278ed91501278ed915b30000','u','0005','b59c67bf196a4758191e42f76670ceba','4028803127b6f15a0127b7294a7a0004',NULL,NULL,'u@u.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('40288031287fd27f012880415d96006c','xz',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('40288031287fd27f01288045ecd3006e','xw',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('40288031287fd27f012880469c2d006f','hz',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('40288031287fd27f012880472b4e0070','wz',NULL,'c4ca4238a0b923820dcc509a6f75849b','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('40288031288a2b8501288a3704e00005','lz','lz','d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c2119240002','sdf',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c30d1e20005','ddfd',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c35462e0006','1223',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c38e77c0007','dsfsd',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c39cc2c0008','sdf',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c3b474a0009','dfdfd',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c40dcae000a','wqewqe',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c4469f6000b','23',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c485166000c','sdf',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('297e65bf2d9c1f31012d9c4ce2a5000f','33',NULL,'d41d8cd98f00b204e9800998ecf8427e','4028803127b6f15a0127b7294a7a0004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `do_org_user_delegate` */

DROP TABLE IF EXISTS `do_org_user_delegate`;

CREATE TABLE `do_org_user_delegate` (
  `objuid` varchar(32) collate utf8_bin NOT NULL,
  `user_uid` varchar(50) collate utf8_bin default NULL,
  `delegate_uid` varchar(50) collate utf8_bin default NULL,
  `isValid` char(1) collate utf8_bin default NULL,
  `startTime` datetime default NULL,
  `endTime` datetime default NULL,
  `note` text collate utf8_bin,
  `creator` varchar(255) collate utf8_bin default NULL,
  `modifier` varchar(255) collate utf8_bin default NULL,
  `modifyDate` datetime default NULL,
  `mVersion` varchar(50) collate utf8_bin default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `do_org_user_delegate` */

/*Table structure for table `do_org_user_role` */

DROP TABLE IF EXISTS `do_org_user_role`;

CREATE TABLE `do_org_user_role` (
  `OBJUID` varchar(32) NOT NULL,
  `USER_UID` varchar(32) default NULL,
  `ROLE_UID` varchar(50) default NULL,
  PRIMARY KEY  (`OBJUID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_org_user_role` */

insert  into `do_org_user_role`(`OBJUID`,`USER_UID`,`ROLE_UID`) values ('40288031287fd27f01288047ad6e0071','40288031287fd27f01288045ecd3006e','4028803127b6f15a0127b725c6930003'),('40288031287fd27f01288047d1cf0072','40288031287fd27f012880469c2d006f','40288031287fd27f01287fe80c5a0009'),('40288031287fd27f01288047f3de0073','40288031287fd27f012880472b4e0070','40288031287fd27f01287fdc23670002'),('40288031287fd27f0128804811f50074','40288031287fd27f012880415d96006c','40288031287fd27f01287fdc70100003'),('40288031288a2b8501288a3d2a1e000e','40288031278ed91501278ed915b30000','40288031288a2b8501288a3d009d000d');

/*Table structure for table `do_wfi_his_ni_dependency` */

DROP TABLE IF EXISTS `do_wfi_his_ni_dependency`;

CREATE TABLE `do_wfi_his_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_ni_dependency` */

/*Table structure for table `do_wfi_his_nodeinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_nodeinstance` */

/*Table structure for table `do_wfi_his_processinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_processinstance` */

/*Table structure for table `do_wfi_his_varinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_his_varinstance` */

/*Table structure for table `do_wfi_ni_dependency` */

DROP TABLE IF EXISTS `do_wfi_ni_dependency`;

CREATE TABLE `do_wfi_ni_dependency` (
  `objuid` varchar(32) NOT NULL,
  `Pre_NID_UID` varchar(32) default NULL,
  `Post_NID_UID` varchar(32) default NULL,
  `do_condition` varchar(255) default NULL,
  PRIMARY KEY  (`objuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_ni_dependency` */

/*Table structure for table `do_wfi_nodeinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_nodeinstance` */

/*Table structure for table `do_wfi_processinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_processinstance` */

/*Table structure for table `do_wfi_varinstance` */

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `do_wfi_varinstance` */

/*Table structure for table `t_expense` */

DROP TABLE IF EXISTS `t_expense`;

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

/*Table structure for table `wf_bj` */

DROP TABLE IF EXISTS `wf_bj`;

/*!50001 DROP VIEW IF EXISTS `wf_bj` */;
/*!50001 DROP TABLE IF EXISTS `wf_bj` */;

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

/*!50001 DROP VIEW IF EXISTS `wf_db` */;
/*!50001 DROP TABLE IF EXISTS `wf_db` */;

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

/*!50001 DROP VIEW IF EXISTS `wf_yb` */;
/*!50001 DROP TABLE IF EXISTS `wf_yb` */;

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

/*View structure for view wf_bj */

/*!50001 DROP TABLE IF EXISTS `wf_bj` */;
/*!50001 DROP VIEW IF EXISTS `wf_bj` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_bj` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNiUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_his_nodeinstance` `ni` join `do_wfi_his_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 3)) */;

/*View structure for view wf_db */

/*!50001 DROP TABLE IF EXISTS `wf_db` */;
/*!50001 DROP VIEW IF EXISTS `wf_db` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_db` AS select distinct `wpi`.`curState` AS `curstate`,`ni`.`node_uid` AS `node_uid`,`ni`.`nodeDate` AS `nodeDate`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`pass_txt` AS `pass_txt`,`ni`.`reject_txt` AS `reject_txt`,`ur`.`USER_UID` AS `user_uid`,`wpi`.`WFI_Desc` AS `WFI_Desc`,`wpi`.`startUser` AS `startUser`,`wpi`.`startTime` AS `startTime` from (((`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi`) join `do_org_user_role` `ur`) join `do_authorization` `a`) where ((`wpi`.`OBJUID` = `ni`.`PI_UID`) and (`a`.`parterUid` = _utf8'9') and (`a`.`ouUid` = `ur`.`ROLE_UID`) and (`ni`.`node_uid` = `a`.`whatUid`) and (`ni`.`ExeStatus` = 2) and (`wpi`.`ExeStatus` = 2)) */;

/*View structure for view wf_yb */

/*!50001 DROP TABLE IF EXISTS `wf_yb` */;
/*!50001 DROP VIEW IF EXISTS `wf_yb` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `wf_yb` AS select distinct `wpi`.`WFI_Desc` AS `wfi_desc`,`wpi`.`startUser` AS `startuser`,`wpi`.`startTime` AS `starttime`,`ni`.`OBJUID` AS `contextNIUid`,`wpi`.`OBJUID` AS `contextPIUid`,`wpi`.`curState` AS `curState`,`wpi`.`instance_uid` AS `instance_uid`,`ni`.`performerUid` AS `USER_UID`,`ni`.`nodeDate` AS `nodeDate` from (`do_wfi_nodeinstance` `ni` join `do_wfi_processinstance` `wpi` on((`ni`.`PI_UID` = `wpi`.`OBJUID`))) where ((`ni`.`ExeStatus` = 3) and (`wpi`.`ExeStatus` = 2)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
