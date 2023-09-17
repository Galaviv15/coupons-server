package com.gal.coupons.timertasks;

import com.gal.coupons.dal.ICouponsDal;
import com.gal.coupons.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.TimerTask;

@Component
public class ExpiredCouponDeletionTimerTask extends TimerTask{

    CouponsLogic couponsLogic;

    @Autowired
    public ExpiredCouponDeletionTimerTask(CouponsLogic couponsLogic) {
        this.couponsLogic = couponsLogic;
    }


    @Override
    public void run() {
            Date date = new Date();
            long dateInMillis = date.getTime();
            Date todayDate = new Date(dateInMillis);

            try {
                couponsLogic.removeExpiredCoupons(todayDate);
            } catch (Exception e) {     //good example to swallow Exception//
                e.printStackTrace();
            }
    }


}
