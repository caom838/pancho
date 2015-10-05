package com.s2c.android.asea.Model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Inginiero on 05/10/2015.
 */
public class Service implements Serializable {

    private Long idPlan;
    private String AddressService;
    private Calendar startDateService;
    private Calendar endDateService;

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public String getAddressService() {
        return AddressService;
    }

    public void setAddressService(String addressService) {
        AddressService = addressService;
    }

    public Calendar getStartDateService() {
        return startDateService;
    }

    public void setStartDateService(Calendar startDateService) {
        this.startDateService = startDateService;
    }

    public Calendar getEndDateService() {
        return endDateService;
    }

    public void setEndDateService(Calendar endDateService) {
        this.endDateService = endDateService;
    }
}
