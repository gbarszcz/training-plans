CREATE TABLE `TRAINING_CENTER`.`TAGS`
(
    `TAG_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'Tag ID',
    `TAG_AUDIT_CD` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'Tag creation date',
    `TAG_AUDIT_CU` INT UNSIGNED COMMENT 'Tag creation user',
    `TAG_AUDIT_MD` DATETIME COMMENT 'Tag modification date',
    `TAG_AUDIT_MU` INT UNSIGNED COMMENT 'Tag modification user',
    `TAG_AUDIT_RD` DATETIME COMMENT 'Tag removal date',
    `TAG_AUDIT_RU` INT UNSIGNED COMMENT 'Tag removal user',
    `TAG_CODE` VARCHAR(5) NOT NULL COMMENT 'Tag code',
    `TAG_NAME` VARCHAR(50) NOT NULL COMMENT 'Tag name'
);

ALTER TABLE `TRAINING_CENTER`.`TAGS` ADD UNIQUE `UN_TAG_CODE_AUDIT_RD`(`TAG_CODE`, `TAG_AUDIT_RD`);
ALTER TABLE `TRAINING_CENTER`.`TAGS` ADD UNIQUE `UN_TAG_NAME_AUDIT_RD`(`TAG_NAME`, `TAG_AUDIT_RD`);

CREATE TABLE `TRAINING_CENTER`.`EQUIPMENTS` (
    `EQU_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `EQU_AUDIT_CD` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'Equipment creation date',
    `EQU_AUDIT_CU` INT UNSIGNED COMMENT 'Equipment creation user',
    `EQU_AUDIT_MD` DATETIME COMMENT 'Equipment modification date',
    `EQU_AUDIT_MU` INT UNSIGNED COMMENT 'Equipment modification user',
    `EQU_AUDIT_RD` DATETIME COMMENT 'Equipment removal date',
    `EQU_AUDIT_RU` INT UNSIGNED COMMENT 'Equipment removal user',
    `EQU_NAME` VARCHAR(255) NOT NULL COMMENT 'Equipment name',
     UNIQUE KEY `UN_EQU_NAME_RD` (`EQU_ID`, `EQU_AUDIT_RD`)
);

CREATE TABLE `TRAINING_CENTER`.`EXERCISES` (
    `EXC_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `EXC_AUDIT_CD` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'Exercise creation date',
    `EXC_AUDIT_CU` INT UNSIGNED COMMENT 'Exercise creation user',
    `EXC_AUDIT_MD` DATETIME COMMENT 'Exercise modification date',
    `EXC_AUDIT_MU` INT UNSIGNED COMMENT 'Exercise modification user',
    `EXC_AUDIT_RD` DATETIME COMMENT 'Exercise removal date',
    `EXC_AUDIT_RU` INT UNSIGNED COMMENT 'Exercise removal user',
    `EXC_NAME` VARCHAR(50) NOT NULL COMMENT 'Exercise name',
    `EXC_DESCRIPTION` VARCHAR(255) COMMENT 'Exercise description',
    `EXC_DIFFICULTY_LVL` VARCHAR (1) NOT NULL COMMENT 'Difficulty level for exercise',
    `EXC_DEMO` BOOLEAN NOT NULL DEFAULT 0 COMMENT 'Flag informing if the exercise should be viewed by not registered users'
);

ALTER TABLE `TRAINING_CENTER`.`EXERCISES` ADD UNIQUE `UN_EXC_NAME_AUDIT_RD`(`EXC_NAME`, `EXC_AUDIT_RD`);

CREATE TABLE `TRAINING_CENTER`.`EXERCISE_EQUIPMENT` (
  `EXC_ID` INT UNSIGNED NULL,
  `EQU_ID` INT UNSIGNED NULL,
  INDEX `EXC_ID_idx` (`EXC_ID` ASC),
  INDEX `EQU_ID_idx` (`EQU_ID` ASC),
  CONSTRAINT `EXC_EQU_ID`
    FOREIGN KEY (`EXC_ID`)
    REFERENCES `training_center`.`exercises` (`EXC_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EQU_EXC_ID`
    FOREIGN KEY (`EQU_ID`)
    REFERENCES `training_center`.`equipments` (`EQU_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `TRAINING_CENTER`.`EXERCISE_TAG` (
  `EXC_ID` INT UNSIGNED NULL,
  `TAG_ID` INT UNSIGNED NULL,
  INDEX `EXC_ID_idx` (`EXC_ID` ASC),
  INDEX `TAG_ID_idx` (`TAG_ID` ASC),
  CONSTRAINT `EXC_TAG_ID`
    FOREIGN KEY (`EXC_ID`)
    REFERENCES `training_center`.`exercises` (`EXC_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TAG_EXC_ID`
    FOREIGN KEY (`TAG_ID`)
    REFERENCES `training_center`.`tags` (`TAG_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
