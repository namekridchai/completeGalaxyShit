public class MyArrayList {
    private char[] key = new char[10];
    private int size = 10,count = 0;
    public MyArrayList(){}
    
    public void add(char n){
        if(count!=size){
            key[count] = n;
            count++;    
        }
        else{
            for(int i = 1;i<size;i++){
                key[i-1] = key[i];
            }
            key[size-1] = n;
        
        }
    }
    
    public String ToString(){
        String n = "" ;
        for(int i =0;i<count;i++){
            n+=key[i];
        }
        return n;
    
    }
    
    public int checkWin(){
        String n= ToString();
        if(n.equals("uuddlrlrba"))
            return 1;
        else
            return 0;
    
    }
}
