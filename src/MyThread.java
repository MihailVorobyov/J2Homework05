public class MyThread  extends Thread {
	private float[] partOfArray;
	private int number;
	private int mainPartSize;

	public MyThread(float[] partOfArray, int number, int mainPartSize) {
		this.partOfArray = partOfArray;
		this.number = number;
		this.mainPartSize = mainPartSize;
	}

	@Override
	public void run() {
		for (int i = 0, j = number * mainPartSize; i < partOfArray.length; i++, j++) {
			partOfArray[i] =
					(float)(partOfArray[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
		}
	}
}
