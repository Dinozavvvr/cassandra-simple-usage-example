package ru.dinar.cassandraexmapleproject.job;

import com.datastax.driver.core.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@Profile("jobs-test")
@RequiredArgsConstructor
public class SessionTwoLoader {

    private static final int QUERY_COUNT = 10;

    private final Session sessionTwo;
    private final ExecutorService executorService = Executors.newFixedThreadPool(QUERY_COUNT);

    @Scheduled(fixedRate = 1000)
    private void load() {
        for (int i = 0; i < QUERY_COUNT; i++) {
            executorService.submit(() -> sessionTwo.executeAsync("select * user"));
        }
    }

}