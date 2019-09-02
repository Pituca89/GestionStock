package servidor.kiosco;

import java.sql.ResultSet;

public class Conversor {

	   /**
     * Convert a result set into a JSON Array
     * @param resultSet
     * @return a JSONArray
     * @throws Exception
     */
    public static String convertToJSON(ResultSet rs) throws Exception {
		        //JSONArray jsonArray = new JSONArray();
    	String json = "";		
        while (rs.next()) {
            int total_rows = rs.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                //JSONObject obj = new JSONObject();
            	/*
            	rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3) 
					+ "/" + rs.getInt(4) + "/" + rs.getDouble(5) + "/" + rs.getDouble(6) 
					+ "/" + rs.getDate(7) + "/" + rs.getInt(8)
               */
            	json += rs.getObject(i + 1) + "|";
            }
            json += "\n";
        }
        return json;
    }
}

