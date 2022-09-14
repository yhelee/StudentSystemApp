package yhelee;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> arrayList = new ArrayList<>();

        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作：1.登录 2.注册 3.忘记密码 4.退出系统");
            String choose = sc.next();
            switch (choose) {
                case "1" -> login(arrayList);
                case "2" -> regedit(arrayList);
                case "3" -> fogetPassword(arrayList);
                case "4" -> {
                    System.out.println("退出");
                    System.exit(0);
                }
                default -> System.out.println("请输入正确的选项");
            }
        }
    }

    private static void fogetPassword(ArrayList<User> arrayList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.next();
        if(!(containsUsername(arrayList,username))){
            System.out.println("用户名" +
                    username +
                    "不存在");
            return;
        }

        User user = null;
        while (true) {
            System.out.println("请输入身份证号码");
            String id = sc.next();
            System.out.println("请输入手机号码");
            String phoneNum = sc.next();

            //比较用户对象中的手机号码和身份证号码是否相同
            //需要把用户对象通过索引先取出来
            int index = findIndex(arrayList,username);
            user = arrayList.get(index);
            //判断
            if(!(user.getId().equalsIgnoreCase(id)&&user.getPhoneNum().equals(phoneNum))){
                System.out.println("身份证号码或者手机号输入有误,请重新输入");
                continue;
            }
            break;
        }

        //输入新密码
        String newPs;
        while (true) {
            System.out.println("请输入新密码");
            newPs = sc.next();
            System.out.println("请再次输入新密码");
            String newPsAg = sc.next();
            if(newPs.equals(newPsAg)){
                System.out.println("两次输入一致");
                break;
            }else {
                System.out.println("两次输入不一致,请再次输入");
                continue;
            }
        }

        //修改密码
        user.setPassword(newPs);
        System.out.println("密码修改成功");
    }

    private static int findIndex(ArrayList<User> arrayList, String username) {
        for (int i = 0; i < arrayList.size(); i++) {
            String AUsername = arrayList.get(i).getUsername();
            if(AUsername.equals(username)){
                return i;
            }
        }
        return -1;
    }

    private static void regedit(ArrayList<User> arrayList) {
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        String id;
        String phoneNum;
        //键盘录入用户名
        while (true) {
            System.out.println("请输入用户名");
            username = sc.next();

            //用户名格式校验
            if (!(checkUsername(username))) {
                System.out.println("用户名不满足格式，请输入正确的用户名");
                continue;
            }

            //用户名唯一校验
            if (containsUsername(arrayList, username)) {
                System.out.println("用户名" + username + "已经存在，请更换用户名");
            } else {
                System.out.println("当前用户名" + username + "可用！");
                break;
            }
        }
        //键盘录入密码
        while (true) {
            System.out.println("请输入密码");
            password = sc.next();
            System.out.println("请再次输入密码");
            String checkPassword = sc.next();
            if (!(password.equals(checkPassword))) {
                System.out.println("两次输入的密码不一样，请重新输入!");
                continue;
            } else {
                System.out.println("密码注册成功！");
                break;
            }
        }
        //键盘录入身份证号码
        while (true) {
            System.out.println("请输入身份证号码");
            id = sc.next();
            if (checkId(id)) {
                System.out.println("身份证号码满足要求");
                break;
            } else {
                System.out.println("身份证号码格式有误，请重新输入");
                continue;
            }
        }
        //键盘录入手机号码
        while (true) {
            System.out.println("请输入手机号码");
            phoneNum = sc.next();
            if (checkPhoneNum(phoneNum)) {
                System.out.println("手机号格式正确");
                break;
            } else {
                System.out.println("手机号格式有误，请重新输入");
                continue;
            }
        }
        //将注册信息放入对象中
        User user = new User(username, password, id, phoneNum);
        arrayList.add(user);
        System.out.println("注册成功！");

//        printList(arrayList);

    }

   /* private static void printList(ArrayList<User> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i).getUsername()+",,,"+
                    arrayList.get(i).getPassword()+",,,"+
                    arrayList.get(i).getId()+",,,"+
                    arrayList.get(i).getPhoneNum());
        }
    }*/

    private static boolean checkPhoneNum(String phoneNum) {
        if (phoneNum.length() != 11) {
            return false;
        }
        if (phoneNum.startsWith("0")) {
            return false;
        }
        for (int i = 0; i < phoneNum.length(); i++) {
            char c = phoneNum.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;

    }

    private static boolean checkId(String id) {
        if (id.length() != 18) {
            return false;
        }

        //如果以0开头，返回false
        if (id.startsWith("0")) {
            return false;
        }

        //校验前17位是否为数字
        for (int i = 0; i < id.length() - 2; i++) {
            char c = id.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }

        //校验最后一位
        char x = id.charAt(id.length() - 1);
        if ((x >= '0' && x <= '9') || (x == 'x') || (x == 'X')) {
            return true;
        } else {
            return false;
        }
    }


    //用户名唯一校验，有一样的返回true，没有一样的返回false
    public static boolean containsUsername(ArrayList<User> arrayList, String username) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    //用户名格式校验，满足返回true、不满足返回false
    private static boolean checkUsername(String username) {
        int length = username.length();

        //校验长度
        if (length < 3 || length > 15) {
            return false;
        }

        //校验内容
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }

        //校验是否为纯数字
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count++;
                break;
            }
        }
        return count > 0;
    }

    public static void login(ArrayList<User> arrayList) {
        Scanner sc = new Scanner(System.in);

        //登录计时
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名");
            String username = sc.next();
            if (!(containsUsername(arrayList, username))) {
                System.out.println("用户名" + username + "未注册，请先完成注册");
                return;
            }

            System.out.println("请输入密码");
            String password = sc.next();

            //使用验证码
            while (true) {
                String code = identifyCode();
                System.out.println("验证码：" + code);
                System.out.println("请输入验证码");
                String identifyCode = sc.next();
                if (identifyCode.equalsIgnoreCase(code)) {
                    System.out.println("验证码正确");
                    break;
                } else {
                    System.out.println("验证码错误，请重新输入");
                    continue;
                }
            }

            //验证用户名和密码
            //封装思想的应用
            //可以把一些零散的数据封装成一个对象
            //传递参数时，传递整体
            User userInfo = new User(username, password, null, null);
            if (checkUserInfo(arrayList, userInfo)) {
                System.out.println("登录成功，可以使用系统");
                //创建对象，调用方法，启动学生管理系统
                StudentSystem ss = new StudentSystem();
                ss.startStudentSystem();
                break;
            } else {
                System.out.println("登录失败，用户名或密码不正确");
                if (i == 2) {
                    System.out.println("当前账号" +
                            username +
                            "被锁定");
                    return;
                } else {
                    System.out.println("还剩下" +
                            (2 - i) +
                            "次机会");
                }

            }
        }
        System.out.println();
    }

    //生成验证码
    public static String identifyCode() {
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            charList.add((char) ('a' + i));
            charList.add((char) ('A' + i));
        }

        //随机抽取四个字符
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            //抽取四个字符
            char c = charList.get(r.nextInt(charList.size()));
            //添加到容器中
            sb.append(c);
        }

        //添加数字到队尾
        int num = r.nextInt(10);
        sb.append(num);

        //数字位置随机
        char[] chars = sb.toString().toCharArray();
        int charsIndex = r.nextInt(chars.length);
        //数据交换
        char temp = chars[charsIndex];
        chars[charsIndex] = chars[chars.length - 1];
        chars[chars.length - 1] = temp;

        //返回验证码
        return new String(chars);
    }

    //验证用户名和密码
    public static boolean checkUserInfo(ArrayList<User> arrayList, User userInfo) {
        for (int i = 0; i < arrayList.size(); i++) {
            User user = arrayList.get(i);
            if ((user.getUsername().equals(userInfo.getUsername())) && (user.getPassword().equals(userInfo.getPassword()))) {
                return true;
            }
        }
        return false;
    }
}


