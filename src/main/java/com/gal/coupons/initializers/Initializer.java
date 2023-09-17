package com.gal.coupons.initializers;

import com.gal.coupons.timertasks.ExpiredCouponDeletionTimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;

@Component
public class Initializer {

    private Timer timer;
    private ExpiredCouponDeletionTimerTask expiredCouponDeletionTimerTask;


    @Autowired
    public Initializer(ExpiredCouponDeletionTimerTask expiredCouponDeletionTimerTask) {
        this.timer = new Timer();
        this.expiredCouponDeletionTimerTask = expiredCouponDeletionTimerTask;
        removeExpiredCoupons();
    }

    private void removeExpiredCoupons(){
        timer.schedule(expiredCouponDeletionTimerTask,0, 86400000);
    }


}
