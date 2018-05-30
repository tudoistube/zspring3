function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName){
		
	var fileName,imgsrc, getLink;
	
	var fileLink;
	
	console.log('fullName = ' + fullName);
	
	if(checkImageType(fullName)){
		imgsrc = "/y4b/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // /2015/07/01/ 
		var end = fullName.substr(14);
		
		getLink = "/y4b/displayFile?fileName="+front + end;
		
	}else{
		imgsrc ="/resources/dist/img/file.png";
		fileLink = fullName.substr(12);
		getLink = "/y4b/displayFile?fileName="+fullName;
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}


