package com.pokweb.web.thread.service.impl;

import com.pokweb.common.utils.RandomStrUtil;
import com.pokweb.web.thread.dao.mapper.ThreadDao;
import com.pokweb.web.thread.service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

@Service("threadServiceImpl")
public class ThreadServiceImpl implements ThreadService {
    private static final Logger logger = LoggerFactory.getLogger(ThreadServiceImpl.class);
    @Resource
    private ThreadDao threadDao;
    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Override
    public void add() {
        threadDao.select();
    }

    @Override
    public void insert() {

        new Thread(() -> {
            transactionTemplate.execute(new TransactionCallback<Object>() {
                @Override
                public Object doInTransaction(TransactionStatus status) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            String num = RandomStrUtil.getNum(5);
                            if (Integer.parseInt(num) > 50000) {
                                int a = 1 / 0;
                            }
                            System.out.println("........");
                            insert01(num);
                        }
                        return null;
                    } catch (Exception e) {
                        //事务回滚
                        status.setRollbackOnly();
                        return null;
                    }

                }

            });

        }).start();

//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                String num = RandomStrUtil.getNum(5);
//                if (Integer.parseInt(num) > 50000) {
//                    int a = 1 / 0;
//                }
//                insert01(num);
//            }
//        }).start();

    }

    @Override
    public void PoolTaskExecutor() {
        long start = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
            threadDao.select();
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPoolTaskExecutor);

        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
            threadDao.select();
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPoolTaskExecutor);
        try {
            //1 和2 完成再执行下去
            CompletableFuture.allOf(voidCompletableFuture1, voidCompletableFuture2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("完成====>" + (System.currentTimeMillis() - start));
    }

    public void insert01(String num) {
        threadDao.insertorupdate(Integer.parseInt(num), "hua", "9999");
    }

}



