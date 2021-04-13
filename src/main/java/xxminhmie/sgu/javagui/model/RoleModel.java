package xxminhmie.sgu.javagui.model;

public class RoleModel extends AbstractModel {
	protected String name;
	protected String description;

	public RoleModel() {

	}

	public RoleModel(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public RoleModel(Long id, String name, String description) {
		super(id);
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
