# WebPractice

프로젝트 작업 내용 설명
### DaehanEventCrawl
- 대한극장 홈페이지에서 이벤트 데이터를 크롤링, 개인 오라클 DB에 저장하였다.
- jsp,css를 이용하여 웹페이지를 제작하였다. (메가박스, 대한극장 디자인 참고)
- DB에 저장해둔 데이터를 웹에 출력. 영화/극장별 카테고리로 나누었다.
- GitHub에 업로드하기 위해서 DBCP를 은닉화하여 오라클 연결 정보를 숨겨두었다. <br>

### NaverMovieDataCrawl
- 네이버 영화 사이트에서 크롤링을 하는 기능에 대한 클래스이다.<br>
- 해당 사이트는 일부 영화들이 관람등급 __태그가 빠져있거나, 하나의 태그안에 여러 데이터__ 가 있어서<br>
  이부분을 크롤링 과정에서 해결해야했다. ( 장르|상영시간|개봉일이 상영시간|개봉일 로 되어있는 등의 문제) <br>
  for문과 if문을 이용하여 예외를 가진 컬럼 num을 기준으로 문제를 해결하였는데, 데이터가 바뀌면 의미가 없어지기에 다른 해결법이 필요하다. <br>
  
### MovieWeb
##### 01
- DaehanEventCrawl 프로젝트를 MVC패턴으로 구현하려고 노력하였다.
- 다운받은 Spring.jar 파일을 사용해서, Controller를 직접 구현하지 않았는데, 이에 대한 공부가 필요하다.
- EventDAO에 대한극장 사이트의 이벤트 페이지의 __상세 페이지에서 데이터를 크롤링하는 기능__ 을 추가하였다.<br>
- EventDAO에 이벤트 기간을 (DB에 저장된 term 컬럼을) 현재 날짜와 비교하여, <br>
  __진행중인지 지난 이벤트인지 구분__ 하여 state컬럼에 값을 넣는 기능을 추가하였다.<br>
  이는 나중에 sql문 조건절에서 state를 쓰기 위함이다. <br>
##### 02
- state 데이터를 기준으로 진행중인 이벤트와 지난 이벤트 페이지를 나누었다. <br>
- 이벤트 페이지에서 각 이벤트를 클릭할 경우 __세부페이지로 이동하는 기능__ 을 추가하였다. <br>
- 당첨자 게시판 페이지에서 각 게시물을 클릭할 경우 세부페이지로 이동하는 기능을 추가하였다. <br>
- Servlet을 통해 각 item의 mno를 받고 __세부페이지에서__ mno에 해당하는 vo의 __데이터를 출력하는 기능__ 을 추가하였다. <br>
- CSS가 중복된 경우의 문제점을 발견하고 수정하였다. <br>
- 중복되어 의미없는 CSS속성 정리가 필요하다. 또, 데이터가 많은 지난 이벤트 페이지의 페이징처리가 필요하다. <br>

