package com.echoenergy.trustleadnamesextractor.util;

import java.util.List;

public class NameUtil {

    public static String formatText(String text){
        String name;
        int x = text.trim().indexOf("(");
        //"\u0028"
        if(x > 0 && !text.trim().toLowerCase().substring(0,x).contains("trustee")){
            name = text.trim().substring(0,x);
        }else{
            name = replaceAmpersand(text);
            name = getSubString(name);
        }
        return name;
    }

    public static String extractFirstName (String text){
        if(text != null) {
            String[] names = text.trim().split(" ");
            return names[0];
        }
        return null;
    }

    public static String extractMiddleName (String text){
        String middleName = "";
        if(text != null){
            String[] names = text.trim().split(" ");
            if (names.length == 2 || names.length < 2){
                return "";
            }
            if (names.length == 3){
                return names[1];
            }
            if (names.length > 3){
                for(int i =0; i < names.length ;i++){
                    if(i != 0 && i != names.length -1){
                        middleName = middleName + " "+ names[i];
                    }
                }
                return middleName;
            }
        }
        return null;
    }

    public static String extractLastName (String text){
        if (text != null){
            String[] names = text.trim().split(" ");
            return names[names.length - 1];
        }
        return null;
    }

    private static String getSubString(String text){
        if (text != null) {
            if (text.trim().toLowerCase().contains("trustee")) {
                int trustee_index = text.trim().indexOf("trustee");
                if (!text.trim().toLowerCase().equals("trustee")) {
                    int space_index = text.trim().lastIndexOf(" ", trustee_index);
                    return text.trim().substring(space_index);
                } else {
                    int comma_index = text.trim().indexOf(",");
                    return text.trim().substring(comma_index + 1, trustee_index);
                }
            }
        }
        return null;
    }

    public static String filter(String text){
        if(text != null){
            text = text.trim();
            text = text.toLowerCase();
            text = text.replace("the", "");
            text = text.replace("irrevocable", "");
            text = text.replace("revocable", "");
            text = text.replace("trust", "");
            text = text.replace("joint", "");
            text = text.replace("living", "");
            text = text.replace("family", "");
            text = text.replace("amended", "");
            text = text.replace("holdings", "");
            text = text.replace("testamentary", "");
            text = text.replace("descendents", "");
            text = text.replace("descendants", "");
            text = text.replace("exemption", "");
            text = text.replace("exempt", "");
            text = text.replace("residuary", "");
            text = text.replace("estate", "");
            text = text.replace("mineral", "");
            text = text.replace("royalty", "");
            text = text.replace("interest", "");
            text = text.replace("real", "");
            text = text.replace("working", "");

            return text;
        }
        return null;
    }

    private static String replaceAmpersand(String text){
        if(text != null){
            return text.trim().replace("&","and");
        }
        return null;
    }



}
