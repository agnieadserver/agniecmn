package com.springriver.example.mybatis;

import java.io.FileReader;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.util.CatgegoryManager;
import com.springriver.example.mybatis.util.CatgegoryManager.SortOrder;

public class CategoryManagerTest {

    private Injector injector;

    @Before
    public void setupData() {
        try {
            this.injector = Guice.createInjector(JdbcHelper.MySQL, new XMLMyBatisModule() {

                @Override
                protected void initialize() {
                    setEnvironmentId("test");
                    setClassPathResource("database-config.xml");
                }
            });
            // Prepare the test db
            Environment environment = this.injector.getInstance(SqlSessionFactory.class).getConfiguration().getEnvironment();
            DataSource dataSource = environment.getDataSource();
            ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
            runner.setAutoCommit(true);
            runner.setStopOnError(true);

            ClassLoader cl = ClassLoader.getSystemClassLoader();
            runner.runScript(new FileReader(cl.getResource("create.sql").getFile()));
            runner.closeConnection();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Exception Before " + e.getMessage());
        }
    }

    @After
    public void cleanData() {

        try {
            // Clear the test db
            Environment environment = this.injector.getInstance(SqlSessionFactory.class).getConfiguration().getEnvironment();
            DataSource dataSource = environment.getDataSource();
            ScriptRunner runner = new ScriptRunner(dataSource.getConnection());
            runner.setAutoCommit(true);
            runner.setStopOnError(true);
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            runner.runScript(new FileReader(cl.getResource("clean.sql").getFile()));
            runner.closeConnection();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Exception After " + e.getMessage());
        }
    }

    @Test
    public void testUpdateUser() {

        // INSERT INTO test.category VALUES (1,'Apolo',26,1000,'Active','Super');
        // INSERT INTO test.category VALUES (2,'Raj Gaurav',27,2000,'InActive','Max');
        // INSERT INTO test.category VALUES (3,'Mohan',28,3000,'InActive','Awesome');
        // INSERT INTO test.category VALUES (4,'Prank',29,4000,'Active','Moka');
        // INSERT INTO test.category VALUES (5,'Habib',30,5000,'InActive','Des');

        Category category = CatgegoryManager.selectCategoryById(1);
        Assert.assertNotNull(category);
        Assert.assertEquals("Apolo", category.getName());
        Assert.assertEquals(26, category.getAge());
        Assert.assertEquals("Active", category.getStatus());
        Assert.assertEquals(4, CatgegoryManager.getALLTestElements("Raj Gaurav", null, null).size());
        Assert.assertEquals(3, CatgegoryManager.getALLElements(null, null, "Active", "name", 2, 3, SortOrder.ASC).size());
        Assert.assertEquals(20, CatgegoryManager.getALLTestElements("Guru", null, null).size());
        Assert.assertEquals("Raj", CatgegoryManager.getALLElements(null, null, "Active", "name", 3, 3, SortOrder.ASC).get(1).getName());
        List<Category> list = CatgegoryManager.getALLElementsWithoutPaging(null, null, "Active", "name", SortOrder.DESC);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Name " + list.get(i).getName());

        }
    }
}
