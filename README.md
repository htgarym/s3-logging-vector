# Logging to S3 with vector.dev

Simple example that captures log events that are formatted in the Logstash format.

## To run

In one terminal run
```shell
docker compose up
```

In a second terminal run

```shell
./gradlew run
```

It should start writing logging events to the console at ERROR and WARN levels.  Once the buffer has filled to a 
sufficient degree it will upload the logs to an S3 bucket running in localstack.  To see them you can run
`docker exec -it localstack awslocal s3 ls logging/`
or
`docker exec -it localstack awslocal s3 ls logging/tester/`

## vector configuration
The vector configuration is located in ./support/development/vectordev

## Query the logs
Using DuckDB it's possible to query the json logs using SQL.  You would need to point DuckDB at the localstack instance
once the httpfs plugin is installed.

Then just run `duckdb` and once inside the REPL, execute the following.  (Note: you don't have to install the json plugin,
but I want to be able to do some additional manipulation on the stack_trace column later :) )   

```sql
INSTALL httpfs;
LOAD httpfs;
INSTALL json;
LOAD json;
SET s3_region='us-east-1';
SET s3_endpoint='localhost:4566';
SET s3_url_style='path';
SET s3_use_ssl=false;
SET s3_access_key_id='AKIAIOSFODNN7EXAMPLE';
SET s3_secret_access_key='wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY';

select * from read_ndjson_auto('s3://logging/tester/*.log', filename=true) where level = 'ERROR' and stack_trace <> '';
```
