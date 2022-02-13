package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class FuncPage  extends JFrame{
    private ArrayList<Registration> Re_Data = new  ArrayList<Registration>();
    public final JPanel[] panel1 = {new JPanel()};
    public JPanel panel = new JPanel();
    public JLabel BG=new JLabel();
    public JLabel Num=new JLabel();
    block[] ref = new block[13];//用于存储下标的索引表
    public  FuncPage()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);//固定大小
        setBounds(300, 100,1280,720);
    }
    //管理员界面
    public void AdminFuncPage(User user)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                re_Init();//引入数据库
                QucikSort(Re_Data);
            }
        }).start();
        panel.setBackground(new Color(250, 250, 250));
        panel.setBounds(0,0,1280,720);
        panel.setLayout(null);
        this.add(panel);
        //背景图片
        Image img1 = null;
        try {
            img1 = ImageIO.read(new File("src/img/AdminFuncBG.png"));
        } catch (IOException e) {
            System.out.println("not find img!!!");
        }
        ImageIcon bg = new ImageIcon(img1);
        bg = scaleImage(bg,1280,720);
        BG.setIcon(bg);
        BG.setBounds(0,0,1280,720);
        BG.setVisible(true);
        //时间显示
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String _Date ="当前时间："+sdf.format(date);// 输出已经格式化的现在时间（24小时制）
        JLabel Date=new JLabel();
        Date.setBounds(965,50,300,30);
        Date.setFont(new Font("宋体",Font.BOLD,25));
        Date.setForeground(Color.black);
        Date.setVisible(true);
        panel.add(Date);
        Date.setText(_Date);
        Date.setForeground(Color.black);
        //userName显示
        String _User = user.userName;
        JLabel User=new JLabel();
        User.setBounds(130,500,200,30);
        User.setFont(new Font("微软雅黑",Font.BOLD,25));
        User.setVisible(true);
        panel.add(User);
        User.setText(_User);
        User.setForeground(Color.black);
        //数据库内容显示
        String _Num = Integer.toString(Re_Data.size());
        Num.setBounds(200,560,200,30);
        Num.setFont(new Font("微软雅黑",Font.BOLD,25));
        Num.setVisible(true);
        panel.add(Num);
        Num.setText(_Num+"条");
        Num.setForeground(Color.black);
        //添加func功能按钮
        Func_Button Add = new Func_Button("增加数据");
        Func_Button Delet = new Func_Button("删除数据");
        Func_Button Change = new Func_Button("修改数据");
        Func_Button Return = new Func_Button("退出系统");
        Add.setBounds(130,90,120,40);
        panel.add(Add);
        Delet.setBounds(430,90,120,40);
        panel.add(Delet);
        Change.setBounds(730,90,120,40);
        panel.add(Change);
        Return.setBounds(1030,90,120,40);
        panel.add(Return);
        //预置为添加车牌信息的界面panel
        panel1[0] = new Func_AddNumber_Panel();
        panel.add(panel1[0]);
        panel.add(BG);
        panel.repaint();
        Add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.remove(panel1[0]);
                panel.remove(BG);
                panel.revalidate();
                panel1[0] = new Func_AddNumber_Panel();
                panel.add(panel1[0]);
                panel.add(BG);
                repaint();
            }
        });
        Delet.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.remove(panel1[0]);
                panel.remove(BG);
                panel.revalidate();
                panel1[0] =new Func_DelNumber_Panel();
                panel.add(panel1[0]);
                panel.add(BG);
                panel.repaint();
            }
        });
        Change.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.remove(panel1[0]);
                panel.remove(BG);
                panel.revalidate();
                panel1[0] = new Func_ChaNumber_Panel();
                panel.add(panel1[0]);
                panel.add(BG);
                panel.repaint();
            }
        });
        Return.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Object[] options ={ "退出系统", "取消退出" };  //自定义按钮上的文字
                int choice =JOptionPane.showOptionDialog(panel, "确认退出管理系统吗？", "退出系统",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                switch (choice)
                {
                    case 0 :
                        setVisible(false);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                re_Store();
                                HomePage home = new  HomePage();
                            }
                        }).start();
                        break;
                    case 1:
                        break;
                }
            }
        });
    }
    //用户界面
    public void UserFuncPage(User user)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                re_Init();//引入数据库
                QucikSort(Re_Data);
