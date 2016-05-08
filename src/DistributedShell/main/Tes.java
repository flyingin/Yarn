package DistributedShell.main;

import java.util.Map;

/**
 * Created by yml on 16/5/8.
 */
public class Tes {
    public static void main(String []args){
        Map<String, String> envs = System.getenv();
        System.out.println(envs);
        if (envs.containsKey("SHELL")){
            System.out.println(envs.get("SHELL"));
        }else {
            System.out.println("222");
        }

    }
}
