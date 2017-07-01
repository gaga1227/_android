package ch05.Java;

public class ClassWithMethodTakesFunctionalInterfaceParam {
	public void runWithDelay(long delay, Runnable runnable) {
		System.out.print("Delay started... ");

		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			System.out.print(e.toString());
		}

		runnable.run();
	}
}

