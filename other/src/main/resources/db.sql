CREATE TABLE `jdbc-user` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `group` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `jurisdiction` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `age` int(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;