package JsonMakePractice01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import JsonMakePractice01.DataVO;

public class JsonMaking {

	public static void main(String[] args) {
		
		// 1) 어떤 로직에 의해 VO에 값을  담는다.
								// 현재는 DB, DAO가 없으므로 직접 값을 넣어주겠다.
								/*
								 *	userNm : 유지연,
								 *	emailAddr : jyy2112@kico.co.kr
								 *	phonenum : 010-3258-3707
									
								 *	userNm : 홍희수,
								 *	emailAddr : hhs3861@naver.com
								 *	phonenum : 		
								 */
		DataVO dataVo = new DataVO();
		dataVo.setUserNm("유xx");
		dataVo.setEmailAddr("xxx@kico.co.kr");
		dataVo.setPhonenum("010-3258-xxxx");
							
		DataVO dataVo2 = new DataVO();
		dataVo2.setUserNm("홍ww");
		dataVo2.setEmailAddr("www@naver.com");
		dataVo2.setPhonenum("");
		
		// 2) 이 데이터들을 어떻게 가져와야할까?
		// 여러개의 데이터를 가져오려면 -> 배열!
		JSONArray jArray = new JSONArray(); //[{JSONObject} , ... , {JSONObect}]를 담을 공간
		//jObj.put("key", "value"); // 일일히 넣을 수 없으니, for문을 돌려야함. 그를 위해서는 Vo를 리스트화해야함.
		List<DataVO> dataVoList =  new ArrayList<>();
		dataVoList.add(dataVo);
		dataVoList.add(dataVo2);
		
		for(int i =0 ; i < dataVoList.size(); i++) { //총 데이터 량만큼
			
			JSONObject innerObj = new JSONObject();
			innerObj.put("userNm", dataVoList.get(i).getUserNm());
			innerObj.put("emailAddr", dataVoList.get(i).getEmailAddr());
			innerObj.put("phonenum", dataVoList.get(i).getPhonenum()); //Object를 꾸린 후,
			jArray.add(innerObj); //해당 오브젝트를 배열로 넣어줌.			
		} //여기까지하면 [ { vo1 } , { vo2 } ] 가 되었다.
		
		//해당 배열을 다시 최종 json 에 넣으면,
		JSONObject returnObj = new JSONObject();
		returnObj.put("resultData",jArray);
		
		// 2) 위 로직을 정상처리 했을 경우, resultCode와 resultMsg를 넣어준다.
		String resultCode = "0000";
		String resultMsg = "정상 처리 되었습니다.";
		
		returnObj.put("resultCode", resultCode);
		returnObj.put("resultMsg", resultMsg);
		System.out.println(returnObj);
		
		//출력물을 정리하면,
		/*
		   {
		  		"resultData":	[
						  			{
						  				"userNm":"유xx",
						  				"emailAddr":"xxx@kico.co.kr",
						  				"phonenum":"010-3258-xxxx"
						  			},
						  			{
						  				"userNm":"홍ww",
						  				"emailAddr":"www@naver.com",
						  				"phonenum":""
						  			}
				  				],
				 "resultCode":"0000",
				 "resultMsg":"정상 처리 되었습니다."}

		 */			
		
		
	}

}
