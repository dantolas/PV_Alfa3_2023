package com.kuta;

import com.kuta.app.ApplicationLayer;


public class Main{

    public static void main(String[] args) {

        ApplicationLayer app = ApplicationLayer.DEFAULT_INIT(System.out,System.in);
        if(app == null) return;
        app.run();
    }

}
