
public class Player implements Comparable<Player>{
    private String name;
    private int score,min,second;
    
    public Player(String name,int score,int min,int second){
        this.name = name;
        this.score = score;
        this.min = min;
        this.second = second;
    }
    @Override
    public int compareTo(Player o) {
        if(this.score>o.score)
            return -1;
        else if(this.score<o.score)
            return 1;
        else{
            if(this.min>o.min)
                return 1;
            else if(this.min<o.min)
                return -1;
            else{
                if(this.second>o.second)
                    return 1;
                else if(this.second<o.second)
                    return -1;
                else{
                    if(this.name.compareTo(o.name)>0)
                            return 1;
                        else if(this.name.compareTo(o.name)<0)
                            return -1;
                        else
                            return 0;      
                }
            }
        
        }
    
    }
    public String toString(){
        return(name+","+score+","+min+","+second);
    }
    public String getName(){
        return name;
    }
    public String getScore(){  return ""+score;}
    public String getMin(){return ""+min;}
    public String getSec(){return ""+second;}
}
