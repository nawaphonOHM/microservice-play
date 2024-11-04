#!/bin/bash

echo "Verifying required env...."

if ! command -v aws > /dev/null 2>&1
then
  >&2 echo "Please install awscli."
  exit 1
fi

IS_ERROR=0

if [[ -z "$AWS_ACCESS_KEY_ID" ]]
then
  >&2 echo "Please set 'AWS_ACCESS_KEY_ID' at env"
  IS_ERROR=1
fi

if [[ -z "$AWS_SECRET_ACCESS_KEY" ]]
then
  >&2 echo "Please set 'AWS_SECRET_ACCESS_KEY' at env"
  IS_ERROR=1
fi

if [[ -z "$AWS_DEFAULT_REGION" ]]
then
  >&2 echo "Please set 'AWS_DEFAULT_REGION' at env"
  IS_ERROR=1
fi


if [[ "$IS_ERROR" -eq 1 ]]
then
  echo "Verifying required env....FAILED"
  exit 1
fi

echo "Verifying required env....PASSED"

echo "Creating VPC... "
VPC_ID=$(aws ec2 create-vpc --cidr-block 192.168.1.0/16 --output text --query Vpc.VpcId)
echo "Create VPC is done ID=$VPC_ID"