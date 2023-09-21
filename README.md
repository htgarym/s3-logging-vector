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