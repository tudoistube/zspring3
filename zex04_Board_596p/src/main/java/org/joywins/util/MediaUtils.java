package org.joywins.util;
//...556p.
/*
 * ...확장자를 가지고 이미지 타입인지를 판단해줌.
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	private static Map<String, MediaType> mediaMap;

	static {

		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}

	public static MediaType getMediaType(String type) {

		return mediaMap.get(type.toUpperCase());
	}
}
