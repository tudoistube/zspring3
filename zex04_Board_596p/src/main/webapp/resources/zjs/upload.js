function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName){
		
	var fileName,imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "/z4b/displayFile?fileName="+fullName;
		console.log("getFileInfo : imgsrc : " + imgsrc);
		
		fileLink = fullName.substr(14);
		console.log("getFileInfo : fileLink : " + fileLink);
		
		var front = fullName.substr(0,12); // /2015/07/01/
		console.log("getFileInfo : front : " + front);
		
		var end = fullName.substr(14);
		console.log("getFileInfo : end : " + end);
		
		getLink = "/z4b/displayFile?fileName="+front + end;
		console.log("getFileInfo : getLink : " + getLink);
		
	}else{
		//...현재 위치인 zjs에서 상대경로로 dist/img폴더로 이동함.
		imgsrc ="../dist/img/file.png";
		fileLink = fullName.substr(12);
		console.log("getFileInfo : fileLink : " + fileLink);
		
		getLink = "/z4b/displayFile?fileName="+fullName;
		console.log("getFileInfo : getLink : " + getLink);
		
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	console.log("getFileInfo : fileName : " + fileName);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}


