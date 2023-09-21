#!/usr/bin/env python3
import boto3
import os
from botocore.config import Config


# AWS configuration
config = Config(
    signature_version = 'v4',
    retries = {
        'max_attempts': 10,
        'mode': 'standard'
    }
)

## Create the S3 client
s3 = boto3.client('s3', config = config, endpoint_url = 'http://localstack:4566')
## Create the S3 client
# AWS configuration

logging_bucket = 'logging'

## Create logging bucket
print(f"Creating bucket: {logging_bucket}")
s3.create_bucket(Bucket = logging_bucket)
s3.get_waiter('bucket_exists').wait(Bucket = logging_bucket)
print(f"Created bucket: {logging_bucket}")
## Create logging bucket

