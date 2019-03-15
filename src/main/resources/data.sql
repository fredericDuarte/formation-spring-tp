INSERT INTO SITE (ID, NAME, version) VALUES('site1', 'Bigcorp Lyon', 0);


INSERT INTO CAPTOR (DTYPE, ID, NAME, site_id, default_power_in_watt, version,POWER_SOURCE) VALUES('FIXED', 'c1', 'Eolienne', 'site1', 1000000, 0, 'FIXED');
INSERT INTO CAPTOR (DTYPE, ID, NAME, site_id, min_power_in_watt, max_power_in_watt,version, POWER_SOURCE) VALUES('SIMULATED', 'c2', 'Laminoire à chaud', 'site1', 500000,10000000, 0, 'SIMULATED');


INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-1,PARSEDATETIME ('09-08-18 11:00:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), 1000000, 'c1',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-2,PARSEDATETIME ('09-08-18 11:01:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), 1000124, 'c1',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-3,PARSEDATETIME ('09-08-18 11:02:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), 1001234, 'c1',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-4,PARSEDATETIME ('09-08-18 11:03:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), 1001236, 'c1',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-5,PARSEDATETIME ('09-08-18 11:04:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), 1009678, 'c1',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-6,PARSEDATETIME ('09-08-18 11:00:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), -9000000,'c2', 0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-7,PARSEDATETIME ('09-08-18 11:01:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), -900124, 'c2',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-8,PARSEDATETIME ('09-08-18 11:02:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), -901234, 'c2',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-9,PARSEDATETIME ('09-08-18 11:03:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), -901236, 'c2',0);
INSERT INTO MEASURE (ID, INSTANT, VALUE_IN_WATT, CAPTOR_ID, version) VALUES(-10,PARSEDATETIME ('09-08-18 11:04:00','dd-MM-yy hh:mm:ss', 'fr', 'UTC'), -909678,'c2', 0);