package SessionHandler;

import ChatSystem.chatClient;
import ChatSystem.chatServer;
import User.Client;

import java.io.IOException;

public class SessionHandler {
    public static Client currentSignedInClient;
    public static chatClient currentChatClient;
    public static String serverIp = "localhost";
    public static int port = 6969;
    public static String currentChatWithClient;


}

