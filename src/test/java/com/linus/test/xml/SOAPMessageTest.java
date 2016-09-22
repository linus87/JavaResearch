package com.linus.test.xml;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.linus.xml.dom.DOMResolver;
import com.linus.xml.soap.CSSOAPMessageFactory;
import com.linus.xml.soap.response.GetTokenResponse;

public class SOAPMessageTest {
	public static void main(String[] args) throws SOAPException, IOException {
//		SOAPMessage message = CSSOAPMessageFactory.createGetTokenMessage();
//		System.out.println(CSSOAPMessageFactory.parseToString((message)));
//		
//		message = CSSOAPMessageFactory.createGetUserCoreByNameMessage("AwAAAA**AQAAAA**aAAAAA**zk07Vw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AGkYCjDpCEqAqdj6x9nY+seQ**WQkAAA**AAMAAA**i/AL3eNL/4L9nqQkPfConcdqj+oGVV57XEUP85zmbJPJRcxHZ5xs/pYp4SHxlNlKkwdJ0B+PnoSRTLbYYF0Vs5rmI4oWauGVm++hQTfRYA9Zt01CKBsM2zsFSFJ/eU32aHX1Sh7GPcEpwUTRnoMfHw8hH8/NpIiE/RjdnCsHFZOxNVP/jXKlFxN/fktIkG9ozbrNcMUi6Tv44qIvMDoWjn38GcwcowAXRJUINnipAjWj5UmqaoAD1xuaOcKleag/cpOcMlX8jt6SwzMZ11KTojuzG3QPC3M3INC/lB1eki7xyMGr1k+o6a4DhOh3ZCubSipYwfvpfqcs6ogviBElgnA+eOs5S0MEjhS7taM2zyYK841FAj4f/aroe+q4RLfVxC3w84bVaobDaFVUA031ZpFo4PXQDTfJQa1OudFVsG2eMU52MtyN6ci9FbnFjN7QPJRnvL617veNu+x5o39008XY2miHRHmxR+LBq/lVoSWhmqQtdjYWuSzTwgcp9LR5PUYPiNbT0RwfAWCaFnCXpnCBcXHHiUcExPTKYW48J9Obg5vuLa+sJrnRX+IvCMzxSwbVW6TKvTB0zf/kXPviW0pRetTtA9+aYv7hUVNFCAr7FYIafxn84LUc5o6lTJdQb+yt/To3O41w5MBTb99yARLuf9b7acob9LqlUqxMFtxmnSPD63zSy4Ve7XNKv8SukQpkVqodYmxmSSvVxintPA4z7jfMGm9SKL+RbLUTiVdmR5n2+7lAcgV1qBXlfHec", "keyhere");
//		System.out.println(CSSOAPMessageFactory.parseToString((message)));
//		
//		message = CSSOAPMessageFactory.createGetItemDetailMessage("AwAAAA**AQAAAA**aAAAAA**zk07Vw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AGkYCjDpCEqAqdj6x9nY+seQ**WQkAAA**AAMAAA**i/AL3eNL/4L9nqQkPfConcdqj+oGVV57XEUP85zmbJPJRcxHZ5xs/pYp4SHxlNlKkwdJ0B+PnoSRTLbYYF0Vs5rmI4oWauGVm++hQTfRYA9Zt01CKBsM2zsFSFJ/eU32aHX1Sh7GPcEpwUTRnoMfHw8hH8/NpIiE/RjdnCsHFZOxNVP/jXKlFxN/fktIkG9ozbrNcMUi6Tv44qIvMDoWjn38GcwcowAXRJUINnipAjWj5UmqaoAD1xuaOcKleag/cpOcMlX8jt6SwzMZ11KTojuzG3QPC3M3INC/lB1eki7xyMGr1k+o6a4DhOh3ZCubSipYwfvpfqcs6ogviBElgnA+eOs5S0MEjhS7taM2zyYK841FAj4f/aroe+q4RLfVxC3w84bVaobDaFVUA031ZpFo4PXQDTfJQa1OudFVsG2eMU52MtyN6ci9FbnFjN7QPJRnvL617veNu+x5o39008XY2miHRHmxR+LBq/lVoSWhmqQtdjYWuSzTwgcp9LR5PUYPiNbT0RwfAWCaFnCXpnCBcXHHiUcExPTKYW48J9Obg5vuLa+sJrnRX+IvCMzxSwbVW6TKvTB0zf/kXPviW0pRetTtA9+aYv7hUVNFCAr7FYIafxn84LUc5o6lTJdQb+yt/To3O41w5MBTb99yARLuf9b7acob9LqlUqxMFtxmnSPD63zSy4Ve7XNKv8SukQpkVqodYmxmSSvVxintPA4z7jfMGm9SKL+RbLUTiVdmR5n2+7lAcgV1qBXlfHec", 54254352);
//		System.out.println(CSSOAPMessageFactory.parseToString((message)));
		
		try {
			getTokenTest();
		} catch (UnsupportedOperationException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | DOMException
				| IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		getUserCoreTest();
	}
	
	public static void getTokenTest() throws UnsupportedOperationException, SOAPException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DOMException, IntrospectionException {
		SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
		SOAPMessage message = CSSOAPMessageFactory.createGetTokenMessage();
		SOAPConnection con = factory.createConnection();
		
		System.out.println(CSSOAPMessageFactory.parseToString((message)));
		SOAPMessage responseMessage = con.call(message, "https://apics.vip.ebay.com/ws/websvc/eBayCSAPI?callname=CSGetToken");
		
//		System.out.println(CSSOAPMessageFactory.parseToString((response)));
		
		Node responseNode = CSSOAPMessageFactory.getResponse(responseMessage, "CSGetTokenResponse");
		
//		NodeList nodes = responseNode.getChildNodes();
//		for (int i = 0; i < nodes.getLength(); i++) {
//			Node node = (Node) nodes.item(i);
//			if ("eBayAuthToken".equalsIgnoreCase(node.getNodeName())) {
//				System.out.println(node.getTextContent());
//				break;
//			}
//		}
		
		DOMResolver resolver = new DOMResolver();
		GetTokenResponse response = resolver.parseElementToObject(responseNode, GetTokenResponse.class);
		System.out.println(response.geteBayAuthToken());

	}
	
	public static void getUserCoreTest() throws UnsupportedOperationException, SOAPException, IOException {
		SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
		SOAPMessage message = CSSOAPMessageFactory.createGetUserCoreByNameMessage("AwAAAA**AQAAAA**aAAAAA**e+E7Vw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AGkYCjDpCEqAqdj6x9nY+seQ**WQkAAA**AAMAAA**ow6/E2Cpcko0AQVLLdlXtDRSA6NBowx1hgRKjg8FYxSLGf6Zf2ucOkR3Nydzu8+FPDuuN2cFeANEXbPzuQMKJnvJCsgWDnStKQS5kzprKRMIUpJlt0yI1NbELYgHHPnZBpLM1WoKRAAPqpBmlOfr3Gp7LBf3CWyFeIQYu/Vlf7USt2TKPSTP7JYDsFLmvxx/yOg66FFJHljwx4lKhzhCD9z2hCgNpnVstAiGHriUhnmyrglOhQIw3JLHKg4DRennIiHEuw4Ix3jog1FBgKikzk7dKeOOOTqRZjJUXYmjdpMVolE07EXAob5vVPRc7kdbRUMKEqmR9PjTeezzIfkue6WIDEpKlly4el/3tFxDJu6V08TgMceAd9yHkiK8VqRbkd4jd7axuGK3WbMKf+//PTKMbx6hwlBE8HP8qBO9FOGvGeFG0nVuCvJDm0LsJ8Uka4gP1UqIJCrKW4CULPYpBLDILiyWL7z3WUQTGIEFpvxw9FnwuHy2flutxhaRryCLhuk6dFp+m11tJbYh8GRRZRNdtQ77qnKFbjMBC67+9blNRuzXdVUqiwx/4CXe1LMryalhGTDtc5iZVWQG+eEACcGFP4JiKNL2KvwnyGYmAWd0As+c8VZLhmBftfSIwT3eDDvK25lI9m7DwVVFEfSG9B+CR/89s94djYsQuMQ/IQJHlxt/2PiJxZNwuM6PXhxw4ijrh3p1u368R8lZ8sytMtVwlPNT3o4MhtbG9ftXrzKPWwMCuZcGN0sv3frHB1jl", "keyhere");
		SOAPConnection con = factory.createConnection();
		
		System.out.println(CSSOAPMessageFactory.parseToString((message)));
		SOAPMessage responseMessage = con.call(message, "https://apics.vip.ebay.com/ws/websvc/eBayCSAPI?callname=CSGetUserCore");
		
//		System.out.println(CSSOAPMessageFactory.parseToString((response)));
		
		Node response = CSSOAPMessageFactory.getResponse(responseMessage, "CSGetUserCoreResponse");
		NodeList nodes = response.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = (Node) nodes.item(i);
			if ("User".equalsIgnoreCase(node.getNodeName())) {
				nodes = node.getChildNodes();
				for (i = 0; i < nodes.getLength(); i++) {
					node = nodes.item(i);
					if ("UserId".equalsIgnoreCase(node.getNodeName())) {
						System.out.println(node.getTextContent());
					}
				}
				break;
			}
		}

	}
}
