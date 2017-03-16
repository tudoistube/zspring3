package org.joywins.dao;

import java.util.Map;

/*
 * ...692p.MyBatis 는 XML 또는 @SelectProvider 를 이용해서 동적 SQL 을 처리함.
 *    @SelectProvider 는 Select 구문을 만들어 냄.
 *    type 과 method 속성을 가짐.
 *    type : SQL 을 만들어 내는 클래스이고, 어떤 종류의 클래스이든 사용 가능함.
 *    method : SQL 문이 반환되는 메서드 이름이고, 문자열이 반환됨.
 *    다만, 메서드는 static 으로 작성되어야 해서, @SelectProvide 는 객체를 생성
 *    하지 않도록 설계되어 있음.
 */
public class SampleSelectProvider {
	
	/*
	 * ...693p.메서드의 파라미터는 2개 이상의 데이터를 던질 수 있도록 Map<String, Object> 로
	 *    설정함.
	 */
	public static String searchUname(Map<String, Object> params){
		
		String strSql = "select UNAME from ztbl_msg_user where 1 =1 ";
		
		if(params.get("type").equals("uid")){
			strSql += " and UID = #{uid}";
		}
		
		return strSql;
	}

}
