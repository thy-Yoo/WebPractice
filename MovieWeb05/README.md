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
매개변수를 추가하여 기존 함수를 변경하였다.<br>
```java
public List<EventVO> eventMovieData(String searchData) //유저가 입력한 데이터를 저장할 용도로 매개변수로 정하고
{
	List<EventVO> list=new ArrayList<EventVO>();
	try
	{
		getConnection();
		String sql;
		if(searchData==null) { //만일 검색데이터가 없을 경우(검색을 하지 않았을 경우)
			sql="SELECT * FROM event_main2 "
			+ "WHERE event_poster IS NOT NULL AND event_state = '진행중인 이벤트' AND event_category = '영화'"; //기본 데이터를 뽑아온다.
	}else { //반면 검색을 했을 경우
		sql="SELECT * FROM event_main2 "
		+ "WHERE event_poster IS NOT NULL AND event_state = '진행중인 이벤트' AND event_category = '영화' "
		+ "AND event_title LIKE '%"+searchData+"%'"; //검색한 글자가 들어가는 데이터를 뽑아온다.
}
```
이렇게 DAO 메소드 안에서 매개변수와 관련해 if문을 만들어 sql구문을 선택해주면 EventModel은<br>
```java
@RequestMapping("event/event_category_movie.do")
public String event_category_movie(HttpServletRequest request,HttpServletResponse response)
{
	String findstr = request.getParameter("eventFindText");
	EventDAO dao = new EventDAO(); 
	List<EventVO> list = dao.eventMovieData(findstr);	//바로 매개변수를 이용하면 된다.
		
	request.setAttribute("list", list);
	request.setAttribute("main_jsp", "../event/event_category_movie.jsp");
	return "../main/main.jsp";
}
```
이렇게 할 경우 DAO 메소드가 짧아지고 Model도 간결해졌다.<br>
하지만 반드시 매개변수를 사용해야한다는 단점이 있다. 그러니까 내말은, 매개변수를 사용하지 않을 생각일 때에<br>
결국은 1번처럼 매개변수가 없는 메소드도 선언해야한다는 것이다. (결국은 똑같다..!) <br>
<br><br>
#### 고찰

이벤트 페이지에 관해 점점 기능을 추가할수록 처음의 구조설정이 비효율적이었나? 라는 생각이 들었다.<br>
처음 작업을 할 때 Servlet이 어려워서, 단순하고 직관적으로 page만 주고받는 형태로 구현하였었다.<br><br>
	all category page는 all.do와 연결되어 all data를 출력, <br>
	movie category page는 movie.do와 연결되어 movie data를 출력, <br>
	theater category page는 theater.do와 연결되어 theater data를 출력하는 형태<br>
즉, 이벤트 페이지 하나가 총 세페이지로 되어 있어서 [전체 / 영화 / 극장] 이 각각의 페이지와 그에 매핑된 컨트롤러가 있는 상태.<br>

지금 드는 생각은, <br>
1. 이벤트페이지(전체,영화,극장)를 하나의 event_main.jsp로 구성한다. <br>
2. 전체 / 영화 / 극장을 선택하는 메뉴를 폼 형식으로 변경하고 <br>
3. 각 메뉴를 클릭할 때 servlet에 selectCategoryData를 전송한다. <br>
4. 검색 기능을 클릭할 때 servlet에 searchData를 전송한다. <br>
5. DAO에서 db data를 뽑아오는 함수에서 selectCategoryData와 searchData를 매개변수로 하여 sql 조건문에서 <br>
```java
sql = "WHERE event_category='"+selectCategoryData+"' AND event_title='"+searchData+"'" //...생략...
```
과 같은 형식으로 구현하면, View도 DAO메소드도 Servlet도 더 간결한 형태로 짤 수 있지 않을까.. 라는 생각을 한다<br>
<br>
현재는 팀프로젝트에서 맡은 페이지(event, viplounge)를 제작하며, 정리와 복습용도로 개인으로 웹을 다시 제작해보고 있는것인데,<br>
시간에 여유가 생기면 다시 처음부터 더 효율적으로 고치고싶다.<br>
어쩌면 과제가 끝난 후 Spring에 들어가면 더 단순한 형태를 배울지도 모르겠다.<br>

__화이팅!!__ <br>
