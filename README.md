# pacttestingdemo1

./deletePact.sh

./gradlew -m consumer:clean consumer:build consumer:pactPublish

./gradlew -m provider:clean provider:build provider:pactVerify

./gradlew consumer:clean consumer:build consumer:pactPublish

./gradlew provider:clean provider:build provider:pactVerify

http://localhost:80

docker 
dius/pact-broker
postgres