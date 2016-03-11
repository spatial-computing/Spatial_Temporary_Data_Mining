package edu.usc.spatialcomputing.crawlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RemoveKeysFromJson {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		String location = "result/last";
		File []lists = new File(location).listFiles();
		JSONParser parser = new JSONParser();
		JSONArray finalArr = new JSONArray();
		for (File f1 : lists) {
			if (f1.isFile() && !f1.isHidden() && f1.getName().endsWith("json")) {
				JSONArray root = (JSONArray) parser.parse(new FileReader(f1));
				Iterator<JSONObject> ll = root.iterator();
				while (ll.hasNext()) {
					JSONObject obj = new JSONObject();
					JSONObject old = ll.next();
					
				//	obj.put(((JSONObject)((JSONArray)parser.parse(old.values().toString())).get(0)).get("publicationid"),old.values());
					finalArr.add(((JSONArray)parser.parse(old.values().toString())).get(0));
				}
				new CreateJsonRead().writeToFile(finalArr, location+"/input/"+f1.getName());
			}
		}
	}

}
