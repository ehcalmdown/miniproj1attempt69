package sg.nus.iss.mini6969.repositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class PairingRepository {

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;
    public void save(String food, String payload){
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set(food.toLowerCase(),payload, Duration.ofMinutes(4));


    }

    public Optional<String> get(String food){
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get(food.toLowerCase());
        if(null==value)
            return Optional.empty();//chuk's empty box rule
        return Optional.of(value);
    }
    
}
