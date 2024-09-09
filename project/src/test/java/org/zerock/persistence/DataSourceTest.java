package org.zerock.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

import static org.junit.Assert.fail; // 추가된 부분
import org.slf4j.Logger; // SLF4J Logger 추가
import org.slf4j.LoggerFactory; // SLF4J LoggerFactory 추가

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {

    private static final Logger log = LoggerFactory.getLogger(DataSourceTest.class); // SLF4J Logger 초기화

    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;
    
    @Setter(onMethod_ = {@Autowired})
    private SqlSessionFactory sqlSessionFactory;
    
    // HikariCP 커넥트 테스트
    @Test
    public void testConnection() {
        try (Connection con = dataSource.getConnection()) {
            log.info("Connection successful: " + con); // 로그 출력
        } catch (Exception e) {
            fail(e.getMessage()); // 실패 메시지로 예외의 메시지 출력
        }
    }
    
    // Mybatis 커넥트 테스트
    @Test
    public void testMybatis() {
    	try (SqlSession session = sqlSessionFactory.openSession();
    			Connection con = session.getConnection()) {
            log.info(session.toString());
            log.info(con.toString());
        } catch (Exception e) {
            fail(e.getMessage()); // 실패 메시지로 예외의 메시지 출력
        }
    }
    
}
