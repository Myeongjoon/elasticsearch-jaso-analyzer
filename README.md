원본 오픈소스 :  https://github.com/netcrazy/elasticsearch-jaso-analyzer

위의 오픈소스에서 white space가 초성 기능에 적용되지 않는 버그를 수정하였습니다.

###### *설치*
```
elasticsearch-plugin.bat install "http://192.168.105.92/myeongjoon_kim/elasticsearch-jaso-analyzer/uploads/88857e8ca1d56d0e6190901bba0b0e96/jaso-analyzer-plugin-7.8.0-plugin.zip"
```

###### *삭제 (필요시)*
```
bin/elasticsearch-plugin remove jaso-analyzer
```


## 빌드
만약 코드를 변경하거나 elastic search등의 버전 변경이 필요하면 아래 명령어를 통해서 재 빌드 한다.

~~~shell
$ gradle build buildPluginZip
~~~

그러면 아래의 두개의 path에 jar와 zip파일이 생성된다.

 - build/distributions
 - build/libs

나오는 zip파일 통해서 위의 명령어를 실행하고 엘라스틱 서치 플러그인 lib 위치에 jar파일을 집어 넣는다.

