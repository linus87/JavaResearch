package com.linus.soap.axis2;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;

public class RpcServiceClientTest {

	public static void main(String[] args) {
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			
			HttpTransportProperties.ProxyProperties proxyProperties = new HttpTransportProperties.ProxyProperties();  
		    proxyProperties.setProxyName("qa-proxy.qa.ebay.com");
		    proxyProperties.setProxyPort((int) (80));
			options.setProperty(HTTPConstants.PROXY, proxyProperties);
			
			EndpointReference targetEPR = new EndpointReference("http://www.ebay.cn/maisha/sellercenter/performancetool/api/dashboard/info/demo.php");
			options.setTo(targetEPR);

			options.setTimeOutInMilliSeconds((long)20000); // 20s timeout
			
			QName qName = new QName("http://www.ebay.cn/maisha/sellercenter/performancetool/api/dashboard/info", "shipment_pre");
			String enRes = (String) serviceClient.invokeBlocking(qName, new Object[]{"hello world"}, new Class[]{String.class})[0];
			System.out.println(enRes);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	

}
