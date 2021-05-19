ALTER TABLE `training_center`.`trainings_history`
ADD COLUMN `THI_TITLE` VARCHAR(45) NULL COMMENT 'Trainings history title';
ALTER TABLE `training_center`.`trainings_history`
ADD COLUMN `THI_DIFFICULTY_LVL` VARCHAR (1) NULL COMMENT 'Difficulty level for training';