package com.linus.json.org;

import net.sf.json.JSONObject;


public class JsonTest {

    public static void main(String[] args) {
	createObject();

    }

    public static void createObject() {
	String str = "{\"code\":\"0000\", \"msg\":{\"availableBalance\":31503079.02}}";
	JSONObject json = JSONObject.fromObject(str);
	
	JSONObject msgObj = json.getJSONObject("msg");
	
	String availableBalance = msgObj.getString("availableBalance");
	System.out.println(availableBalance);
    }

}
