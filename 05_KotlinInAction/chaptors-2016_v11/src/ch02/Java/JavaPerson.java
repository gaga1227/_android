package ch02.Java;

import ch02.Person;

public class JavaPerson {
	private String name;
	private boolean isMarried;

	public JavaPerson(String name, boolean isMarried) {
		this.name = name;
		this.isMarried = isMarried;
	}

	public String getName() {
		return name;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean married) {
		isMarried = married;
	}

	public static void main(String[] args) {
		// Use Kotlin class
		Person person = new Person("Kotlin", false);
		System.out.println(person.getName() + " is isMarried: " + person.isMarried());
	}
}
