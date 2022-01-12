package Movie;

//import ConfigurationControl.Configuration;
//import ConfigurationControl.ConfigurationList;
//import CustomOutputStream.CustomOutputStream;
//import Halls.Halls;
//import User.Client;
import Configuration.Configuration;
import CustomStreams.CustomObjectOutputStream;
import SessionHandler.SessionHandler;
import User.Client;

import java.io.Serializable;
import java.io.*;
import java.util.Arrays;

public class Movie implements Serializable {
    //Member variables
    private String name;
    private int duration;
    private String description;
    private float score;
    private String[] genres;
    private String trailer; //*A url or relative path to the local video file* (//TODO : To be added to constructors)
    private int id = 0;
    //private Halls hall;

    //Parameterized constructor
    public Movie(String name, int duration, String description, float score, String[] genres) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.score = score;
        this.genres = genres;
        id++;
    }
    //public void setHall(Halls hall){
    //    this.hall = hall;
    //}
    //public Halls getHall(){
    //    return hall;
    //}

    //No arg constructor (for testing) //TODO : to be deleted ?
    public Movie() {
        this("TEST", 120, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam malesuada.", 4.5f, new String[]{"Horror", "Comedy"});
        id++;
    }


    //Getters
    public float getScore() {
        return score;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTrailer() {
        return trailer;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }
    //endGetters

    //Setters
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    private void setId(int id) {
        this.id = id;
    }
    // endSetters
    public void writeMovie() throws IOException, ClassNotFoundException {
        File file = new File("Movies.BIN");
        if(file.length() == 0) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Movies.BIN", true));
            Configuration conf = new Configuration();
            //-this.setMovieId(conf.getCurrentMaxMovieId() + 1);
            conf.writeCurrentMaxMovieId(conf.getCurrentMaxMovieId() + 1);
            out.writeObject(this);
            out.close();

        }
        else{
            CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Movies.BIN", true));
            Configuration conf = new Configuration();
            this.setId(conf.getCurrentMaxClientId() + 1);
            conf.writeCurrentMaxMovieId(conf.getCurrentMaxMovieId() + 1);
            out.writeObject(this);
            out.close();
        }
    }

    public void writeMovie2() throws IOException {
        CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Movies.BIN", true));
        out.writeObject(this);
        out.close();
    }

    public static void readClient() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Movies.BIN"));
        while(true){
            try{
                Movie movie = (Movie)in.readObject();
                System.out.println(movie.getName());
                System.out.println(movie.getId());
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public static void readMovie(int ID) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Movies.BIN"));
        while(true){
            try{
                Movie movie  = (Movie)in.readObject();
                if(movie.getId() == ID)
                    System.out.println(movie.getName());
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public void editMovie() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Movies.BIN"));
        while(true){
            try{
                Movie movie = (Movie)in.readObject();
                System.out.println(movie.getName());
                System.out.println(movie.getId());
            } catch (EOFException e) {
                in.close();
                break;
            }
        }
        in.close();
    }

    public static void deleteMovie(Movie cl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Movies.Bin");
        File movies = new File("Movies.Bin");
        File temp = new File("Temp.Bin");
        File movies2 = new File("Movies.Bin");
        int count = 0;
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            while(fileInputStream.available() != 0){
                Movie movie = (Movie) input.readObject();
                if(movie.getId() == cl.getId()){
                    continue;
                }
                else{
                    if(count == 0){
                        os.writeObject(movie);
                        count++;
                    }
                    else{
                        out.writeObject(movie);
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
        movies.delete();
        if(temp.renameTo(movies2)){
            System.out.println("Succesfuly renamed file");
        }
    }

    public static void editMovie(Movie cl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Movies.Bin");
        File movies = new File("Movies.Bin");
        File temp = new File("Temp.Bin");
        File movies2 = new File("Movies.Bin");
        int count = 0;
        try {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            CustomObjectOutputStream out = new CustomObjectOutputStream(new FileOutputStream("Temp.Bin", true));
            while(fileInputStream.available() != 0){
                Movie movie = (Movie) input.readObject();
                if(movie.getId() == cl.getId()){
                    if(count == 0){
                        os.writeObject(cl);
                        count++;
                    }
                    else{
                        out.writeObject(movie);
                    }
                }
                else{
                    if(count == 0){
                        os.writeObject(movie);
                        count++;
                    }
                    else{
                        out.writeObject(movie);
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
        movies.delete();
        if(temp.renameTo(movies2)){
            System.out.println("Succesfuly renamed file");
        }
    }

    public static Movie returnMovie(int ID) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Movies.BIN"));
        while(true){
            try{
                Movie movie = (Movie) in.readObject();
                if(movie.getId() == ID)
                    in.close();
                return movie;
            } catch (EOFException e) {
                in.close();
                break;

            }
        }
        in.close();
        return null;
    }
}
