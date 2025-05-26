package com.demo.constants;

public class Constants {
    /*
     * 169.10.11.23
     * URL
     * 
     * mvn -test production
     */

    public  final static String BASE_URL = "https://dummyjson.com";
    String env, BASE_URL1;

    public void setEnv(){
        if (env == "Staging") {
            BASE_URL1 = "https://dummyjson-staging.com";
        }else{
            BASE_URL1 = "https://dummyjson.com";
        }
    }
}
