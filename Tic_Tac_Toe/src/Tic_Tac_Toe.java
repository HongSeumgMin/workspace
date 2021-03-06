import java.util.Scanner;

public class Tic_Tac_Toe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = new char[3][3];
		int x, y;

		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = ' ';

		while(set_com(board)) {
			//set_com(board) 컴퓨터 자리 선정

			for (int i = 0; i < 3; i++) {
				System.out.println("  " + board[i][0] + "|  " + board[i][1] + "|  " + board[i][2]);
				if (i != 2)
					System.out.println("---|---|---");
			}

			System.out.print("다음 수의 좌표를 입력하시오");
			x = scan.nextInt();
			y = scan.nextInt();

			if (board[x][y] != ' ') {
				System.out.println("잘못된 위치입니다.");
				continue;
			} else
				board[x][y] = 'X';

/*
			int i = 0, j = 0;
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++)
					if (board[i][j] == ' ')
						break;
				if (board[i][j < 3 ? j : 2] == ' ')
					// 안쪽 for문 탈출시 j값이 3넘으면 오류.3을 2로 변경
					break;
			}
			if (i < 3 && j < 3)
				board[i][j] = 'O';
*/
		}
		
		System.out.println();
		System.out.println("게임끝.");
	}

	static boolean set_com(char[][] board) {
		int[] rank = new int[9];

		////////////////// 주변에 빈칸이 많은가? 최소랭크
		if (board[0][0] == ' ') {
			if (board[0][1] == ' ' && board[0][2] == ' ') {
				rank[0]++;
			}
			if (board[1][1] == ' ' && board[1][2] == ' ') {
				rank[0]++;
			}
			if (board[1][0] == ' ' && board[2][0] == ' ') {
				rank[0]++;
			}
		}
		if (board[0][1] == ' ') {
			if (board[1][1] == ' ' && board[2][1] == ' ') {
				rank[1]++;
			}
			if (board[0][0] == ' ' && board[0][2] == ' ') {
				rank[1]++;
			}
		}
		if (board[0][2] == ' ') {
			if (board[0][0] == ' ' && board[0][1] == ' ') {
				rank[2]++;
			}
			if (board[1][1] == ' ' && board[2][0] == ' ') {
				rank[2]++;
			}
			if (board[1][2] == ' ' && board[2][2] == ' ') {
				rank[2]++;
			}
		}
		if (board[1][0] == ' ') {
			if (board[0][0] == ' ' && board[2][0] == ' ') {
				rank[3]++;
			}
			if (board[1][1] == ' ' && board[1][2] == ' ') {
				rank[3]++;
			}
		}
		if (board[1][1] == ' ') {
			if (board[0][0] == ' ' && board[2][2] == ' ') {
				rank[4]++;
			}
			if (board[0][1] == ' ' && board[2][1] == ' ') {
				rank[4]++;
			}
			if (board[0][2] == ' ' && board[2][0] == ' ') {
				rank[4]++;
			}
			if (board[1][0] == ' ' && board[1][2] == ' ') {
				rank[4]++;
			}
		}
		if (board[1][2] == ' ') {
			if (board[0][2] == ' ' && board[2][2] == ' ') {
				rank[5]++;
			}
			if (board[1][0] == ' ' && board[1][1] == ' ') {
				rank[5]++;
			}
		}
		if (board[2][0] == ' ') {
			if (board[0][0] == ' ' && board[1][0] == ' ') {
				rank[6]++;
			}
			if (board[1][1] == ' ' && board[0][2] == ' ') {
				rank[6]++;
			}
			if (board[2][1] == ' ' && board[2][2] == ' ') {
				rank[6]++;
			}
		}
		if (board[2][1] == ' ') {
			if (board[0][1] == ' ' && board[1][1] == ' ') {
				rank[7]++;
			}
			if (board[2][0] == ' ' && board[2][2] == ' ') {
				rank[7]++;
			}
		}
		if (board[2][2] == ' ') {
			if (board[0][0] == ' ' && board[1][1] == ' ') {
				rank[8]++;
			}
			if (board[0][2] == ' ' && board[1][2] == ' ') {
				rank[8]++;
			}
			if (board[2][0] == ' ' && board[2][1] == ' ') {
				rank[8]++;
			}
		}
		///////////////// 주변에 'O'이 있는가? 최소랭크
		if (board[0][0] == ' ') {
			if (board[0][1] == 'O' || board[0][2] == 'O') {
				rank[0]++;
			}
			if (board[1][1] == 'O' || board[1][2] == 'O') {
				rank[0]++;
			}
			if (board[1][0] == 'O' || board[2][0] == 'O') {
				rank[0]++;
			}
		}
		if (board[0][1] == ' ') {
			if (board[1][1] == 'O' || board[2][1] == 'O') {
				rank[1]++;
			}
			if (board[0][0] == 'O' || board[0][2] == 'O') {
				rank[1]++;
			}
		}
		if (board[0][2] == ' ') {
			if (board[0][0] == 'O' || board[0][1] == 'O') {
				rank[2]++;
			}
			if (board[1][1] == 'O' || board[2][0] == 'O') {
				rank[2]++;
			}
			if (board[1][2] == 'O' || board[2][2] == 'O') {
				rank[2]++;
			}
		}
		if (board[1][0] == ' ') {
			if (board[0][0] == 'O' || board[2][0] == 'O') {
				rank[3]++;
			}
			if (board[1][1] == 'O' || board[1][2] == 'O') {
				rank[3]++;
			}
		}
		if (board[1][1] == ' ') {
			if (board[0][0] == 'O' || board[2][2] == 'O') {
				rank[4]++;
			}
			if (board[0][1] == 'O' || board[2][1] == 'O') {
				rank[4]++;
			}
			if (board[0][2] == 'O' || board[2][0] == 'O') {
				rank[4]++;
			}
			if (board[1][0] == 'O' || board[1][2] == 'O') {
				rank[4]++;
			}
		}
		if (board[1][2] == ' ') {
			if (board[0][2] == 'O' || board[2][2] == 'O') {
				rank[5]++;
			}
			if (board[1][0] == 'O' || board[1][1] == 'O') {
				rank[5]++;
			}
		}
		if (board[2][0] == ' ') {
			if (board[0][0] == 'O' || board[1][0] == 'O') {
				rank[6]++;
			}
			if (board[1][1] == 'O' || board[0][2] == 'O') {
				rank[6]++;
			}
			if (board[2][1] == 'O' || board[2][2] == 'O') {
				rank[6]++;
			}
		}
		if (board[2][1] == ' ') {
			if (board[0][1] == 'O' || board[1][1] == 'O') {
				rank[7]++;
			}
			if (board[2][0] == 'O' || board[2][2] == 'O') {
				rank[7]++;
			}
		}
		if (board[2][2] == ' ') {
			if (board[0][0] == 'O' || board[1][1] == 'O') {
				rank[8]++;
			}
			if (board[0][2] == 'O' || board[1][2] == 'O') {
				rank[8]++;
			}
			if (board[2][0] == 'O' || board[2][1] == 'O') {
				rank[8]++;
			}
		}
		///////////////// 주변에 'X'가 1개라도 있는가? 랭크감소
		if (board[0][0] == ' ') {
			if (board[0][1] == 'X' || board[0][2] == 'X') {
				rank[0]--;
			}
			if (board[1][1] == 'X' || board[1][2] == 'X') {
				rank[0]--;
			}
			if (board[1][0] == 'X' || board[2][0] == 'X') {
				rank[0]--;
			}
		}
		if (board[0][1] == ' ') {
			if (board[1][1] == 'X' || board[2][1] == 'X') {
				rank[1]--;
			}
			if (board[0][0] == 'X' || board[0][2] == 'X') {
				rank[1]--;
			}
		}
		if (board[0][2] == ' ') {
			if (board[0][0] == 'X' || board[0][1] == 'X') {
				rank[2]--;
			}
			if (board[1][1] == 'X' || board[2][0] == 'X') {
				rank[2]--;
			}
			if (board[1][2] == 'X' || board[2][2] == 'X') {
				rank[2]--;
			}
		}
		if (board[1][0] == ' ') {
			if (board[0][0] == 'X' || board[2][0] == 'X') {
				rank[3]--;
			}
			if (board[1][1] == 'X' || board[1][2] == 'X') {
				rank[3]--;
			}
		}
		if (board[1][1] == ' ') {
			if (board[0][0] == 'X' || board[2][2] == 'X') {
				rank[4]--;
			}
			if (board[0][1] == 'X' || board[2][1] == 'X') {
				rank[4]--;
			}
			if (board[0][2] == 'X' || board[2][0] == 'X') {
				rank[4]--;
			}
			if (board[1][0] == 'X' || board[1][2] == 'X') {
				rank[4]--;
			}
		}
		if (board[1][2] == ' ') {
			if (board[0][2] == 'X' || board[2][2] == 'X') {
				rank[5]--;
			}
			if (board[1][0] == 'X' || board[1][1] == 'X') {
				rank[5]--;
			}
		}
		if (board[2][0] == ' ') {
			if (board[0][0] == 'X' || board[1][0] == 'X') {
				rank[6]--;
			}
			if (board[1][1] == 'X' || board[0][2] == 'X') {
				rank[6]--;
			}
			if (board[2][1] == 'X' || board[2][2] == 'X') {
				rank[6]--;
			}
		}
		if (board[2][1] == ' ') {
			if (board[0][1] == 'X' || board[1][1] == 'X') {
				rank[7]--;
			}
			if (board[2][0] == 'X' || board[2][2] == 'X') {
				rank[7]--;
			}
		}
		if (board[2][2] == ' ') {
			if (board[0][0] == 'X' || board[1][1] == 'X') {
				rank[8]--;
			}
			if (board[0][2] == 'X' || board[1][2] == 'X') {
				rank[8]--;
			}
			if (board[2][0] == 'X' || board[2][1] == 'X') {
				rank[8]--;
			}
		}
		///////////////// 주변에 'O'가 2개 연달아 있는가? 최고랭크
		if (board[0][0] == ' ') {
			if (board[0][1] == 'O' && board[0][2] == 'O') {
				rank[0] += 100;
			}
			if (board[1][1] == 'O' && board[1][2] == 'O') {
				rank[0] += 100;
			}
			if (board[1][0] == 'O' && board[2][0] == 'O') {
				rank[0] += 100;
			}
		}
		if (board[0][1] == ' ') {
			if (board[1][1] == 'O' && board[2][1] == 'O') {
				rank[1] += 100;
			}
			if (board[0][0] == 'O' && board[0][2] == 'O') {
				rank[1] += 100;
			}
		}
		if (board[0][2] == ' ') {
			if (board[0][0] == 'O' && board[0][1] == 'O') {
				rank[2] += 100;
			}
			if (board[1][1] == 'O' && board[2][0] == 'O') {
				rank[2] += 100;
			}
			if (board[1][2] == 'O' && board[2][2] == 'O') {
				rank[2] += 100;
			}
		}
		if (board[1][0] == ' ') {
			if (board[0][0] == 'O' && board[2][0] == 'O') {
				rank[3] += 100;
			}
			if (board[1][1] == 'O' && board[1][2] == 'O') {
				rank[3] += 100;
			}
		}
		if (board[1][1] == ' ') {
			if (board[0][0] == 'O' && board[2][2] == 'O') {
				rank[4] += 100;
			}
			if (board[0][1] == 'O' && board[2][1] == 'O') {
				rank[4] += 100;
			}
			if (board[0][2] == 'O' && board[2][0] == 'O') {
				rank[4] += 100;
			}
			if (board[1][0] == 'O' && board[1][2] == 'O') {
				rank[4] += 100;
			}
		}
		if (board[1][2] == ' ') {
			if (board[0][2] == 'O' && board[2][2] == 'O') {
				rank[5] += 100;
			}
			if (board[1][0] == 'O' && board[1][1] == 'O') {
				rank[5] += 100;
			}
		}
		if (board[2][0] == ' ') {
			if (board[0][0] == 'O' && board[1][0] == 'O') {
				rank[6] += 100;
			}
			if (board[1][1] == 'O' && board[0][2] == 'O') {
				rank[6] += 100;
			}
			if (board[2][1] == 'O' && board[2][2] == 'O') {
				rank[6] += 100;
			}
		}
		if (board[2][1] == ' ') {
			if (board[0][1] == 'O' && board[1][1] == 'O') {
				rank[7] += 100;
			}
			if (board[2][0] == 'O' && board[2][2] == 'O') {
				rank[7] += 100;
			}
		}
		if (board[2][2] == ' ') {
			if (board[0][0] == 'O' && board[1][1] == 'O') {
				rank[8] += 100;
			}
			if (board[0][2] == 'O' && board[1][2] == 'O') {
				rank[8] += 100;
			}
			if (board[2][0] == 'O' && board[2][1] == 'O') {
				rank[8] += 100;
			}
		}
		///////////////// 주변에 'X'가 2개 연달아 있는가? 중간랭크
		if (board[0][0] == ' ') {
			if (board[0][1] == 'X' && board[0][2] == 'X') {
				rank[0] += 50;
			}
			if (board[1][1] == 'X' && board[1][2] == 'X') {
				rank[0] += 50;
			}
			if (board[1][0] == 'X' && board[2][0] == 'X') {
				rank[0] += 50;
			}
		}
		if (board[0][1] == ' ') {
			if (board[1][1] == 'X' && board[2][1] == 'X') {
				rank[1] += 50;
			}
			if (board[0][0] == 'X' && board[0][2] == 'X') {
				rank[1] += 50;
			}
		}
		if (board[0][2] == ' ') {
			if (board[0][0] == 'X' && board[0][1] == 'X') {
				rank[2] += 50;
			}
			if (board[1][1] == 'X' && board[2][0] == 'X') {
				rank[2] += 50;
			}
			if (board[1][2] == 'X' && board[2][2] == 'X') {
				rank[2] += 50;
			}
		}
		if (board[1][0] == ' ') {
			if (board[0][0] == 'X' && board[2][0] == 'X') {
				rank[3] += 50;
			}
			if (board[1][1] == 'X' && board[1][2] == 'X') {
				rank[3] += 50;
			}
		}
		if (board[1][1] == ' ') {
			if (board[0][0] == 'X' && board[2][2] == 'X') {
				rank[4] += 50;
			}
			if (board[0][1] == 'X' && board[2][1] == 'X') {
				rank[4] += 50;
			}
			if (board[0][2] == 'X' && board[2][0] == 'X') {
				rank[4] += 50;
			}
			if (board[1][0] == 'X' && board[1][2] == 'X') {
				rank[4] += 50;
			}
		}
		if (board[1][2] == ' ') {
			if (board[0][2] == 'X' && board[2][2] == 'X') {
				rank[5] += 50;
			}
			if (board[1][0] == 'X' && board[1][1] == 'X') {
				rank[5] += 50;
			}
		}
		if (board[2][0] == ' ') {
			if (board[0][0] == 'X' && board[1][0] == 'X') {
				rank[6] += 50;
			}
			if (board[1][1] == 'X' && board[0][2] == 'X') {
				rank[6] += 50;
			}
			if (board[2][1] == 'X' && board[2][2] == 'X') {
				rank[6] += 50;
			}
		}
		if (board[2][1] == ' ') {
			if (board[0][1] == 'X' && board[1][1] == 'X') {
				rank[7] += 50;
			}
			if (board[2][0] == 'X' && board[2][2] == 'X') {
				rank[7] += 50;
			}
		}
		if (board[2][2] == ' ') {
			if (board[0][0] == 'X' && board[1][1] == 'X') {
				rank[8] += 50;
			}
			if (board[0][2] == 'X' && board[1][2] == 'X') {
				rank[8] += 50;
			}
			if (board[2][0] == 'X' && board[2][1] == 'X') {
				rank[8] += 50;
			}
		}
		/////////////////
		int tmp = 0;
		boolean flag = false;

		System.out.println("우선순위");
		for (int i = 0; i < 9; i++) {
			 System.out.println(i+"번칸 :"+rank[i]);
			if (rank[tmp] < rank[i]) {
				tmp = i;
				flag = true;
			}
		}
		
		if(tmp==0&&rank[0]>0)
			flag=true;

		 System.out.println("선택 칸:"+tmp);
		if (flag)
			switch (tmp) {
			case 0:
				board[0][0] = 'O';
				break;
			case 1:
				board[0][1] = 'O';
				break;
			case 2:
				board[0][2] = 'O';
				break;
			case 3:
				board[1][0] = 'O';
				break;
			case 4:
				board[1][1] = 'O';
				break;
			case 5:
				board[1][2] = 'O';
				break;
			case 6:
				board[2][0] = 'O';
				break;
			case 7:
				board[2][1] = 'O';
				break;
			case 8:
				board[2][2] = 'O';
				break;
			}
		
		return flag;
	}
}