from ts.training_prediction.model.assets import *
from aws_conf.training_prediction.s3_bucket_conf import *

import boto3
import os
import s3

def send_to_s3(file, object_name, bucket_name=BUCKET_NAME, aws_access_key_id=ACCESS_KEY_ID, aws_secret_access_key=ACCESS_SECRET_KEY):
    """
    Configures AWS S3 bucket with passed key parameters.
    """
    s3 = boto3.resource(
    's3',
    aws_access_key_id=aws_access_key_id,
    aws_secret_access_key=aws_secret_access_key,
    config=Config(signature_version='s3v4'))

    s3.Bucket(bucket_name).put_object(Key=object_name, Body=file)
    print(f'Sent file to AWS S3 Bucket: {bucket_name}')

def get_file_from_s3(file_name, object_name, bucket_name=BUCKET_NAME, aws_access_key_id=ACCESS_KEY_ID, aws_secret_access_key=ACCESS_SECRET_KEY, region_name=REGION_NAME):
    """
    Retrieves file from AWS S3 bucket.
    """
    session = boto3.Session(
    aws_access_key_id=aws_access_key_id,
    aws_secret_access_key=aws_secret_access_key)
    
    s3 = session.resource('s3')
    s3_client = boto3.client('s3',
                            aws_access_key_id=aws_access_key_id,
                            aws_secret_access_key=aws_secret_access_key,
                            region_name=region_name
                            )
    
    s3.Bucket(bucket_name).download_file(object_name, file_name)
    print(f'Retrieved {file_name} from AWS S3 Bucket: {bucket_name}')

def get_all_files_from_s3(file_name, bucket_name=BUCKET_NAME, aws_access_key_id=ACCESS_KEY_ID, aws_secret_access_key=ACCESS_SECRET_KEY):
    """
    Retrieves all files from AWS S3 bucket.
    """
    session = boto3.Session(
    aws_access_key_id=aws_access_key_id,
    aws_secret_access_key=aws_secret_access_key)
    
    bucket = s3.Bucket(bucket_name)
    s3_objects = bucket.objects.all()
    
    for s3_obj in s3_objects:
        # Use the below line if the file is directly available int the bucket
        bucket.download_file(s3_obj.key, file_name)
        
        # Use the below lines if there are subdirectories available in S3 bucket
        path, filename = os.path.split(s3_obj.key)
        os.makedirs(path)
        bucket.download_file(s3_obj.key, path + "/" + filename)