@echo off

set DEBUG_PORT=18082
set DEBUG_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=*:%DEBUG_PORT%,server=y,suspend=n

java %DEBUG_OPTS% -jar .jar-output\alpha-module.jar