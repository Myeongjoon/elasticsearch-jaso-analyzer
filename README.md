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

나오는 jar파일을 zip파일에 넣고 위의 설치 명령어를 통해서 플러그인을 설치 한다. 이때 path는 "file///파일 위치" 로 설정해주면 된다.

