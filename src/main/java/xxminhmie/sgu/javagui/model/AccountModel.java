package xxminhmie.sgu.javagui.model;
//DONE
public class AccountModel extends AbstractModel {
	protected Long roleId;
	protected Long staffId;

	protected String username;
	protected String password;
	
	public AccountModel() {
	}
	
	public AccountModel(Long roleId, Long staffId, String username, String password) {
		super();
		this.roleId = roleId;
		this.staffId = staffId;
		this.username = username;
		this.password = password;
	}
	public AccountModel(Long id,Long roleId, Long staffId, String username, String password) {
		super(id);
		this.roleId = roleId;
		this.staffId = staffId;
		this.username = username;
		this.password = password;
	}
	
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AccountModel [roleId=" + roleId + ", staffId=" + staffId + ", username=" + username + ", password="
				+ password + "]";
	}
	
}
