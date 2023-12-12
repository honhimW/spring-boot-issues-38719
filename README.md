# spring-boot-issues-38719

**with-discovery-server-branch**

### Build
JDK21: OpenJDK21/GraalVM-JDK-21

```bash
# linux
gradlew :alpha-module:build
# windows
.\gradlew.bat :alpha-module:build

# file out put in ./.jar-output/** as follow:
Mode                LastWriteTime         Length Name
----                -------------         ------ ----
-a---         2023/12/9     18:27       36518945   alpha-module.jar
```

#### alpha-module
spring-cloud-gateway
providing a rest api: /hello
```bash
# run
java -jar .jar-output/alpha-module.jar
# org.example.alpha.RequestRunner.run will throw an exception
```

## Problem
access /beta/hello through gateway with discovery will be block.
only occur when `spring.threads.virtual.enabled=true` and running as boot-jar.
