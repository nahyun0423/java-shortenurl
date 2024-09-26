# 📌단축 URL 서비스 개발

### ✅서비스 요구사항
- URL을 단축 URL로 변환해준다.
- 단축된 URL 키는 5글자로 생성되어야 한다.
- 단축된 URL은 척추 케이스로 표현되어야 한다.
- 단축된 URL로 요청하면 원래의 URL로 리다이렉트 되어야 한다.
- 동일한 URL을 여러 번 요청하더라도 항상 새로운 단축 URL이 생성되어야 한다.
- 단축된 URL로만 조회를 할 수 있다.
- 정상 작동하는 것을 확인할 수 있는 테스트 코드가 있어야 한다.
  
----

### ✅개발 요구사항
- 단축 URL 생성 기능
- [ ] Post - 단축할 URL 등록
- [ ] 단축 URL 생성
- [ ] Map으로 저장
  
<br>

  
- 단축 URL 리다이렉팅 기능
- [ ] GET - 단축 URL을 원본 URL로 리다이렉팅
- [ ] 리다이렉팅 횟수 저장
- [ ] 에러 - 존재하지 않는 단축 URL 요청

<br>

- 단축 URL 조회 기능
- [ ] Get - 단축된 URL 요청 시 원본 URL 정보 조회
- [ ] Get - 단축된 URL 전체 조회
- [ ] Get - 단축된 URL 횟수 조회
- [ ] 에러 - 존재하지 않는 단축 URL 요청
 
  <br>

- Test Code

----

### ✅개발 일정 
![image](https://github.com/user-attachments/assets/815e7529-42b7-4761-a6bd-2242d3e169a2)


---- 

### ✅프론트 요구사항
- 단축할 URL 입력 창
- 입력 버튼
- 단축된 URL
- 단축된 URL 조회 창
- 조회 버튼
- 조회된 정보
- 추후 이미지 업로드 예정

-----

### ☑️ PostMan 응답 화면
- 입력
  ![image](https://github.com/user-attachments/assets/9e793b44-025d-4365-b894-60155b6be2bc)


- 리다이렉트
![image](https://github.com/user-attachments/assets/d42b3854-359d-422d-b1a1-74cb32956527)
![image](https://github.com/user-attachments/assets/4403254d-1234-4b3c-9ec4-2742dfd78661)
![image](https://github.com/user-attachments/assets/c730edc6-d2f0-4c7a-a7a1-821a1af5e18c)

- 조회
![image](https://github.com/user-attachments/assets/2dfd9161-7bdb-4c85-8b3d-8a98f13857bd)
