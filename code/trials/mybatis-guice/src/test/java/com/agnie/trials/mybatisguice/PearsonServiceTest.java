package com.agnie.trials.mybatisguice;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
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

	@Test
	public void getPearsonTest() {
		final Properties myBatisProperties = new Properties();
		try {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			File f = new File(cl.getResource("mybatis.properties").getFile());
			myBatisProperties.load(new FileInputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Injector injector = Guice.createInjector(new MyBatisModule() {

			@Override
			protected void initialize() {
				install(JdbcHelper.MySQL);

				bindDataSourceProviderType(PooledDataSourceProvider.class);
				bindTransactionFactoryType(JdbcTransactionFactory.class);
				addMapperClass(PearsonMapper.class);

				bind(PearsonService.class).asEagerSingleton();
			}

		}, new Module() {
			public void configure(Binder binder) {
				/*
				 * binds the properties configuration; JDBC.host, JDBC.port and JDBC.schema will be used to be replaced
				 * in the URL Pattern
				 */
				Names.bindProperties(binder, myBatisProperties);
			}
		});
		PearsonService ps = injector.getInstance(PearsonService.class);
		System.out.println(ps.getPearsonByEmailId("pranoti.patil@gmail.com"));
		List<Pearson> list = ps.getAll();
		System.out.println(list);
	}

}
