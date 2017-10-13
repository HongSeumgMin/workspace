package studentsmanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Management {
	private DBM db;
	private String insertQuery = "insert into student values(?,?,?);";
	private String selectQuery = "select * from student;";
	private String deleteQuery = "delete from student where number=?;";

	private void addStudent() {
		Scanner scan = new Scanner(System.in);
		Student student = new Student();

		System.out.println("�̸�:");
		student.setName(scan.nextLine());
		System.out.println("�й�:");
		student.setNumber(scan.nextLine());
		System.out.println("�а�:");
		student.setDept(scan.nextLine());

		if(db.updateQuery(insertQuery, student.getName(), student.getNumber(), student.getDept())>0)
			System.out.println("�߰��Ͽ����ϴ�.");
		else
			System.out.println("�����Ͽ����ϴ�.");
		db.close();
	}

	private void delStudent() {
		Scanner scan = new Scanner(System.in);

		showStudent();
		System.out.println("������ �л��� �й��� �Է��ϼ���.");
		String number = scan.nextLine();

		if(db.updateQuery(deleteQuery, number)>0)
			System.out.println("�����Ͽ����ϴ�.");
		else
			System.out.println("�����Ͽ����ϴ�.");
		db.close();
	}

	private void showStudent() {
		ResultSet rs = db.executeQuery(selectQuery);

		try {
			System.out.println("----------------");
			while (rs.next()) {
				System.out.println("�̸�:" + rs.getString("name"));
				System.out.println("�й�:" + rs.getString("number"));
				System.out.println("�а�:" + rs.getString("dept"));
				System.out.println("----------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	public Management() {
		db = new DBM();
		db.createTable();
	}

	public void start() {
		Scanner scan = new Scanner(System.in);
		boolean flag = true;

		int sel = 0;
		while (flag) {
			System.out.println("�л����� ����");
			System.out.println("1.�߰�  2.����  3.��ȸ  0.����");
			sel = scan.nextInt();

			switch (sel) {
			case 0:
				db.closeDB();
				flag = false;
				break;
			case 1:
				addStudent();
				break;
			case 2:
				delStudent();
				break;
			case 3:
				showStudent();
				break;
			default:
				System.out.println("�߸��Է��Ͽ����ϴ�.");
			}
		}
	}
}