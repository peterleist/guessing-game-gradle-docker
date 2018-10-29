import java.io.*;

public class Serialize {
    public static Node deSer(){
        Node root = null;
        try {
            FileInputStream fileIn = new FileInputStream("tree.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            root = (Question) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        return root;
    }

    public static void Ser(Node root){
        try {
            FileOutputStream fileOut =
            new FileOutputStream("tree.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(root);
            out.close();
            fileOut.close();
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}