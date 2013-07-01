@Entity
@Table( name = "Users" )
public class User {
	private String name;
	private QuestionSet[] questionSets;
	private String id;
	
	public User() {}
	
	public User(String n, String i) {
		name = n;
		id = i;
	}
	
	@Id
	public String getID() {return id;}
	
	@OneToMany
	public QuestionSet[] 
	
}
