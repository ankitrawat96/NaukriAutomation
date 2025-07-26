
public class Testcases {
    public static void main(String[] args) {
    String str="aeiousdfg ";
    findVowels(str);




    }

    public static void findVowels(String str){
        StringBuffer result=new StringBuffer();
        for(int i=0;i<str.length();i++){
            if (str.charAt(i)=='a'||str.charAt(i)=='e'||str.charAt(i)=='o'||str.charAt(i)=='i'||str.charAt(i)=='l'|| str.charAt(i)=='u'){
               result.append(str.charAt(i));

            }
        }
        System.out.println(result);
    }
}
