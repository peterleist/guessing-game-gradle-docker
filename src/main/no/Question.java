import java.io.Serializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question extends Node implements Serializable{
    private static final long serialVersionUID = 1L;
    private Node right_a = null;
    private Node false_a = null;

    public Question(String question){
        super(question);
        
    }

    public void SetRA(String answer){
        right_a = new Answer(answer);
    }
    public Node GetRA(){
        return right_a;
    }
    public void SetFA(String answer){
        false_a = new Answer(answer);
    }
    public Node GetFA(){
        return false_a;
    }

    public boolean run() throws IOException, NullPointerException{

            if(right_a == null || false_a == null)
            { throw new NullPointerException(); 
                
            }
            else{
            System.out.println(this.getData());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String user_input = br.readLine();

            if(user_input == "y" && !right_a.run()) wrong_ans(true);
            else if(user_input == "n" && !false_a.run()) wrong_ans(false);
            else return false;
            }
        return true;
        
    }

    public void wrong_ans(boolean side) throws IOException, NullPointerException{

        String true_ans, true_ques;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
            System.out.println("Mire gondoltal?");
            true_ans = br.readLine();
            System.out.println("Mond egy krdest amire az igen valasz: " + true_ans);
            true_ques = br.readLine();
            System.out.println("A kedes: " + true_ques);
            if(side){
                Node temp;
                temp = right_a;
    
                right_a = new Question(true_ques);
                right_a.SetFA(temp.getData());
                right_a.SetRA(true_ans);
            }
            else{
                Node temp;
                temp = false_a;
    
                false_a = new Question(true_ques);
                false_a.SetFA(temp.getData());
                false_a.SetRA(true_ans); 
            }


        
        
    }
}