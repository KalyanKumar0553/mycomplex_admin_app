package com.src.mycomplex.main.utils;

import com.src.mycomplex.main.response.dto.JSONResponseDTO;

public class AppUtils {
	
	@SuppressWarnings("unchecked")
	public static  <T> JSONResponseDTO<T> getJSONObject(T data) {
		return (JSONResponseDTO<T>) JSONResponseDTO.builder().statusMsg(data).isError(false).build();
	}
}
