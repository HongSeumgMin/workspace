

public class Member{
	//ȸ���� ���̵�
	private String id;
	
	//ȸ���� �̸�
	private String name;
	
	//ȸ���� ��й�ȣ
	private String pw;
	
	//ȸ���� �г���
	private String alias;
	
	//ȸ���� ��й�ȣ
	private String tel;

	//ȸ���� ���̵� �����ϴ� �޼���
	public void setId(String id){	
		this.id = id;	
	}
	
	//ȸ���� �̸��� �����ϴ� �޼���
	public void setName(String name){
		this.name = name;
	}
	
	//ȸ���� ��й�ȣ�� �����ϴ� �޼���
	public void setPw(String pw){	
		this.pw = pw;	
	}
	
	//ȸ���� �г����� �����ϴ� �޼���
	public void setAlias(String alias){	
		this.alias = alias;	
	}
	
	//ȸ���� ��ȭ��ȣ�� �����ϴ� �޼���
	public void setTel(String tel){	
		this.tel = tel;
	}

	//ȸ���� ���̵� ��� �޼���
	public String getId(){	
		return id;	
	}
	
	//ȸ���� �̸��� ��� �޼���
	public String getName(){
		return name;
	}
	
	//ȸ���� ��й�ȣ�� ��� �޼���
	public String getPw(){	
		return pw;
	}
	
	//ȸ���� �г����� ��� �޼���
	public String getAlias(){	
		return alias;	
	}
	
	//ȸ���� ��ȭ��ȣ�� ��� �޼�
	public String getTel(){	
		return tel;	
	}
}