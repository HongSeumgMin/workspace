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

			System.out.println("DB ���ӿϷ�");
			if (stmt.execute("SHOW DATABASES")) {
				rs = stmt.getResultSet();

				System.out.println("table ���");
				while (rs.next()) {
					String str = rs.getNString(1);
					System.out.println(str);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Ŭ���� ����
	public void start() {
		String word = null;
		String mean = null;

		int sel = 10;
		Scanner scan = new Scanner(System.in);

		while (sel != 0) {
			System.out.println("���� �ܾ �ܿ�ô�!");
			System.out.println("1.�߰�  2.����  3.�˻�  4.����  5.�˻�");
			System.out.print(">> ");
			sel = scan.nextInt();
			scan.nextLine();

			switch (sel) {
			case 1:
				System.out.print("�߰��� �ܾ �Է��ϼ���.");
				System.out.print(">> ");
				word = scan.nextLine();

				System.out.print("�ܾ��� ���� �Է��ϼ���.");
				System.out.print(">> ");
				mean = scan.nextLine();

				insert(word, mean);
				break;
			case 2:
				System.out.print("������ �ܾ �Է��ϼ���.");
				System.out.print(">> ");
				word = scan.nextLine();

				System.out.print("�ܾ��� ���� �Է��ϼ���.");
				System.out.print(">> ");
				mean = scan.nextLine();

				modify(word, mean);
				break;
			case 3:
				System.out.print("�˻��� �ܾ �Է��ϼ���.");
				System.out.print(">> ");
				word = scan.nextLine();

				search(word);
				break;
			case 4:
				System.out.print("������ �ܾ �Է��ϼ���.");
				System.out.print(">> ");
				word = scan.nextLine();

				delete(word);
				break;
			case 5:
				testGame();
				break;
			default:
				System.out.println("�߸��Է��Ͽ����ϴ�.");
			}
		}
	}

	// ���� ���
	public void insert(String word, String mean) {
		query = "insert iinto english values('" + word + "','" + mean + "');";

		try {
			if (stmt.executeUpdate(query) != 0)
				System.out.println("�߰� �Ǿ����ϴ�.");
			else
				System.out.println("���� �Ͽ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���� ����
	public void delete(String word) {
		query = "delete from english where word='" + word + "';";

		try {
			if (stmt.executeUpdate(query) != 0)
				System.out.println("���� �Ǿ����ϴ�.");
			else
				System.out.println("���� �Ͽ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���� ����
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

				System.out.println("���� �� ���� �����ϼ���.");
				System.out.print(">> ");
				int sel = scan.nextInt();
				rs.absolute(sel);

				query = "update english set mean='" + mean + "' where word='" + word + "' and mean='" + rs.getString(1)
						+ "';";
				if (stmt.executeUpdate(query) != 0)
					System.out.println("���� �Ǿ����ϴ�.");
				else
					System.out.println("���� �Ͽ����ϴ�.");
			} else
				System.out.println("���� �Ͽ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���� �˻�
	public void search(String word) {
		query = "select word, mean from english where word='" + word + "';";

		try {
			stmt.execute(query);
			if (stmt.getResultSet().next()) {
				System.out.println(word + "�� ���� ������ �����ϴ�.");
				rs = stmt.getResultSet();

				int i = 1;
				do {
					System.out.println(i + "." + rs.getString(2));
					i++;
				} while (rs.next());
			} else
				System.out.println("���� �Ͽ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �н� �˻�
	public void testGame() {
		query = "select * from english;";
		HashMap<String, Word> testData = new HashMap<String, Word>();

		// db���� �ܾ� ���� �� hashData ���
		try {
			stmt.execute(query);
			rs = stmt.getResultSet();

			if (!rs.next()) {
				System.out.println("��ϵ� �ܾ �����ϴ�.");
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

		// �н� �˻� ����
		System.out.println("�н� �˻縦 �����մϴ�.");

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
				System.out.println("�����Դϴ�.");
			else
				System.out.println("�����Դϴ�.");
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