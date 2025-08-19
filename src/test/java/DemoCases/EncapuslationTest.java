package DemoCases;

public class EncapuslationTest {
        private String  userName;
        private String password;

        public EncapuslationTest(){

        }

        public EncapuslationTest(String userName,String password){
            this.userName=userName;
            this.password=password;
        }

        public String getUserName(){
            return userName;
        }
        public void setUserName(String userName){
            this.userName=userName;
        }

        public String getPassword(){
            return password;
        }
        public void setPassword(String password){
            this.password=password;
        }


    public String toString(){
            return "Encapuslation [userName="+userName+",password="+password+"]";
        }






}
