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
 <br><br>
 요청해야할 데이터의 종류가 다르므로, Servlet을 만들 때도 주의하여야 한다.
 ```java
 /* 일반 목록 페이지의 경우 */
@RequestMapping("event/event_last_event.do") //지난 이벤트 페이지와 연결할 것이기에
public String event_last_event(HttpServletRequest request,HttpServletResponse response) { 
  EventDAO dao = new EventDAO();
  List<EventVO> list = dao.eventLastEventData(); //어떤 함수를 불러올지 결정하고(지난 이벤트 함수)
  request.setAttribute("list", list); //List형으로 받아올것이기에 list형을 받아와서 넣는다.
  request.setAttribute("main_jsp", "../event/event_last_event.jsp"); 
  return "../main/main.jsp";
}
/* 상세 페이지의 경우 */
@RequestMapping("event/event_detail.do") //상세 데이터를 출력할 페이지이기 때문에
public String event_detail(HttpServletRequest request,HttpServletResponse response) {
  String mno = request.getParameter("mno"); //데이터를 구분할 mno값을 받아오고,
	EventDAO dao = new EventDAO();
	EventVO vo = dao.eventDetailData(Integer.parseInt(mno)); //mno에 해당하는 데이터를 VO형태로 받아온다.
	request.setAttribute("vo", vo);
	request.setAttribute("main_jsp", "../event/event_detail.jsp");
	return "../main/main.jsp";
}	   
 ```
 <br><br>
 #### 기능 추가 3 - 관련 페이지 추가 및 css 수정.
 가장 기본적인 사항인데, 각종 중복되는 css속성과 a태그의 기본 속성 때문에 원하는 속성이 입혀지지 않아 고생을 많이 했다. <br>
 div.Abox a.abox 가 있을 경우, <br>
 div.Abox에 color: red 를 지정해 두어도, a에 color:blue 가 걸려있다면, <br>
 div.Abox a.abox내의 텍스트는 a의 스타일을 따라가게 된다. <br>
 처음 작업 할 때 그냥 a{} 에 속성을 주는게 편하다고 느낄 수 있겠지만, css가 방대해지면 문제가 생겼을 때 발견하기가 어렵다.<br>
 스타일을 만들때에는 최대한 원하는 특정 태그에 class를 줘서 그 __클래스를 지정하여 스타일을 주도록 하자!!__ <br><br><br>
 
