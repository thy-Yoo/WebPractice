### 개인기록용
#### 기능 추가 1 - 검색 기능 구현
두가지 방식으로 검색 기능을 구현해보았다. <br><br>
__1.1 event_category_all.do 의 경우__ <br><br>
기존의 전체 데이터를 출력하던 DAO의 eventAllData() 메소드는 그대로 둔채, <br>
eventAllData_Finder() 메소드를 새로 생성하였다. <br>
부가 기능 없이 전체 데이터를 출력하는 구문은 하나 있어야 할 것 같았다. <br>
```java
public List<EventVO> eventAllData_Finder(String whatFind) //이렇게 servlet을 통해 받아올, 유저가 검색한 단어를 매개변수로 정하고,
{
  List<EventVO> list=new ArrayList<EventVO>();
  try {
    getConnection();
    String sql="SELECT * FROM event_main2 "
      + "WHERE event_poster IS NOT NULL AND event_state = '진행중인 이벤트' AND "
      + "event_title LIKE '%"+whatFind+"%' ORDER BY event_term DESC"; // 해당 단어를 포함하는 데이터들을 가져온다.
    /* ... 생략 ...*/
```
그리고 EventModel Class에서 (Controller역할) 아래와 같은 역할을 수행한다. <br>
```java
@RequestMapping("event/event_category_all.do")
public String event_category_all(HttpServletRequest request,HttpServletResponse response)
{
  EventDAO dao = new EventDAO(); //newInstance형태로 수정해야함.
  List<EventVO> list = dao.eventAllData();	//기본적으로는 모든 데이터를 출력하지만, 
  /*검색기능*/
  String findstr = request.getParameter("eventFindText"); //유저가 검색창을 이용했을때, 입력한 데이터를 받아와서
  System.out.println(findstr); //(데이터가 잘 들어왔는지 한번 확인하고)
  if(findstr!=null) { //검색을 했을 경우,
    list = dao.eventAllData_Finder(findstr); //list를 검색결과 리스트로 바꾸어준다.
  }   
	request.setAttribute("list", list); //그리고 View(jsp)에서 데이터를 출력할 수 있도록 list를 준비해둔다.
	request.setAttribute("main_jsp", "../event/event_category_all.jsp");
	return "../main/main.jsp";
}
```
데이터를 받는 View 부분은 아래와 같다.
```jsp
<form action="event_category_all.do"> <!-- 폼 형식으로 버튼을 변경하였다. 폼을 제출할 시 해당 .do를 실행한다.-->
  <span class="search">
    <input type="text" title="검색어를 입력해 주세요." placeholder="검색어를 입력해 주세요." class="input-text" name="eventFindText">
    <input type="submit" class="btn-search-input" value="">
    <!-- "eventFindText"라는 이름으로 입력한 데이터가 들어간다. servlet에서 해당 이름으로 참조를 해오면 된다.-->
    <!-- 검색어를 입력할 input태그는 text타입, 폼을 제출할 input태그는 submit타입으로 하면 된다. -->
  </span>
</form>
```
위의 방식은 View의 상황에 따라 servlet에서 if문으로 사용할 함수를 선택할 수 있다는 장점이 있고,<br>
대신 기능을 추가할때마다 DAO에 비슷한 메소드를 생성해야 한다는 단점이 있다.<br>
<br><br>
__1.2 event_category_movie(theater).do 의 경우__ <br><br>
