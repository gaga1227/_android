package ch03

class User(val id: Int, val name: String, val address: String) {
	private fun validate() {
		// abstract common logic into a local function
		fun validateField(value: String, field: String) {
			if (value.isEmpty()) {
				throw IllegalArgumentException("Can't save user $id: empty $field")
			}
		}

		// validate all required fields for a user
		validateField(name, "Name")
		validateField(address, "Address")
	}

	fun save() {
		validate()
		// saving user...
		println("User $id: $name saved!");
	}
}

fun main(args: Array<String>) {
	User(1, "Jane", "CHINA").save()
	User(2, "John", "").save()
}