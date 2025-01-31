package com.autsub.autsub.AICalls.Config;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucketBuilder;

import java.time.Duration;

public class RateLimiter {

    private final Bucket bucket;

    public RateLimiter(int requestsPerMinute) {
       
        Bandwidth limit = Bandwidth.classic(requestsPerMinute, Refill.intervally(requestsPerMinute,
         Duration.ofMinutes(1)));


        LocalBucketBuilder builder = Bucket.builder();
        this.bucket = builder.addLimit(limit).build();
    }

    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }
}