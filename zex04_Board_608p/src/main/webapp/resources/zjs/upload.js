function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName){
		
	var fileName,imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "/z4b/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // /2015/07/01/ 
		var end = fullName.substr(14);
		
		getLink = "/z4b/displayFile?fileName="+front + end;
		
	}else{
		//...현재 위치인 zjs에서 상대경로로 dist/img폴더로 이동함.
		//imgsrc ="../dist/img/file.png";
		imgsrc ="/z4b/resources/dist/img/file.png";
		fileLink = fullName.substr(12);
		getLink = "/z4b/displayFile?fileName="+fullName;
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}


