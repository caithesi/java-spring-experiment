CREATE TABLE IF NOT EXISTS `user` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(20),
    `password` varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
