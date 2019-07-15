
public class Data {

	public float id;
	public String email;
	public String first_name;
	public String last_name;
	public String avatar;
	
	public String dataString() {
		
		return "id: " + this.id + " " +"email: " + this.email + " "+ "first_name: " + this.first_name + " " + "last_name: " + this.last_name + "avatar: " + this.avatar;
		
	}
	
}
