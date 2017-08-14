package enigma;

import registry.ServiceRegistry;
import registry.ServiceProvider;
import services.EnigmaService;

import java.util.ArrayList;
import java.util.List;


public class ServiceRepository implements ServiceRegistry, ServiceProvider {
    List<EnigmaService> services;

    public ServiceRepository()
    {
		this.services = new ArrayList<EnigmaService>();

	}

	public void register(EnigmaService service)
    {
        this.services.add(service);
        
	}

	public List<String> listAll()
    {

        return null;
	}

	public EnigmaService getByName(String name)
    {

        return null;
	}

}
