/*
 * Copyright (c) 2018.  semo
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;


public class Main {

    private static final Logger log = LogManager.getLogger("Numberplate Generator");

    public static void main(String[] args) throws Exception {

//        NumberPlate np = new NumberPlate();
//        System.out.println(np.buildPlateImage("."));

        ReadConfigs rc = new ReadConfigs();
        Map<String, String> stuff = rc.getPropertiesAsMap("/opt/kafka_2.11-1.0.1/config/zookeeper.properties");
//
//        for (String key: stuff.keySet()) {
//            System.out.println(key + " " + stuff.get(key));
//        }
//
        Properties props = new Properties();
//        props.putAll(stuff);
//        System.out.println(props.stringPropertyNames());

        props.put("dataDir", stuff.get("dataDir"));
        props.put("clientPort", "5999");

        QuorumPeerConfig quorumConfiguration = new QuorumPeerConfig();
        try {
            quorumConfiguration.parseProperties(props);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        ZooKeeperServerMain zooKeeperServer = new ZooKeeperServerMain();
        final ServerConfig configuration = new ServerConfig();
        configuration.readFrom(quorumConfiguration);

        new Thread(() -> {
            try {
                zooKeeperServer.runFromConfig(configuration);
            } catch (IOException e) {
                log.error("ZooKeeper Failed", e);
            }
        }).start();
    }
}
