package io.spldeolin.bestpractice.util;

import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * 初始化工具类：用于自检运行环境是否正常<br>
 * 除init()方法外，每一种方法代表了对一项环境条件的检查，<br>
 * 若某一项环境出现异常，本系统将会直接退出。<br>
 *
 * @author Deolin
 */
public class EnvironmentChecker {

    private static final Logger LOG = LogManager.getLogger(EnvironmentChecker.class);

    public void init() {
//        checkMysqlConnection();
//        checkRedisConnection();
    }

    private void checkMysqlConnection() {
        LOG.info("开始检查Mysql环境");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/best_practice?characterEncoding=UTF-8&useSSL=false", "root", "root");
        } catch (Exception e) {
            LOG.error("Mysql无法连接，系统关闭");
            System.exit(0);
            return;
        }
        LOG.info("Mysql环境正常");
    }

    private void checkRedisConnection() {
        LOG.info("开始检查Redis环境");
        Jedis jedis = new Jedis("localhost", 6379);
        try {
            jedis.get("a");

        } catch (Exception e) {
            LOG.error("Redis无法连接，系统关闭");
            System.exit(0);
            return;
        } finally {
            jedis.close();
        }
        LOG.info("Redis环境正常");
    }

}
