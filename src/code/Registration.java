package code;

public class Registration {
    public  String province ="苏";
    public  String cityNumber;
    public  String number;
    public  String carNumber;
    public  String city;
    public  String owner;
    public  String car;
    public  String carType;
    public void setCarNumber(String str)
    {
        this.carNumber = new String (str);
        this.cityNumber = this.carNumber.substring(0,1);
        this.number = this.carNumber.substring(1);
    }
    public void setOwner(String str)
    {
        this.owner = new String(str);
    }
    public void setCar(String str)
    {
        this.car = new String(str);
    }
    public void setCarType(String str)
    {
        this.carType = new String(str);
    }
    public String FindCity()//由cityNumber标号确定所属城市
    {
        switch (this.cityNumber)
        {
            case "A":
                this.city = "南京";
                break;
            case "B":
                this.city = "无锡";
                break;
            case "C":
                this.city = "徐州";
                break;
            case "D":
                this.city = "常州";
                break;
            case "E":
                this.city = "苏州";
                break;
            case "F":
                this.city = "南通";
                break;
            case "G":
                this.city = "连云港";
                break;
            case "H":
                this.city = "淮安";
                break;
            case "J":
                this.city = "盐城";
                break;
            case "K":
                this.city = "扬州";
                break;
            case "L":
                this.city = "镇江";
                break;
            case "M":
                this.city = "泰州";
                break;
            case "N":
                this.city = "宿迁";
                break;
        }
        return this.city;
    }
    public String NumberCamp(String str)//返回两相比较中较小的字符串
    {
        char[] S_tem = new char[6];
        char[] tem = new char[6];
        S_tem=str.toCharArray();
        tem = this.carNumber.toCharArray();
        for(int i =0;i<6;i++)
        {
           if((int)tem[i]<(int)S_tem[i])
           {
               return this.carNumber;
           }
            if((int)tem[i]>(int)S_tem[i])
            {
                return str;
            }
        }
        return null;//若相同则返回null
    }
}
