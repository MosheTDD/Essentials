package me.moshe.training.file;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class ChatColorData {
    public static HashMap<UUID, String> chatColors = new HashMap<UUID, String>();
    public static String Path = "plugins/Training" + File.separator + "ChatColorMap.dat";

    public static void setup(){
        File file = new File(Path);
        new File("plugins/Training").mkdir();
        if(file.exists()){
            chatColors = load();
        }
        if(chatColors == null){
            chatColors = new HashMap<UUID, String>();
        }
    }

    public static void save(){
        File file = new File(Path);
        new File("plugins/Training").mkdir();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Path));
            oos.writeObject(chatColors);
            oos.flush();
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<UUID, String> load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Path));
            Object result = ois.readObject();
            ois.close();
            return (HashMap<UUID,String>)result;
        }catch(Exception e){
            return null;
        }
    }
}
