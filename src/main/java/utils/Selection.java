package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Selection extends GoogleSheetReader {

	private String GoogleString="1DEXQ3ogh7f6xWbk1gwWbU18J9xafJ3r9PoqL_2OrvHw";

	public Map<Object, Object> getValues(String data) throws IOException
	{
		Map<Object, Object> allSheets;
		allSheets=this.getSheetMap(GoogleString, data); 
		return allSheets;
	}

	private  Map<Object, Map<Object, Object>> getSheetData(Map<Object,Object> sheetVals) 
	{ 
		int count =0;		
		Map<Object, Map<Object, Object>> wholeSheet= new HashMap<Object,Map<Object, Object>>();
		String mapVal;
		Map<Object, Object> oneSheet;
		// oneSheet;
		try
		{
			for(Map.Entry<Object, Object> mapVals: sheetVals.entrySet())
			{   
				mapVal= mapVals.getValue().toString();
				//System.out.println("mapVal= "+mapVal);
				oneSheet=getValues(mapVal);
				//System.out.println("oneSheet"+oneSheet);
				wholeSheet.put(mapVal,oneSheet);
				//oneSheet.clear();
			}	
		}

		catch(Exception e)
		{
			System.out.println("Exception Occured there"+e);
		}

		return wholeSheet;

	}

	public List<String> allCityList(String tab)
	{
		Map<Object, Object> allSheets;
		Map<Object, Map<Object, Object>> fullSheets;
		Map<Object, Object> interimMap;
		List<String> cityLlist=new ArrayList<String>();
		try {
			allSheets= this.getValues("Config");
			fullSheets =this.getSheetData(allSheets);
			interimMap= fullSheets.get(tab);

			for(Map.Entry<Object, Object> temp :interimMap.entrySet() )
			{
				String listValues=(String) temp.getValue();
				cityLlist.add(listValues);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cityLlist;

	}

	private String getEnv(List<String> env)
	{
		String envVal="";
		for(String vals:env)
		{
			if(vals.equalsIgnoreCase("local")){
				return envVal=vals;
				
				
			}
			else
				if(vals.equalsIgnoreCase("remote"))
				{
					return envVal=vals;
					
				}
			
		}
		 return envVal;
		
	}
	
	public String setEnv(){
		
		Map<Object, Object> allSheets;
		Map<Object, Map<Object, Object>> fullSheets;
		Map<Object, Object> interimMap;
		List<String>allVals = new ArrayList<String>();
		  String envVal ="";
		  String finalEnv="";
		try {
			allSheets= this.getValues("Config");
			fullSheets =this.getSheetData(allSheets);
			
			interimMap= fullSheets.get("Environment");

			for(Map.Entry<Object, Object> temp :interimMap.entrySet() )
			{
				envVal= (String) temp.getValue();
				allVals.add(envVal);
				

			}
			finalEnv =this.getEnv(allVals);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalEnv;
	}
//	public static void main(String [] args)
//
//	{
//		Selection select = new Selection();
//		 String Env;
//		 Env=select.setEnv();
//			System.out.println(Env);
//	}



}
