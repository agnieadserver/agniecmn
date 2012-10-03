package com.agnie.trials.mybatisguice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class PearsonServiceTest {

	private Injector		injector;
	private PearsonService	ps;

	@Before
	public void setupMyBatisGuice() throws Exception {

		// bindings
		List<Module> modules = this.createMyBatisModule();
		this.injector = Guice.createInjector(modules);

		// prepare the test db
		Environment environment = this.injector.getInstance(SqlSessionFactory.class).getConfiguration().getEnvironment();
		DataSource dataSource = environment.getDataSource();
		ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
		runner.setAutoCommit(true);
		runner.setStopOnError(true);
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		runner.runScript(new FileReader(cl.getResource("initdb.sql").getFile()));
		runner.closeConnection();

		this.ps = injector.getInstance(PearsonService.class);
	}

	@After
	public void shutdown() throws Exception {
		Environment environment = this.injector.getInstance(SqlSessionFactory.class).getConfiguration().getEnvironment();
		DataSource dataSource = environment.getDataSource();
		ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
		runner.setAutoCommit(true);
		runner.setStopOnError(true);
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		runner.runScript(new FileReader(cl.getResource("cleandb.sql").getFile()));
		runner.closeConnection();
	}

	protected List<Module> createMyBatisModule() {
		List<Module> modules = new ArrayList<Module>();
		modules.add(new MyBatisModule() {

			@Override
			protected void initialize() {
				install(JdbcHelper.MySQL);

				bindDataSourceProviderType(PooledDataSourceProvider.class);
				bindTransactionFactoryType(JdbcTransactionFactory.class);
				addMapperClass(PearsonMapper.class);

				bind(PearsonService.class).asEagerSingleton();
			}

		});
		/*
		 * modules.add(new XMLMyBatisModule() {
		 * 
		 * @Override protected void initialize() { setEnvironmentId("test");
		 * setClassPathResource("org/mybatis/guice/sample/mybatis-config.xml"); }
		 * 
		 * });
		 */
		modules.add(new Module() {
			public void configure(Binder binder) {
				Names.bindProperties(binder, createTestProperties());
			}
		});

		return modules;
	}

	protected Properties createTestProperties() {
		Properties myBatisProperties = new Properties();
		try {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			File f = new File(cl.getResource("mybatis.properties").getFile());
			myBatisProperties.load(new FileInputStream(f));
			return myBatisProperties;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void getPearsonTest() {

		System.out.println(ps.getPearsonByEmailId("pranoti.patil@gmail.com"));
		List<Pearson> list = ps.getAll();
		System.out.println(list);
	}

}
