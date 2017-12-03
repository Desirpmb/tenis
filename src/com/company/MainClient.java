package com.company;

import com.sun.tools.internal.xjc.model.CBuiltinLeafInfo;

public class MainClient {


    public static void main(String[] args) {

        Client client = new Client(13000,"192.168.1.143",12000);

        client.start();



    }
}

