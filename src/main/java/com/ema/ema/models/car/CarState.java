package com.ema.ema.models.car;

public enum CarState {
    MECHANIC("MECHANIC"),
    GROUNDED("GROUNDED"),
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE"),
    ASSIGNED("ASSIGNED"),
    STANDBY("STANDBY"),
    NONE("NONE");

    private final String state;

    CarState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
