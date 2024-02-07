# 배포 방법

## 1. gpg 키 생성

- [gpg 설치 및 키 생성](https://mangkyu.tistory.com/237)

```bash
# gpg 설치
brew install gpg

# 키 생성 명령어
gpg --gen-key

# 키 확인 명령어
gpg --list-secret-keys --keyid-format LONG

# 공개키 등록
gpg --keyserver keyserver.ubuntu.com --send-keys ABCDEF1234567890

# 만약 키서버를 못 찾으면
# https://stackoverflow.com/questions/67251078/gpg-keyserver-send-failed-no-keyserver-available-when-sending-to-hkp-pool 
gpgconf --kill dirmngr
dirmngr --debug-all --daemon --standard-resolver

# 공개키 등록 후 singing.gpg 파일 생성
gpg --export-secret-keys ABCDEF1234567890 > signing.gpg
```

### ~/.m2/settings.xml
위 위치에 maven central에서 발급받은 토큰과 gpg 키 정보를 등록한다.
- [maven central](https://central.sonatype.com/account)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings>
    <servers>
        <server>
            <id>central</id>
            <username>${central.username}</username>
            <password>${central.token}</password>
        </server>
    </servers>
    <profiles>
        <profile>
            <id>motolies</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <!--  -->
            <properties>
                <gpg.keyname>${gpg.keyname}</gpg.keyname>
                <gpg.passphrase>${gpg.passphrase}</gpg.passphrase>
            </properties>
        </profile>
    </profiles>
</settings>


```

여기까지 설정이 되면 maven central에 배포가 가능하다.

```bash
# maven central에 배포
mvn clean deploy -P motolies
```


## 2. gpg 키 백업 및 복원

- gpg 키를 백업한다.
```bash
# 공개키 내보내기
gpg --list-keys
gpg --export -a "사용자 이메일" > public_key.asc

# 비밀키 내보내기
gpg --list-secret-keys
gpg --export-secret-keys -a "사용자 이메일" > private_key.asc

# 비밀키 암호화
gpg --symmetric --cipher-algo AES256 private_key.asc

```

- gpg 키를 복원한다.
```bash
# gpg 설치
brew install gpg

# 공개키 복원
gpg --import public_key.asc

# 비밀키 복호화
gpg --decrypt private_key.asc.gpg > private_key.asc

# 비밀키 복원
gpg --import private_key.asc
```

- [가져온 키 확인 및 오류 해결](https://rainbow-flavor.tistory.com/11)
```bash
# 키를 import 했다면 아래 명령어를 실행해봅니다.
echo "test" | gpg --clearsign

# 만약 다음과 같은 오류가 발생한다면
gpg: signing failed: Inappropriate ioctl for device
gpg: [stdin]: clear-sign failed: Inappropriate ioctl for device

# .zshrc 파일 혹은 .bashrc 파일에 다음 줄을 추가 후 다시 명령을 실행해줍니다.
export GPG_TTY=$(tty)

# 다시 명령을 실행해봅니다.
# 정상적으로 실행이 되면 PassPhrase를 입력하라고 나올 것입니다.
```

