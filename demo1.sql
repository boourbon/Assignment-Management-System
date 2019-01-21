/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `demo1`;

/*Table structure for table `assignment` */

DROP TABLE IF EXISTS `assignment`;

CREATE TABLE `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '作业题目',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `course` int(11) DEFAULT NULL COMMENT '所属课程',
  `teacher` int(11) DEFAULT NULL COMMENT '部署作业的教师',
  `descr` text COMMENT '作业描述',
  `timeCreated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `assignment` */

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `title` varchar(128) DEFAULT NULL COMMENT '课程名',
  `teacher` int(11) DEFAULT NULL COMMENT '代课教师',
  `timeCreated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`title`,`teacher`,`timeCreated`) values (1001,'Digital Circuit',51,'2017-06-27 18:21:28'),(1002,'Algorithm Design',52,'2017-06-27 18:21:43'),(1003,'C++ Language',52,'2017-06-27 18:22:11'),(1004,'Calculus',53,'2017-06-27 18:24:38');

/*Table structure for table `exercise` */

DROP TABLE IF EXISTS `exercise`;

CREATE TABLE `exercise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生提交的作业ID',
  `title` varchar(128) DEFAULT NULL COMMENT '与assignment一致',
  `assignment` int(11) DEFAULT NULL COMMENT '题目ID',
  `course` int(11) DEFAULT NULL COMMENT '课程ID',
  `teachear` int(11) DEFAULT NULL COMMENT '教师ID',
  `student` varchar(64) DEFAULT NULL COMMENT '学生ID',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态: 0, 普通; 1, 已经提交;100,已经验收;-1,退回',
  `score` tinyint(4) DEFAULT NULL COMMENT '分数。0-100',
  `timeCreated` datetime DEFAULT NULL COMMENT '创建时间',
  `timeCommit` datetime DEFAULT NULL COMMENT '提交时间',
  `storePath` varchar(256) DEFAULT NULL COMMENT '附件存储位置',
  `files` varchar(512) DEFAULT NULL COMMENT '附件列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exercise` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` varchar(64) NOT NULL COMMENT '学生ID (登录ID)',
  `password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `displayName` varchar(64) DEFAULT NULL COMMENT '显示名称',
  `cellphone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `timeCreated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`password`,`displayName`,`cellphone`,`timeCreated`) values ('20170001','1','Student A','15307777777','2017-06-27 18:25:42'),('20170002','1','Student B','15308888888','2017-06-27 18:25:44'),('20170003','2','Student C','15309999999','2017-06-27 18:25:46');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师ID',
  `username` varchar(64) DEFAULT NULL COMMENT '登录名',
  `password` varchar(64) DEFAULT NULL,
  `displayName` varchar(64) DEFAULT NULL COMMENT '显示名称(汉字名称)',
  `cellphone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `timeCreated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`username`,`password`,`displayName`,`cellphone`,`timeCreated`) values (51,'Bob','1','Mr. Bob','15301111111','2017-06-27 18:22:47'),(52,'Alex','1','Mr.Alex','15302222222','2017-06-27 18:23:11'),(53,'Rose','2','Ms.Ross','15304444444','2017-06-27 18:23:56');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
