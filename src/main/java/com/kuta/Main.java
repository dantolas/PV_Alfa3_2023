package com.kuta;

import com.kuta.app.ApplicationLayer;
import com.kuta.conf.Config;
import com.kuta.db.DatabaseConnector;


public class Main{

    public static void main(String[] args) {

        ApplicationLayer.DEFAULT_INIT(System.out,System.in).run();
    }

}
