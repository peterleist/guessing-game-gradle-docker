import java.io.Serializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Answer extends Node implements Serializable{
    private static final long serialVersionUID = 1L;

    public Answer(String answer){
        super(answer);
    }

    public void SetRA(String a){}
    public void SetFA(String a){}

    public boolean run() throws IOException, NullPointerException{
            System.out.println(this.getData());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            switch(br.readLine()){
                case "y": return true;
                case "n": return false;
            }  
        return false;
    }

}