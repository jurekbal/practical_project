package com.javapoz24.sda.pp.http;

import com.javapoz24.sda.pp.model.Slip;
import com.javapoz24.sda.pp.model.SlipDto;

public class SlipResponse {
    private SlipDto slip;

    public SlipResponse(SlipDto slip) {
        this.slip = slip;
    }

    public SlipDto getSlip() {
        return slip;
    }

    public void setSlip(SlipDto slip) {
        this.slip = slip;
    }
}
