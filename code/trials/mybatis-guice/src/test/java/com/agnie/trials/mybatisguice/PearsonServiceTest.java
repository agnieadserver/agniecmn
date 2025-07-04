/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.trials.mybatisguice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

public class PearsonServiceTest {

    private Injector       injector;
    private PearsonService ps;

    @Before
    public void setupMyBatisGuice() throws Exception {

        // bindings
        this.injector = Guice.createInjector(new MyBatisModule() {

            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);

                bindDataSourceProviderType(PooledDataSourceProvider.class);
                bindTransactionFactoryType(JdbcTransactionFactory.class);
                addMapperClass(PearsonMapper.class);

                bind(PearsonService.class).asEagerSingleton();
                Properties myBatisProperties = new Properties();
                try {
                    ClassLoader cl = ClassLoader.getSystemClassLoader();
                    File f = new File(cl.getResource("mybatis.properties").getFile());

                    myBatisProperties.load(new FileInputStream(f));
                    Names.bindProperties(binder(), myBatisProperties);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

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

    @Test
    public void getPearsonTest() {

        System.out.println(ps.getPearsonByEmailId("pranoti.patil@gmail.com"));
        List<Pearson> list = ps.getAll();
        System.out.println(list);
        System.out.println(ps.getPearsonNameByEmailId("pranoti.patil@gmail.com"));
    }

}
