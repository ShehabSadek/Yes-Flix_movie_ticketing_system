package GraphsAndStats;

import Configuration.Configuration;

import java.io.*;

public class gData implements Serializable {
    String[] movies = new String[3];
    Double[] values = new Double[3];

    gData() throws IOException, ClassNotFoundException {
        File file = new File("gData.BIN");
        if(file.length() == 0) {
            for(int i = 0; i < movies.length; i++){
                movies[i] = " ";
                values[i] = 0.0;
            }
        }
        else{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("gData.BIN"));
            gData data = (gData) in.readObject();
            for(int i = 0; i < 3; i++){
                movies[i] = data.movies[i];
                values[i] = data.values[i];
            }
        }
    }

    public void increment(){

    }
}
