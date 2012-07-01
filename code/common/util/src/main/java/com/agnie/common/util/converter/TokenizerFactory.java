package com.agnie.common.util.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.tablefile.GeneralException;

public class TokenizerFactory {
	protected static final Log					logger			= LogFactory.getLog(TokenizerFactory.class);
	private static final Map<String, Tokenizer>	methodMapping	= new HashMap<String, Tokenizer>();
	private static TokenizerFactory				INSTANCE;

	private TokenizerFactory() {

	}

	public static TokenizerFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TokenizerFactory();
		}
		return INSTANCE;
	}

	public Tokenizer getTokenizer(Method method) {
		if (method != null) {
			try {
				String methodName = method.getDeclaringClass().getCanonicalName() + "." + method.getName();
				Tokenizer tokenizer = methodMapping.get(methodName);
				if (tokenizer == null) {
					UseTokenizer useTokenizer = method.getAnnotation(UseTokenizer.class);
					if (useTokenizer != null) {
						Class<? extends Tokenizer> tokenzierCls = useTokenizer.tokenizer();
						Constructor<? extends Tokenizer> ctor = tokenzierCls.getDeclaredConstructor(String.class);
						if ("".equals(useTokenizer.separator())) {
							throw new GeneralException("Programming error: Token spearator must not be empty string but yes it can contain other white spaces");
						}
						tokenizer = ctor.newInstance(useTokenizer.separator());
						methodMapping.put(methodName, tokenizer);
					}
				}
				return tokenizer;
			} catch (InstantiationException e) {
				logger.error(e);
			} catch (SecurityException e) {
				logger.error(e);
			} catch (NoSuchMethodException e) {
				logger.error(e);
			} catch (IllegalArgumentException e) {
				logger.error(e);
			} catch (IllegalAccessException e) {
				logger.error(e);
			} catch (InvocationTargetException e) {
				logger.error(e);
			}
		}
		return null;
	}
}
