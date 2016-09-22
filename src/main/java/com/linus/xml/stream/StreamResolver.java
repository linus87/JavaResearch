package com.linus.xml.stream;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * 
 * @author lyan2
 *
 */
public class StreamResolver {
	private final static XMLInputFactory _streamFactory = XMLInputFactory.newInstance();
	private static Logger log = Logger.getLogger(StreamResolver.class.getName());
	
	/**
	 * Parse the xml, and map to an object.
	 *
	 * @param inputStream    The Input Stream
	 * @param clazz          The Class
	 * @return               The Object
	 * @throws IntrospectionException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws XmlParseException
	 */
	public <T> T parseXmlToObject (InputStream inputStream, Class <T> clazz) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IntrospectionException  {
		return parseXmlToObject(inputStream, clazz, null);
	}
	
	/**
	 * Parse the xml, and map to an object.
	 *
	 * @param inputStream    The Input Stream
	 * @param clazz          The Class
	 * @param root           The root element matched object in the XML tree
	 * @return               The Object
	 * @throws IntrospectionException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws XmlParseException
	 */
	public <T> T parseXmlToObject (InputStream inputStream, Class <T> clazz, String root) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IntrospectionException {
		XMLStreamReader reader = null;
		T obj = null;

		try {
			reader = _streamFactory.createXMLStreamReader(inputStream);

			while (reader.hasNext()) {
				reader.next();
				int event = reader.getEventType();

				// parse staring from the root element.
				if (XMLStreamConstants.START_ELEMENT == event) {
					String startElement = reader.getLocalName();
					log.info(startElement);
					if (root != null && !root.isEmpty()) {
						if (root.equalsIgnoreCase(startElement)) {
							obj = parseElement(reader, startElement, clazz, null);
							break;
						} else {
							continue;
						}
					} else {
						obj = parseElement(reader, startElement, clazz, null);
						break;
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return obj;
	}
	
	private <T> T parseElement(XMLStreamReader reader,
			String element, Class<T> clazz, List<Object> listInstance) throws XMLStreamException,
			IllegalAccessException, InstantiationException,
			IntrospectionException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException {
		Stack<String> stack = new Stack<String>();
		Map<Method, List<Object>> listMap = new HashMap <Method, List<Object>> ();
		String temValue = null;
		T obj = null;
		
		if (!String.class.isAssignableFrom(clazz) && !Collection.class.isAssignableFrom(clazz) 
				&& !clazz.isPrimitive() && !clazz.isEnum() && !Number.class.isAssignableFrom(clazz) && listInstance == null){
			obj = clazz.newInstance();
		}
		
		Map<String, List<PropertyDescriptor>> map = getPropertyDescriptors(clazz);
		List<PropertyDescriptor> instanceProperties = map.get("instance");
		List<PropertyDescriptor> listProperties = map.get("list");
		List<PropertyDescriptor> stringProperties = map.get("string");
		List<PropertyDescriptor> primitiveProperties = map.get("primitive");
		List<PropertyDescriptor> enumProperties = map.get("enum");
		
		// deal with all attributes
		for (int i = 0; i < reader.getAttributeCount(); i++) {
			String attributeName = reader.getAttributeLocalName(i);
			log.info("Attrbute name:" + attributeName + ", value: " + reader.getAttributeValue(i));
			
			if (resolveAsStringProperty(reader.getAttributeLocalName(i), reader.getAttributeValue(i), stringProperties, obj)
				|| resolveAsPrimitiveProperty(attributeName, reader.getAttributeValue(i), primitiveProperties, obj)
				|| resolveAsEnumProperty(attributeName, reader.getAttributeValue(i), enumProperties, obj)) {
				continue;
			}
		}

		while (reader.hasNext()) {
			reader.next();
			int event = reader.getEventType();

			if (XMLStreamConstants.START_ELEMENT == event) {
				String startElement = reader.getLocalName();
				boolean isInstanceType = false;
				
				if (listInstance == null) {
					// from xml, check if the element has children elements;
					// to the object, check if the related property is user defined type.
					if (resolveAsInstanceProperty(startElement, reader, instanceProperties, obj)
						|| resolveAsListProperty(startElement, reader, listProperties, obj, listMap)) {
						isInstanceType = true;
						continue;
					}
				} else {
					log.info("startElement:" + startElement + ", property type:" + clazz.getName());
					if (!String.class.isAssignableFrom(clazz) && !Collection.class.isAssignableFrom(clazz) 
							&& !clazz.isPrimitive() && !clazz.isEnum() && !Number.class.isAssignableFrom(clazz)){
						isInstanceType = true;
						Object child = parseElement(reader, startElement, clazz, null);
						listInstance.add(child);
					}
				}

				// the root element and the elements with children won't be put into stack.
				if (!startElement.equals(element) && !isInstanceType) {
					stack.push(startElement);
				}
			} else if (XMLStreamConstants.CHARACTERS == event) {
				temValue = reader.getText();
			} else if (XMLStreamConstants.END_ELEMENT == event) {
				String endElement = reader.getLocalName();
				// stop parsing when meeting the end of the root element
				if (endElement.equals(element)) {
					if (clazz.isPrimitive()) {
						return (T)resolvePrimitiveValue(temValue, clazz);
					} else if (String.class.isAssignableFrom(clazz)) {
						return (T)temValue;
					} else if (clazz.isEnum()) {
						return (T)resolveEnumValue(temValue, clazz);
					}
					break;
				}

				// retrieve the element with value and match the object property.
				if (endElement.equals(stack.pop()) && stack.isEmpty()) {
					if (resolveAsStringProperty(endElement, temValue, stringProperties, obj)
							|| resolveAsPrimitiveProperty(endElement, temValue, primitiveProperties, obj)
							|| resolveAsEnumProperty(endElement, temValue, enumProperties, obj)) {
								continue;
						}
				}
			}
		}

		return obj;
	}
	
	/**
	 * If element is one of the parent bean's string properties, then parse value as a primitive value and return true.
	 * @param name
	 * @param value
	 * @param descriptors Parent bean's all string type property descriptors.
	 * @param obj Parent bean.
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private boolean resolveAsStringProperty(String name, String value, List<PropertyDescriptor> descriptors, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean found = false;
		for (PropertyDescriptor property : descriptors) {
			if (name.equalsIgnoreCase(property.getDisplayName())) {
				Method setter = property.getWriteMethod();
				setter.invoke(obj, value);
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * If element is one of the parent bean's primitive properties, then parse value as a primitive value and return true.
	 * @param name
	 * @param value
	 * @param descriptors Parent bean's all primitive type property descriptors.
	 * @param obj Parent bean.
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private boolean resolveAsPrimitiveProperty(String name, String value, List<PropertyDescriptor> descriptors, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean found = false;
		for (PropertyDescriptor property : descriptors) {
			Type t = property.getPropertyType();
			if (name.equalsIgnoreCase(property.getDisplayName())) {
				Method setter = property.getWriteMethod();
				setPrimitiveValue(setter, obj, value, (Class<?>)t);
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * If element is one of the parent bean's enumerate type properties, then parse this element as a enum constant and return true.
	 * @param name
	 * @param value
	 * @param descriptors Parent bean's all enumerate type property descriptors.
	 * @param obj Parent bean.
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private boolean resolveAsEnumProperty(String name, String value, List<PropertyDescriptor> descriptors, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean found = false;
		for (PropertyDescriptor property : descriptors) {
			Type t = property.getPropertyType();
			if (name.equalsIgnoreCase(property.getDisplayName())) {
				Method setter = property.getWriteMethod();
				setEnumValue(setter, obj, value, (Class<?>)t);
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * If element is one of the parent bean's instance properties, then parse this element as a instance and return true.
	 * @param name Element name
	 * @param reader
	 * @param descriptors Parent bean's all instance property  descriptors.
	 * @param obj Parent bean.
	 * @return
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws XMLStreamException
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private boolean resolveAsInstanceProperty(String name, XMLStreamReader reader, List<PropertyDescriptor> descriptors, Object obj) throws InstantiationException, ClassNotFoundException, XMLStreamException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean found = false;
		for (PropertyDescriptor property : descriptors) {
			if (name.equalsIgnoreCase(property.getDisplayName())) {
				Class <?> type = property.getPropertyType();
				Method setter = property.getWriteMethod();

				Object child = parseElement(reader, name, type, null);
				setter.invoke(obj, child);
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * 
	 * @param name
	 * @param reader
	 * @param descriptors
	 * @param obj
	 * @param listMap
	 * @return
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws XMLStreamException
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private boolean resolveAsListProperty(String name, XMLStreamReader reader, List<PropertyDescriptor> descriptors, Object obj, Map<Method, List<Object>> listMap) throws InstantiationException, ClassNotFoundException, XMLStreamException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean found = false;
		for (PropertyDescriptor property : descriptors) {
			// support the plural as well
			String propertyName = property.getDisplayName();
			if (name.equalsIgnoreCase(propertyName)
					|| (name + "s").equalsIgnoreCase(propertyName)
					|| (name + "es").equalsIgnoreCase(propertyName)) {
				Method setter = property.getWriteMethod();
				ParameterizedType genericType = (ParameterizedType) property.getReadMethod().getGenericReturnType();
				Type [] types = genericType.getActualTypeArguments();

				if (types.length == 1) {
					List <Object> list = listMap.get(setter);
					if (list == null) {
						list = new ArrayList<Object>();
						listMap.put(setter, list);
						setter.invoke(obj, list);
					}
					
					log.info("startElement:" + name + ", property:" + propertyName);
					Class<?> listGenericClass = (Class<?>)types[0];
					if (name.equalsIgnoreCase(propertyName)) {
						parseElement(reader, name, listGenericClass, list);
					} else {
						list.add(parseElement(reader, name, listGenericClass, null));
					}
				}

				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * Get a bean's all listing, string, primitive, enumerate and other instance properties.
	 * @param clazz
	 * @return
	 * @throws IntrospectionException
	 */
	private Map<String, List<PropertyDescriptor>> getPropertyDescriptors(Class<?> clazz) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		Map<String, List<PropertyDescriptor>> map = new HashMap<String, List<PropertyDescriptor>>();
		List<PropertyDescriptor> listProperties = new ArrayList<PropertyDescriptor>();
		List<PropertyDescriptor> instanceProperties = new ArrayList<PropertyDescriptor>();
		List<PropertyDescriptor> stringProperties = new ArrayList<PropertyDescriptor>();
		List<PropertyDescriptor> primitiveProperties = new ArrayList<PropertyDescriptor>();
		List<PropertyDescriptor> enumProperties = new ArrayList<PropertyDescriptor>();
		List<PropertyDescriptor> arrayProperties = new ArrayList<PropertyDescriptor>();
		
		for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
			Class<?> type = property.getPropertyType();
			if (List.class.isAssignableFrom(type)) {
				listProperties.add(property);
			} else if (type.isPrimitive()) {
				primitiveProperties.add(property);
			} else if (String.class.isAssignableFrom(type)) {
				stringProperties.add(property);
			} else if (type.isEnum()) {
				enumProperties.add(property);
			} else if (type.isArray()) {
				arrayProperties.add(property);
			} else if (!String.class.isAssignableFrom(type)
					&& !Number.class.isAssignableFrom(type)
					&& !Boolean.class.isAssignableFrom(type)
					&& !Collection.class.isAssignableFrom(type)
					&& !type.isPrimitive()) {
				instanceProperties.add(property);
			} 
		}
		
		map.put("list", listProperties);
		map.put("instance", instanceProperties);
		map.put("string", stringProperties);
		map.put("primitive", primitiveProperties);
		map.put("enum", enumProperties);
		
		return map;
	}
	
	/**
	 * Resolve string value to primitive type if this string could be parsed into primitive type.
	 * @param value
	 * @param type
	 * @return
	 */
	private Object resolvePrimitiveValue(String value, Class<?> type) {
		if (long.class.isAssignableFrom(type)) {
			return Long.parseLong(value);
		} else if (int.class.isAssignableFrom(type)) {
			return Integer.parseInt(value);
		} else if (double.class.isAssignableFrom(type)) {
			return Double.parseDouble(value);
		} else if (float.class.isAssignableFrom(type)) {
			return Float.parseFloat(value);
		} else if (short.class.isAssignableFrom(type)) {
			return Short.parseShort(value);
		} else if (boolean.class.isAssignableFrom(type)) {
			// only "true" will get true
			return Boolean.parseBoolean(value);
		} else if (byte.class.isAssignableFrom(type)) {
			return Byte.parseByte(value);
		} else if (char.class.isAssignableFrom(type)) {
			if (!value.isEmpty()) {
				return value.charAt(0);
			}
		}
		
		return null;
	}
	
	private <T> T resolveEnumValue(String value, Class<T> type) {
		for (T constant : type.getEnumConstants()) {
			if (constant.toString().equalsIgnoreCase(value)) {
				return constant;
			}
		}
		
		return null;
	}
	
	/**
	 * Cast String value to specified primitive type. Then call setter, set value as a property value of obj.
	 * @param setter
	 * @param obj
	 * @param value
	 * @param type
	 * @throws NumberFormatException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void setPrimitiveValue(Method setter, Object obj, String value, Class<?> type) throws NumberFormatException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = resolvePrimitiveValue(value, type);
		if (result != null) {
			setter.invoke(obj, result);
		}
	}
	
	private void setEnumValue(Method setter, Object obj, String value, Class<?> type) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = resolveEnumValue(value, type);
		if (result != null) {
			setter.invoke(obj, result);
		}
	}
}
