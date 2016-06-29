package common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TechDAOImpl implements TechDAO{
	private JdbcTemplate awsTemplate;
	 
    public TechDAOImpl(DataSource dataSource) {
    	awsTemplate = new JdbcTemplate(dataSource);
    }
    
    public TechDAOImpl(JdbcTemplate awsTemplate) {
    	this.awsTemplate = awsTemplate;
    }
	@Override
	public void saveOrUpdate(Technician tech) {
		String uuid = UUID.randomUUID().toString();
		String sql = String.format("insert into Employee (id, first_name, last_name, title) "
				+ "values ('%s','%s','%s','%s') ",uuid,tech.getFirstName(),tech.getLastName(),tech.getTitle());
		awsTemplate.execute(sql);
	}

	@Override
	public void delete(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Cacheable(value="tech",key="#id")
	public Technician get(String id) {
		String sql = "SELECT * FROM Employee WHERE id='" + id+"'";
		System.out.println(sql);
        return awsTemplate.queryForObject(sql, new RowMapper<Technician>(){
        	@Override
        	public Technician mapRow(ResultSet arg0, int arg1) throws SQLException {
        		System.out.println(arg0);
        		// TODO Auto-generated method stub
        		Technician technician=new Technician();
        		technician.setFirstName(arg0.getString("first_name"));
        		return technician;
        	}
        });
	}
	
	@Cacheable(value="techs")
	public List<Technician> get() {
		List<Technician> technicians = new ArrayList<Technician>();
		String sql = "SELECT * FROM Employee";
		System.out.println("kiiiiiiii");
		List<Map<String, Object>> list=awsTemplate.queryForList(sql);
        for(Map<String, Object> map:list){           
        	Technician technician=new Technician();
        	technician.setFirstName((String)map.get("first_name"));
        	technician.setUUID((String)map.get("id"));
        	technicians.add(technician);
        }
		return technicians;
	}

	@Override
	public List<Technician> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Cacheable(value="tech",key="#id")
	public Technician test(String id){
		System.out.println("inside");
		return null;
	}

}
