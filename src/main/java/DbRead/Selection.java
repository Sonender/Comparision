package DbRead;

import java.util.HashMap;
import java.util.Map;

public class Selection {

	Map<String,String> map1= new HashMap<String,String>();
	Map<String,String> map2= new HashMap<String,String>();

	public  Selection(Map<String, String> map1, Map<String, String> map2){
		this.map1=map1;
		this.map2=map2;
	}

	public Map<String, String> returnMap1()
	{
		return map1;
	}

	public Map<String, String> returnMap2()
	{
		return map2;
	}

}
