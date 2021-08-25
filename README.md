## 개요

엘라스틱 서치에서 한국어 초성 검색, 한영 변환 기능들을 지원하기 위한 플러그인 입니다.

원본 오픈소스 :  https://github.com/netcrazy/elasticsearch-jaso-analyzer

위의 오픈소스에서 white space가 초성 기능에 적용되지 않는 버그를 수정하였습니다.

## 설치
설치시에는 엘라스틱 서치가 실행되어 있으면 에러가 발생할 수 있다. 

7.8.0 버전

```
bin/elasticsearch-plugin.bat install "http://192.168.105.92/myeongjoon_kim/elasticsearch-jaso-analyzer/uploads/88857e8ca1d56d0e6190901bba0b0e96/jaso-analyzer-plugin-7.8.0-plugin.zip"
```

7.13.3 버전

```
bin/elasticsearch-plugin.bat install "http://192.168.105.92/myeongjoon_kim/elasticsearch-jaso-analyzer/uploads/87ade080d9d3336682b9f9600214b9e3/jaso-analyzer-plugin-7.13.3-plugin.zip"
```




## 삭제 (필요시)
```
bin/elasticsearch-plugin remove jaso-analyzer
```
위의 명령어시에 파일이 없다고 에러가 뜨면 플러그인이 설치가 안되어 있거나, 직접 폴더를 찾아서 삭제해야 한다.


## 빌드
만약 코드를 변경하거나 elastic search등의 버전 변경이 필요하면 아래 명령어를 통해서 재 빌드 한다.

~~~shell
$ gradle build buildPluginZip
~~~

그러면 아래의 두개의 path에 jar와 zip파일이 생성된다.

 - build/distributions
 - build/libs

나오는 jar파일을 zip파일에 넣고 위의 설치 명령어를 통해서 플러그인을 설치 한다. 이때 path는 "file///파일 위치" 로 설정해주면 된다.


## 자소 검색 인덱스 설정하기
아래와 같이 인덱스를 생성 합니다. 
만약 초성 or 한영 기능이 필요 없다면, chosung이나 mistype를 false로 바꾸면 됩니다.
```
{
    "settings": {
        "index": {
            "analysis": {
                "filter": {
                    "char_filter": {
                        "type": "pattern_replace",
                        "pattern": "\\p{Punct}|\\d",
                        "replacement": ""
                    },
                    "suggest_filter": {
                        "type": "edge_ngram",
                        "min_gram": 1,
                        "max_gram": 50,
                        "token_chars": [
                            "letter",
                            "digit"
                        ]
                    }
                },
                "tokenizer": {
                    "jaso_search_tokenizer": {
                        "type": "jaso_tokenizer",
                        "mistype": true,
                        "chosung": false
                    },
                    "jaso_index_tokenizer": {
                        "type": "jaso_tokenizer",
                        "mistype": true,
                        "chosung": true
                    }
                },
                "analyzer": {
                    "suggest_search_analyzer": {
                        "type": "custom",
                        "tokenizer": "jaso_search_tokenizer",
                        "filter": [
                            "char_filter"
                        ]
                    },
                    "suggest_index_analyzer": {
                        "type": "custom",
                        "tokenizer": "jaso_index_tokenizer",
                        "filter": [
                            "suggest_filter",
                            "char_filter"
                        ]
                    }
                }
            }
        }
    }
}
```

mapping은 다음과 같이 입력 합니다.
초성 검색이 필요한 필드에 다음과 같이 입력합니다. 아래의 예시는 msg_body 필드에 초성 검색을 지원 합니다. 

```
{
  "properties": {
    "msg_body": {
      "type": "text",
      "store": true,
      "analyzer": "suggest_index_analyzer",
      "search_analyzer": "suggest_search_analyzer"
    }
  }
}
```
위와 같이 인덱스를 생성하고 데이터를 입력한 후 해당 필드에 초성 검색을 하면 검색이 됩니다.