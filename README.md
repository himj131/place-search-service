# 장소 검색 서비스

### 프로젝트 요구사항
- 장소 검색 (최대 10개)
- 검색 키워드 목록 (많이 검색한 순으로 10개)

### 프로젝트 기본 환경
- jdk 17
- springBoot 2.7.1
- DB: redis
- gradle


### 테스트 방법
- 도거 설치 후 레디스 로컬 실행(아래 명령어 실행)
  - `$ docker run -p 6379:6379 redis`
- [해당 http](../place-search-service/src/test/callTest.http) 파일에서 api 호출 테스트 

