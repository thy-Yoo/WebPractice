### 개인 기록용

#### 문제 발견 1 - 하나의 태그안에 여러 종류의 data, data중 정보가 없는 부분이 존재.
<br>
네이버 영화 사이트의는 개요라는 태그 안에 __액션,판타지 | 125분 | 2021.09.01 개봉__ 이와 같은 형태로 세가지 정보가 담겨있다. <br>
세가지 데이터를 각각의 DB 컬럼에 나누어 넣기 위해, 처음에는 | 문자를 기준으로 문자열을 잘라 나누었다. <br>
그런데, 데이터들을 살펴보니 __개요 : 상영시간 | 개봉일__ 과 같이 장르가 없이 빠져있는 것을 발견하였고, 단순히 문자열을 자르는 함수를 사용하기엔 문제가 있었다. <br>


때문에<br>
1. '|'가 아닌 상영시간의 문자'분'을 기준으로 데이터를 나누기로 정하였다.
```
for(int i=0; i<temp.get(j).length(); i++) {
  if( temp.get(j).charAt(i)=='분') {/*p1=0, p2=1이 초기값. 무조건 처음에 만족*/
    bun=i;								         	/*p1=8,p2=1인 상태. (예를들어서)*/
    System.out.println("분위치:"+bun);
	}
}
```
2. '분'이라는 문자의 위치가 3이상이라는 것은, 장르 데이터가 존재한다는 의미이고, (사이트 확인 후, 장르의 최소글자수를 2로 잡음)
   '분'이라는 문자의 위치가 3미만이라는 것은, 장르 데이터가 존재하지 않다는 의미이다. 때문에 아래와 같이 나누어 해결하였다.
```
  if(bun>=3) { 
		runningtime = (String) temp.get(j).subSequence(bun-3,bun+1);
		releasedate = (String) temp.get(j).subSequence(bun+4,bun+17);
	}else if(bun<3) {
		runningtime = (String) temp.get(j).subSequence(0,bun+1);
		releasedate = (String) temp.get(j).subSequence(bun+4,bun+17);
	}
```
