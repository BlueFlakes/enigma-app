package enigma;

import registry.ServiceRegistry;
import registry.ServiceProvider;
import services.EnigmaService;

import java.util.LinkedList;
import java.util.List;


public class ServiceRepository implements ServiceRegistry, ServiceProvider {
	LinkedList<EnigmaService> services;
    LinkedList<String> usedServices;


	public ServiceRepository()
    {
		this.services = new LinkedList<EnigmaService>();
        this.usedServices = new LinkedList<>();

	}

	public void register(EnigmaService service)
    {
		this.services.add(service);

	}

	public List<String> listAll()
    {
		List<String> result = new LinkedList<String>();

		for(EnigmaService service : this.services)
        {
            String serviceName = service.getName();

            if ((this.usedServices.contains(serviceName)) == false)
            {
                result.add(service.getName());

            }
		}

		return result;
	}

    public void release(String name)
    {
        Integer serviceIndex = getServiceIndex(name);

        if (serviceIndex != null)
        {
            this.usedServices.remove((int) serviceIndex);

        }
    }

    private Integer getServiceIndex(String name)
    {
        Integer index = null;
        String lowerCasedName = name.toLowerCase();

        for(int i = 0; i < this.usedServices.size(); i++)
        {
            String lowerCasedServiceName = this.usedServices.get(i).toLowerCase();

            if (lowerCasedName.equals(lowerCasedServiceName))
            {
                index = i;
                break;

            }
        }

        return index;
    }

	public EnigmaService getByName(String name)
    {

		for(EnigmaService service : this.services)
        {
            String serviceName = service.getName();


			if (serviceName.equals(name) && (this.usedServices.contains(serviceName) == false))
            {
                this.usedServices.add(service.getName());
				return service;

			}
		}
		return null;
	}

}
