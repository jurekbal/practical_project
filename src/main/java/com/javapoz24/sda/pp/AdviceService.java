package com.javapoz24.sda.pp;

import com.javapoz24.sda.pp.database.SlipDao;
import com.javapoz24.sda.pp.http.HttpClient;
import com.javapoz24.sda.pp.http.SlipResponse;
import com.javapoz24.sda.pp.model.Slip;
import com.javapoz24.sda.pp.model.SlipDto;

import java.util.List;

public class AdviceService {

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

    public boolean deleteSlip(Long slipId) {
        SlipDao slipDao = new SlipDao();
        if (slipDao.deleteSlip(slipId)){
            return true;
        }
        return false;
    }
}
