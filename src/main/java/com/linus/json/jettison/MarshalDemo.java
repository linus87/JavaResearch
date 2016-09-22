package com.linus.json.jettison;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import com.linus.json.jettison.pojo.Customer;

public class MarshalDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JAXBContext jc = JAXBContext.newInstance(Customer.class);
		 
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Customer customer = (Customer) unmarshaller.unmarshal(new File(MarshalDemo.class.getResource("customer.xml").getFile()));

        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        Writer writer = new OutputStreamWriter(System.out);
        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(customer, xmlStreamWriter);
	}

}
