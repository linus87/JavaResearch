package com.linus.soap.axis2;

import java.io.StringWriter;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class ClientTest {
	private static EndpointReference targetEPR =
	        new EndpointReference("http://www.ebay.cn/maisha/sellercenter/performancetool/api/dashboard/info/demo.php");


	public static void main(String[] args) throws ServiceException, RemoteException, XMLStreamException, FactoryConfigurationError {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		ServiceClient serviceClient = new ServiceClient();
		
		OMNamespace omNs = factory.createOMNamespace(
                "http://springExample.org/example1", "example1");

	    OMElement method = factory.createOMElement("shipment_pre", omNs);
	    OMElement value = factory.createOMElement("Text", omNs);
	    value.addChild(factory.createOMText(value, "Some String "));
	    method.addChild(value);
		
		Options options = new Options();
        serviceClient.setOptions(options);
        options.setTo(targetEPR);
        
        // Blocking invocation
        OMElement result = serviceClient.sendReceive(method);

        StringWriter writer = new StringWriter();
        result.serialize(XMLOutputFactory.newInstance()
                .createXMLStreamWriter(writer));
        writer.flush();

        System.out.println("Response: " + writer.toString());
	}

}
