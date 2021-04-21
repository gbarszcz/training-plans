ALTER TABLE `TRAINING_CENTER`.`TRAINING_SERIES_RESULTS`
    ADD `TSR_AUDIT_CD` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'Training series result creation date',
    ADD `TSR_AUDIT_CU` INT UNSIGNED COMMENT 'Training series result creation user',
    ADD `TSR_AUDIT_MD` DATETIME COMMENT 'Training series result modification date',
    ADD `TSR_AUDIT_MU` INT UNSIGNED COMMENT 'Training series result modification user',
    ADD `TSR_AUDIT_RD` DATETIME COMMENT 'Training series result removal date',
    ADD `TSR_AUDIT_RU` INT UNSIGNED COMMENT 'Training series result removal user';

ALTER TABLE `TRAINING_CENTER`.`TRAINING_SERIES`
DROP FOREIGN KEY `TSE_EXCERCISE_FK`;
ALTER TABLE `TRAINING_CENTER`.`TRAINING_SERIES`
ADD CONSTRAINT `TSE_EXERCISE_FK`
  FOREIGN KEY (`TSE_EXC_ID`)
  REFERENCES `TRAINING_CENTER`.`EXERCISES` (`EXC_ID`);