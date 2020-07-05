package com.javapoz24.sda.pp;

import com.javapoz24.sda.pp.database.SlipDao;
import com.javapoz24.sda.pp.http.HttpClient;
import com.javapoz24.sda.pp.http.SlipResponse;
import com.javapoz24.sda.pp.model.Slip;
import com.javapoz24.sda.pp.model.SlipDto;

import java.util.List;

public class AdviceService {
    private static final String URL = "https://api.adviceslip.com/";
    private final HttpClient httpClient = new HttpClient();

    public SlipDto getRandomAdvice(){
        return httpClient.fetch(URL + "advice", SlipResponse.class).getSlip();
    }

    public boolean saveAdvice(SlipDto slipDto) {
        Slip slip = new Slip(slipDto);

        SlipDao slipDao = new SlipDao();
        if (slipDao.insertOrUpdate(slip)) {
            return true;
        }
        return false;
    }

    public List<Slip> getAllAdvices() {
        SlipDao slipDao = new SlipDao();
        List<Slip> list = slipDao.getAll();
        return list;
    }
}
