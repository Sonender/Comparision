package DbRead;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadDb {

	public Selection convertResultSetToMap(ResultSet set) throws SQLException
	{
		Map<String,String> map1= new HashMap<String,String>();
		Map<String,String> map2= new HashMap<String,String>();
		while( set.next() )
		{
			map1.put(set.getString("latitude"), set.getString("longitude"));
			map2.put(set.getString("fromdate1"), set.getString("todate1"));

		}
		return new Selection(map1,map2);

	}

	public String createInsertStatement(List<List<String>> list, String tableName)
	{		 		

		String Headers = "";
		String values = "";
		List<String> header = list.get(0);
		for(int i = 0; i<header.size(); i++)
		{
			Headers = Headers + header.get(i).toString() + ',';
		}
		Headers = removeLastComma(Headers);


		for(int i =1; i < list.size(); i++)
		{
			List<String> value = list.get(i);
			String temp = "";
			for(int  j= 0; j<value.size(); j++)
			{
				temp = temp + "'" + value.get(j).toString() + "'" + ',';
			}
			temp = removeLastComma(temp);
			values = values + "(" + temp + "),";
			//System.out.println(temp);
		}
		values = removeLastComma(values);
		String insert = "insert into " + tableName + "(" + Headers + ") values " + values + ";" ;
		return insert;
	}

	public  String removeLastComma(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length()-1)==',') {
			str = str.substring(0, str.length()-1);
		}


		return str;

	}
}		  	




