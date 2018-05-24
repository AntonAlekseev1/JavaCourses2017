-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema BlackBox
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BlackBox
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BlackBox` DEFAULT CHARACTER SET utf8 ;
USE `BlackBox` ;

-- -----------------------------------------------------
-- Table `BlackBox`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL,
  `role` ENUM('ADMIN', 'USER') NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BlackBox`.`Dialogs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`Dialogs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `header` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BlackBox`.`Groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`Groups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `creation_date` DATE NULL,
  `admin` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `admin_idx` (`admin` ASC),
  CONSTRAINT `admin`
    FOREIGN KEY (`admin`)
    REFERENCES `BlackBox`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BlackBox`.`Group_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`Group_users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `group_id` INT NOT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user_id` ASC),
  INDEX `group_idx` (`group_id` ASC),
  CONSTRAINT `user_gr`
    FOREIGN KEY (`user_id`)
    REFERENCES `BlackBox`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `group_gr`
    FOREIGN KEY (`group_id`)
    REFERENCES `BlackBox`.`Groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BlackBox`.`Events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`Events` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `body` VARCHAR(500) NULL,
  `date` DATE NULL,
  `group_id` INT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `group_idx` (`group_id` ASC),
  INDEX `user_idx` (`user_id` ASC),
  CONSTRAINT `group_ev`
    FOREIGN KEY (`group_id`)
    REFERENCES `BlackBox`.`Groups` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_ev`
    FOREIGN KEY (`user_id`)
    REFERENCES `BlackBox`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BlackBox`.`User_frends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`User_frends` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `frend_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user_id` ASC),
  INDEX `frend_idx` (`frend_id` ASC),
  CONSTRAINT `user_fr`
    FOREIGN KEY (`user_id`)
    REFERENCES `BlackBox`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `frend`
    FOREIGN KEY (`frend_id`)
    REFERENCES `BlackBox`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BlackBox`.`Messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`Messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(500) NULL,
  `date` DATE NULL,
  `dialog_id` INT NULL,
  `user_name` VARCHAR(45) NULL,
  `user_last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `dialog_idx` (`dialog_id` ASC),
  CONSTRAINT `dialog_msg`
    FOREIGN KEY (`dialog_id`)
    REFERENCES `BlackBox`.`Dialogs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BlackBox`.`User_dialogs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BlackBox`.`User_dialogs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `dialog_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user_id` ASC),
  INDEX `dialog_idx` (`dialog_id` ASC),
  CONSTRAINT `user_di`
    FOREIGN KEY (`user_id`)
    REFERENCES `BlackBox`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dialog_di`
    FOREIGN KEY (`dialog_id`)
    REFERENCES `BlackBox`.`Dialogs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
