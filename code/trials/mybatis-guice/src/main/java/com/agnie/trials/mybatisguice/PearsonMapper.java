package com.agnie.trials.mybatisguice;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PearsonMapper {

	@Select("SELECT * FROM test.PEARSON WHERE EMAILID=#{emailId}")
	Pearson getPearson(@Param("emailId") String emailId);

	@Select("SELECT * FROM test.PEARSON")
	List<Pearson> getAll();

	@Select("SELECT FNAME FROM test.PEARSON WHERE EMAILID=#{emailId}")
	String getName(@Param("emailId") String emailId);
}
