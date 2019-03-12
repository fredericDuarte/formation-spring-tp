CREATE TABLE SITE(ID VARCHAR(255) PRIMARY KEY, NAME VARCHAR(255) NOT NULL);
CREATE TABLE CAPTOR(ID VARCHAR(255) PRIMARY KEY, NAME VARCHAR(255) NOT NULL, SITE_ID
VARCHAR(255) NOT NULL);
CREATE TABLE SITE_CAPTORS(SITE_ID VARCHAR(255) NOT NULL, CAPTOR_ID VARCHAR(255) NOT
NULL);
ALTER TABLE CAPTOR ADD FOREIGN KEY (SITE_ID) REFERENCES SITE(ID);
ALTER TABLE SITE_CAPTORS ADD FOREIGN KEY (SITE_ID) REFERENCES SITE(ID);
ALTER TABLE SITE_CAPTORS ADD FOREIGN KEY (CAPTOR_ID) REFERENCES CAPTOR(ID);

CREATE TABLE MEASURE( ID BIGINT PRIMARY KEY AUTO_INCREMENT, INSTANT TIMESTAMP WITH TIME ZONE
 NOT NULL, VALUE_IN_WATT BIGINT NOT NULL, CAPTOR_ID VARCHAR(255));
  ALTER TABLE MEASURE ADD FOREIGN KEY (CAPTOR_ID) REFERENCES CAPTOR(ID);