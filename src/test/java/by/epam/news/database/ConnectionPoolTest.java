package by.epam.news.database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:SpringBeansTest.xml" })
public class ConnectionPoolTest {

	private ConnectionPool pool;
	private final static int POOL_USERS = 100;
	private final static int USERS_USING = 100;
	
	@Autowired
	public void setConnectionPool(ConnectionPool pool) {
		this.pool = pool;
	}

	@Test
	public void multuThreadUsing() {
		ExecutorService executor = Executors.newFixedThreadPool(POOL_USERS);
		for (int i = 0; i < POOL_USERS; i++) {
			executor.execute(new PoolUser());
		}
		executor.shutdown();
        while (!executor.isTerminated()) {
        }

	}
	
	@Test
	public void getConnection() throws DataBaseException, SQLException{
		Connection connection = pool.getConnection();
		assertNotNull(connection);
		assertFalse(connection.isClosed());
		pool.returnConnection(connection);
	}
	
	@Test(expected = DataBaseException.class)
	public void returnBadConnection() throws DataBaseException, SQLException{
		Connection connection = pool.getConnection();
		connection.close();
		pool.returnConnection(connection);
	}

	private class PoolUser implements Runnable {
		

		@Override
		public void run() {
			try {
				for (int i = 0; i < USERS_USING; i++) {
					Connection connection = pool.getConnection();
					Thread.sleep(1);
					pool.returnConnection(connection);
				}
			} catch (DataBaseException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
