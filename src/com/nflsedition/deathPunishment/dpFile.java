package com.nflsedition.deathPunishment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class dpFile {

	public static void saveMap(HashMap<String , String> map, String path) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(map);
			oos.flush();
			oos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> loadMap(String path){
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			Object result = ois.readObject();
			ois.close();
			return (HashMap<String, String>)result;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String unicode2Text(final String unicode){
		
		int start = 0,end = 0;
		final StringBuffer buffer= new StringBuffer();
		
		if (unicode.indexOf("\\u")!=-1) {		
			while (start>-1) {
	
	            end = unicode.indexOf("\\u", start + 2);
	            String charStr = "";
	            if (end == -1) {
	                charStr = unicode.substring(start + 2, unicode.length());
	            } else {
	                charStr = unicode.substring(start + 2, end);
	            }
	            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
	            buffer.append(new Character(letter).toString());
	            start = end;
			}
			return buffer.toString();
		}
		return unicode;
	}
	public static String getLocaleName(Material material,String locale) {
		String result = material.toString();
		if (deathPunishment.langhash.containsKey(locale+".lang")) {
			JSONObject lang = deathPunishment.langhash.get(locale+".lang");
			if (material.isBlock()) {
				if (!(lang.get("block.minecraft."+material.toString().toLowerCase())==null)) {
					result = (String)lang.get("block.minecraft."+material.toString().toLowerCase());
				}
			}else{
				if (!(lang.get("item.minecraft."+material.toString().toLowerCase())==null)) {
					result = (String)lang.get("item.minecraft."+material.toString().toLowerCase());
				}
			}
		}
		return StringUtils.replaceChars(result.toLowerCase(),'_',' ');
	}
	public static String getLocaleMessage(String message,String locale,String def) {
		if (deathPunishment.langhash.containsKey(locale+".lang")) {
			JSONObject lang = deathPunishment.langhash.get(locale+".lang");
			if (!(lang.get("deathPunishment.message."+message)==null)) {
				message = (String)lang.get("deathPunishment.message."+message);
				return message;
			}else {
				return def;
			}
		}else {
			return def;
		}
	}
	public static HashMap<String, JSONObject> loadLangs(String path){
		HashMap<String, JSONObject> langHash = new HashMap<>();
		File dir = new File(path);
		File[] files = dir.listFiles();
		JSONObject jsonObject = new JSONObject();
		for (File file : files) {
			if (!(file.isDirectory())) {
				String jsonString = readFileToString(file);
				jsonObject = (JSONObject) JSONValue.parse(jsonString);
				langHash.put(file.getName(),jsonObject);
				System.out.println("语言"+file.getName()+"已加载！");
				//System.out.println(jsonObject.toJSONString());
			}
		}
		
		return langHash;
	}
    public static String readFileToString(File file) {
        // 定义返回结果
        String jsonString = "";

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// 读取文件  
            String thisLine = null;
            while ((thisLine = in.readLine()) != null) {
                jsonString += thisLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException el) {
                }
            }
        }
        //jsonString = StringUtils.deleteWhitespace(jsonString);
        // 返回拼接好的JSON String
        return jsonString;
    }
}
