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

		System.out.println("이름:");
		student.setName(scan.nextLine());
		System.out.println("학번:");
		student.setNumber(scan.nextLine());
		System.out.println("학과:");
		student.setDept(scan.nextLine());

		if(db.updateQuery(insertQuery, student.getName(), student.getNumber(), student.getDept())>0)
			System.out.println("추가하였습니다.");
		else
			System.out.println("실패하였습니다.");
		db.close();
	}

	private void delStudent() {
		Scanner scan = new Scanner(System.in);

		showStudent();
		System.out.println("삭제할 학생의 학번을 입력하세요.");
		String number = scan.nextLine();

		if(db.updateQuery(deleteQuery, number)>0)
			System.out.println("삭제하였습니다.");
		else
			System.out.println("실패하였습니다.");
		db.close();
	}

	private void showStudent() {
		ResultSet rs = db.executeQuery(selectQuery);

		try {
			System.out.println("----------------");
			while (rs.next()) {
				System.out.println("이름:" + rs.getString("name"));
				System.out.println("학번:" + rs.getString("number"));
				System.out.println("학과:" + rs.getString("dept"));
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
			System.out.println("학생정보 관리");
			System.out.println("1.추가  2.삭제  3.조회  0.종료");
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
				System.out.println("잘못입력하였습니다.");
			}
		}
	}
}
