
public class View {
	int id;
	public View(int id) {
		this.set(id);
	}
	public void set(int id){
		this.id = id;
	}
	public < T > void say(T t){
		System.out.println(t);
	}
	public static < T > void Say(T t ){
		System.out.println(t);
	}
}