-- DEVELOPMENT ENVIRONMENT
 drop user SRC_DEV cascade;
 create user SRC_DEV identified by password123;
 grant all privileges to SRC_DEV;
 drop user TRG_DEV cascade;
 create user TRG_DEV identified by password123;
 grant all privileges to TRG_DEV;

 -- TEST ENVIRONMENT
 drop user SRC_TEST cascade;
 create user SRC_TEST identified by password123;
 grant all privileges to SRC_TEST;
 drop user TRG_TEST cascade;
 create user TRG_TEST identified by password123;
 grant all privileges to TRG_TEST;

 -- PROD ENVIRONMENT
 drop user SRC_PROD cascade;
 create user SRC_PROD identified by password123;
 grant all privileges to SRC_PROD;
 drop user TRG_PROD cascade;
 create user TRG_PROD identified by password123;
 grant all privileges to TRG_PROD;