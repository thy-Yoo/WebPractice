package com.yjy.dao;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieDataCrawl {

	public void naverMovieData() {
		MovieDAO dao = new MovieDAO();
		ArrayList<String> temp = new ArrayList<String>();
		
		int p1=0; int p2=1; int bun=0;
		
		try {
			int k = 1;

			Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.naver").get();
			Elements poster = doc.select("div.thumb > a > img");
			Elements title = doc.select("dl.lst_dsc dt.tit a");// <
			Elements genre = doc.select("dd:nth-child(2) > span.link_txt");
			Elements director = doc.select("dd:nth-child(4) > span.link_txt");
			Elements actor = doc.select("dd:nth-child(6) > span.link_txt");
			Elements ticketsales = doc.select("dl.info_exp span.num");
			Elements grade = doc.select("dt.tit span:nth-child(1)");
			Elements rate = doc.select("dl.info_star span.num");
			String runningtime = null;
			String releasedate = null;
			//Elements runningtime = doc.select("dl.lst_dsc dt a");
			//Elements releasedate = doc.select("dl.lst_dsc dt a");
			//Elements key = doc.select("dl.lst_dsc dt a");
			String string_temp = null;
			
			temp = (ArrayList<String>) doc.select("dd:nth-child(3) > dl > dd:nth-child(2)").eachText(); //상영시간+개봉일 텍스트
			
			
			for (int j = 0; j < title.size(); j++) {			
				try {					
					System.out.println("NUM:" + k);
					System.out.println("제목:" + title.get(j).text());	
					System.out.println("장르:" + genre.get(j).text());
					System.out.println("감독:" + director.get(j).text());
					System.out.println("배우:" + actor.get(j).text());
					System.out.println("예매율:" + ticketsales.get(j).text());
					System.out.println("평점:" + rate.get(j).text());
					System.out.println("임시:" + temp.get(j));
					System.out.println("관람등급:" + grade.get(j).text());
					//System.out.println("키:" + key.get(j).text());				
					System.out.println("p1:"+p1);
					System.out.println("p2:"+p2);
					
					for(int i=0; i<temp.get(j).length(); i++) {
						if( temp.get(j).charAt(i)=='분') {	/*p1=0, p2=1이 초기값. 무조건 처음에 만족*/
							bun=i;									/*p1=8,p2=1인 상태. 예를들어서*/
							System.out.println("분위치:"+bun);
						}
					}
					if(bun>=3) {
						System.out.println("상영시간:"+temp.get(j).subSequence(bun-3,bun+1));
						System.out.println("개봉일:"+temp.get(j).subSequence(bun+4,bun+17));
						runningtime = (String) temp.get(j).subSequence(bun-3,bun+1);
						releasedate = (String) temp.get(j).subSequence(bun+4,bun+17);
					}else if(bun<3) {
						System.out.println("상영시간:"+temp.get(j).subSequence(0,bun+1));
						System.out.println("개봉일:"+temp.get(j).subSequence(bun+4,bun+17));
						runningtime = (String) temp.get(j).subSequence(0,bun+1);
						releasedate = (String) temp.get(j).subSequence(bun+4,bun+17);
					}
				
					p1=0; p2=1;
					// System.out.println("Ű:"+youtubeGetKey(idcrment));
				
					MovieVO vo = new MovieVO();
					vo.setNum(k);
					vo.setPoster(poster.get(j).attr("src"));
					vo.setTitle(title.get(j).text());
					vo.setGenre(genre.get(j).text());
					vo.setDirector(director.get(j).text());
					vo.setActor(actor.get(j).text());
					vo.setTicketsales(ticketsales.get(j).text());
						
						/*0811기준, 밑의 세 영화들이 아예 네이버 웹페이지에서 관람등급 태그가 빠져있어서
						 * 단순히 null값을 넣고 넘어가면 데이터가 밀려서.. 저렇게 처리하였다.
						 * 문제는 네이버 페이지에 따라서 87,103,112라는 숫자가 바뀔수 있다는 것인데.. 이를 어떻게 처리해야 좋을지 고민이다.
						 * k==87:뜨거운 것이 좋아
						 * k==103:역마차
						 * k==112:킹콩
						 */						
						if(k<87) {
							vo.setGrade(grade.get(j).text());
						}else if( k==87 || k==103 || k==112 ){
							vo.setGrade(null);
						}else if( k>87 && k<103 ){
							vo.setGrade(grade.get(j-1).text());
						}
						else if( k>103 && k<112 ){
							vo.setGrade(grade.get(j-2).text());
						}
						else {
							vo.setGrade(grade.get(j-3).text());
						}	
					
					vo.setRate(rate.get(j).text());
					vo.setRunningtime(runningtime);
					vo.setReleasedate(releasedate);
					//vo.setKey(key.get(j).text());
				
					dao.naverMovieDataInsert(vo);
					// System.out.println("Ű:"+youtubeGetKey(vo.getTitle()));
					System.out.println("=========================================");
						
					k++;
					
				}catch (Exception ex) {
					ex.getStackTrace();
				}
			}
		}catch (Exception ex) {
		}
	}	
}

		
		
		/*
		if( temp.get(j).charAt(i)=='|' && p1<p2) {	//p1=0, p2=1이 초기값. 무조건 처음에 만족
		p1=i;									//p1=8,p2=1인 상태. 예를들어서
			System.out.println("p1:"+p1);
		}
		else if( temp.get(j).charAt(i)=='|' && p1>1) {	//p1=8,p2=1
			p2=i;
			System.out.println("p2:"+p2);			
		}
		*/
		//System.out.println("상영시간:"+temp.get(j).subSequence(p1+2,p2));
		//System.out.println("개봉일:"+temp.get(j).subSequence(p2+2,p2+12));
	
		
		
		