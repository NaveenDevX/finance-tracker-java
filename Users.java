public class Users{
    private String username;
    private String password;

    public Users(String username, String password)
    {
         this.username= username;
         this.password= password;

    }
    public String getUserName(){
        return username;
    }
    public String getUserPassword(){
        return password;
    }
    public String Convert(){
        return username+','+password;
        }
    
}