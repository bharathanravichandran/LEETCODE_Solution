class Solution {
    public int countSeniors(String[] details) {
        String str="";
        int c=0;
        for(int i=0;i<details.length;i++){
           str=details[i];
        char c1=str.charAt(11);
        char c2=str.charAt(12);
           String con=""+c1+c2;
           int m=Integer.parseInt(con);
           if(m>60){
            c++;
           }

        }
        return c;

    }
}