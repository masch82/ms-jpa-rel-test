package de.marcoschuh;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaRelTestApp {

	public static void main(String[] args) {
		SpringApplication.run(JpaRelTestApp.class, args);
	}
	
    @Autowired
    private Bus bus;
    
    @Autowired
    private JpaRelTestSoapWS exampleWs;
	
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, exampleWs);
        endpoint.publish("/JpaRelTestSoapWS");
        return endpoint;
    }
	
}
