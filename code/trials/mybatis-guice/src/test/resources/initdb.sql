CREATE  TABLE IF NOT EXISTS test.PEARSON (
  ID INT NOT NULL AUTO_INCREMENT ,
  FNAME VARCHAR(100) NULL ,
  LNAME VARCHAR(100) NULL ,
  EMAILID VARCHAR(200) NULL ,
  PRIMARY KEY (ID) )
ENGINE = InnoDB;
  
INSERT INTO `test`.`PEARSON` (`FNAME`, `LNAME`, `EMAILID`) VALUES ('Pranoti', 'Patil', 'pranoti.patil@gmail.com');
INSERT INTO `test`.`PEARSON` (`FNAME`, `LNAME`, `EMAILID`) VALUES ('Pandurang', 'Patil', 'pandurang.patil@gmail.com');

