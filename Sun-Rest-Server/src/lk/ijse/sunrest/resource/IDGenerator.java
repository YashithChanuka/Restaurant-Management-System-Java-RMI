package lk.ijse.sunrest.resource;

import java.text.NumberFormat;

public class IDGenerator {

    public static String getNewID(String tableName, String colName, String prefix)throws Exception{

        String lastID=IDController.getLastID(tableName, colName);

        if(lastID != null){
            int id=Integer.parseInt(lastID.split(prefix)[1]);
            id++;
            NumberFormat numberFormat=NumberFormat.getIntegerInstance();
            numberFormat.setMinimumIntegerDigits(3);
            numberFormat.setGroupingUsed(false);
            String newID=numberFormat.format(id);
            return prefix + newID;
        }else{
            return prefix + "001";
        }
    }

}
