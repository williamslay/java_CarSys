package code;
import java.io.*;

public class User {
    public static String userName;
    private static String password;
    public int isAdmin;//0不是管理员，1是管理员，2为未注册用户
    public User (String userName,String password,int i)
    {
        this.userName = userName;
        this.password = password;
        this.isAdmin =i;
    }
    public void FindUser()
    {
        int flag = 0;//确认用户用flag
        FileReader fr = null;
        try {
            fr = new FileReader(new File("src/database/User.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(fr);
        String b = null;
        while (true) {
            try {
                if (!((b = bf.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println(b);
            int num[] = new int[]{0,0};
            for(int i=0,j=0;i<b.length();i++){
                char ch=b.charAt(i);
                if(ch==' '){
                    num[j++]=i;
                }
            }
            String u = b.substring(0,num[0]);
            String p = b.substring(num[0]+1,num[1]);
            int i =Integer.parseInt(b.substring(num[1]+1));
            if(u.equals(this.userName) &&p.equals(this.password))
            {
                this.isAdmin=i;//匹配到用户设置权限
                flag=1;
                return;
            }
        }
        //未匹配到用户返回失败虚拟用户
        if(flag==0)
        this.isAdmin=2;
    }
}
