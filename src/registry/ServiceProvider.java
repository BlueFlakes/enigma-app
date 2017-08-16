package registry;

import services.EnigmaService;
import java.util.List;

public interface ServiceProvider{
	public List<String> listAll();
	public EnigmaService getByName(String name);
	public void release(String name);
}
