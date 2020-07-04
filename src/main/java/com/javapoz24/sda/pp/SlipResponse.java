package com.javapoz24.sda.pp;

public class SlipResponse {
    private Slip slip;

    public SlipResponse(Slip slip) {
        this.slip = slip;
    }

    public Slip getSlip() {
        return slip;
    }

    public void setSlip(Slip slip) {
        this.slip = slip;
    }
}
