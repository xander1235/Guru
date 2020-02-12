CREATE database `guru` ;

CREATE TABLE `guru`.`courses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `guru`.`courses_roles` (
  `id` INT NOT NULL,
  `role_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `guru`.`link_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `link` VARCHAR(500) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `added_by` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `guru`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
