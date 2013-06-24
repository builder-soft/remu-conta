package cl.buildersoft.framework.util;

import java.util.HashMap;
import java.util.Map;

import cl.buildersoft.framework.exception.BSConfigurationException;
import cl.buildersoft.framework.service.BSParentChildService;

public class BSFactory {
	private static Map<String, Class> instances = new HashMap<String, Class>();

	public static BSParentChildService getParentChildService() {
		return (BSParentChildService) getObject("cl.buildersoft.framework.service.impl.BSParentChildServiceImpl");
	}

	private static Object getObject(String className) {
		Object out;
		try {
			Class claz = instances.get(className);
			if (claz == null) {
				claz = Class.forName(className);
				instances.put(className, claz);
			}
			out = claz.newInstance();
		} catch (Exception e) {
			throw new BSConfigurationException(e);
		}

		return out;
	}
}
