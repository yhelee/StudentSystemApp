package yhelee;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void startStudentSystem() {

        //创建集合
        ArrayList<Student> arrayList = new ArrayList<>();

        loop:
        while (true) {
            System.out.println("--------欢迎来到学生管理系统--------");
            System.out.println("  --------1:添加学生信息--------");
            System.out.println("  --------2:删除学生信息--------");
            System.out.println("  --------3:修改学生信息--------");
            System.out.println("  --------4:查询学生信息--------");
            System.out.println("    --------5:退出系统--------");
            System.out.println("请输入您的选择");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> addStudent(arrayList);
                case "2" -> delStudent(arrayList);
                case "3" -> updateStudent(arrayList);
                case "4" -> queryStudent(arrayList);
                case "5" -> {
                    System.out.println("exit");
//                    break loop;
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项");
            }
        }
    }

    //添加学生信息
    public static void addStudent(ArrayList<Student> arrayList) {

        //创建一个学生对象
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("请输入学生的id");
            String id = sc.next();
            if (checkId(arrayList, id)) {
                System.out.println("集合中id已经存在");
            } else {
                stu.setId(id);
                break;
            }
        }

        System.out.println("请输入学生的姓名");
        String name = sc.next();
        stu.setName(name);

        System.out.println("请输入学生的年龄");
        int age = sc.nextInt();
        stu.setAge(age);

        System.out.println("请输入学生的地址");
        String address = sc.next();
        stu.setAddress(address);

        //把学生对象添加到集合中
        arrayList.add(stu);

        //提示
        System.out.println("添加成功！");
    }

    //删除学生信息
    public static void delStudent(ArrayList<Student> arrayList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的id");
        String id = sc.next();

        //查询id在集合中的索引
        int index = getIndex(arrayList, id);

        if (index >= 0) {
            arrayList.remove(index);
            System.out.println("id为" + id + "的学生删除成功");
        } else {
            System.out.println("id不存在，删除失败!");
        }
    }

    //修改学生信息
    public static void updateStudent(ArrayList<Student> arrayList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生的id");
        String id = sc.next();

        int index = getIndex(arrayList, id);
        if (index == -1) {
            System.out.println("要修改的id不存在，请重新输入id");
            return;
        }

        //获取要修改的学生对象
        Student stu = arrayList.get(index);

        //修改信息
        System.out.println("请输入要修改的姓名");
        String newname = sc.next();
        stu.setName(newname);

        System.out.println("请输入要修改的年龄");
        int newage = sc.nextInt();
        stu.setAge(newage);

        System.out.println("请输入要修改的地址");
        String newaddress = sc.next();
        stu.setAddress(newaddress);

        System.out.println("修改成功！");


    }

    //查询学生信息
    public static void queryStudent(ArrayList<Student> arrayList) {
        if (arrayList.size() == 0) {
            System.out.println("无学生信息，请添加后再查询");
            //结束方法
            return;
        }

        //打印表头信息
        System.out.println("id\t\t姓名\t年龄\t家庭住址");

        for (int i = 0; i < arrayList.size(); i++) {
            Student stu = arrayList.get(i);
            System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
        }
    }

    //判断id在集合中是否存在
    public static boolean checkId(ArrayList<Student> arrayList, String id) {
        return getIndex(arrayList, id) >= 0;
    }

    //通过id获取索引
    public static int getIndex(ArrayList<Student> arrayList, String id) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
