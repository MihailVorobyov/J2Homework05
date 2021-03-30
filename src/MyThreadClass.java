import sun.awt.windows.ThemeReader;

import java.util.Arrays;

public class MyThreadClass {
//	1. Необходимо написать два метода, которые делают следующее:

	static final int SIZE = 10_000_000;

	public static void main(String[] args) {
		method1();
		method2(2);
	}

	public static void method1 () {
		//1) Создают одномерный длинный массив, например:
		float[] arr = new float[SIZE];

		//2) Заполняют этот массив единицами;
		Arrays.fill(arr, 1.0f);

		//3) Засекают время выполнения:
		long a = System.currentTimeMillis();

		//4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
		for (int i = 0; i < SIZE; i++) {
			arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
		}

		//5) Проверяется время окончания метода System.currentTimeMillis();
		//6) В консоль выводится время работы:
		System.out.println("1 поток: " + (System.currentTimeMillis() - a) + " мс");
	}

	public static void method2 (int numOfThreads) {

		//1) Создают одномерный длинный массив, например:
		float[] arr = new float[SIZE];

		//2) Заполняют этот массив единицами;
		Arrays.fill(arr, 1.0f);

		//3) Засекают время выполнения:
		long a = System.currentTimeMillis();

		//	Второй разбивает массив на то количество потоков, которые будет передано в качестве аргумента для этого метода, в отдельных потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

		int length = SIZE / numOfThreads;

		// создаём массив массивов float для хранения частей arr
		float[][] fragmentedArray = new float[numOfThreads][];

		//Создаём массив с указанным количеством потоков
		MyThread[] threads = new MyThread[numOfThreads];

		int start = 0;

		// делим arr на части
		for (int i = 0; i < numOfThreads; i++) {
			//устанавливаем длинну последнего временного массива
			if ((SIZE % numOfThreads != 0) && (i == numOfThreads - 1)) {
				length += SIZE % numOfThreads;
			}
			fragmentedArray[i] = new float[length];
			System.arraycopy(arr, start, fragmentedArray[i], 0, length);
			start += length;
			threads[i] = new MyThread(fragmentedArray[i]);
			threads[i].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		start = 0;
		for (int i = 0; i < numOfThreads; i++) {
			System.arraycopy(fragmentedArray[i], 0, arr, start, fragmentedArray[i].length);
			start += fragmentedArray[i].length;
		}

		// выводим время выполнения
		System.out.println(numOfThreads + " потока(ов): " + (System.currentTimeMillis() - a) + " мс");
	}
}

