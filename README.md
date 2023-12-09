# spring-boot-issues-38719

### Build
JDK21: OpenJDK21/GraalVM-JDK-21

Run a discovery server Consul/Nacos, and change bootstrap.yaml configuration.

```bash
# linux
gradlew build
# windows
.\gradlew.bat build

# file out put in ./.jar-output/** as follow:
Mode                LastWriteTime         Length Name
----                -------------         ------ ----
-a---         2023/12/9     18:27       36518945   alpha-module.jar
-a---         2023/12/9     18:28       35852461   beta-module.jar
```

#### alpha-module
spring-cloud-gateway
providing a rest api: /hello
```bash
# run
java -jar .jar-output/alpha-module.jar

curl 'http://localhost:8082/hello
# return 'hello world'
```

#### beta-module
simple webflux-application
providing a rest api: /beta/hello
```bash
# run
java -jar .jar-output/beta-module.jar

curl 'http://localhost:8081/beta/hello
# return 'from beta'
```

## Problem
access /beta/hello through gateway with discovery will be block.
only occur when `spring.threads.virtual.enabled=true` and running as boot-jar.
```bash
curl 'http://localhost:8082/beta/hello
# should return 'from beta'
```
