package Configuration;

import java.io.*;

public class Configuration implements Serializable {
    int currentSignedInId = 0;
    int currentMaxClientId = 0;
    int currentMaxAdminId = 0;
    int currentMaxMovieId= 0;

    public Configuration(int currentSignedInId, int currentMaxClientId, int currentMaxAdminId,int currentMaxMovieId) throws IOException, ClassNotFoundException {
        File file = new File("Configurations.BIN");
        if(file.length() == 0) {
            this.currentSignedInId = currentSignedInId;
            this.currentMaxClientId = currentMaxClientId;
            this.currentMaxAdminId = currentMaxAdminId;
            this.currentMaxMovieId= currentMaxMovieId;
        }
        else{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Configurations.BIN"));
            Configuration conf = (Configuration)in.readObject();
            this.currentSignedInId = conf.currentSignedInId;
            this.currentMaxClientId = conf.currentMaxClientId;
            this.currentMaxAdminId = conf.currentMaxAdminId;
            this.currentMaxMovieId = conf.currentMaxMovieId;
        }
    }

    public Configuration() throws IOException, ClassNotFoundException {
        File file = new File("Configurations.BIN");
        if(file.length() == 0) {
            this.currentSignedInId = 0;
            this.currentMaxClientId = 0;
            this.currentMaxAdminId = 0;
            this.currentMaxMovieId = 0;
        }
        else{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Configurations.BIN"));
            Configuration conf = (Configuration)in.readObject();
            this.currentSignedInId = conf.currentSignedInId;
            this.currentMaxClientId = conf.currentMaxClientId;
            this.currentMaxAdminId = conf.currentMaxAdminId;
            this.currentMaxMovieId = conf.currentMaxMovieId;
        }
    }

    public int getCurrentSignedInId() {
        return currentSignedInId;
    }

    public int getCurrentMaxClientId() {
        return currentMaxClientId;
    }

    public int getCurrentMaxAdminId() {
        return currentMaxAdminId;
    }

    public int getCurrentMaxMovieId(){return  currentMaxMovieId;}

    public void writeCurrentSignedInId(int currentId) throws IOException {
        this.currentSignedInId = currentId;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Configurations.BIN"));
        out.writeObject(this);
        out.close();
    }

    public void writeCurrentMaxClientId(int currentId) throws IOException {
        this.currentMaxClientId = currentId;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Configurations.BIN"));
        out.writeObject(this);
        out.close();
    }
    public void writeCurrentMaxMovieId(int currentId) throws IOException {
        this.currentMaxMovieId = currentId;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Configurations.BIN"));
        out.writeObject(this);
        out.close();
    }


    public void readConfiguration() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Configurations.BIN"));
        Configuration conf = (Configuration)in.readObject();
        this.currentMaxAdminId = conf.currentMaxAdminId;
        this.currentSignedInId = conf.currentSignedInId;
        this.currentMaxClientId = conf.currentMaxClientId;
        this.currentMaxMovieId = conf.currentMaxMovieId;
        in.close();
    }
}
