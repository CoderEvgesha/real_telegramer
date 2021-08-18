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

echo Main App STARTING building image...

echo Main App creating jar...
gradle bootJar

echo Main App creating docker image...
docker build -t $imageFullName .

echo Main App FINISHED image has been built