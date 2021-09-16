### 개인기록용
#### 페이지 구조 변경 (jsp, servlet, DAO 메소드 변경)

기존 페이지 구성은 category_all.jsp, category_movie.jsp, category_theater.jsp 세개였다.<br>
같은 레이아웃인데도 jsp가 3개 있고, 비슷한 동작을 하는데도 세번의 작업이 필요했기 때문에 비효율적임을 느끼고<br>
event_main.jsp 라는 하나의 페이지로 통일하였다.<br><br>

각 메뉴에 링크를 달아서, servlet이 해당 메뉴를 클릭하면 요청을 받도록 한다.<br>
```jsp
<li class="subMenuTitle"><a id="subMenu_total" href="event_category_all.do">전체</a></li>
<li class="subMenuTitle"><a id="subMenu_movie" href="event_category_movie.do">영화</a></li>
<li class="subMenuTitle"><a id="subMenu_theater" href="event_category_theater.do">극장</a></li>
```
<br><br>
__- 기본 페이지__ <br>
Controller역할을 하는 EventMainModel의 기본 구조는 아래와 같다.<br>
```java
@Controller
public class EventMainModel {
		/*************************기본 페이지***************************************/	
	   @RequestMapping("event/event_main.do") //1. event_main.do랑 연결되면 이벤트 메인 페이지와 연결한다.
	   public String event_main(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String selectCategoryData = "전체"; //2. 이벤트 메인 페이지는 "전체" 데이터를 출력할 것이기에 카테고리를 "전체"로 저장한다.
		   String thisPage = "event_category_all.do"; //3. 현재의 페이지 주소도 event_category_all.do 임을 저장한다. (검색 기능을 위헤)
		   String searchFind = null; //4. 첫 페이지 요청 시에는 검색을 하지 않으므로 검색값은 null이다.
		   
		   EventMainDAO dao = new EventMainDAO(); 
		   List<EventVO> list = dao.eventMainDataList(selectCategoryData, searchFind);	//"전체"와 "검색한 값"을 전송한다.
		   
		   request.setAttribute("thisPage",thisPage); //jsp에게 thisPage에 해당하는 값을 알려준다.
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../event/event_main.jsp");
		   return "../main/main.jsp";
	   }
```
__- 전체/영화/카테고리 기능__ <br>
그리고 각 카테고리에 대한 기능을 수행할 메소드는 아래와 같다.<br>
```java
@RequestMapping("event/event_category_all.do") //1. View에서 전체 카테고리를 요청하면
	   public String event_categoryIsAll(HttpServletRequest request,HttpServletResponse response)
	   { 
		   String selectCategoryData = "전체"; //2. 선택한 카테고리가 "전체"임을 저장하고,
		   String thisPage = "event_category_all.do"; //3. 검색기능 구현을 위한 페이지 주소도 all.do임을 저장한다.
		   String searchFind = null; //4. 첫 페이지 요청 시 검색값은 null인데,
		   searchFind = request.getParameter("eventFindText"); //5. 만일 View에서 name="eventFindText"인 곳에 데이터가 입력되면 데이터를 받아온다.
		   EventMainDAO dao = new EventMainDAO();
		   List<EventVO> list = dao.eventMainDataList(selectCategoryData, searchFind);	//"전체" 와 "검색한 값"을 사용할 수 있도록 전송하고
		   
		   System.out.println("선택한 카테고리 : "+selectCategoryData); //확인을 위해 썼다.
		   request.setAttribute("thisPage",thisPage); //현재 페이지 값과
		   request.setAttribute("list", list); //View에서 필요한 리스트 값을 전송한다.
		   request.setAttribute("main_jsp", "../event/event_main.jsp");
		   return "../main/main.jsp";
	   }
```
영화, 극장도 위와 완전히 동일한 구조이며, 지난 이벤트 페이지도 검색 기능을 위해 searchFind를 추가하였다.<br><br><br>
그리고, View에서 전송받은 값을 이용하여 데이터를 분류할 수 있도록 DAO의 메소드를 수정하였다.<br><br>
__-DAO 메소드__ <br>
메소드도 갯수가 줄어들었다. <br>
기존에는 eventAllData(모든 리스트 출력), eventAllData_Finder(모든 리스트 중 검색기능 추가), event_MovieData(영화 리스트 출력),<br>
event_MovieData_Finder(영화 리스트 중 검색기능 추가), ... 이런식으로 하나의 기능을 구현할 때마다 메소드를 추가하였었는데,<br>
이번에는 __최대한 하나의 함수로 모든 기능을 이용할 수 있도록__ 바꾸어보았다. <br><br>
```java
//event_main.jsp 에서 이용할 함수이다.
public List<EventVO> eventMainDataList(String whatCategory, String whatFind) //카테고리 값과, 검색한 값을 이용할 것이다.
	   {
		   List<EventVO> list=new ArrayList<EventVO>();
		   try
		   {
			   getConnection();
			   String sql;
			   System.out.println("여기는DAO:"+whatCategory+","+whatFind); //먼저 servlet을 통해 잘 전송 받았는지 확인 후,
			   if(whatFind==null) { //1. 검색 데이터가 없을 때는 아래와 같이 sql문을 사용해 데이터를 선택한다.
				   if(whatCategory=="전체") { //"전체"로 걸러주는 이유는, data table에 "전체"라는 값을 쓰지 않기 때문이다. (영화/극장만 있음)
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' "
						   		+ "ORDER BY event_term DESC";
				   }else {
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' AND "
						   		+ "event_category = '" + whatCategory + "' "
						   		+ "ORDER BY event_term DESC";
				   }
			   }
			   else { //2. 검색 데이터가 있을 때는 아래와 같이 sql문으로 데이터를 선택한다.
				   if(whatCategory=="전체") {
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' AND "
						   		+ "event_title LIKE '%"+whatFind+"%' ORDER BY event_term DESC";
				   }else {
					   sql="SELECT * FROM event_main2 "
						   		+ "WHERE event_poster IS NOT NULL AND "
						   		+ "event_state = '진행중인 이벤트' AND "
						   		+ "event_category = '" + whatCategory + "' AND "
						   		+ "event_title LIKE '%"+whatFind+"%' ORDER BY event_term DESC";
				   }
			   }
			   
			   ps=conn.prepareStatement(sql);	
```
지난 이벤트 페이지 함수에도 searchFind 매개변수를 추가해주었다. sql구문에 서브쿼리가 많으니 그부분만 주의하면 된다.<br>
```java
public List<EventVO> eventLastEventData_Paging(int page, String whatFind){
			
			List<EventVO> list = new ArrayList<EventVO>();
			try {
				getConnection();
				String sql;
				System.out.println("들어온데이터 확인1: "+ whatFind);
				if(whatFind == null) {
					 sql="SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rownum as rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content "
						     +"FROM event_main2 WHERE event_poster IS NOT NULL AND event_state = '지난 이벤트' )) "
						     +"WHERE rnum BETWEEN ? AND ?";
				}else {
					sql="SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content,rownum as rnum "
						     +"FROM (SELECT mno,event_category,event_poster,event_title,event_term,event_state,event_content "
						     +"FROM event_main2 WHERE event_poster IS NOT NULL AND event_state = '지난 이벤트' AND "
						     +"event_title LIKE '%" + whatFind + "%' )) "
						     +"WHERE rnum BETWEEN ? AND ?";
				}
				/*..생략..*/
```
총 페이지 갯수를 구하는 함수도 같은 방식이다. 매개변수 추가해주고, if문으로 sql구문 분류.<br>
<br><br>
## 웹 페이지 결과물
똑같이 "코다"라는 검색어를 입력했을 때,<br>
전체 카테고리 / 극장 카테고리 / 지난 이벤트 페이지에서 각각에 알맞는 정보를 출력한 결과물을 첨부한다.<br>
(데이터 자체가 "코다"이벤트의 경우 전부 영화 카테고리여서 같은 결과물이라 영화 페이지는 첨부하지 않았다.)<br>
__전체 카테고리 페이지에서 "코다"를 검색한 경우__ <br>
<img src = "../imgs/06_allFind.PNG" width="99%"><br>
===
__극장 카테고리 페이지에서 "코다"를 검색한 경우__ <br>
<img src = "../imgs/06_theaterFind.PNG" width="99%"><br>
===
__지난 이벤트 페이지에서 "코다"를 검색한 경우__ <br>
<img src = "../imgs/06_lastFind.PNG" width="99%"><br>
===

__:D__
