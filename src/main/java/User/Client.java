package User;

import Configuration.Configuration;
import CustomStreams.CustomObjectOutputStream;
import SessionHandler.SessionHandler;

import java.io.*;

public class Client extends User implements Serializable {
    //private String ClientId;


    public Client(int ID, String userName, String password){
        super(ID, userName, password);
    }

    public Client(String userName, String FName, String LName, String password, String email) {
        super(userName, FName, LName, password, email);
    }




    public void writeClient() throws IOException, ClassNotFoundException {
        File file = new File("Clients.BIN");
        if(file.length() == 0) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Clients.BIN", true));
            Configuration conf = new Configuration();
            this.setUserID(conf.getCurrentMaxClientId() + 1);
            conf.writeCurrentMaxClientId(conf.getCurrentMaxClientId() + 1);
            out.writeObject(this);
            out.close();

        }
        else{
            CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Clients.BIN", true));
            Configuration conf = new Configuration();
            this.setUserID(conf.getCurrentMaxClientId() + 1);
            conf.writeCurrentMaxClientId(conf.getCurrentMaxClientId() + 1);
            out.writeObject(this);
            out.close();
        }
    }

    public void writeClient2() throws IOException {
        CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Clients.BIN", true));
        out.writeObject(this);
        out.close();
    }

    public static void readClient() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        while(true){
            try{
                Client client = (Client)in.readObject();
                System.out.println(client.getUserName());
                System.out.println(client.getUserID());
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public static void readClient(int ID) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        while(true){
            try{
                Client client = (Client)in.readObject();
                if(client.getUserID() == ID)
                    System.out.println(client.getUserName());
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public void editClient() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        while(true){
            try{
                Client client = (Client)in.readObject();
                System.out.println(client.getUserName());
                System.out.println(client.getUserID());
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public static void deleteClient(Client cl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Clients.Bin");
        File clients = new File("Clients.Bin");
        File temp = new File("Temp.Bin");
        File clients2 = new File("Clients.Bin");
        int count = 0;
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            while(fileInputStream.available() != 0){
                Client client = (Client) input.readObject();
                if(client.getUserID() == cl.getUserID()){
                    continue;
                }
                else{
                    if(count == 0){
                        os.writeObject(client);
                        count++;
                    }
                    else{
                        out.writeObject(client);
                    }
                }

            }
            input.close();
            os.close();
            out.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fileInputStream.close();
        clients.delete();
        if(temp.renameTo(clients2)){
            System.out.println("Succesfuly renamed file");
        }
    }

    public static void editClient(Client cl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Clients.Bin");
        File clients = new File("Clients.Bin");
        File temp = new File("Temp.Bin");
        File clients2 = new File("Clients.Bin");
        int count = 0;
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            while(fileInputStream.available() != 0){
                Client client = (Client) input.readObject();
                if(client.getUserID() == cl.getUserID()){
                    if(count == 0){
                        os.writeObject(cl);
                        count++;
                    }
                    else{
                        out.writeObject(client);
                    }
                }
                else{
                    if(count == 0){
                        os.writeObject(client);
                        count++;
                    }
                    else{
                        out.writeObject(client);
                    }
                }

            }
            input.close();
            os.close();
            out.close();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fileInputStream.close();
        clients.delete();
        if(temp.renameTo(clients2)){
            System.out.println("Succesfuly renamed file");
        }
    }

    public static Client returnClient(int ID) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        while(true){
            try{
                Client client = (Client)in.readObject();
                if(client.getUserID() == ID)
                    in.close();
                return client;
            } catch (EOFException e) {
                in.close();
                break;

            }
        }
        in.close();
        return null;
    }


    public static boolean Login(String username, String password) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Clients.BIN"));
        while(true){
            try{
                Client client = (Client)in.readObject();
                if(client.getUserName().equals(username)){
                    if(client.getPassword().equals(password)){
                        SessionHandler.currentSignedInClient = client;
                        in.close();
                        return true;
                    }
                }
            } catch (EOFException | ClassNotFoundException e) {
                in.close();
                break;

            }
        }
        in.close();
        return false;
    }


}





