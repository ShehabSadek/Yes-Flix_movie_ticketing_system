package SessionHandler;

import ChatSystem.chatClient;
import ChatSystem.chatServer;
import User.Admin;
import User.Client;

import java.io.IOException;

public class SessionHandler {
    public static Client currentSignedInClient;
    public static Admin currentSignedInAdmin;
    public static chatClient currentChatClient;
    public static String serverIp = "localhost";
    public static int port = 6969;
    public static String currentChatWithClient;


}

