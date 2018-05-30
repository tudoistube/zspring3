package org.joywins.domain;
//...305p.
//...Criteria를 상속하여 페이징 처리를 하는 PageMaker에서도 그대로
//...사용할 수 있도록 함.
public class SearchCriteria extends Criteria {

	private String searchType;
	private String keyword;	
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + 
							 ", keyword=" + keyword + "]";
	}	
	
}
