package com.agnie.common.util.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.util.client.converter.MultiColumnType;
import com.agnie.common.util.client.tablefile.DevException;
import com.agnie.common.util.client.tablefile.TableBean;
import com.agnie.common.util.client.tablefile.TableHeader;
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
		Iterator<TypeInfo> allDirectProperties = info.getImmidiateAllPropertiesIterator();
		int count = 0;
		for (; allDirectProperties.hasNext();) {
			@SuppressWarnings("unused")
			TypeInfo info1 = allDirectProperties.next();
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

	@SuppressWarnings("unused")
	@Test
	public void negativeTest() {
		try {
			TypeInfo info = new TypeInfo(NegativeBean.class);
			Assert.assertTrue(false);
		} catch (DevException e) {
			Assert.assertTrue(true);
			Assert.assertEquals("neither.multicolumn.nor.has.converter", e.getErrorCode());
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void negativeTest1() {
		try {
			TypeInfo info = new TypeInfo(NegativeBean1.class);
			Assert.assertTrue(false);
		} catch (DevException e) {
			Assert.assertTrue(true);
			Assert.assertEquals("need.toimplement.tablebean.interface", e.getErrorCode());
		}
	}

	@Test
	public void tokenizerTest() {
		TypeInfo info = new TypeInfo(SampleBean.class);
		Assert.assertEquals(false, info.isCollectionType());
		Assert.assertEquals(true, info.isMultiColumnType());
		List<String> directSingleCols = info.getImmidiateSingleColumnList();
		Assert.assertEquals(3, directSingleCols.size());
		Assert.assertEquals(true, info.getTypeInfo("Phones").isCollectionType());
		Assert.assertEquals(false, info.getTypeInfo("Phones").isMultiColumnType());
		TypeInfo phoneType = info.getTypeInfo("Phones");
		Assert.assertNotNull(phoneType.getTokenizer());
		Assert.assertEquals(DefaultTokenizer.class, phoneType.getTokenizer().getClass());
		Assert.assertEquals("~", phoneType.getTokenizer().getTokenSeparator());
		String[] tokens = phoneType.getTokenizer().tokenize("pandurang~patil");
		Assert.assertNotNull(tokens);
		Assert.assertEquals(2, tokens.length);

	}

	@Test
	public void negativeTest2() {
		try {
			@SuppressWarnings("unused")
			TypeInfo info = new TypeInfo(NegativeBean2.class);
		} catch (DevException e) {
			Assert.assertEquals(DevException.class, e.getClass());
			Assert.assertEquals("property.require.tokenizer", e.getErrorCode());
		}
	}

	@Test
	public void negativeTest3() {
		try {
			@SuppressWarnings("unused")
			TypeInfo info = new TypeInfo(NegativeBean3.class);
		} catch (DevException e) {
			Assert.assertEquals(DevException.class, e.getClass());
			Assert.assertEquals("require.one.single.noncollection.notnull", e.getErrorCode());
		}
	}

	@Test
	public void notNullTest() {
		TypeInfo info = new TypeInfo(SampleBean.class);
		Assert.assertEquals(false, info.isCollectionType());
		Assert.assertEquals(true, info.isMultiColumnType());
		Assert.assertEquals(true, info.getTypeInfo("Name").isNotNull());
		Assert.assertEquals(false, info.getTypeInfo("Test").isNotNull());
		Assert.assertNotNull(info.getImmNotNullSingleColList().size());
		Assert.assertEquals(1, info.getImmNotNullSingleColList().size());
	}

}

@MultiColumnType
class NegativeBean3 implements TableBean {
	private Set<SampleBean>	sampleBeans;

	public void addSampleBean(SampleBean bean) {
		sampleBeans.add(bean);
	}

	public void insertError(String property, String value, List<String> errors) {
	}
}

@MultiColumnType
class NegativeBean2 implements TableBean {
	private String			name;
	private List<String>	phones;

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

	public void addPhone(String phone) {
		phones.add(phone);
	}

	public void insertError(String property, String value, List<String> errors) {
		// TODO Auto-generated method stub

	}
}

@MultiColumnType
class NegativeBean1 {

	private String	negative;

	/**
	 * @return the negative
	 */
	public String getNegative() {
		return negative;
	}

	/**
	 * @param negative
	 *            the negative to set
	 */
	public void setNegative(String negative) {
		this.negative = negative;
	}
}

class NegativeBean implements TableBean {
	private String	negative;

	/**
	 * @return the negative
	 */
	public String getNegative() {
		return negative;
	}

	/**
	 * @param negative
	 *            the negative to set
	 */
	public void setNegative(String negative) {
		this.negative = negative;
	}

	public void insertError(String property, String value, List<String> errors) {
		// TODO Auto-generated method stub

	}

}

@MultiColumnType
class AnotherComplicatedBean implements TableBean {
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

	public void insertError(String property, String value, List<String> errors) {
		// TODO Auto-generated method stub

	}

}

@MultiColumnType
class ComplicatedBean implements TableBean {
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
	@NotNull
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

	public void insertError(String property, String value, List<String> errors) {
		// TODO Auto-generated method stub

	}

}

@MultiColumnType
class SampleBean implements TableBean {
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
	@NotNull
	public void setName(String name) {
		this.name = name;
	}

	@UseTokenizer(separator = "~")
	public void addPhones(String phone) {
		phones.add(phone);
	}

	public void insertError(String property, String value, List<String> errors) {
		// TODO Auto-generated method stub

	}

}
