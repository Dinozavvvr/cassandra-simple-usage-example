package ru.dinar.cassandraexmapleproject.monitoring;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.LoadBalancingPolicy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConnectionPoolConsoleMonitoring {

    private final Cluster cluster;
    private final List<Session> sessions;

    private LoadBalancingPolicy loadBalancingPolicy;
    private PoolingOptions poolingOptions;

    @PostConstruct
    private void start() {
        loadBalancingPolicy = cluster.getConfiguration().getPolicies().getLoadBalancingPolicy();
        poolingOptions = cluster.getConfiguration().getPoolingOptions();
    }

    @Scheduled(fixedRate = 500) // 5 seconds
    public void monitorConnectionPool() {
        for (Session session : sessions) {
            Session.State state = session.getState();

            for (Host host : state.getConnectedHosts()) {
                HostDistance distance = loadBalancingPolicy.distance(host);
                int connections = state.getOpenConnections(host);

                int inFlightQueries = state.getInFlightQueries(host);
                log.info("{} {}: {} connections={}, current load={}, max load={}",
                        session, state, host, connections, inFlightQueries,
                        connections * poolingOptions.getMaxRequestsPerConnection(distance));
            }
        }
    }

}
