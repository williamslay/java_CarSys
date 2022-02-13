package code;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomePage {
    public   HomePage()//创建登录面板
    {
        JFrame homePage=new JFrame();
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePage.setVisible(true);
        homePage.setResizable(false);//固定大小
        homePage.setBounds(300, 100,1280,720);
        //创建面板
        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 250, 250));
        panel.setLayout(null);
        panel.setBounds(0,0,1280,720);
        homePage.add(panel);
        //创建背景
        Image img1 = null;
        try {
            img1 = ImageIO.read(new File("src/img/HomeBG.png"));
        } catch (IOException e) {
            System.out.println("not find img!!!");
        }
        ImageIcon bg = new ImageIcon(img1);
        bg = scaleImage(bg,1280,720);
        JLabel BG=new JLabel();
        BG.setIcon(bg);
        BG.setBounds(0,0,1280,720);
        BG.setVisible(true);
        //欢迎语句
        JLabel Welcome=new JLabel();
        Welcome.setBounds(450,100,500,50);
        Welcome.setFont(new Font("微软雅黑",Font.BOLD,30));
        Welcome.setForeground(Color.black);
        Welcome.setVisible(true);
        panel.add(Welcome);
        Welcome.setText("欢迎使用车牌查询管理系统");
        Welcome.setForeground(Color.black);
        JLabel Welcome1=new JLabel();
        Welcome1.setBounds(380,140,600,50);
        Welcome1.setFont(new Font("微软雅黑",Font.BOLD,25));
        Welcome1.setForeground(Color.black);
        Welcome1.setVisible(true);
        panel.add(Welcome1);
        Welcome1.setText("Welcom Use Vehicle Registrations System");
        Welcome1.setForeground(Color.black);
        //登录框相关
        //用户图标
        Image img2 = null;
        try {
            img2 = ImageIO.read(new File("src/img/user.png"));
        } catch (IOException e) {
            System.out.println("not find img!!!");
        }
        ImageIcon user = new ImageIcon(img2);
        user = scaleImage(user,40,40);
        JLabel User=new JLabel();
        User.setIcon(user);
        User.setBounds(450,300,40,40);
        User.setVisible(true);
        panel.add(User);
        //密码图标
        Image img3 = null;
        try {
            img3 = ImageIO.read(new File("src/img/password.png"));
        } catch (IOException e) {
            System.out.println("not find img!!!");
        }
        ImageIcon password = new ImageIcon(img3);
        password = scaleImage(password,40,40);
        JLabel Password=new JLabel();
        Password.setIcon(password);
        Password.setBounds(450,400,40,40);
        Password.setVisible(true);
        panel.add(Password);
        //账号输入框
        JTextField user_field=new Login_textfield(10);
        //密码输入框
        JPasswordField code_field=new Login_codefield(16);
        user_field.setBounds(500,310,300,30);
        code_field.setBounds(500,410,300,30);
        panel.add(user_field);
        panel.add(code_field);
        //登录按钮
        JButton Lgibutton=new Login_Button("登录");
        Lgibutton.setBounds(540,500,215,35);
        panel.add(Lgibutton);
        panel.add(BG);
        panel.repaint();
//        //注册按钮
//        JButton Lgubutton=new Login_Button("注册");
//        Lgutton.setBounds(540,500,215,35);
        Lgibutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String names=user_field.getText();
                @SuppressWarnings("deprecation")
                String password=code_field.getText();
                if(names.length()==0||password.length()==0)
                {
                    JOptionPane.showConfirmDialog( panel,"请输入您的用户名或密码！！！","登录失败",JOptionPane.DEFAULT_OPTION);
                    return ;
                }
                User user = new User(names,password,0);
                user.FindUser();
                switch (user.isAdmin)
                {
                    case 0://普通用户
                        homePage.setVisible(false);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                FuncPage UserPage = new FuncPage();
                                UserPage.UserFuncPage(user);
                            }
                        }).start();
                        break;
                    case 1://超级管理员
                        homePage.setVisible(false);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                FuncPage AdminPage = new FuncPage();
                                AdminPage.AdminFuncPage(user);
                            }
                        }).start();
                        break;
                    case 2://未注册用户
                        JOptionPane.showConfirmDialog( panel,"您输入的用户名或密码有误！！！","登录失败",JOptionPane.DEFAULT_OPTION);
                        break;
                }
            }
        });
    }
    //自定义文本框
    private class Login_textfield extends JTextField{
        MatteBorder matteBorder=new MatteBorder(0, 0, 1, 0, Color.white);
        public Login_textfield(int n) {
            super(n);
            setFont(new Font("宋体", 1, 30));
            setPreferredSize(new Dimension(255, 30));
            setOpaque(false);
            setBorder(matteBorder);
        }
    }
    //自定义密码框
    private class Login_codefield extends JPasswordField{
        MatteBorder matteBorder=new MatteBorder(0, 0, 1, 0, Color.white);
        public Login_codefield(int n) {
            super(n);
            setFont(new Font("宋体", 1, 30));
            setPreferredSize(new Dimension(255, 30));
            setOpaque(false);
            setBorder(matteBorder);
        }
    }
    //自定义按钮
    private class Login_Button extends JButton{
        public Login_Button(String text) {
            super(text);
            setBackground(new Color(0,131,255));
            setPreferredSize(new Dimension(215, 37));
            setForeground(Color.white);
            setFocusPainted(false);
            setFont(new Font("微软雅黑", 1, 15));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVisible(true);
        }
    }
    public static ImageIcon scaleImage(ImageIcon icon, int w, int h) //该函数用于按照比例拉伸ImageIcon
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
}
