package greeting;

import java.util.List;

public interface UserDAO {
	public void saveOrUpdate(User user);

	public void delete(int userId);

	public User get(String userId);

	public List<User> list();
}
