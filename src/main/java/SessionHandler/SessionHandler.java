package SessionHandler;

import ChatSystem.chatClient;
import ChatSystem.chatServer;
import Movie.Movie;
import User.Admin;
import User.Client;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SessionHandler {
    public static Client currentSignedInClient;
    public static Admin currentSignedInAdmin;
    public static chatClient currentChatClient;
    public static String serverIp = "localhost";
    public static int port = 6969;
    public static String currentChatWithClient;
    public static Movie currentMovie = null;
    public static Pane GPane = null;
}

