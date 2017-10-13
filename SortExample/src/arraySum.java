import java.util.Random;
import java.util.Scanner;

public class arraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a;
		Scanner scan = new Scanner(System.in);

		System.out.println("�迭 ũ�⸦ �Է��ϼ���.");
		int n, l;

		n = scan.nextInt();
		l = scan.nextInt();

		// �迭�� �ʱ�ȭ
		a = init_array(n, l);

		// �迭 ���
		print_array(a);

		int sum;

		// �迭�� ��
		sum = sum_array(a);

		System.out.println("���� " + sum + "�Դϴ�.");
	}

	static int[][] init_array(int n, int l) {
		int[][] a = new int[n][l];

		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[i].length; j++)
				a[i][j] = (int) (Math.random() * 100);

		return a;
	}

	// �迭 ���
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