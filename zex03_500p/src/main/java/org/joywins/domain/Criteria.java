package org.joywins.domain;
//...253p.

/*
 * ...251p. 만일 한 페이지에서 보여지는 데이터가 10개가 아니라면 sql limit 구문의 마지막에
 * ...사용되는 10이라는 숫자 역시 변경되야 하고, 매번 원하는 페이지를 처리할 때 마다 계산을
 * ...하는 불편함을 해결하기 위한 방법.
 * ...1. 두개의 파라미터를 받는 방법(예.페이지번호 + 목록페이지).
 * ...	 검색등의 기능이 추가될수록 전달되는 파라미터 양도 늘어나서 관리가 복잡해서 방법2 추천.
 * ...2. 일반적으로 게시물의 성격에 따라 10개 혹은 20개가 지정되나 두 개의 파라미터를
 * ...	 하나로 묶어서 활용하는 형태로 하나의 클래스 객체로 처리.
 * 
 * ...252p. MyBatis SQL Mapper 규칙.
 * ...	#{page}와 같은 파라미터를 사용할 때 내부적으로 page 속성의 getter()를 호출함.
 * ...	select * from ztbl_board
 * ...	where bno > 0
 * ...	order by bno desc
 * ...	limit #{pageStart}, #{perPageNum}
 * ...	파라미터가 여러 개로 늘어나면 관리하기 어려우므로 아예 클래스로 만들어 객체로 처리함.
 */
public class Criteria {

	private int page;
	private int perPageNum;
	private int pageStart;

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}	
	public void setPage(int page) {

		if (page <= 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	
	/*
	 * 	<select id="listCriteria" resultType="BoardVO">
	 <![CDATA[
	 select 
	   bno, title, content, writer, regdate, view_count 
	 from 
	   ztbl_board 
	 where bno > 0 
	 order by bno desc, regdate desc
	 limit #{pageStart}, #{perPageNum}
	 ]]>
	</select>
	 */
	// method for MyBatis SQL Mapper -
	// ...252p. MyBatis SQL Mapper 규칙.
	//...#{perPageNum}와 같은 파라미터를 사용할 때 내부적으로 page 속성의 getter()를 호출함.
	//...255p. limit 구문에서 시작 위치를 지정할 때 사용함.
	//...limit 시작데이터번호 10.
	//...예) 10개씩 출력하는 경우 3페이지의 데이터는 limt 20, 10과 같은 형태.
	public int getPageStart() {
		//...시작데이터번호 = (페이지번호 - 1)*페이지당 보여지는 개수.
		pageStart = (this.page - 1) * perPageNum;
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}	
	// method for MyBatis SQL Mapper
	// ...252p. MyBatis SQL Mapper 규칙.	
	//...#{perPageNum}와 같은 파라미터를 사용할 때 내부적으로 page 속성의 getter()를 호출함.
	//...255p. limit 20 한페이지당 보여지는 개수.
	public int getPerPageNum() {
		return this.perPageNum;
	}	
	public void setPerPageNum(int perPageNum) {

		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", pageStart=" + pageStart + "]";
	}

}
