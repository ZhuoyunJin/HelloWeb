package common;

import java.util.List;

public interface TechDAO {
	public void saveOrUpdate(Technician user);

	public void delete(int userId);

	public Technician get(String userId);

	public List<Technician> list();
	
	public Technician test(String id);
}

