package info.joyindemo.dao;

import java.util.List;

import info.joyindemo.entity.User;

public interface UserDAO {
	public void saveOrUpdate(User user);

	public void delete(int userId);

	public User get(String userId);

	public List<User> list();
}
