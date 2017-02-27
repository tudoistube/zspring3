package org.joywins.util;
//...548p.
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
//...548p.
//...org.springframework.util.FileCopyUtils와 유사하게 static기능을 호출해서 파일업로드를 하게 함.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	/*
	 * ...별도의 데이터가 보관될 필요가 없기 때문에 static으로 설계됨.
	 * ...UUID를 이용한 고유한 값 생성.
	 * ...↓
	 * ...UUID와 결합한 업로드 파일 이름 생성.
	 * ...↓
	 * ...파일이 저장될 '년/월/일' 정보 생성.
	 * ...↓
	 * ...업로드 기본경로(uploadPath)와 '년/월/일' 폴더 생성.
	 * ...↓
	 * ...기본경로(uploadPath) + 폴더경로 + 파일이름으로 파일 저장.
	 */
	public static String uploadFile(String uploadPath, 
									String originalName, 
									byte[] fileData) throws Exception {

		//...557p.
	    UUID uid = UUID.randomUUID();
	    
	    String savedName = uid.toString() +"_"+originalName;
	    
	    //...저장될 경로를 계산함.
	    String savedPath = calcPath(uploadPath);
	    
	    File target = new File(uploadPath +savedPath,savedName);
	    
	    //...원본파일을 저장함.
	    FileCopyUtils.copy(fileData, target);
	    
	    //...원본파일의 확장자.
	    String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
	    
	    String uploadedFileName = null;
	    
	    //...이미지 파일인 경우와 그렇지 않은 경우를 나눠서 처리함.
	    //...이미지 파일인 경우 : 썸네일을 생성함.
	    //...이미지 파일이 아닌 경우 : 아이콘으로 만듦.
	    if(MediaUtils.getMediaType(formatName) != null){
	      uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
	    }else{
	      uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
	    }
	    
	    return uploadedFileName;
	}
	
	/*
	 * ...551p.
	 * ...calcPath()의 리턴 값은 최종 결과 폴더를 반환함.
	 * ...내부적으로 폴더를 생성해주는 기능이 필요하므로 기본적인 경로(uploadPath)를 파라미터로 전달받음.
	 * ...날짜정보와 기본경로를 makeDir()에 전달해서, 폴더가 생성됨.
	 */
	private static String calcPath(String uploadPath){

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator+cal.get(Calendar.YEAR);

		String monthPath = yearPath + 
						   File.separator + 
						   new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

		String datePath = monthPath + 
						  File.separator + 
						  new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath,monthPath,datePath);

		logger.info(datePath);

		return datePath;
	}	
	
	private static void makeDir(String uploadPath, String... paths){
	    
	    if(new File(paths[paths.length-1]).exists()){
	      return;
	    }
	    
	    for (String path : paths) {
	      
	      File dirPath = new File(uploadPath + path);
	      
	      if(! dirPath.exists() ){
	        dirPath.mkdir();
	      } 
	    }
	  }
	
	//...553p.
	private static  String makeThumbnail(String uploadPath, //...기본경로.
								         String path, 	    //...'년/월/일 폴더(path).
								         String fileName    //...현재 업로드된 파일이름.
								            )throws Exception{
          
	  /*
	   * ...554p.
	   * ...BufferedImage는 실제 이미지가 아닌 메모리상의 이미지임.
	   * ...원본 파일을 메모리상으로 로딩하고, 정해진 크기에 맞게 작은 이미지 파일에
	   * ...원본 이미지를 복사함.
	   */
	  BufferedImage sourceImg = 
	      ImageIO.read(new File(uploadPath + path, fileName));
	  
	  BufferedImage destImg = 
	      Scalr.resize(sourceImg, 
	          Scalr.Method.AUTOMATIC,
	          //...Scalr.Mode.FIT_TO_HEIGHT설정은 썸네일 이미지 파일의 높이를 뒤에 지정한
	          //...100px로 동일하게 만들어 줌.
	          Scalr.Mode.FIT_TO_HEIGHT,100);
	  
	  //...썸네일이미지 파일명 = UUID + 's_' + 파일명.
	  String thumbnailName = 
	      uploadPath + path + File.separator +"s_"+ fileName;
	  
	  File newFile = new File(thumbnailName);
	  String formatName = 
	      fileName.substring(fileName.lastIndexOf(".")+1);
	  
	  
	  ImageIO.write(destImg, formatName.toUpperCase(), newFile);
	  
	  //...브라우저에서 윈도우의 경로로 사용하는 '\'문자가 정상적인 경로로 인식되지 않으므로
	  //...'/'로 치환함.
	  return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}	
	
	//...557p.
	private static  String makeIcon(String uploadPath, 
									String path, 
									String fileName)throws Exception{
	
		String iconName = uploadPath + path + File.separator+ fileName;
	
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}


}
