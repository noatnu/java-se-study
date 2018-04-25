package zch.help;

import java.util.Random;

/**
 * @since jdk1.6
 * @author Zhou
 *
 */
public class Zhou_StdRandom {

	private static Random random = new Random(System.currentTimeMillis());
	/**
	 * Chinese 静态初始化 static initializer
	 */
	private static long seed;
	{
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}

	public static void setSeed(long num) {
		seed = num;
		random = new Random(seed);
	}

	/**
	 * @see Returns a random real number uniformly in [0, 1]
	 * @return
	 */
	public static double uniform() {
		return random.nextDouble();
	}

	/**
	 * @see must b > a (a-b)
	 * @param a
	 * @param b
	 * @return
	 */
	public static double uniform(double a, double b) {
		if (b <= a || b - a >= Double.MAX_VALUE) {
			throw new IllegalArgumentException("argument must be positive");
		}
		return a + (b - a) * uniform();
	}

	/**
	 * @see 0——n
	 * @param n
	 * @return
	 */
	public static int uniform(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("argument must be positive");
		}
		return random.nextInt(n);
	}

	/**
	 * @see a>b(a——b)
	 * @param a
	 * @param b
	 * @return
	 */
	public static int uniform(int a, int b) {
		if (b <= a || b - a >= Integer.MAX_VALUE) {
			throw new IllegalArgumentException("argument must be positive");
		}
		return a + uniform(b - a);
	}

	/**
	 * @see 0-n
	 * @param n
	 * @return
	 */
	public static long uniformLong(long n) {
		if (n <= 0) {
			throw new IllegalArgumentException("argument must be positive");
		}
		long num = StrictMath.round((StrictMath.random() * 1) * n);
		return num;
	}

	/**
	 * @see a-b (b>a)
	 * @param a
	 * @param b
	 * @return
	 */
	public static long uniformLong(long a, long b) {
		if (b <= a || b - a >= Long.MAX_VALUE) {
			throw new IllegalArgumentException("argument must be positive");
		}
		long num = a + uniformLong(b - a);
		return num;
	}

	/**
	 * @see 0-f
	 * @param f
	 * @return
	 */
	public static float uniformFloat(float f) {
		if (f <= 0) {
			throw new IllegalArgumentException("argument must be positive");
		}
		float num = StrictMath.round((StrictMath.random() * 1) * f);
		return num;
	}

	/**
	 * a-b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static float uniformFloat(float a, float b) {
		if (b <= a || b - a >= Float.MAX_VALUE) {
			throw new IllegalArgumentException("argument must be positive");
		}
		float num = a + uniformFloat(b - a);
		return num;
	}

	/**
	 * @see 不能创建对象 (don't instantiate)
	 */
	private Zhou_StdRandom() {
	}
}
