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
