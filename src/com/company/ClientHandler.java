package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socketClientOutput;
    private Socket socketEnemyInput;
    String message;

    ClientHandler(Socket clientOutput, Socket enemyInput) {
        socketClientOutput = clientOutput;
        socketEnemyInput = enemyInput;
        message = "100";
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketClientOutput.getOutputStream());//говорит,что сообщение считано
            ObjectInputStream clientInputStream = new ObjectInputStream(socketClientOutput.getInputStream());//считывает сообщение для отправления
            ObjectOutputStream enemyOutputStream = new ObjectOutputStream(socketEnemyInput.getOutputStream());//отправляет сообщение врагу
            ObjectInputStream enemyInputStream = new ObjectInputStream(socketEnemyInput.getInputStream());//ждет ответа врага,что сообщение прочтено

            while (true) {
                message = clientInputStream.readObject().toString();
                enemyOutputStream.writeObject(message);
                enemyInputStream.readObject();
                clientOutputStream.writeObject("");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}