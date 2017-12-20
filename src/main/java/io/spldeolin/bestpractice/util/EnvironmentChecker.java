package io.spldeolin.bestpractice.util;

import java.sql.DriverManager;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;

/**
 * 初始化工具类：用于自检运行环境是否正常<br>
 * 除init()方法外，每一种方法代表了对一项环境条件的检查，<br>
 * 若某一项环境出现异常，本系统将会直接退出。<br>
 *
 * @author Deolin
 */
@Log4j2
public class EnvironmentChecker {

    public void init() {
//        checkMysqlConnection();
//        checkRedisConnection();
    }

    private void checkMysqlConnection() {
        log.info("开始检查Mysql环境");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/best_practice?characterEncoding=UTF-8&useSSL=false", "root", "root");
        } catch (Exception e) {
            log.error("Mysql无法连接，系统关闭");
            System.exit(0);
            return;
        }
        log.info("Mysql环境正常");
    }

    private void checkRedisConnection() {
        log.info("开始检查Redis环境");
        Jedis jedis = new Jedis("localhost", 6379);
        try {
            jedis.get("a");

        } catch (Exception e) {
            log.error("Redis无法连接，系统关闭");
            System.exit(0);
            return;
        } finally {
            jedis.close();
        }
        log.info("Redis环境正常");
    }

}
