package com.linus.json.jackson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linus.json.jackson.pojo.Account;
import com.linus.json.jackson.pojo.Name;
import com.linus.json.jackson.pojo.User;
import com.linus.json.jackson.pojo.User.Gender;

public class JacksonMain {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		// By default, JsonGenerator will close the underlying output target that is not owned by the generator.
		mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		// By default, ObjectMapper will throw  UnrecognizedPropertyException if encounter unknown properties in JSON string.
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,	false);
		
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

		// 1.1 Simple Data Binding
		simpleDataBinding(mapper);

		// 1.2 Full Data Binding
		fullDataBinding(mapper);

		// 1.3 Data Binding with Generics
		dataBindingWithGenerics(mapper);

		// 1.4 Data Binding with Annotation
		// dataBindingWithAnnotation(mapper);

		// 1.4 Data Binding with unusual value
		dataBindingWithUnusualValue(mapper);

		// 2.1 Tree Model
		treeModel(mapper);

		// 2.2 Construct a Tree
		constructTreeModel(mapper);

		// 3.1 Streaming API (write Json)
		streamingAPIRead(mapper);

		// 3.2 Streaming API (read Json)
		streamingAPIWrite();
	}

	/**
	 * Simple Data Binding: It converts JSON to and from Java Maps, Lists,
	 * Strings, Numbers, Booleans, and null objects.
	 * 
	 * @param mapper
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void simpleDataBinding(ObjectMapper mapper) throws Exception {
		System.out.println("simpleDataBinding(): ");
		// json -> Map
		File file = new File(JacksonMain.class.getResource("user.json")
				.getFile());

		Map<String, Object> userDataRead = mapper.readValue(file, Map.class);
		System.out.println(userDataRead);

		// Map -> json
		Map<String, Object> userData = new HashMap<String, Object>();
		Map<String, String> nameStruct = new HashMap<String, String>();
		nameStruct.put("first", "Joe");
		nameStruct.put("last", "Sixpack");
		userData.put("name", nameStruct);
		userData.put("gender", "MALE");
		userData.put("verified", Boolean.FALSE);
		userData.put("userImage", "Rm9vYmFyIQ==");

		mapper.writeValue(System.out, userData);
	}

	/**
	 * Full Data Binding: It converts JSON to and from any Java type.
	 * 
	 * @param mapper
	 * @throws Exception
	 */
	public static void fullDataBinding(ObjectMapper mapper) throws Exception {
		System.out.println("\nfullDataBinding(): ");
		// json -> Object
		File file = new File(JacksonMain.class.getResource("user.json")
				.getFile());
		User user = mapper.readValue(file, User.class);
		user.setGender(Gender.FEMALE);
		user.age = 28;

		// Object -> json
		System.out.println(mapper.writeValueAsString(user));
		user.setName(null);
		user.age = 0;
		mapper.writeValue(System.out, user);
	}

	public static void dataBindingWithGenerics(ObjectMapper mapper)
			throws Exception {
		System.out.println("\ndataBindingWithGenerics(): ");
		// json -> Map
		Map<String, Name> genericData = mapper.readValue(new File(
				JacksonMain.class.getResource("user.json").getFile()),
				new TypeReference<Map<String, Object>>() {
				});
		mapper.writeValue(System.out, genericData);
	}

	public static void dataBindingWithAnnotation(ObjectMapper mapper)
			throws Exception {
		System.out.println("\ndataBindingWithAnnotation(): ");
		// json -> Object
		Account account = mapper.readValue(new File(JacksonMain.class
				.getResource("account.json").getFile()), Account.class);
		mapper.writeValue(System.out, account);

		account.setGmtCreate(new Date());
		// Object -> json
		mapper.writeValue(System.out, account);
	}

	public static void dataBindingWithUnusualValue(ObjectMapper mapper)
			throws Exception {
		System.out.println("\ndataBindingWithUnusualValue(): ");

		// json -> Object
		Account account = mapper.readValue(new File(JacksonMain.class
				.getResource("account.json").getFile()), Account.class);
		mapper.writeValue(System.out, account);

		System.out.println("\ndataBindingWithUnusualValue(): ");
		account.setGmtCreate(new Date());
		// Object -> json
		mapper.writeValue(System.out, account);
	}

	public static void treeModel(ObjectMapper mapper) throws Exception {
		System.out.println("\ntreeModel(): ");
		// can either use mapper.readTree(JsonParser), or bind to JsonNode
		JsonNode rootNode = mapper.readValue(new File(JacksonMain.class
				.getResource("user.json").getFile()), JsonNode.class);

		// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
		JsonNode nameNode = rootNode.path("name");
		String lastName = nameNode.path("last").textValue();
		if ("xmler".equalsIgnoreCase(lastName)) {
			((ObjectNode) nameNode).put("last", "Jsoner");
		}
		// write it out
		mapper.writeValue(System.out, rootNode);
	}

	public static void constructTreeModel(ObjectMapper mapper) throws Exception {
		System.out.println("\nconstructTreeModel(): ");
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode userOb = objectMapper.createObjectNode();
		ObjectNode nameOb = userOb.putObject("name");
		nameOb.put("first", "Thomas");
		nameOb.put("last", "Zhou");
		userOb.put("gender", User.Gender.MALE.toString());
		userOb.put("verified", false);
		userOb.put("userImage", "Foobar!".getBytes());
		// write it out
		mapper.writeValue(System.out, userOb);
	}

	public static void streamingAPIWrite() throws Exception {
		System.out.println("\nstreamingAPIWrite(): ");
		JsonFactory f = new JsonFactory();
		JsonGenerator g = f.createGenerator(System.out, JsonEncoding.UTF8);

		g.writeStartObject();
		g.writeObjectFieldStart("name");
		g.writeStringField("first", "Thomas");
		g.writeStringField("last", "Zhou");
		g.writeEndObject(); // for field 'name'
		g.writeStringField("gender", Gender.MALE.name());
		g.writeBooleanField("verified", false);
		g.writeFieldName("userImage"); // no 'writeBinaryField' (yet?)
		byte[] binaryData = "Foobar!".getBytes();
		g.writeBinary(binaryData);
		g.writeEndObject();
		g.close(); // important: will force flushing of output, close underlying
					// output stream
	}

	public static void streamingAPIRead(ObjectMapper mapper) throws Exception {
		System.out.println("\nstreamingAPIRead(): ");
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(new File(JacksonMain.class
				.getResource("user.json").getFile()));
		User user = new User();
		jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldname = jp.getCurrentName();
			jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
			if ("name".equals(fieldname)) { // contains an object
				Name name = new Name();
				while (jp.nextToken() != JsonToken.END_OBJECT) {
					String namefield = jp.getCurrentName();
					jp.nextToken(); // move to value
					if ("first".equals(namefield)) {
						name.setFirst(jp.getText());
					} else if ("last".equals(namefield)) {
						name.setLast(jp.getText());
					} else {
						throw new IllegalStateException("Unrecognized field '"
								+ fieldname + "'!");
					}
				}
				user.setName(name);
			} else if ("gender".equals(fieldname)) {
				user.setGender(User.Gender.valueOf(jp.getText()));
			} else if ("verified".equals(fieldname)) {
				user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
			} else if ("userImage".equals(fieldname)) {
				user.setUserImage(jp.getBinaryValue());
			} else {
				throw new IllegalStateException("Unrecognized field '"
						+ fieldname + "'!");
			}
		}
		jp.close(); // ensure resources get cleaned up timely and properly
		// Object -> json
		mapper.writeValue(System.out, user);
	}
}