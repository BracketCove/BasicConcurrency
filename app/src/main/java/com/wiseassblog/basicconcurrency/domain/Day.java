package com.wiseassblog.basicconcurrency.domain;

import java.io.Serializable;

public class Day implements Serializable {
    private final String day = "DAY";

    public String getDay() {
        return day;
    }
}
