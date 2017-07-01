package ch03

// top level definitions
var topLevelVariable = "top Level variable"
val topLevelValue = "top Level value"
const val constValue = "const value" // top level only

fun topLevelFunction() {
	println("top level function")
}

/*
 * Java equivalent
public final class _06_topLevelFunctionAndPropertiesKt {
   @NotNull
   private static String topLevelVariable = "top Level variable";
   @NotNull
   private static final String topLevelValue = "top Level value";
   @NotNull
   public static final String constValue = "const value";

   @NotNull
   public static final String getTopLevelVariable() {
      return topLevelVariable;
   }
   public static final void setTopLevelVariable(@NotNull String var0) {
      Intrinsics.checkParameterIsNotNull(var0, "<set-?>");
      topLevelVariable = var0;
   }

   @NotNull
   public static final String getTopLevelValue() {
      return topLevelValue;
   }

   public static final void topLevelFunction() {
      System.out.println("top level function");
   }
}
 */


// class level definitions
class Class() {
	var classVariable = "class variable"
	val classValue = "class value"
	fun classFunction() {
		println("class function")
	}
}

/*
 * Java equivalent
public final class Class {
	@NotNull
	private String classVariable = "class variable";
	@NotNull
	private final String classValue = "class value";

	@NotNull
	public final String getClassVariable() {
		return this.classVariable;
	}
	public final void setClassVariable(@NotNull String var1) {
		Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
		this.classVariable = var1;
	}

	@NotNull
	public final String getClassValue() {
		return this.classValue;
	}

	public final void classFunction() {
		System.out.println("class function");
	}
}
 */