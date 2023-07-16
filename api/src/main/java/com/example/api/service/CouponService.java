package com.example.api.service;

import com.example.api.dao.CouponCountRepository;
import com.example.api.dao.CouponRepository;
import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;

    public void save(Long userId) {
        long count = couponCountRepository.increment();
        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }

}
