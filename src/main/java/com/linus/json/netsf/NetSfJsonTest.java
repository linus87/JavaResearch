package com.linus.json.netsf;

import org.json.JSONObject;

public class NetSfJsonTest {

    public static void main(String[] args) {
	createObject();

    }

    public static void createObject() {
	String str = "{\"code\":\"0000\", \"msg\":{\"availableBalance\":31503079.02}}";
	JSONObject json = new JSONObject(str);
	
	JSONObject msgObj = json.getJSONObject("msg");
	
	String availableBalance = msgObj.getString("availableBalance");
	System.out.println(availableBalance);
    }
}
