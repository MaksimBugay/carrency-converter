# carrency-converter
Zooplus developer challenge

* to build the project without tests
```
./gradlew clean build -x test
OR
./gradlew clean assemble
```

* to run server
```
java -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Xss400k -Xmx256m -Xms256m -jar build/libs/currency-converter-0.0.1-SNAPSHOT.jar

