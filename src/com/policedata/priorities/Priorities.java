package com.policedata.priorities;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.policedata.objects.Neighbourhood;
import com.policedata.objects.Objects;
import com.policedata.parsing.ObjectMaker;
import com.policedata.requests.Requests;

public class Priorities {

	public static String parsePriorities(Neighbourhood inputNeighbourhood)
	{
		String urlString = urlGeneration(inputNeighbourhood);
		
		JsonParser jsonParser = new JsonParser();
		String httpResponse = null;
		try
		{
			httpResponse = Requests.getRequest(urlString);
		} // try
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		} // catch
		
		JsonElement jsonElement = jsonParser.parse(httpResponse);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		
		String y = null;
		
		for (JsonElement foo : jsonArray)
		{
			JsonObject bar = foo.getAsJsonObject();
			String str = bar.get("issue").getAsString();
			y = str;
		}
		
		return y;
	}

	public static ArrayList<String> parsePriorityList(String par)
	{
		
	}
	public static String urlGeneration(Neighbourhood inputNeighbourhood)
	  {
	    // do a null check on input argument & object elements
	    if (inputNeighbourhood == null || inputNeighbourhood.getForce() == null ||
	      inputNeighbourhood.getNeighbourhood() == null)
	    {
	      return null;
	    } // if
	    String force = inputNeighbourhood.getForce();
	    String neighbourhood = inputNeighbourhood.getNeighbourhood();

	    String urlString = "https://data.police.uk/api/" + force + "/" + neighbourhood + "/priorities";
	    
	    return urlString;
	  } // urlGeneration

}