//                        for(int i=0;i<Re_Data.size();i++)
//        {
//            System.out.println(Re_Data.get(i).cityNumber+Re_Data.get(i).number);
//        }
            }
        }).start();
        panel.setBackground(new Color(250, 250, 250));
        panel.setBounds(0,0,1280,720);
        panel.setLayout(null);
        this.add(panel);
        //背景图片
        Image img1 = null;
        try {
            img1 = ImageIO.read(new File("src/img/UserFuncBG.png"));
        } catch (IOException e) {
            System.out.println("not find img!!!");
        }
        ImageIcon bg = new ImageIcon(img1);
        bg = scaleImage(bg,1280,720);
        BG.setIcon(bg);
        BG.setBounds(0,0,1280,720);
        BG.setVisible(true);
        //时间显示
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String _Date ="当前时间："+sdf.format(date);// 输出已经格式化的现在时间（24小时制）
        JLabel Date=new JLabel();
        Date.setBounds(965,50,300,30);
        Date.setFont(new Font("宋体",Font.BOLD,25));
        Date.setForeground(Color.black);
        Date.setVisible(true);
        panel.add(Date);
        Date.setText(_Date);
        Date.setForeground(Color.black);
        //userName显示
        String _User = user.userName;
        JLabel User1=new JLabel();
        User1.setBounds(1100,12,200,30);
        User1.setFont(new Font("宋体",Font.BOLD,25));
        User1.setVisible(true);
        panel.add(User1);
        User1.setText(_User);
        User1.setForeground(Color.black);
        JLabel User2=new JLabel();
        User2.setBounds(100,500,200,30);
        User2.setFont(new Font("微软雅黑",Font.BOLD,25));
        User2.setVisible(true);
        panel.add(User2);
        User2.setText(_User);
        User2.setForeground(Color.black);
        //添加func功能按钮
        Func_Button Find_Number = new Func_Button("车号牌查找");
        Func_Button Find_Owner = new Func_Button("车主查找");
        Func_Button Find_City = new Func_Button("城市查找");
        Func_Button Return = new Func_Button("退出系统");
        Find_Number.setBounds(130,90,120,40);
        panel.add(Find_Number);
        Find_Owner.setBounds(430,90,120,40);
        panel.add(Find_Owner);
        Find_City.setBounds(730,90,120,40);
        panel.add(Find_City);
        Return.setBounds(1030,90,120,40);
        panel.add(Return);
        //预置为按车牌号查找的界面panel
        panel1[0] = new Func_Number_Panel();
        panel.add(panel1[0]);
        panel.add(BG);
        panel.repaint();
        Find_Number.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.remove(panel1[0]);
                panel.remove(BG);
                panel.revalidate();
                panel1[0] = new Func_Number_Panel();
                panel.add(panel1[0]);
                panel.add(BG);
                repaint();
            }
        });
        Find_Owner.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.remove(panel1[0]);
                panel.remove(BG);
                panel.revalidate();
                panel1[0] =new Func_Owner_Panel();
                panel.add(panel1[0]);
                panel.add(BG);
                panel.repaint();
            }
        });
        Find_City.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel.remove(panel1[0]);
                panel.remove(BG);
                panel.revalidate();
                panel1[0] = new Func_City_Panel();
                panel.add(panel1[0]);
                panel.add(BG);
                panel.repaint();
            }
        });
        Return.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Object[] options ={ "退出系统", "取消退出" };  //自定义按钮上的文字
                int choice =JOptionPane.showOptionDialog(panel, "确认退出用户系统吗？", "退出系统",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                switch (choice)
                {
                    case 0 :
                        setVisible(false);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                HomePage home = new  HomePage();
                            }
                        }).start();
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    //对半查找车牌号
    public ArrayList<Registration> FindByNumber(ArrayList<Registration> Data,String number)
    {
        ArrayList<Registration> Result = new ArrayList<Registration>();
        int result_i = Binarysearch(number,Data,0,Data.size()-1);
        if(result_i==-1)
        {
            ;
        }
        else
        {
            Result.add(0,Data.get(result_i)) ;
        }
        return Result;
    }

    //顺序查找车主
    public ArrayList<Registration> FindByOwner(ArrayList<Registration> Data,String owner)
    {
        ArrayList<Registration> Result = new ArrayList<Registration>();
        int j=0;
        for(Registration ele:Data)
        {
            if(ele.owner.equals(owner)) {
                Result.add(j++, ele);
            }
        }
        return Result;
    }

    //分块索引城市
    public ArrayList<Registration> FindByCity(ArrayList<Registration> Data,String city)
    {
        int i=0,j=1;
        ArrayList<Registration> Result = new ArrayList<Registration>();
        ref[0] = new block();
        ref[0].City = Data.get(0).city;
        String nowCity =Data.get(0).city;
        ref[0].start = 0;
        for(Registration ele:Data)
        {
            if(!ele.city.equals(nowCity))
            {
                ref[j] = new block();
                ref[j].City = ele.city;
                ref[j++].start = i;
                nowCity = ele.city;
            }
            i++;
        }
        int start=0;
        int end=0;
        int flag =0;
        for(block ele: ref)
        {
            if(ele.City.equals(city))
            {
                start=ele.start;
                flag=1;
            }
            if(flag==1&&(!ele.City.equals(city)))
            {
                end = ele.start-1;
                flag=0;
                break;
            }
        }
        if(flag ==1)
            end=Data.size()-1;
        for(i =start,j=0;i<=end;i++)
        {
            Result.add(j++,Data.get(i));
        }
        return Result;
    }
    public class block{
       public String City;//该块部分代表的城市
       public int start;//该块部分起始下标
    }
    //对半查找函数
    int result=0;
    public int Binarysearch(String input, ArrayList<Registration> Data, int low, int high)
    {
        int mid =(high+low)/2;
        if(high>=low)
        {
            if(Data.get(mid).carNumber.equals(Data.get(mid).NumberCamp(input)))
                Binarysearch(input,Data,mid+1,high);
            else if(input.equals(Data.get(mid).NumberCamp(input)))
                Binarysearch(input,Data,low,mid-1);
            else
                result = mid;
        }
        else {
            result = -1;
        }
        return result;
    }
    //对读入文件内数据的数组采取快速排序
    public void QucikSort(ArrayList<Registration> Data)
    {
        Qucik_Sort(Data,0,Data.size()-1);
    }
    //数组快速排序递归函数
    public void Qucik_Sort(ArrayList<Registration> Data,int low,int high)
    {
        int k;
        if(low<high)
        {
            k=Partition(Data,low,high);
            Qucik_Sort(Data,low,k-1);
            Qucik_Sort(Data,k+1,high);
        }
    }
    //数组快速排序序列划分函数
    public int Partition(ArrayList<Registration> Data,int low,int high)
    {
        int i=low,j=high+1;
        String pivot = Data.get(low).carNumber;
        do{
            do i++;
            while (i<=high&&Data.get(i).carNumber.equals(Data.get(i).NumberCamp(pivot)));//i前进
            do j--;
            while (pivot.equals(Data.get(j).NumberCamp(pivot)));//j前进
            if(i<j)
                Swap(Data,i,j);
        }while (i<j);
        Swap(Data,low,j);
        return j;
    }
    //数组交换函数
    public void Swap(ArrayList<Registration> Data,int i,int j)
    {
        Registration a = new Registration();
        a = Data.get(i);
        Data.set(i,Data.get(j));
        Data.set(j,a);
    }
    //该函数用于数据库读入
    public void re_Init()
    {
        FileReader fr = null;
        try {
            fr = new FileReader(new File("src/database/RegisInfor.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String b = new String();
        BufferedReader bf = new BufferedReader(fr);
        while (true) {
            try {
                if (!((b = bf.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            int num[] = new int[]{0, 0,0};
            for (int i = 0, j = 0; i < b.length(); i++) {
                char ch = b.charAt(i);
                if (ch == ' ') {
                    num[j++] = i;
                }
            }
            Registration a = new Registration();
            a.setCarNumber(b.substring(0, num[0]));
            a.setCar(b.substring(num[0] + 1, num[1]));
            a.setCarType(b.substring(num[1] + 1, num[2]));
            a.setOwner(b.substring(num[2] + 1));
            a.FindCity();
            this.Re_Data.add(a);
        }
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //自定义存入数据库
    public void re_Store()
    {
        QucikSort(Re_Data);
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File("src/database/RegisInfor.txt"),false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            String data =new String();
            for(Registration ele :Re_Data)
            {
                data = ele.carNumber+" "+ele.car+" "+ele.carType+" "+ele.owner+"\n";
                fw.write(data);
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //该函数用于按照比例拉伸ImageIcon
    public static ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();
        if (nw > w) {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if (nh > h) {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }
        icon = new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_SMOOTH));
        return icon;
    }
    //自定义增加号码功能面板
    public class Func_AddNumber_Panel extends JPanel
    {
        public Func_AddNumber_Panel()
        {
            setBackground(new Color(243, 240, 239));
            setBounds(350,140,915,540);
            setLayout(null);
            setVisible(true);
            //
            JLabel FuncDes=new JLabel();
            FuncDes.setBounds(100,60,500,50);
            FuncDes.setFont(new Font("微软雅黑",Font.BOLD,40));
            FuncDes.setForeground(Color.black);
            FuncDes.setVisible(true);
            FuncDes.setText("添加车牌信息");
            FuncDes.setForeground(Color.black);
            add(FuncDes);
            //
            JLabel Su=new JLabel();
            Su.setBounds(100,120,500,50);
            Su.setFont(new Font("微软雅黑",Font.BOLD,30));
            Su.setForeground(Color.black);
            Su.setVisible(true);
            Su.setText("车牌：苏");
            Su.setForeground(Color.black);
            add(Su);
            JTextField carNumber = new Infor_textfield(6);
            carNumber.setBounds(230,135,200,32);
            add(carNumber);
            JLabel Remind=new JLabel();
            Remind.setBounds(430,120,300,50);
            Remind.setFont(new Font("微软雅黑",Font.BOLD,30));
            Remind.setForeground(Color.black);
            Remind.setVisible(false);
            Remind.setText("车牌号已存在！！！");
            Remind.setForeground(Color.black);
            add(Remind);
            //
            JLabel City=new JLabel();
            City.setBounds(100,160,500,50);
            City.setFont(new Font("微软雅黑",Font.BOLD,30));
            City.setForeground(Color.black);
            City.setVisible(true);
            City.setText("归属地:");
            City.setForeground(Color.black);
            add(City);
            JLabel City1=new JLabel();
            City1.setBounds(220,160,500,50);
            City1.setFont(new Font("微软雅黑",Font.BOLD,30));
            City1.setForeground(Color.black);
            City1.setVisible(true);
            City1.setText("");
            City1.setForeground(Color.black);
            add(City1);
            //
            JLabel Name=new JLabel();
            Name.setBounds(100,200,500,50);
            Name.setFont(new Font("微软雅黑",Font.BOLD,30));
            Name.setForeground(Color.black);
            Name.setVisible(true);
            Name.setText("车主姓名：");
            Name.setForeground(Color.black);
            add(Name);
            JTextField owner = new Infor_textfield(6);
            owner.setBounds(240,215,270,32);
            add(owner);
            //
            JLabel Car=new JLabel();
            Car.setBounds(100,240,500,50);
            Car.setFont(new Font("微软雅黑",Font.BOLD,30));
            Car.setForeground(Color.black);
            Car.setVisible(true);
            Car.setText("车辆品牌：");
            Car.setForeground(Color.black);
            add(Car);
            JTextField carInfor = new Infor_textfield(8);
            carInfor.setBounds(240,255,270,32);
            add(carInfor);
            //
            JLabel CarType=new JLabel();
            CarType.setBounds(100,280,500,50);
            CarType.setFont(new Font("微软雅黑",Font.BOLD,30));
            CarType.setForeground(Color.black);
            CarType.setVisible(true);
            CarType.setText("汽车类型：");
            CarType.setForeground(Color.black);
            add(CarType);
            // 需要选择的条目
            String[] listData = new String[]{"小型轿车", "新能源汽车"};
            // 创建一个下拉列表框
            final JComboBox<String> comboBox = new JComboBox<String>(listData);
            // 设置默认选中的条目
            comboBox.setSelectedIndex(0);
            comboBox.setFont(new Font("微软雅黑",Font.BOLD,25));
            comboBox.setBounds(240,290,150,35);
            // 添加到内容面板
            add(comboBox);
            //
            JButton AddButton=new Func_Button("添加");
            AddButton.setBounds(200,350,115,35);
            add(AddButton);
            //
            Document document = carNumber.getDocument();
            document.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try{
                        Document doc = e.getDocument();
                        String numbers =doc.getText(0,doc.getLength());
                        char[] tem = new char[6];
                        tem = numbers.toCharArray();
                        String city =new String();
                        switch (tem[0])
                        {
                            case 'A':
                                city = "南京";
                                break;
                            case 'B':
                                city = "无锡";
                                break;
                            case 'C':
                                city = "徐州";
                                break;
                            case 'D':
                                city = "常州";
                                break;
                            case 'E':
                                city = "苏州";
                                break;
                            case 'F':
                                city = "南通";
                                break;
                            case 'G':
                                city = "连云港";
                                break;
                            case 'H':
                                city = "淮安";
                                break;
                            case 'J':
                                city = "盐城";
                                break;
                            case 'K':
                                city = "扬州";
                                break;
                            case 'L':
                                city = "镇江";
                                break;
                            case 'M':
                                city = "泰州";
                                break;
                            case 'N':
                                city = "宿迁";
                                break;
                            default:
                                city = "输入有误";
                                break;
                        }
                        City1.setText(city);
                        if(tem.length==6)
                        {
                            for(Registration ele:Re_Data)
                            {
                                if(numbers.equals(ele.carNumber))
                                {
                                    Remind.setVisible(true);
                                }
                            }
                        }
                        panel1[0].repaint();
                    }catch (BadLocationException e1)
                    {
                        e1.printStackTrace();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    Document doc = e.getDocument();
                    String numbers = null;
                    try {
                        numbers = doc.getText(0,doc.getLength());
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                  if (numbers.equals(""))
                  {
                      City1.setText("");
                      panel1[0].repaint();
                  }
                    if(numbers.length()!=6)
                    {
                        Remind.setVisible(false);
                        panel1[0].repaint();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
            //
            AddButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String numbers=carNumber.getText();
                    String Owner =owner.getText();
                    String car = carInfor.getText();
                    String carType = (String) comboBox.getSelectedItem();
                    if(numbers.length()!=6)
                    {
                        JOptionPane.showConfirmDialog( panel, "您填写车号牌长度不符合要求！！！", "错误提示",JOptionPane.DEFAULT_OPTION);
                    }
                    else if(Owner.equals(""))
                    {
                        JOptionPane.showConfirmDialog( panel, "您未填写车主信息！！！","错误提示",JOptionPane.DEFAULT_OPTION);
                    }
                    else if(car.equals(""))
                    {
                        JOptionPane.showConfirmDialog( panel, "您未填写车辆品牌信息！！！","错误提示",JOptionPane.DEFAULT_OPTION);
                    }
                    else
                    {
                        char[] tem = new char[6];
                        int flag = 0;
                        tem = numbers.toCharArray();
                        for(char c :tem) {
                            if (c<48||(c>59&&c<65)||c>90) {
                                JOptionPane.showConfirmDialog(panel, "您未规范填写车号牌信息！！！", "错误提示", JOptionPane.DEFAULT_OPTION);
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0)
                        {
                            Registration newOne = new Registration();
                            newOne.setCarNumber(numbers);
                            newOne.setOwner(Owner);
                            newOne.setCar(car);
                            newOne.setCarType(carType);
                            Re_Data.add(Re_Data.size(),newOne);
                            panel1[0].removeAll();
                            JLabel Des=new JLabel();
                            Des.setBounds(100,60,500,50);
                            Des.setFont(new Font("微软雅黑",Font.BOLD,40));
                            Des.setForeground(Color.black);
                            Des.setVisible(true);
                            Des.setText("您已成功添加数据！！！");
                            Des.setForeground(Color.black);
                            add(Des);
                            String _Num = Integer.toString(Re_Data.size());
                            panel1[0].repaint();
                            Num.setText(_Num+"条");
                            panel.repaint();
                        }
                    }
                }
            });
        }
    }
    //自定义删除号码功能面板
    public class Func_DelNumber_Panel extends JPanel
    {
        public Func_DelNumber_Panel() {
            setBackground(new Color(243, 240, 239));
            setBounds(350, 140, 915, 540);
            setLayout(null);
            setVisible(true);
            JLabel Des=new JLabel();
            Des.setBounds(20,10,900,30);
            Des.setFont(new Font("宋体",Font.BOLD,25));
            Des.setForeground(Color.black);
            Des.setVisible(true);
            Des.setText("车牌号        汽车信息        汽车类型        车主信息        操作");
            Des.setForeground(Color.black);
            add(Des);
            if(Re_Data.size()<=10)
            {
                for(int i=0;i<Re_Data.size();i++)
                {
                    De_Line line = new De_Line(Re_Data.get(i),i);
                    add(line);
                }
            }
            else
            {
                final int[] nowPage = {0};
                String NowPage ="第"+Integer.toString(nowPage[0] +1)+"页";
                int pagenum = (int) Math.ceil(Re_Data.size()/10.0);
                String page_num ="/总"+Integer.toString(pagenum)+"页";
                De_Line[] De_Pages = new De_Line[10];
                QucikSort(Re_Data);
                for(int i=0;i<10;i++)
                {
                    De_Pages[i] = new De_Line(Re_Data.get(i),i);
                    add(De_Pages[i]);
                }
                JLabel Page_num=new JLabel();
                Page_num.setBounds(800,500,100,30);
                Page_num.setFont(new Font("宋体",Font.BOLD,25));
                Page_num.setForeground(Color.black);
                Page_num.setVisible(true);
                Page_num.setText(page_num);
                Page_num.setForeground(Color.black);
                add(Page_num);
                JLabel Now_Page=new JLabel();
                Now_Page.setBounds(720,500,90,30);
                Now_Page.setFont(new Font("宋体",Font.BOLD,25));
                Now_Page.setForeground(Color.black);
                Now_Page.setVisible(true);
                Now_Page.setText(NowPage);
                Now_Page.setForeground(Color.black);
                add(Now_Page);
                JButton NextP=new Func_Button(">");
                NextP.setBounds(650,500,50,30);
                add(NextP);
                JButton LastP=new Func_Button("<");
                LastP.setBounds(590,500,50,30);
                add(LastP);
                if(nowPage[0] ==0)
                    remove(LastP);
                NextP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nowPage[0]++;
                        int pagenum = (int) Math.ceil(Re_Data.size()/10.0);
                        String page_num ="/总"+Integer.toString(pagenum)+"页";
                        String now = "第"+Integer.toString(nowPage[0]+1)+"页";
                        Now_Page.setText(now);
                        Page_num.setText(page_num);
                        if(nowPage[0] ==pagenum-1)
                        {
                            remove(NextP);
                            int num =Re_Data.size()-nowPage[0]*10;
                            for(int i=0;i<10;i++)
                                De_Pages[i].setVisible(false);
                            for(int i=0;i<num;i++)
                            {
                                De_Pages[i].setVisible(true);
                                De_Pages[i].change(Re_Data.get(i+10*nowPage[0]));
                            }
                        }
                        if(nowPage[0]!=0)
                           add(LastP);
                        if(nowPage[0]!=pagenum-1)
                        {
                            for(int i=0;i<10;i++)
                            {
                                De_Pages[i].setVisible(true);
                                De_Pages[i].change(Re_Data.get(i+10*nowPage[0]));
                            }
                        }
                        repaint();
                    }
                });
                LastP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nowPage[0]--;
                        int pagenum = (int) Math.ceil(Re_Data.size()/10);
                        String page_num ="/总"+Integer.toString(pagenum)+"页";
                        String now = "第"+Integer.toString(nowPage[0]+1)+"页";
                        Page_num.setText(page_num);
                        Now_Page.setText(now);

                        if(nowPage[0] ==0)
                            remove(LastP);
                        if(nowPage[0]!=pagenum)
                            add(NextP);
                        for(int i=0;i<10;i++)
                        {
                            De_Pages[i].setVisible(true);
                            De_Pages[i].change(Re_Data.get(i+10*nowPage[0]));
                        }
                        repaint();
                    }
                });
            }
        }
    }
    //自定义修改号码功能面板
    public class Func_ChaNumber_Panel extends JPanel
    {
        public Func_ChaNumber_Panel() {
            setBackground(new Color(243, 240, 239));
            setBounds(350, 140, 915, 540);
            setLayout(null);
            setVisible(true);
            JLabel Des=new JLabel();
            Des.setBounds(20,10,900,30);
            Des.setFont(new Font("宋体",Font.BOLD,25));
            Des.setForeground(Color.black);
            Des.setVisible(true);
            Des.setText("车牌号        汽车信息        汽车类型        车主信息        操作");
            Des.setForeground(Color.black);
            add(Des);
            if(Re_Data.size()<=10)
            {
                for(int i=0;i<Re_Data.size();i++)
                {
                    Ch_Line line = new Ch_Line(Re_Data.get(i),i);
                    add(line);
                }
            }
            else
            {
                final int[] nowPage = {0};
                String NowPage ="第"+Integer.toString(nowPage[0] +1)+"页";
                int pagenum = (int) Math.ceil(Re_Data.size()/10.0);
                String page_num ="/总"+Integer.toString(pagenum)+"页";
                QucikSort(Re_Data);
                Ch_Line[] Ch_Pages = new Ch_Line[10];
                for(int i=0;i<10;i++)
                {
                    Ch_Pages[i] = new Ch_Line(Re_Data.get(i),i);
                    add(Ch_Pages[i]);
                }
                JLabel Page_num=new JLabel();
                Page_num.setBounds(800,500,100,30);
                Page_num.setFont(new Font("宋体",Font.BOLD,25));
                Page_num.setForeground(Color.black);
                Page_num.setVisible(true);
                Page_num.setText(page_num);
                Page_num.setForeground(Color.black);
                add(Page_num);
                JLabel Now_Page=new JLabel();
                Now_Page.setBounds(720,500,90,30);
                Now_Page.setFont(new Font("宋体",Font.BOLD,25));
                Now_Page.setForeground(Color.black);
                Now_Page.setVisible(true);
                Now_Page.setText(NowPage);
                Now_Page.setForeground(Color.black);
                add(Now_Page);
                JButton NextP=new Func_Button(">");
                NextP.setBounds(650,500,50,30);
                add(NextP);
                JButton LastP=new Func_Button("<");
                LastP.setBounds(590,500,50,30);
                add(LastP);
                if(nowPage[0] ==0)
                    remove(LastP);
                NextP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nowPage[0]++;
                        int pagenum = (int) Math.ceil(Re_Data.size()/10.0);
                        String page_num ="/总"+Integer.toString(pagenum)+"页";
                        String now = "第"+Integer.toString(nowPage[0]+1)+"页";
                        Now_Page.setText(now);
                        Page_num.setText(page_num);
                        if(nowPage[0] ==pagenum-1)
                        {
                            remove(NextP);
                            int num =Re_Data.size()-nowPage[0]*10;
                            for(int i=0;i<10;i++)
                                Ch_Pages[i].setVisible(false);
                            for(int i=0;i<num;i++)
                            {
                                Ch_Pages[i].setVisible(true);
                                Ch_Pages[i].change(Re_Data.get(i+10*nowPage[0]));
                            }
                        }
                        if(nowPage[0]!=0)
                            add(LastP);
                        if(nowPage[0]!=pagenum-1)
                        {
                            for(int i=0;i<10;i++)
                            {
                                Ch_Pages[i].setVisible(true);
                                Ch_Pages[i].change(Re_Data.get(i+10*nowPage[0]));
                            }
                        }
                        repaint();
                    }
                });
                LastP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nowPage[0]--;
                        int pagenum = (int) Math.ceil(Re_Data.size()/10);
                        String page_num ="/总"+Integer.toString(pagenum)+"页";
                        String now = "第"+Integer.toString(nowPage[0]+1)+"页";
                        Page_num.setText(page_num);
                        Now_Page.setText(now);

                        if(nowPage[0] ==0)
                            remove(LastP);
                        if(nowPage[0]!=pagenum-1)
                            add(NextP);
                        for(int i=0;i<10;i++)
                        {
                            Ch_Pages[i].setVisible(true);
                            Ch_Pages[i].change(Re_Data.get(i+10*nowPage[0]));
                        }
                        repaint();
                    }
                });
            }
        }
    }
    //自定义号码牌查照功能面板
    public class Func_Number_Panel extends JPanel
    {
        public Func_Number_Panel()
        {
            setBackground(new Color(243, 240, 239));
            setBounds(350,140,915,540);
            setLayout(null);
            setVisible(true);
            JLabel FuncDes=new JLabel();
            FuncDes.setBounds(100,60,500,50);
            FuncDes.setFont(new Font("微软雅黑",Font.BOLD,40));
            FuncDes.setForeground(Color.black);
            FuncDes.setVisible(true);
            FuncDes.setText("使用车牌号查询车牌信息");
            FuncDes.setForeground(Color.black);
            add(FuncDes);
            JLabel Su=new JLabel();
            Su.setBounds(100,120,500,50);
            Su.setFont(new Font("微软雅黑",Font.BOLD,30));
            Su.setForeground(Color.black);
            Su.setVisible(true);
            Su.setText("苏");
            Su.setForeground(Color.black);
            add(Su);
            JTextField search = new search_textfield(6);
            search.setBounds(150,133,250,30);
            add(search);
            JButton Schbutton=new Func_Button("查询");
            Schbutton.setBounds(220,180,115,35);
            add(Schbutton);
            Schbutton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String numbers=search.getText();
                    if(numbers.length()!=6)
                    {
                        JOptionPane.showConfirmDialog( panel, "您输入的车牌号有误！！！","错误提示",JOptionPane.DEFAULT_OPTION);
                    }
                    //查询函数
                    JPanel re_panel= new Result_Panel(FindByNumber(Re_Data,numbers));
                    removeAll();
                    setVisible(false);
                    panel.remove(panel1[0]);
                    panel.remove(BG);
                    panel.revalidate();
                    panel1[0] = re_panel;
                    panel.add(panel1[0]);
                    panel.add(BG);
                    repaint();
                }
            });
        }
    }
    //自定义车主查照功能面板
    public class Func_Owner_Panel extends JPanel
    {
        public Func_Owner_Panel() {
            setBackground(new Color(243, 240, 239));
            setBounds(350,140,915,540);
            setLayout(null);
            setVisible(true);
            JLabel FuncDes=new JLabel();
            FuncDes.setBounds(100,60,500,50);
            FuncDes.setFont(new Font("微软雅黑",Font.BOLD,40));
            FuncDes.setForeground(Color.black);
            FuncDes.setVisible(true);
            FuncDes.setText("使用车主信息查询车牌信息");
            FuncDes.setForeground(Color.black);
            add(FuncDes);
            JLabel Name=new JLabel();
            Name.setBounds(100,120,500,50);
            Name.setFont(new Font("微软雅黑",Font.BOLD,30));
            Name.setForeground(Color.black);
            Name.setVisible(true);
            Name.setText("姓名:");
            Name.setForeground(Color.black);
            add(Name);
            JTextField search = new search_textfield(6);
            search.setBounds(170,133,230,35);
            add(search);
            JButton Schbutton=new Func_Button("查询");
            Schbutton.setBounds(220,180,115,35);
            add(Schbutton);
            Schbutton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String owner=search.getText();
                    if(!owner.equals(""))
                    {
                        //查询函数
                        JPanel re_panel= new Result_Panel(FindByOwner(Re_Data,owner));
                        removeAll();
                        setVisible(false);
                        panel.remove(panel1[0]);
                        panel.remove(BG);
                        panel.revalidate();
                        panel1[0] = re_panel;
                        panel.add(panel1[0]);
                        panel.add(BG);
                        repaint();
                    }
                    else
                    {
                        JOptionPane.showConfirmDialog( panel, "您未输入车主信息！！！","错误提示",JOptionPane.DEFAULT_OPTION);
                    }
                }
            });
        }
    }
    //自定义城市查照功能面板
    public class Func_City_Panel extends JPanel
    {
        public Func_City_Panel()
        {
            setBackground(new Color(243, 240, 239));
            setBounds(350,140,915,540);
            setLayout(null);
            setVisible(true);
            JLabel FuncDes=new JLabel();
            FuncDes.setBounds(100,60,500,50);
            FuncDes.setFont(new Font("微软雅黑",Font.BOLD,40));
            FuncDes.setForeground(Color.black);
            FuncDes.setVisible(true);
            FuncDes.setText("根据城市查询车牌信息");
            FuncDes.setForeground(Color.black);
            add(FuncDes);
            JLabel City=new JLabel();
            City.setBounds(100,120,500,50);
            City.setFont(new Font("微软雅黑",Font.BOLD,30));
            City.setForeground(Color.black);
            City.setVisible(true);
            City.setText("城市：");
            City.setForeground(Color.black);
            add(City);

            // 需要选择的条目
            String[] listData = new String[]{"南京", "无锡", "徐州", "常州","苏州","南通","连云港","淮安","盐城","扬州","镇江","泰州","宿迁"};

            // 创建一个下拉列表框
            final JComboBox<String> comboBox = new JComboBox<String>(listData);

            // 添加条目选中状态改变的监听器
//            comboBox.addItemListener(new ItemListener() {
//                @Override
//                public void itemStateChanged(ItemEvent e) {
//                    // 只处理选中的状态
//                    if (e.getStateChange() == ItemEvent.SELECTED) {
//                        System.out.println("选中: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
//                    }
//                }
//            });
            // 设置默认选中的条目
            comboBox.setSelectedIndex(0);
            comboBox.setFont(new Font("微软雅黑",Font.BOLD,25));
            comboBox.setBounds(200,130,150,35);
            // 添加到内容面板
            add(comboBox);
            //添加查询按钮
            JButton Schbutton=new Func_Button("查询");
            Schbutton.setBounds(220,180,115,35);
            add(Schbutton);
            Schbutton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String city= String.valueOf(comboBox.getSelectedItem());
                    //查询函数
                    JPanel re_panel= new Result_Panel(FindByCity(Re_Data,city));
                    removeAll();
                    setVisible(false);
                    panel.remove(panel1[0]);
                    panel.remove(BG);
                    panel.revalidate();
                    panel1[0] = re_panel;
                    panel.add(panel1[0]);
                    panel.add(BG);
                    repaint();
                }
            });
        }
    }
    //自定义结果输出面板
    public class Result_Panel extends JPanel
    {
        public Result_Panel(ArrayList<Registration> result) {
            setBackground(new Color(243, 240, 239));
            setBounds(350, 140, 915, 540);
            setLayout(null);
            setVisible(true);
            JLabel Des=new JLabel();
            Des.setBounds(20,10,900,30);
            Des.setFont(new Font("宋体",Font.BOLD,25));
            Des.setForeground(Color.black);
            Des.setVisible(true);
            Des.setText("车牌号            汽车信息            汽车类型            车主信息");
            Des.setForeground(Color.black);
            add(Des);
            if(result.size()!=0)
            {
                for(int i=0;i<result.size();i++)
                {
                    //车牌号
                    JLabel Re_number=new JLabel();
                    Re_number.setBounds(20,45*(i+1),100,30);
                    Re_number.setFont(new Font("宋体",Font.BOLD,20));
                    Re_number.setForeground(Color.black);
                    Re_number.setVisible(true);
                    Re_number.setText(result.get(i).province+result.get(i).carNumber);
                    //汽车信息
                    JLabel Re_Car=new JLabel();
                    Re_Car.setBounds(255,45*(i+1),200,30);
                    Re_Car.setFont(new Font("宋体",Font.BOLD,20));
                    Re_Car.setForeground(Color.black);
                    Re_Car.setVisible(true);
                    Re_Car.setText(result.get(i).car);
                    //汽车类型
                    JLabel Re_CarInfor=new JLabel();
                    Re_CarInfor.setBounds(515,45*(i+1),200,30);
                    Re_CarInfor.setFont(new Font("宋体",Font.BOLD,20));
                    Re_CarInfor.setForeground(Color.black);
                    Re_CarInfor.setVisible(true);
                    Re_CarInfor.setText(result.get(i).carType);
                    //车主信息
                    JLabel Re_owner =new JLabel();
                    Re_owner.setBounds(775,45*(i+1),100,30);
                    Re_owner.setFont(new Font("宋体",Font.BOLD,20));
                    Re_owner.setForeground(Color.black);
                    Re_owner.setVisible(true);
                    Re_owner.setText(result.get(i).owner);
                    add(Re_number);
                    add(Re_Car);
                    add(Re_CarInfor);
                    add(Re_owner);
                }
            }
            else
            {
                JLabel Err=new JLabel();
                Err.setBounds(200,100,600,30);
                Err.setFont(new Font("宋体",Font.BOLD,25));
                Err.setForeground(Color.black);
                Err.setVisible(true);
                Err.setText("未找到匹配信息！！！");
                Err.setForeground(Color.black);
                add(Err);
            }
        }
    }
    //自定义删除行信息
    public class De_Line extends JLabel
    {
        public JLabel Re_number=new JLabel();
        public JLabel Re_Car=new JLabel();
        public JLabel Re_CarInfor=new JLabel();
        public JLabel Re_owner =new JLabel();
        public JButton DelButton=new Func_Button("删除");
        public Registration object;
        public int line;
        public  De_Line(Registration object,int line)
        {
            this.object=object;
            this.line=line;
            setBounds(0,45*(line+1),915,35);
            setLayout(null);
            setVisible(true);
            //车牌号
            Re_number.setBounds(20,5,100,30);
            Re_number.setFont(new Font("宋体",Font.BOLD,20));
            Re_number.setForeground(Color.black);
            Re_number.setVisible(true);
            Re_number.setText(object.province+object.carNumber);
            //汽车信息
            Re_Car.setBounds(200,5,200,30);
            Re_Car.setFont(new Font("宋体",Font.BOLD,20));
            Re_Car.setForeground(Color.black);
            Re_Car.setVisible(true);
            Re_Car.setText(object.car);
            //汽车类型
            Re_CarInfor.setBounds(405,5,200,30);
            Re_CarInfor.setFont(new Font("宋体",Font.BOLD,20));
            Re_CarInfor.setForeground(Color.black);
            Re_CarInfor.setVisible(true);
            Re_CarInfor.setText(object.carType);
            //车主信息
            Re_owner.setBounds(620,5,100,30);
            Re_owner.setFont(new Font("宋体",Font.BOLD,20));
            Re_owner.setForeground(Color.black);
            Re_owner.setVisible(true);
            Re_owner.setText(object.owner);
            //删除按钮
            DelButton.setBounds(800,0,100,35);
            add(Re_number);
            add(Re_Car);
            add(Re_CarInfor);
            add(Re_owner);
            add(DelButton);
            //
            DelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] options ={ "确认删除", "取消删除" };  //自定义按钮上的文字
                    int choice =JOptionPane.showOptionDialog(panel, "确认删除信息吗？",
                            "删除信息",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    switch (choice)
                    {
                        case 0 :
                            for(Registration ele :Re_Data)
                            {
                                if(ele==object)
                                {
                                    Re_Data.remove(object);
                                    break;
                                }
                            }
                            panel1[0].removeAll();
                            JLabel Des=new JLabel();
                            Des.setBounds(100,60,500,50);
                            Des.setFont(new Font("微软雅黑",Font.BOLD,40));
                            Des.setForeground(Color.black);
                            Des.setVisible(true);
                            Des.setText("您已成功删除数据！！！");
                            Des.setForeground(Color.black);
                            panel1[0].add(Des);
                            String _Num = Integer.toString(Re_Data.size());
                            Num.setText(_Num+"条");
                            panel.repaint();
                            break;
                        case 1:
                            break;
                    }
                }
            });
        }
        public void change(Registration object)
        {
            this.object=object;
            //车牌号
            Re_number.setText(object.province+object.carNumber);
            //汽车信息
            Re_Car.setText(object.car);
            //汽车类型
            Re_CarInfor.setText(object.carType);
            //车主信息
            Re_owner.setText(object.owner);
            repaint();
        }
    }
    //自定义修改行信息
    public class Ch_Line extends JLabel
    {
        public JLabel Re_number=new JLabel();
        public JLabel Re_Car=new JLabel();
        public JLabel Re_CarInfor=new JLabel();
        public JLabel Re_owner =new JLabel();
        public JButton ChaButton=new Func_Button("修改");
        public Registration object;
        public int line;
        public  Ch_Line(Registration object,int line)
        {
            this.object=object;
            this.line=line;
            setBounds(0,45*(line+1),915,35);
            setLayout(null);
            setVisible(true);
            //车牌号
            Re_number.setBounds(20,5,100,30);
            Re_number.setFont(new Font("宋体",Font.BOLD,20));
            Re_number.setForeground(Color.black);
            Re_number.setVisible(true);
            Re_number.setText(object.province+object.carNumber);
            //汽车信息
            Re_Car.setBounds(200,5,200,30);
            Re_Car.setFont(new Font("宋体",Font.BOLD,20));
            Re_Car.setForeground(Color.black);
            Re_Car.setVisible(true);
            Re_Car.setText(object.car);
            //汽车类型
            Re_CarInfor.setBounds(405,5,200,30);
            Re_CarInfor.setFont(new Font("宋体",Font.BOLD,20));
            Re_CarInfor.setForeground(Color.black);
            Re_CarInfor.setVisible(true);
            Re_CarInfor.setText(object.carType);
            //车主信息
            Re_owner.setBounds(620,5,100,30);
            Re_owner.setFont(new Font("宋体",Font.BOLD,20));
            Re_owner.setForeground(Color.black);
            Re_owner.setVisible(true);
            Re_owner.setText(object.owner);
            //修改按钮
            ChaButton.setBounds(800,0,100,35);
            add(Re_number);
            add(Re_Car);
            add(Re_CarInfor);
            add(Re_owner);
            add(ChaButton);
            //
            ChaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel1[0].removeAll();
                    JLabel Des=new JLabel();
                    Des.setBounds(100,50,500,50);
                    Des.setFont(new Font("微软雅黑",Font.BOLD,40));
                    Des.setForeground(Color.black);
                    Des.setVisible(true);
                    Des.setText("请修改数据：");
                    Des.setForeground(Color.black);
                    panel1[0].add(Des);
                    //
                    JLabel Su=new JLabel();
                    Su.setBounds(100,120,500,50);
                    Su.setFont(new Font("微软雅黑",Font.BOLD,30));
                    Su.setForeground(Color.black);
                    Su.setVisible(true);
                    Su.setText("车牌：苏"+object.carNumber);
                    Su.setForeground(Color.black);
                    panel1[0].add(Su);
                    //
                    JLabel City=new JLabel();
                    City.setBounds(100,160,500,50);
                    City.setFont(new Font("微软雅黑",Font.BOLD,30));
                    City.setForeground(Color.black);
                    City.setVisible(true);
                    City.setText("归属地:"+object.city);
                    City.setForeground(Color.black);
                    panel1[0].add(City);
                    JLabel City1=new JLabel();
                    City1.setBounds(220,160,500,50);
                    City1.setFont(new Font("微软雅黑",Font.BOLD,30));
                    City1.setForeground(Color.black);
                    City1.setVisible(true);
                    City1.setText("");
                    City1.setForeground(Color.black);
                    panel1[0].add(City1);
                    //
                    JLabel Name=new JLabel();
                    Name.setBounds(100,200,500,50);
                    Name.setFont(new Font("微软雅黑",Font.BOLD,30));
                    Name.setForeground(Color.black);
                    Name.setVisible(true);
                    Name.setText("车主姓名：");
                    Name.setForeground(Color.black);
                    panel1[0].add(Name);
                    JTextField owner = new Infor_textfield(6);
                    owner.setBounds(240,215,270,32);
                    panel1[0].add(owner);
                    //
                    JLabel Car=new JLabel();
                    Car.setBounds(100,240,500,50);
                    Car.setFont(new Font("微软雅黑",Font.BOLD,30));
                    Car.setForeground(Color.black);
                    Car.setVisible(true);
                    Car.setText("车辆品牌：");
                    Car.setForeground(Color.black);
                    panel1[0].add(Car);
                    JTextField carInfor = new Infor_textfield(8);
                    carInfor.setBounds(240,255,270,32);
                    panel1[0].add(carInfor);
                    //
                    JLabel CarType=new JLabel();
                    CarType.setBounds(100,280,500,50);
                    CarType.setFont(new Font("微软雅黑",Font.BOLD,30));
                    CarType.setForeground(Color.black);
                    CarType.setVisible(true);
                    CarType.setText("汽车类型：");
                    CarType.setForeground(Color.black);
                    panel1[0].add(CarType);
                    // 需要选择的条目
                    String[] listData = new String[]{"小型轿车", "新能源汽车"};
                    // 创建一个下拉列表框
                    final JComboBox<String> comboBox = new JComboBox<String>(listData);
                    // 设置默认选中的条目
                    comboBox.setSelectedIndex(0);
                    comboBox.setFont(new Font("微软雅黑",Font.BOLD,25));
                    comboBox.setBounds(240,290,150,35);
                    // 添加到内容面板
                    panel1[0].add(comboBox);
                    //
                    JButton CHButton=new Func_Button("修改");
                    CHButton.setBounds(200,350,115,35);
                    panel1[0].add(CHButton);
                    CHButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String Owner =owner.getText();
                            String car = carInfor.getText();
                            String carType = (String) comboBox.getSelectedItem();
                            Object[] options ={ "确认修改", "取消修改" };  //自定义按钮上的文字
                            int choice =JOptionPane.showOptionDialog(panel, "确认修改信息吗？",
                                    "修改信息",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            switch (choice)
                            {
                                case 0:
                                    if(!Owner.equals("")&&!car.equals(""))
                                    {
                                        for(Registration ele:Re_Data)
                                        {
                                            if(ele.carNumber.equals(object.carNumber))
                                            {
                                                ele.setOwner(Owner);
                                                ele.setCarType(carType);
                                                ele.setCar(car);
                                                break;
                                            }
                                        }
                                        panel1[0].removeAll();
                                        JLabel CON=new JLabel();
                                        CON.setBounds(100,50,500,50);
                                        CON.setFont(new Font("微软雅黑",Font.BOLD,40));
                                        CON.setForeground(Color.black);
                                        CON.setVisible(true);
                                        CON.setText("您已经成功修改信息！！！");
                                        CON.setForeground(Color.black);
                                        panel1[0].add(CON);
                                        panel1[0].repaint();
                                    }
                                    else
                                    {
                                        JOptionPane.showConfirmDialog( panel, "您未正确填写信息！！！",
                                                "错误提示",JOptionPane.DEFAULT_OPTION);
                                    }
                                    break;
                                case 1:
                                    break;
                            }
                        }
                    });
                    panel1[0].repaint();
                }
            });
        }
        public void change(Registration object)
        {
            this.object=object;
            //车牌号
            Re_number.setText(object.province+object.carNumber);
            //汽车信息
            Re_Car.setText(object.car);
            //汽车类型
            Re_CarInfor.setText(object.carType);
            //车主信息
            Re_owner.setText(object.owner);
            repaint();
        }
    }
    //自定义按钮
    private class Func_Button extends JButton
    {
        public Func_Button(String text) {
            super(text);
            setBackground(new Color(206, 205, 205));
            setPreferredSize(new Dimension(150, 37));
            setForeground(Color.black);
            setFocusPainted(false);
            setFont(new Font("微软雅黑", 1, 15));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVisible(true);
        }
    }
    //自定义搜索框
    private static class search_textfield extends JTextField
    {
        MatteBorder matteBorder=new MatteBorder(0, 0, 1, 0, Color.black);
        public search_textfield(int n) {
            super(n);
            setFont(new Font("宋体", 1, 35));
            setPreferredSize(new Dimension(255, 30));
            setOpaque(false);
            setBorder(matteBorder);
        }
    }
    private static class Infor_textfield extends JTextField
    {
        MatteBorder matteBorder=new MatteBorder(0, 0, 1, 0, Color.black);
        public Infor_textfield(int n) {
            super(n);
            setFont(new Font("宋体", 1, 30));
            setPreferredSize(new Dimension(255, 30));
            setOpaque(false);
            setBorder(matteBorder);
        }
    }
}