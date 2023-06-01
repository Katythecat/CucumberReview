package utils;

import org.json.JSONObject;

public class APIPayloadConstants {
    //we will pass the body in multiple formats, for this we have created this class

    public static String createEmployeePayload(){
        String createEmployeePayload="{\n" +
                "    \"emp_firstname\": \"Taloy\",\n" +
                "    \"emp_lastname\": \"Swiff\",\n" +
                "    \"emp_middle_name\": \"s\",\n" +
                "    \"emp_gender\": \"F\",\n" +
                "    \"emp_birthday\": \"1990-05-20\",\n" +
                "    \"emp_status\": \"Confirmed\",\n" +
                "    \"emp_job_title\": \"Singer\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","Taloy");
        obj.put("emp_lastname","Swiff");
        obj.put("emp_middle_name","s");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","1990-05-20");
        obj.put("emp_status","Confirmed");
        obj.put("emp_job_title","Singer");
        return obj.toString();
    }

    public static String createEmployeePayloadDynamic(
            String fname,String lname,String mname,String gender,
            String bd,String status,String title){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname",fname);
        obj.put("emp_lastname",lname);
        obj.put("emp_middle_name",mname);
        obj.put("emp_gender",gender);
        obj.put("emp_birthday",bd);
        obj.put("emp_status",status);
        obj.put("emp_job_title",title);
        return obj.toString();
    }

    public static String updateEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("employee_id","58148A");
        obj.put("emp_firstname","Selena");
        obj.put("emp_lastname","Gomez");
        obj.put("emp_middle_name","T");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","1995-03-15");
        obj.put("emp_status","Part-Time");
        obj.put("emp_job_title","Dancer");
        return obj.toString();
    }


}
