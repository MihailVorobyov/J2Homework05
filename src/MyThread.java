public class MyThread  extends Thread {
	float[] partOfArray;

	public MyThread(float[] partOfArray) {
		this.partOfArray = partOfArray;
	}

	@Override
	public void run() {
		for (int i = 0; i < partOfArray.length; i++) {
			partOfArray[i] = (float)(partOfArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
		}
	}
}
