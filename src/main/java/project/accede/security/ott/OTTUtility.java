package project.accede.security.ott;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class OTTUtility {

    private static final Integer EXPIRES_IN_MIN = 5;

    private final LoadingCache<String, Integer> ottCache;

    public OTTUtility() {
        this.ottCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRES_IN_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }
    public Integer generateOtt(String key) {
        Random random = new Random();
        Integer ottCode = 1000000 + random.nextInt(9000000);

        ottCache.put(key, ottCode);
        return ottCode;
    }

    public Integer getOTTByKey(String key) {
        return ottCache.getIfPresent(key);
    }
}
