#!/bin/bash

### Command For Creating VPC System ###

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
SUBNET_ID_1=$(aws ec2 create-subnet --vpc-id "$VPC_ID" --cidr-block 192.168.0.0/24 --availability-zone-id apse1-az1  \
--output text --query Subnet.SubnetId --tag-specifications '[{"ResourceType":"subnet","Tags":[{"Key":"Name","Value":"Subnet1"}]}]' )

if [[ $? -ne 0 ]]
then
  echo "Create Subnet 1/2. Failed"
  exit 1
fi

echo "Creating Subnet...1/2. Done"

SUBNET_ID_2=$(aws ec2 create-subnet --vpc-id "$VPC_ID" --cidr-block 192.168.1.0/24 --availability-zone-id apse1-az2 \
--output text --query Subnet.SubnetId --tag-specifications '[{"ResourceType":"subnet","Tags":[{"Key":"Name","Value":"Subnet2"}]}]' )

if [[ $? -ne 0 ]]
then
  echo "Create Subnet 2/2. Failed"
  exit 1
fi

echo "Creating Subnet...2/2. Done"

echo "Create Subnet are done ID1=$SUBNET_ID_1 and ID2=$SUBNET_ID_2"

echo "Creating Internet Gateway..."

INTERNET_GATEWAY_ID=$(aws ec2 create-internet-gateway \
--tag-specifications '[{"ResourceType":"internet-gateway","Tags":[{"Key":"Name","Value":"Internet-gateway-1"}]}]' \
--output text --query InternetGateway.InternetGatewayId)

if [[ $? -ne 0 ]]
then
  echo "Create Internet Gateway... Failed"
  exit 1
fi

echo "Create Internet Gateway... Done"

echo "Internet Gateway ID $INTERNET_GATEWAY_ID"

echo "Attaching InternetGateway to VPC ID $VPC_ID"

aws ec2 attach-internet-gateway --internet-gateway-id "$INTERNET_GATEWAY_ID" --vpc-id "$VPC_ID"

if [[ $? -ne 0 ]]
then
  echo "Attaching InternetGateway to VPC ID $VPC_ID Failed"
  exit 1
fi

echo "Attaching InternetGateway to VPC ID $VPC_ID Done"

ROUTE_TABLE_ID=$(aws ec2 describe-route-tables --filters "[{\"Name\":\"vpc-id\",\"Values\":[\"$VPC_ID\"]}]" --output text --query RouteTables[0].RouteTableId)

echo "RouteTableId for VPCID=$VPC_ID is $ROUTE_TABLE_ID"

echo "Adding Route $ROUTE_TABLE_ID to InternetGateway ID $INTERNET_GATEWAY_ID"

aws ec2 create-route --route-table-id "$ROUTE_TABLE_ID" --destination-cidr-block 0.0.0.0/0 --gateway-id "$INTERNET_GATEWAY_ID"

if [[ $? -ne 0 ]]
then
  echo "Adding Route $ROUTE_TABLE_ID to InternetGateway ID $INTERNET_GATEWAY_ID Failed"
  exit 1
fi

echo "Adding Route $ROUTE_TABLE_ID to InternetGateway ID $INTERNET_GATEWAY_ID Done"

### Command For Creating ECS System ###

