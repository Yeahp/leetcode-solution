package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerializable implements Serializable {

    private static final long serialVersionUID = 5887391604554532906L;

    private int id;

    private String name;

    public TestSerializable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestSerializable [id=" + id + ", name=" + name + "]";
    }

    //@SuppressWarnings("resource")
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("TestSerializable.obj"));
        oos.writeObject("测试序列化");
        oos.writeObject(618);
        TestSerializable test = new TestSerializable(1, "ConstXiong");
        oos.writeObject(test);

        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("TestSerializable.obj"));
        System.out.println((String)ois.readObject());
        System.out.println((Integer)ois.readObject());
        System.out.println((TestSerializable)ois.readObject());
    }

}
