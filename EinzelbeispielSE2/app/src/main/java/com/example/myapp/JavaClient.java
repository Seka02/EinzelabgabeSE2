package com.example.myapp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class JavaClient extends Thread {

    String sentence;
    String modifiedSentence;

    JavaClient(String sentence){
        this.sentence = sentence;
    }

    public void client(){
        try{
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER:" + modifiedSentence);

            clientSocket.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
