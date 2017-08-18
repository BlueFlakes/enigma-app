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
            String serviceName = service.getName().toLowerCase();

            result.add(serviceName);
		}

		return result;
	}

	public List<String> listAllWithMarks()
    {
		List<String> result = new LinkedList<String>();

		for(EnigmaService service : this.services)
        {
            String serviceName = service.getName().toLowerCase();

			if (service.isKeyRequired()) {
				serviceName += "#";
			}

            result.add(serviceName);
		}

		return result;
	}

	public List<String> listAvailable()
	{
		List<String> result = new LinkedList<String>();

		for(EnigmaService service : this.services)
		{
			String serviceName = service.getName().toLowerCase();

			if (!this.usedServices.contains(serviceName))
			{
				result.add(serviceName);

			}
		}

		return result;
	}

    public void release(String name)
    {
		for(EnigmaService service : this.services)
        {
			if (service.getName().equalsIgnoreCase(name) && service.getIsUsed()) {
				service.changeIsUsed();
			}

        }
    }

    private Integer getServiceIndex(String name)
    {
        Integer index = null;

        for(int i = 0; i < this.usedServices.size(); i++)
        {
            String serviceName = this.usedServices.get(i);

            if (name.equalsIgnoreCase(serviceName))
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

			if (serviceName.equalsIgnoreCase(name) && (service.getIsUsed()) == false)
            {
                service.changeIsUsed();
				return service;

			}
		}
		return null;
	}

}
