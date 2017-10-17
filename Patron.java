
public class Patron {
	private String uniqueId;
	private String name;
	public Patron(String uniqueId, String name) {
		super();
		this.uniqueId = uniqueId;
		this.name = name;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Patron [Id= " + uniqueId + ", Name= " + name + "]";
	}
	
}
