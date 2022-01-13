package User;

import Configuration.Configuration;
import CustomStreams.CustomObjectOutputStream;

import java.io.*;

public class Admin extends User implements Serializable {

    public Admin(int ID, String userName, String password) {
        super(ID, userName, password);
    }


    public void writeAdmin() throws IOException, ClassNotFoundException {
        File file = new File("Admins.BIN");
        if(file.length() == 0) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Admins.BIN", true));
            Configuration conf = new Configuration();
            this.setUserID(conf.getCurrentMaxAdminId() + 1);
            conf.writeCurrentMaxAdminId(conf.getCurrentMaxAdminId() + 1);
            out.writeObject(this);
            out.close();

        }
        else{
            CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Admins.BIN", true));
            Configuration conf = new Configuration();
            this.setUserID(conf.getCurrentMaxAdminId() + 1);
            conf.writeCurrentMaxAdminId(conf.getCurrentMaxAdminId() + 1);
            out.writeObject(this);
            out.close();
        }
    }

    public static void readAdmin() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Admins.BIN"));
        while(true){
            try{
                Admin admin = (Admin)in.readObject();
                System.out.println(admin.getUserName());
                System.out.println(admin.getUserID());
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Admin a = new Admin(45, "adminnikos", "admin");
        //a.writeAdmin();
        Admin.readAdmin();
    }
}
