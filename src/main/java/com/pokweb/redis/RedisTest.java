package com.pokweb.redis;

import com.google.gson.Gson;
import com.pokweb.common.base.User;
import com.pokweb.common.response.WebResponse;
import com.pokweb.demo2.dao.DemoDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.ReactiveStringCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
@Slf4j
public class RedisTest {
    @Resource
    private DemoDao demoDao;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private Redisson redisson;

    @Resource
    private Gson gson;

    /**
     * 普通的redis使用
     *
     * @return
     */
    @PostMapping("PushRedis")
    public WebResponse PushRedis() {
        User user = new User();
        Map<String, String> demo = demoDao.getDemo();
//        Map<String, String> demo = null;
        Optional.ofNullable(demo.get("username")).orElseThrow(() -> new NullPointerException("空指针"));
        redisTemplate.opsForValue().set(demo.get("username"), demo);
        Object username = redisTemplate.opsForValue().get(demo.get("username"));
        Map<String, String> map = gson.fromJson(username.toString(), Map.class);
        log.info(map.toString());
        return WebResponse.ok(map.get("pokid"));
    }

    /**
     * redisson简单的使用
     */
    @PostMapping("redisson")
    public WebResponse postRedisson() {
//        jdbcTemplate.execute("insert into admin (pokid,username,password) values ('3','yy','cc')");

        RLock lock = redisson.getLock("123");
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        return WebResponse.ok();
    }
    /******************bitmap统计在线人数************************/
    /**
     * redis 命令
     * setbit online 用户id123 1   // 用户id123 上线
     * setbit online 用户id123 0   // 用户id123 下线
     * getbit online 用户id123    //查看用户id123是否在线  0 下线 ， 1 上线
     * bitcount online           //查看多少用户为1(在线人数)
     *
     * @return
     */
    @PostMapping("bitmap1")
    public WebResponse bitmap1() {
        Map<String, String> demo = demoDao.getDemo();
        Optional.ofNullable(demo.get("username")).orElseThrow(() -> new NullPointerException("空指针"));
        redisTemplate.opsForValue().setBit("online", 1, true);
        Object username = redisTemplate.opsForValue().get(demo.get("username"));
        Map<String, String> map = gson.fromJson(username.toString(), Map.class);
        log.info(map.toString());
        return WebResponse.ok(map.get("username"));
    }

    /**
     * 连续签到次数（月初到月末）
     */
    @PostMapping("getSiginMonth")
    public WebResponse getSigin() {
        redisTemplate.opsForValue().setBit("887:202205", 2, true);//20220502 签到
        redisTemplate.opsForValue().setBit("887:202205", 3, true);//20220503 签到
        redisTemplate.opsForValue().setBit("887:202205", 10, true);//20220510 签到
        redisTemplate.opsForValue().setBit("887:202205", 20, true);//20220520 签到

        //30为30天内
        BitFieldSubCommands command = BitFieldSubCommands.create()
                .get(BitFieldSubCommands.BitFieldType.unsigned(30)).valueAt(1);


        //用户id为887的在202205签到
        List list = redisTemplate.opsForValue().bitField("887:202205", command);
        String l = Long.toString((Long) list.get(0), 2);
        int count = 0;
        int maxCount = 0;

        for (int i = 0; i < l.length(); i++) {
            if (l.getBytes()[i] == "1".getBytes()[0]) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        System.out.println(maxCount);
        System.out.println(l);


        return WebResponse.ok(maxCount);
    }

    /**
     * 连续签到次数（7天活动）
     */
    @PostMapping("getSigin7day")
    public WebResponse getSigin7day() {
        //从1开始到7
        redisTemplate.opsForValue().setBit("887:7day", 1, true);//20220502 签到
//        redisTemplate.opsForValue().setBit("887:7day", 2, true);//20220502 签到
        redisTemplate.opsForValue().setBit("887:7day", 3, true);//20220503 签到
        redisTemplate.opsForValue().setBit("887:7day", 4, true);//20220503 签到
        redisTemplate.opsForValue().setBit("887:7day", 5, true);//20220510 签到
        redisTemplate.opsForValue().setBit("887:7day", 6, true);//20220520 签到

        //获取活动开启的时间
        BitFieldSubCommands command = BitFieldSubCommands.create()
                .get(BitFieldSubCommands.BitFieldType.unsigned(7)).valueAt(1);
        List list = redisTemplate.opsForValue().bitField("887:7day", command);
        String l = Long.toString((Long) list.get(0), 2);
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < l.length(); i++) {
            if (l.getBytes()[i] == "1".getBytes()[0]) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
            maxCount = Math.max(maxCount, count);
        }
        System.out.println(maxCount);
        System.out.println(l);
        redisTemplate.expire("887:7day", 5,TimeUnit.SECONDS);
        return WebResponse.ok(maxCount);
    }


    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        String s = Integer.toString(780272192, 2);
        System.out.println(s);
        String t = "101110100000100000001001000000";
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.getBytes()[i] == "1".getBytes()[0]) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        System.out.println(maxCount);
        /************2个日期相差的天数******************/
        // 定义项目进行天数
        long Days = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long time = sdf.parse("2021-02-28").getTime();//现在
            long time1 = sdf.parse("2021-02-27").getTime();//
            Days = (int) ((time -time1) /(24 * 60 * 60 * 1000));
            System.out.println("===>"+Days);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
