import java.io.IOException;
import java.io.Serializable;

public abstract class Node implements Serializable{
    private static final long serialVersionUID = 1L;
    private String data;

    public String getData(){
        return data;
    }
    public Node(String question){
        data = question;
    }
    public abstract void SetRA(String answer);
    public abstract void SetFA(String answer);
    public abstract boolean run() throws IOException, NullPointerException;
}