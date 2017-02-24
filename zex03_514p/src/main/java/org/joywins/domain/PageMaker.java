package org.joywins.domain;
//...272p.
import org.joywins.domain.Criteria;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * ...268p. 목록 하단 페이지나열자의 계산순서.
 * ...예) IF 현재 페이지가 13p THEN, 시작페이지 : 11p, 끝페이지 : 20p이 되어야 함.
 * ...13/10(페이지나열자에 보여줄 페이지수) = 1.3 을 반올림 → 2 : 페이지 나열자에서 두번째 위치.
 * ...2*10(페이지나열자에 보여줄 페이지수) = 20(페이지 나열자의 10번째, 마지막 페이지번호).
 * ...20 - 10 + 1 = 11 : 시작 페이지 번호.
 * ...시작페이지의 번호 = 마지막 페이지 번호 - 페이지나열자에 보여줄 페이지수 + 1.
 * 
 * ...목록 하단 페이지 나열자 산술시 한페이지에 몇개의 데이터를 보여줄지 결정해야 하는데
 * ...일반적으로 10을 기준으로 함.
 * ...페이지 나열자에 보여줄 페이지수는 일반적으로 10개씩 보여줌.
 * ...마지막 페이지번호를 먼저 구하고, 시작 페이지를 구하는 방법을 권장됨.
 * ...마지막 페이지번호는 실제 데이터 개수와 관련이 있어서, 다시 계산해서 확인한다.
 * 
 * ...Prev = 시작페이지 == 1 ? false : true;
 * 
 * ...Next = 마지막 페이지번호 * 페이지 나열자에 보여줄 페이지수 >= DB전체데이터수 ? false : true;
 * 
 */
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean blPrev;	
	private boolean blNext;

	//...화면에 보여지는 페이지 번호의 숫자.
	private int displayPageNum = 10;

	private Criteria cri;

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		calcData();
	}

	private void calcData() {

		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		blPrev = startPage == 1 ? false : true;

		blNext = endPage * cri.getPerPageNum() >= totalCount ? false : true;

	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return blPrev;
	}

	public boolean isNext() {
		return blNext;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}
	
	//...286p.모든 정보는 Criteria에 있음.
	//...readPage 사용함.
	public String makeQuery(int page) {

	    UriComponents uriComponents 
	    	= UriComponentsBuilder.newInstance()
	    						  .queryParam("page", page)
	    						  .queryParam("perPageNum", cri.getPerPageNum())
	    						  .build();
	    String strTemp = uriComponents.toUriString();
	    return uriComponents.toUriString();
	}
  	
	//...312p.searchType, keyWord 링크처리.
	public String makeSearch(int page){
		    
	    UriComponents uriComponents =
	              UriComponentsBuilder.newInstance()
	              .queryParam("page", page)
	              .queryParam("perPageNum", cri.getPerPageNum())
	              .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	              .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
	              .build();             
	    
	    return uriComponents.toUriString();
	} 	
	

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount 
				      + ", startPage=" + startPage 
				      + ", endPage=" + endPage 
				      + ", blPrev="	+ blPrev 
				      + ", blNext=" + blNext 
				      + ", displayPageNum=" + displayPageNum 
				      + ", cri=" + cri + "]";
	}
	

}
