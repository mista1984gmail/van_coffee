package com.mista.soft.exeption;

import com.mista.soft.domain.Van;

public class NotUniqueIdException extends Exception{
    public NotUniqueIdException(Van van) {
        super("Van is not saved: " + van);
    }
}
