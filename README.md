# Common

## Register
[문서를 참고 하세요.](https://central.sonatype.org/publish/publish-guide/)

## Deploy
[DEPLOY.md를 참고하세요.](DEPLOY.md)

## Local Debugging
먼저, 로컬에 있는 JAR 파일을 Maven 로컬 저장소에 설치해야 합니다. 
<br>이를 위해 아래의 명령어를 사용할 수 있습니다.
```bash
mvn install:install-file \
-Dfile=~/git/common/build/JAR \
-DgroupId=io.github.motolies \
-DartifactId=common \
-Dversion=0.0.1 \
-Dpackaging=jar
```

인텔리제이에서는 아래와 같이 설정합니다. 
```bash
# 버전 등은 정리가 필요합니다. 
clean package install:install-file -Dfile=./target/common-0.0.2.jar -DgroupId=io.github.motolies -DartifactId=common -Dversion=0.0.3 -Dpackaging=jar
```

그런 다음 아래의 의존성을 추가하여 사용할 수 있습니다.
```xml
<dependency>
    <groupId>io.github.motolies</groupId>
    <artifactId>common</artifactId>
    <version>0.0.1</version>
</dependency>
```