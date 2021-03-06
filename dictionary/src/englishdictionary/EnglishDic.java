package englishdictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class EnglishDic {
	Connection connection;
	Statement stmt;
	ResultSet rs;
	String query;

	public EnglishDic() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://211.199.232.224:3306/dictionary", "java",
					"12345678");
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			System.out.println("DB 접속완료");
			if (stmt.execute("SHOW DATABASES")) {
				rs = stmt.getResultSet();

				System.out.println("table 목록");
				while (rs.next()) {
					String str = rs.getNString(1);
					System.out.println(str);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// 클래스 시작
	public void start() {
		String word = null;
		String mean = null;

		int sel = 10;
		Scanner scan = new Scanner(System.in);

		while (sel != 0) {
			System.out.println("영어 단어를 외웁시다!");
			System.out.println("1.추가  2.수정  3.검색  4.삭제  5.검사");
			System.out.print(">> ");
			sel = scan.nextInt();
			scan.nextLine();

			switch (sel) {
			case 1:
				System.out.print("추가할 단어를 입력하세요.");
				System.out.print(">> ");
				word = scan.nextLine();

				System.out.print("단어의 뜻을 입력하세요.");
				System.out.print(">> ");
				mean = scan.nextLine();

				insert(word, mean);
				break;
			case 2:
				System.out.print("수정할 단어를 입력하세요.");
				System.out.print(">> ");
				word = scan.nextLine();

				System.out.print("단어의 뜻을 입력하세요.");
				System.out.print(">> ");
				mean = scan.nextLine();

				modify(word, mean);
				break;
			case 3:
				System.out.print("검색할 단어를 입력하세요.");
				System.out.print(">> ");
				word = scan.nextLine();

				search(word);
				break;
			case 4:
				System.out.print("삭제할 단어를 입력하세요.");
				System.out.print(">> ");
				word = scan.nextLine();

				delete(word);
				break;
			case 5:
				testGame();
				break;
			default:
				System.out.println("잘못입력하였습니다.");
			}
		}
	}

	// 사전 등록
	public void insert(String word, String mean) {
		query = "insert iinto english values('" + word + "','" + mean + "');";

		try {
			if (stmt.executeUpdate(query) != 0)
				System.out.println("추가 되었습니다.");
			else
				System.out.println("실패 하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 사전 삭제
	public void delete(String word) {
		query = "delete from english where word='" + word + "';";

		try {
			if (stmt.executeUpdate(query) != 0)
				System.out.println("삭제 되었습니다.");
			else
				System.out.println("실패 하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 사전 수정
	public void modify(String word, String mean) {
		query = "select mean from english where word='" + word + "';";
		Scanner scan = new Scanner(System.in);

		try {
			stmt.execute(query);
			if (stmt.getResultSet().next()) {
				rs = stmt.getResultSet();

				int i = 1;
				do {
					System.out.println(i + "." + rs.getString(1));
					i++;
				} while (rs.next());
				rs.first();

				System.out.println("수정 할 뜻을 선택하세요.");
				System.out.print(">> ");
				int sel = scan.nextInt();
				rs.absolute(sel);

				query = "update english set mean='" + mean + "' where word='" + word + "' and mean='" + rs.getString(1)
						+ "';";
				if (stmt.executeUpdate(query) != 0)
					System.out.println("수정 되었습니다.");
				else
					System.out.println("실패 하였습니다.");
			} else
				System.out.println("실패 하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 사전 검색
	public void search(String word) {
		query = "select word, mean from english where word='" + word + "';";

		try {
			stmt.execute(query);
			if (stmt.getResultSet().next()) {
				System.out.println(word + "의 뜻은 다음과 같습니다.");
				rs = stmt.getResultSet();

				int i = 1;
				do {
					System.out.println(i + "." + rs.getString(2));
					i++;
				} while (rs.next());
			} else
				System.out.println("실패 하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 학습 검사
	public void testGame() {
		query = "select * from english;";
		HashMap<String, Word> testData = new HashMap<String, Word>();

		// db에서 단어 추출 후 hashData 등록
		try {
			stmt.execute(query);
			rs = stmt.getResultSet();

			if (!rs.next()) {
				System.out.println("등록된 단어가 없습니다.");
				return;
			} else {
				String word;
				String mean;
				do {
					word = rs.getString("word");
					mean = rs.getString("mean");
					if (testData.containsKey(word))
						testData.get(word).addMean(mean);
					else
						testData.put(word, new Word(word, mean));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 학습 검사 시작
		System.out.println("학습 검사를 시작합니다.");

		Iterator<String> iter = testData.keySet().iterator();

		Scanner scan = new Scanner(System.in);
		Word word;
		String answer;
		while (iter.hasNext()) {
			word = testData.get(iter.next());
			System.out.print(word.getWord() + "::");
			answer = scan.nextLine();

			boolean right = false;
			for (String mean : word.getMeans()) {
				System.out.println("--" + mean);
				if (mean.equals(answer))
					right = true;
			}

			if (right)
				System.out.println("정답입니다.");
			else
				System.out.println("오답입니다.");
		}
	}

	public static void main(String[] args) {
		new EnglishDic().start();
	}

}

class Word {
	private String word;
	private ArrayList<String> means;

	public Word(String word, String mean) {
		this.word = word;
		means = new ArrayList<String>();
		means.add(mean);
	}

	public String getWord() {
		return word;
	}

	public void addMean(String mean) {
		means.add(mean);
	}

	public ArrayList<String> getMeans() {
		return means;
	}
}
