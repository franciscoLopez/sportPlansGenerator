INSERT INTO `authorities` (`authority`, `username`) VALUES
	('ROLE_ADMIN', NULL),
	('ROLE_SPORT_CENTRE', NULL),
	('ROLE_SPORT_TRAINER', NULL);

INSERT INTO `groups` (`id`, `group_name`) VALUES
	(1, 'sportCenters'),
	(2, 'admin'),
	(3, 'sportTrainers');

INSERT INTO `group_authorities` (`group_id`, `authority`) VALUES
	(2, 'ROLE_ADMIN'),
	(3, 'ROLE_SPORT_TRAINER'),
	(1, 'ROLE_SPORT_CENTRE');