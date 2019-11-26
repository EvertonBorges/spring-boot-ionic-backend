package com.tcc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String s) {
		List<Integer> list = new ArrayList<>();
		String[] vet = s.split(",");
		for (String n: vet) {
			list.add(Integer.parseInt(n));
		}
		return list;
	}
	
}
