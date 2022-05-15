package JsonMakePractice01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import JsonMakePractice01.DataVO;

public class JsonMaking {

	public static void main(String[] args) {
		
		// 1) � ������ ���� VO�� ����  ��´�.
								// ����� DB, DAO�� �����Ƿ� ���� ���� �־��ְڴ�.
								/*
								 *	userNm : ������,
								 *	emailAddr : jyy2112@kico.co.kr
								 *	phonenum : 010-3258-3707
									
								 *	userNm : ȫ���,
								 *	emailAddr : hhs3861@naver.com
								 *	phonenum : 		
								 */
		DataVO dataVo = new DataVO();
		dataVo.setUserNm("��xx");
		dataVo.setEmailAddr("xxx@kico.co.kr");
		dataVo.setPhonenum("010-3258-xxxx");
							
		DataVO dataVo2 = new DataVO();
		dataVo2.setUserNm("ȫww");
		dataVo2.setEmailAddr("www@naver.com");
		dataVo2.setPhonenum("");
		
		// 2) �� �����͵��� ��� �����;��ұ�?
		// �������� �����͸� ���������� -> �迭!
		JSONArray jArray = new JSONArray(); //[{JSONObject} , ... , {JSONObect}]�� ���� ����
		//jObj.put("key", "value"); // ������ ���� �� ������, for���� ��������. �׸� ���ؼ��� Vo�� ����Ʈȭ�ؾ���.
		List<DataVO> dataVoList =  new ArrayList<>();
		dataVoList.add(dataVo);
		dataVoList.add(dataVo2);
		
		for(int i =0 ; i < dataVoList.size(); i++) { //�� ������ ����ŭ
			
			JSONObject innerObj = new JSONObject();
			innerObj.put("userNm", dataVoList.get(i).getUserNm());
			innerObj.put("emailAddr", dataVoList.get(i).getEmailAddr());
			innerObj.put("phonenum", dataVoList.get(i).getPhonenum()); //Object�� �ٸ� ��,
			jArray.add(innerObj); //�ش� ������Ʈ�� �迭�� �־���.			
		} //��������ϸ� [ { vo1 } , { vo2 } ] �� �Ǿ���.
		
		//�ش� �迭�� �ٽ� ���� json �� ������,
		JSONObject returnObj = new JSONObject();
		returnObj.put("resultData",jArray);
		
		// 2) �� ������ ����ó�� ���� ���, resultCode�� resultMsg�� �־��ش�.
		String resultCode = "0000";
		String resultMsg = "���� ó�� �Ǿ����ϴ�.";
		
		returnObj.put("resultCode", resultCode);
		returnObj.put("resultMsg", resultMsg);
		System.out.println(returnObj);
		
		//��¹��� �����ϸ�,
		/*
		   {
		  		"resultData":	[
						  			{
						  				"userNm":"��xx",
						  				"emailAddr":"xxx@kico.co.kr",
						  				"phonenum":"010-3258-xxxx"
						  			},
						  			{
						  				"userNm":"ȫww",
						  				"emailAddr":"www@naver.com",
						  				"phonenum":""
						  			}
				  				],
				 "resultCode":"0000",
				 "resultMsg":"���� ó�� �Ǿ����ϴ�."}

		 */			
		
		
	}

}
