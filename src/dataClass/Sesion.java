package dataClass;

public class Sesion {
	private static Usuario user;

	public static Usuario getUser() {
		return user;
	}

	public static void setUser(Usuario user) {
		Sesion.user = user;
	}
}
