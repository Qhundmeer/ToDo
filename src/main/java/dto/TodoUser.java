package dto;

import javax.persistence.*;

import helper.AES;
import lombok.Data;

@Entity
@Data
public class TodoUser  {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long mobile;
	private String dob;
	private String gender;
	private String password;
	
	public void setPassword(String password) {
		this.password=AES.encrypt(password, "123");
	}
	public String getPassword()
	{
		return AES.decrypt(password, "123");
	}
}



