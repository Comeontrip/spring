#!/bin/sh

echo "first parameter $0"
imageName = $1
echo "stop image $1"  
docker stop $1
docker cp ./target/FirstSpringBoot-0.0.1-SNAPSHOT.jar firstspringboot:/usr/local/firstspringboot/FirstSpringBoot-0.0.1-SNAPSHOT.jar
docker start $1

