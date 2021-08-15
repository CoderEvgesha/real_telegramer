#1 /bin/bash
set -e

imageTag=$1
if [ -z "$1" ]
  then
    echo No image tag provided. latest will be used
    imageTag=latest
fi

repositoryName=739411955168.dkr.ecr.us-east-2.amazonaws.com/real_telegramer
imageFullName=$repositoryName:$imageTag

echo Main App STARTING pushing image...

echo Main App get login...
aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 739411955168.dkr.ecr.us-east-2.amazonaws.com

echo Main App push image...
docker push $imageFullName

echo Main App FINISHED image has been pushed