# MyRealTripServer

고솝트 합동세미나 마이리얼트립 리디자인 서버 레포

<hr>
</br>


## 😊 역할분담 & 팀원 소개

|담당자|담당 내용|
|:---|:---|
|유수화|RDS 세팅,  tour 관련 api|
|김윤지|프로젝트 세팅, scrap & tourDeail 관련 api|


<hr>
</br>



# 📁 Folder 구조

```jsx
📁 src. 📁 main. 📁 java
|                   |_ 📁 sopt. 📁 org. 📁 MyRealTrip
|                   |                          |_ 📁 common
|                   |                          |_ 📁 controller
|                   |                          |_ 📁 domain
|                   |                          |_ 📁 exception
|                   |                          |_ 📁 infrastructure
|                   |                          |_ 📁 service
|                   |                          |_ MyRealTripApplication.java
|                   |                  
|                   |
|                   |_ 📁 resources
|
|
📁 test
```




<hr>
</br>


## 😉 API 최종명세서

[최종명세서](https://www.notion.so/go-sopt/1cb7782e4f7b4d5fa256c1ee2ebeb6d8?v=2bf2ae0575d54ca69a8573e2ff69044b)

<img width="891" alt="image" src="https://github.com/Runnect/Runnect-Server/assets/65851554/1b4f6659-72c1-469a-a89c-2f51f299a436">


<hr>
</br>

## 💽 Database ERD




<img width="721" alt="image" src="https://github.com/GOSOPT-CDS-TEAM4/MyRealTripServer/assets/65851554/bcd75e15-8804-474a-97d0-f89956b7e57a">

</aside>
<hr>
</br>

## 🌳 Branch

🌱 git branch 전략

`main branch` : 배포 단위 branch

`dev branch` : 주요 개발 branch, main merge 전 거치는 branch

`feat branch`: 각자 개발 branch

- 할 일 issue 등록 후 issue 번호와 isuue 이름으로 branch 생성 후 작업
  - ex) feat/#`issue num`-`isuue name(기능요약)`
- 해당 branch 작업 완료 후 PR 보내기
  - 항상 local에서 충돌 해결 후 → remote에 올리기
  - reviewer에 서로 tag후 code-review
  - comment 전 merge 불가!
  - review반영 후, 본인이 merge.

### branch 구조

```jsx
- main
- dev
- feat
   ├── #1-isuue name1
   └── #2-isuue name2
```

</aside>
<hr>
</br>

## 🧵 Commit Convention

<aside>
📍  git commit message convention

`ex) feat(변경한 파일) : 변경 내용 (/#issue num)`

```plain
- ✨ feat:      새로운 기능 구현
- 🐛 fix:       버그, 오류 해결
- 🧹 chore:     src 또는 test 파일을 수정하지 않는 기타 변경 사항 ( 새로운 파일 생성, 파일 이동, 이름 변경 등 )
- ♻️ refactor:  버그 수정이나 기능 추가가 없는 코드 변경 ( 코드 구조 변경 등의 리팩토링 )
- 💎 style:     코드의 의미에 영향을 미치지 않는 변경 사항 ( 코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없음 )
- 🏗️ build:    빌드 시스템 또는 외부에 영향을 미치는 변경 사항 종속성 ( 라이브러리 추가 등 )
- 📈 perf:      성능을 향상 시키기 위한 코드 변경
- 🧪 test:      테스트 추가 또는 이전 테스트 수정
- 📝 docs:      README나 WIKI 등의 문서 개정
- ⏪️ revert:    이전 커밋을 되돌리는 경우
- 📦 ci:      CI 구성 파일 및 스크립트 변경
- Merge: 다른브렌치를 merge하는 경우
- Init : Initial commit을 하는 경우
```
