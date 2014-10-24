/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import com.agnie.gwt.client.renderer.Person;

public class TestGenericMulti<K,V> {

	private K k;
	private V v;
	
	public K getK(){
		return this.k;
	}
	public V getV(){
		return this.v;
	}
	
	public void setK(K k) {
		this.k=k;
	}
	public void setV(V v) {
		this.v=v;
	}
	public static void main(String[] args) {
		TestGenericMulti<String,String> tGenMulti=new TestGenericMulti<String,String>();
		tGenMulti.setK("k1");
		tGenMulti.setV("v1");
		
		Person p=new Person();
		p.setFname("Suru");
		p.setEmailid("Suru@Agnie");
		p.setAge(30);
		
		TestGenericMulti<String,Person> tGenPerson=new TestGenericMulti<String, Person>();
		tGenPerson.setK(p.getFname());
		tGenPerson.setV(p);
		System.out.println(tGenMulti.getK()+" =="+tGenMulti.getV());
		
		System.out.println(tGenPerson.getK()+"=="+"\n"+tGenPerson.getV().getFname()+""+tGenPerson.getV().getAge()+" "+tGenPerson.getV().getEmailid());
	}

}
