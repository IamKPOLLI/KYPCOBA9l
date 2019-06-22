package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        Socket[] clientSocketArray = new Socket[8];
        ClientHandler handler1;
        ClientHandler handler2;
        ClientHandler handler3;
        ClientHandler handler4;
        ServerSocket serverSocket = new ServerSocket(4444);
        int numberOfThreads =0;

        System.out.println("Сервер готов к работе");
        while (numberOfThreads != 8) {
            clientSocketArray[numberOfThreads] = serverSocket.accept();
            System.out.println("Подключение номер"+ numberOfThreads);
            numberOfThreads++;
        }

        handler1 = new ClientHandler(clientSocketArray[1],clientSocketArray[4]);
        handler2 = new ClientHandler(clientSocketArray[5],clientSocketArray[0]);
        handler3 = new ClientHandler(clientSocketArray[3],clientSocketArray[6]);
        handler4 = new ClientHandler(clientSocketArray[7],clientSocketArray[2]);

        handler1.start();
        handler2.start();
        handler3.start();
        handler4.start();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}