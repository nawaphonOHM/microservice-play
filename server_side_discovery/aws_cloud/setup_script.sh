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
VPC_ID=$(aws ec2 create-vpc --cidr-block 192.168.0.0/16 --output text --query Vpc.VpcId --tag-specifications '[{"ResourceType":"vpc","Tags":[{"Key":"Name","Value":"Test_Vpc_Name"}]}]')

if [[ $? -ne 0 ]]
then
  echo "Create VPC is failed. exit..."
  exit 1
fi


echo "Create VPC is done ID=$VPC_ID"

echo "Creating Subnet...0/2"
SUBNET_ID_1=$(aws ec2 create-subnet --vpc-id "$VPC_ID" --cidr-block 192.168.0.0/24 --availability-zone-id apse1-az1  --tag-specifications '[{"ResourceType":"subnet","Tags":[{"Key":"Name","Value":"Subnet1"}]}]' )

if [[ $? -ne 0 ]]
then
  echo "Create Subnet 1/2. Failed"
  exit 1
fi

SUBNET_ID_2=$(aws ec2 create-subnet --vpc-id "$VPC_ID" --cidr-block 192.168.1.0/24 --availability-zone-id apse1-az2 --tag-specifications '[{"ResourceType":"subnet","Tags":[{"Key":"Name","Value":"Subnet2"}]}]' )

if [[ $? -ne 0 ]]
then
  echo "Create Subnet 2/2. Failed"
  exit 1
fi

echo "Create Subnet are done ID1=$SUBNET_ID_1 and ID2=$SUBNET_ID_2"