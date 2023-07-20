import java.io.*;

interface FileDataManager {
 
public static Object[] getData(String path) throws Exception {
    File file = new File(path);
    if (!file.exists()) {
        System.out.println("File does not exist");
        return null;
    }

    try (ObjectInputStream files = new ObjectInputStream(new FileInputStream(file))) {
        Object[] object = (Object[])files.readObject();
        if (object instanceof Object[]) {
            return  object;
        } else {
            throw new Exception("Invalid data format");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}

    public static void saveData(String path, Object[] obj)throws Exception{
        try(ObjectOutputStream files = new ObjectOutputStream(new FileOutputStream(path));){
            files.writeObject(obj);
            files.flush(); 
        }catch(Exception e){
            throw e;
        }
    }
}
