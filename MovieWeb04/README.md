### 개인기록용
#### 기능 추가 1 - 로그인 기능
session 부분 작성할 것.
#### 기능 추가 2 - 다른 테이블을 참조하여 원하는 테이블에 데이터를 Insert하는 기능
##### 2.1 DAO : 회원DB가 업데이트 되면 유저의 vip혜택 정보를 담는 멤버십 테이블 데이터도 업데이트 시킨다.
해당 메소드는 멤버십페이지(Viplounge)를 방문할 때 업데이트 되도록 해두었다. ...Viplounge모델 참고<br>
```java
 public ViploungeVO memberDataUpdate() {
  ViploungeVO vo = new ViploungeVO();
  try {
    getConnection();
    String sql= "INSERT INTO membership_benefit(userid) SELECT id FROM project_member "
    + "WHERE NOT EXISTS (SELECT userid FROM membership_benefit WHERE membership_benefit.userid = project_member.id)";
    /* 
     * membership_benefit table에 project_member id를 추가한다. (project_member table은 추후 회원가입 시 데이터가 들어갈 테이블임)
     * 단, 기존에 membership_benefit table에 같은 id정보가 존재할 경우, 가져오지 않는다.
     */
    ps=conn.prepareStatement(sql);
    ResultSet rs=ps.executeQuery();
    vo.setUserid(rs.getString(1));
    rs.next();
    rs.close();
  }catch (Exception ex) {
    ex.printStackTrace();
  }finally {
    disConnection();
  }
  return vo;
 }

```
<br><br>
#### 기능 추가 3 - session 데이터를 가져와서 페이지에 출력하는 기능 구현.
##### 3.1 DAO : 받아오는 UserData를 Viplounge페이지에 뿌릴 수 있도록 준비한다.
```java
 public ViploungeVO viploungeUserData(String idstr) { //string형 변수ID를 받아와서 (idstr)
  ViploungeVO vo=new ViploungeVO();
  try {
   getConnection();
   String sql="SELECT * FROM membership_benefit WHERE userid=?";//받아온ID와 table의 userid가 같은 행의 데이터를 뽑아온다.	
   ps=conn.prepareStatement(sql);
   ps.setString(1, idstr);
   ResultSet rs=ps.executeQuery();
   rs.next();
 
   vo.setMno(rs.getInt(1));
   vo.setUserid(rs.getString(2));
   vo.setUsergrade(rs.getString(3));
   vo.setTotal_point(rs.getInt(4));
   vo.setTotal_ticketnums(rs.getInt(5));
   rs.close();
  
  }catch(Exception ex) {
   ex.printStackTrace();
  }finally {
   disConnection();
  }
  return vo;
 }
```
##### 3.2 Model/Controller : session에서 데이터를 받아오고, 함수를 실행시켜 vip페이지에 세션과 매칭되는 vo데이터를 전송한다.
```java
@Controller
public class VipLoungeModel {
	
	@RequestMapping("viplounge/viplounge_main.do")
	   public String event_board_list(HttpServletRequest request,HttpServletResponse response){
		
		//서버에 생성된 세션이 있다면 받아오고 없다면 새 세션을 생성 후 받아온다.
		HttpSession session = request.getSession(); 
		
		//1. login되어있는 상태의 id값을 받아온다.
		String id=(String) session.getAttribute("id");
		//System.out.println("세션에 저장된 id값은:"+id);//개인확인용 메세지. 삭제하지말것.
	
		//2. 세션에서 가져온 id==viplounge의 userid 같은 행에 대해 vo데이터를 가져온다.
		ViploungeDAO dao = new ViploungeDAO(); //newInstance형태로 수정해야함.
		dao.memberDataUpdate();		
		ViploungeVO vo = dao.viploungeUserData(id);	//id==userid인경우 데이터를 뽑아온다.		
		
		request.setAttribute("vo", vo);				
		request.setAttribute("main_jsp", "../viplounge/viplounge_main.jsp");
		return "../main/main.jsp";
	   }
}
```

##### 3.3 View(header) : 로그인/비로그인 상태에 따라 다른 링크를 출현시킨다.
```jsp
<c:if test="${sessionScope.id==null }">
<!-- 비로그인 상태일 때 회원가입과 로그인 링크 출현-->
	<a class="signup">회원가입</a>
	<a class="login" href="../login/login.do">로그인</a>
</c:if>
<c:if test="${sessionScope.id!=null }">
<!-- 로그인 상태일 때 로그인정보(유저이름, 관리자인지 일반유저인지 등급표시)와 로그아웃 링크 출현 -->
	<div class="main_header_loginInfo">
 	<table class="table" style="border: none">
 		<tr class="inline">
 			<td class="loginSessionInfoName">${sessionScope.name }(${sessionScope.admin=='y'?"관리자":"일반유저" })</td>
			<td class="loginSessionBtnLogout"><a href="../login/logout.do">로그아웃</a></td>
		</tr>
	</table>
	</div>
</c:if>
```
<br><br>


<br><br><br><br>
## 웹 페이지 결과물
__로그인 페이지__ <br><br>
 <img src = "../imgs/04_loginPage.PNG" style="width:99%">
 <br><br><br><br>
 
__로그인 상태 : 유저 멤버쉽 데이터를 출력하는 Viplounge 페이지__ <br><br>
  <img src = "../imgs/04_loginSession_viplounge.png" style="width:99%">
  <br><br><br><br>

__비 로그인 상태 : 기본 Viplounge 페이지__ <br><br>
  <img src = "../imgs/04_loginSessionNull_viplounge.png" style="width:99%">
