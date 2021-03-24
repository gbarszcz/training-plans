CREATE TABLE `TRAINING_CENTER`.`ROLES` (
  `ROL_ID` INT NOT NULL AUTO_INCREMENT COMMENT 'Role ID',
  `ROL_AUDIT_CD` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Role creation date',
  `ROL_AUDIT_CU` INT NULL COMMENT 'Role creation user',
  `ROL_AUDIT_MD` DATETIME NULL COMMENT 'Role modification date',
  `ROL_AUDIT_MU` INT NULL COMMENT 'Role modification user',
  `ROL_AUDIT_RD` DATETIME NULL COMMENT 'Role removal date',
  `ROL_AUDIT_RU` INT NULL COMMENT 'Role removal user',
  `ROL_NAME` VARCHAR(20) NOT NULL COMMENT 'Role name',
  PRIMARY KEY (`ROL_ID`),
  UNIQUE KEY `ROL_NAME_RD_UNIQUE` (`ROL_NAME`, `ROL_AUDIT_RD`)
);

INSERT INTO `TRAINING_CENTER`.`ROLES` (ROL_NAME)
VALUES ('ADMIN');
INSERT INTO `TRAINING_CENTER`.`ROLES` (ROL_NAME)
VALUES ('USER');

CREATE TABLE `TRAINING_CENTER`.`ACCOUNTS` (
  `ACC_ID` int NOT NULL AUTO_INCREMENT COMMENT 'Account ID',
  `ACC_AUDIT_CD` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'Account creation date',
  `ACC_AUDIT_CU` int DEFAULT NULL COMMENT 'Account creation user',
  `ACC_AUDIT_MD` datetime DEFAULT NULL COMMENT 'Account modification date',
  `ACC_AUDIT_MU` int DEFAULT NULL COMMENT 'Account modification user',
  `ACC_AUDIT_RD` datetime DEFAULT NULL COMMENT 'Account removal date',
  `ACC_AUDIT_RU` int DEFAULT NULL COMMENT 'Account removal user',
  `ACC_EMAIL` varchar(255) DEFAULT NULL,
  `ACC_PASSWORD` varchar(255) NOT NULL COMMENT 'Account password',
  `ACC_STATUS` varchar(1) NOT NULL DEFAULT 'I' COMMENT 'Account status',
  PRIMARY KEY (`ACC_ID`),
  UNIQUE KEY `ACC_EMAIL_RD_UNIQUE` (`ACC_EMAIL`, `ACC_AUDIT_RD`)
);
