### 개인기록용
#### 기능 추가 1 - 진행중(전체/영화/극장), 지난 이벤트 페이지 나누기.

```sql
-- 각 페이지에 알맞는 데이터 출력을 위해
-- eventAllData(), eventMovieData(), eventTheaterData() 에서 sql문에 조건절을 추가하였다.

"WHERE event_poster IS NOT NULL AND event_state = '진행중인 이벤트' "; //기본 이벤트 페이지이자 진행중인 이벤트[전체]페이지
"WHERE event_poster IS NOT NULL AND event_state = '진행중인 이벤트' AND event_category = '영화'"; // 진행중인 이벤트[영화]페이지
"WHERE event_poster IS NOT NULL AND event_state = '진행중인 이벤트' AND event_category = '극장'"; // 진행중인 이벤트[극장]페이지

-- 지난 이벤트 페이지도 일단 구현해 두었으나, 데이터가 많아서 페이징 처리가 필요하다.
```
<br><br>
#### 기능 추가 2 - 상세페이지 추가, Servlet을 통한 데이터 요청과 출력.
```java
public EventVO eventDetailData(int mno) {
  EventVO vo=new EventVO();
  try{
    getConnection();
    String sql="SELECT * FROM event_main2 WHERE mno=?"; //각 게시물의 mno를 통해 데이터에 접근할 것이기에
    ps=conn.prepareStatement(sql);
    ps.setInt(1, mno); //설정해주지 않으면 처음엔 아무값도 없기에 1로 설정해주었다.
    ResultSet rs=ps.executeQuery();
    rs.next();
    vo.setMno(rs.getInt(1));
    vo.setCategory(rs.getString(2));
    /* ... 생략 ... */
 ```
 
 #### 기능 추가 3 - 관련 페이지 추가 및 css 수정.
 가장 기본적인 사항인데, 각종 중복되는 css속성과 a태그의 기본 속성 때문에 원하는 속성이 입혀지지 않아 고생을 많이 했다. <br>
 div.Abox a.abox 가 있을 경우, <br>
 div.Abox에 color: red 를 지정해 두어도, a에 color:blue 가 걸려있다면, <br>
 div.Abox a.abox내의 텍스트는 a의 스타일을 따라가게 된다. <br>
 처음 작업 할 때 그냥 a{} 에 속성을 주는게 편하다고 느낄 수 있겠지만, css가 방대해지면 문제가 생겼을 때 발견하기가 어렵다.<br>
 최대한 특정 부분에 스타일을 만들때에는 해당 태그에 class를 줘서 그 클래스를 지정하여 스타일을 주도록 하자!! <br><br><br>
 
