package com.mista.soft.util;

import com.mista.soft.domain.Van;

public class VanFixture {
    private static final String NAME = "Van #1";

    public static Van createVan(){
        Van van = new Van();
        van.setNameVan(NAME);
        return van;
    }
}
