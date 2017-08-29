-- DEVELOPMENT ENVIRONMENT
drop user DEV_MREP cascade;
drop user DEV_WREP cascade;

create user DEV_MREP identified by password123;
grant connect, resource to DEV_MREP;

create user DEV_WREP identified by password123;
grant connect, resource to DEV_WREP;

-- TEST ENVIRONMENT
drop user TEST_MREP cascade;
drop user TEST_WREP cascade;

create user TEST_MREP identified by password123;
grant connect, resource to TEST_MREP;

create user TEST_WREP identified by password123;
grant connect, resource to TEST_WREP;


-- PROD ENVIRONMENT
drop user PROD_MREP cascade;
drop user PROD_WREP cascade;

create user PROD_MREP identified by password123;
grant connect, resource to PROD_MREP;

create user PROD_WREP identified by password123;
grant connect, resource to PROD_WREP;