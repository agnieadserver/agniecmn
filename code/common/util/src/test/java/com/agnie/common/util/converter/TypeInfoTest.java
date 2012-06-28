package com.agnie.common.util.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.util.tablefile.TableHeader;
import com.agnie.common.util.validator.NotNull;
import com.agnie.common.util.validator.NotNullValidator;

public class TypeInfoTest {

	@Test
	public void SimpleBeanTest() {
		TypeInfo info = new TypeInfo(SampleBean.class);
		Assert.assertEquals(false, info.isCollectionType());
		Assert.assertEquals(true, info.isMultiColumnType());
		List<String> directSingleCols = info.getImmidiateSingleColumnList();
		Assert.assertEquals(3, directSingleCols.size());
		Assert.assertEquals(true, info.getTypeInfo("Phones").isCollectionType());
		Assert.assertEquals(false, info.getTypeInfo("Phones").isMultiColumnType());
	}

	@Test
	public void complicatedTypeTest() {
		TypeInfo info = new TypeInfo(ComplicatedBean.class);
		Assert.assertEquals(false, info.isCollectionType());
		Assert.assertEquals(true, info.isMultiColumnType());
		List<String> directSingleCols = info.getImmidiateSingleColumnList();
		Assert.assertEquals(1, directSingleCols.size());
		List<String> allSingleCols = info.getAllSingleColumnList();
		Assert.assertEquals(4, allSingleCols.size());
		Iterator<String> allDirectProperties = info.getImmidiateAllPropertiesIterator();
		int count = 0;
		for (; allDirectProperties.hasNext();) {
			String string = (String) allDirectProperties.next();
			count++;

		}
		Assert.assertEquals(2, count);
		Assert.assertEquals(true, info.getTypeInfo("SampleBeans").isCollectionType());
		Assert.assertEquals(true, info.getTypeInfo("SampleBeans").isMultiColumnType());
		Assert.assertEquals(true, info.getTypeInfo("Phones").isCollectionType());
		Assert.assertEquals(false, info.getTypeInfo("Phones").isMultiColumnType());

	}

	@Test
	public void moreComplicatedTest() {
		TypeInfo info = new TypeInfo(AnotherComplicatedBean.class);
		Assert.assertEquals(false, info.isCollectionType());
		Assert.assertEquals(true, info.isMultiColumnType());
		List<String> directSingleCols = info.getImmidiateSingleColumnList();
		boolean found = false;
		for (String string : directSingleCols) {
			if ("Full Name".equals(string)) {
				found = true;
			}
		}
		Assert.assertEquals(true, found);
		Assert.assertEquals(NotNullValidator.class, info.getTypeInfo("Full Name").getValidators().get(0).getClass());
	}

}

@MultiColumnType
class AnotherComplicatedBean {
	private String		fullname;
	private SampleBean	sampleBean;

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	@TableHeader(name = "Full Name")
	@NotNull
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the sampleBean
	 */
	public SampleBean getSampleBean() {
		return sampleBean;
	}

	/**
	 * @param sampleBean
	 *            the sampleBean to set
	 */
	public void setSampleBean(SampleBean sampleBean) {
		this.sampleBean = sampleBean;
	}

}

@MultiColumnType
class ComplicatedBean {
	private String				fullname;
	private List<SampleBean>	sampleBeans;

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @param sampleBeans
	 *            the sampleBeans to set
	 */
	public void addSampleBeans(SampleBean sampleBean) {
		this.sampleBeans.add(sampleBean);
	}

}

@MultiColumnType
class SampleBean {
	private int				test;
	private String			name;
	private List<String>	phones	= new ArrayList<String>();

	/**
	 * @return the test
	 */
	public int getTest() {
		return test;
	}

	/**
	 * @param test
	 *            the test to set
	 */
	public void setTest(int test) {
		this.test = test;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void addPhones(String phone) {
		phones.add(phone);
	}

}
