import java.util.Random;
import java.util.Scanner;

public class arraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a;
		Scanner scan = new Scanner(System.in);

		System.out.println("배열 크기를 입력하세요.");
		int n, l;

		n = scan.nextInt();
		l = scan.nextInt();

		// 배열에 초기화
		a = init_array(n, l);

		// 배열 출력
		print_array(a);

		int sum;

		// 배열의 합
		sum = sum_array(a);

		System.out.println("합은 " + sum + "입니다.");
	}

	static int[][] init_array(int n, int l) {
		int[][] a = new int[n][l];

		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[i].length; j++)
				a[i][j] = (int) (Math.random() * 100);

		return a;
	}

	// 배열 출력
	static void print_array(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
	}

	static int sum_array(int[][] a) {
		int sum = 0;

		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[i].length; j++)
				sum += a[i][j];

		return sum;
	}

}
