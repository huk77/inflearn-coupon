package com.example.api.service;

import com.example.api.dao.CouponCountRepository;
import com.example.api.dao.CouponRepository;
import com.example.api.dao.RedisUserRepository;
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
    private final RedisUserRepository redisUserRepository;

    public void save(Long userId) {
        Long save = redisUserRepository.save(String.valueOf(userId));
        if (save != 1) {
            return;
        }

        long count = couponCountRepository.increment();
        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }

}
