package info.joyindemo.dao;

import java.util.List;

import info.joyindemo.entity.Technician;

public interface TechDAO {
	public void saveOrUpdate(Technician user);

	public void delete(int userId);

	public Technician get(String userId);

	public List<Technician> list();
	
	public Technician test(String id);
}

