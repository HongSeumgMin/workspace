

import java.util.*;

public class MsgHandler{
	private Server server;
	private Guest guest;
	private Member mem;
	
	public MsgHandler(Server server, Guest guest){
		this.server = server;
		this.guest = guest;
		mem = new Member();
	}
	
	private String getAllAlias(){
		String alias = server.getallconnectedguest();
		
		return alias;
	}
	
	
	//Ŭ���̾�Ʈ���� ���� �޽����� ó���ϴ� �޼���
	public void manageMsg(String msg){
		System.out.println("Server MsgHandler : " + msg);
		
		//���� �޽����� �Ľ���
		StringTokenizer st = new StringTokenizer(msg, "/");
		String[] arr = new String[st.countTokens()];
		for(int i = 0; st.hasMoreTokens(); i++){
			arr[i] = st.nextToken();
		}

		//���α׷��� �α����ϴ� �۾�
		if(arr[0].equals("Login")){	// Login/id/pw
			boolean isNull = false;
			System.out.println("Login");
			
			//�Է����� ���� ������ �ִ��� üũ
			if(arr.length != 3)
				isNull = true;
			
			if(isNull){
				guest.sendMsg(arr[0] + "/�Է����� ���� ������ �ֽ��ϴ�.");
			}
			else{
				String pw = "";
				String alias = "";
				
				alias = arr[1];
				String[] all = getAllAlias().split("/");
				boolean isConn = false;
				for(String s: all){
					if(s.equals(alias)){
						isConn = true;
						break;
					}
				}
				if(isConn){
					guest.sendMsg(arr[0] + "/�̹� ���ӵǾ� �ֽ��ϴ�.");
				}
				else{
					//Ŭ���̾�Ʈ�� ���̵� ���̵� ����
					guest.id = arr[1];
					//Ŭ���̾�Ʈ�� �г��ӿ� �г����� ����
					guest.alias = alias;
					//���� ���� ���� ��Ͽ� Ŭ���̾�Ʈ �߰�
					server.addGuest(guest);
					//���� ���� ������ ������ ��������Ʈ�� ���
					server.broadcastGuestlist();
					//������ �� ����Ʈ�� ���
					server.broadcastRoomlist();
					//���Ƿ� �Ѿ�� ���� �޽��� ����
					guest.sendMsg(arr[0] + "/true");
				}
			}
		}
		//�α׾ƿ����� �� �α��� ���� ���� ��
		else if(arr[0].equals("Logout")){
			//�α��� ���� ���� ���� �޽��� ����
			guest.sendMsg(arr[0]);
			//������ ���� ��Ͽ��� Ŭ���̾�Ʈ�� ����
			server.removeGuest(guest);
			//������ ���� ����� ������ ���� ����Ʈ�� ���
			server.broadcastGuestlist();
		}
		//���� ��ȭ�� ó���ϴ� �۾�
		else if(arr[0].equals("���Ǵ�ȭ")){ // ���Ǵ�ȭ/jtf_waitRoom_write.getText()/fontName/fontSize/fontColor/toUser
			System.out.println("Server ���Ǵ�ȭ : " + msg);
			//���ǿ� ������ ��� ������� �޽����� ���
			if(arr[5].equals("��ο���")){
				server.broadcast(arr[0] + "/" + "[" + guest.alias + "] " + arr[1] + "/" + arr[2] + "/" + arr[3] + "/" + arr[4]);
			}
			//�ӼӸ��� �� ����� �ڽ��̸� �����޽��� ���
			else if(arr[5].equals(guest.alias)){
				guest.sendMsg("Error/�ڽſ��� �ӼӸ��� �� �� �����ϴ�.");
			}
			//������ Ư���� �������Ը� �޽��� ���(�Ӹ� ���)
			else
				server.broadcastonlysomeone("���ǱӼӸ�/" + arr[1] + "/" + guest.alias + "/" + arr[5]);
		}
		//�� ����� �۾�
		else if(arr[0].equals("MakeRoom")){     //	MakeRoom/roomname/personlimit/roompw
			//������� �ϴ� �� �̸��� �̹� �����ϴ��� üũ
			if(server.roomCheck(arr[1])){
				guest.sendMsg("RoomError/�̹� �����ϴ� ���Դϴ�.");
			}
			else{
				//Ŭ���̾�Ʈ�� �����̶�� ����
				guest.isCaptain = true;
				//���� ���� �ؽ��ʿ� �߰�
				server.addRoom(arr[1]);
				//��й�ȣ�� �ִ� ���� ����� �۾�
				if(arr.length == 5){
					//�� ������ �ؽ��ʿ� �߰�
					server.setRoomInfo(arr[1], guest.alias, arr[2], arr[4]);
				}
				//��й�ȣ�� ���� ���� ����� �۾�
				else{
					//�� ������ �ؽ��ʿ� �߰�
					server.setRoomInfo(arr[1], guest.alias, arr[2], null);
				}
				//Ŭ���̾�Ʈ�� ���� ���� ��Ͽ��� ����
				server.removeGuest(guest);
				
				//�� ���� ��Ͽ� �ڽ��� �߰�
				server.addRoomguest(guest, arr[1]);
				//�濡 ���� ������ �ο��� ��� �۾�
				int num = server.getRoomguestNumber(arr[1]);
				//�ڽſ��� �游��µ� �ʿ��� �޽��� ����
				guest.sendMsg("MakeRoom/" + guest.alias + "/" + arr[1] + "/" + arr[2] + "/" + num);
				
				//������ �濡 ����޽��� ���
				server.broadcastRoom("������/" + "[" + guest.alias + "]", arr[1]);
				//�ڽ��� �������� ����
				server.setCaptain(arr[1]);
				//�� ���� ����� ���� ��������Ʈ�� ���
				server.broadcastRoomguestlist(arr[1]);
				//���� ���� ����� ���� ��������Ʈ�� ���
				server.broadcastGuestlist();
				//���� �� ��ϸ���Ʈ�� �� ����� ���
				server.broadcastRoomlist();
			}
		}
		//�濡 �����ϴ� �۾�
		else if(arr[0].equals("JoinRoom")){ //JoinRoom/roomname/roompw
			//��й�ȣ�� �ִ� �濡 �����ϴ� �۾�
			if(!arr[2].equals("null")){
				//������ �濡 ���� ������ ��� �۾�
				String[] roomInfo = server.getRoomInfo(arr[1]); //roomname/captain/limit/roompw
				//���� ��й�ȣ�� �Է��� ��й�ȣ�� ��ġ�ϴ��� üũ
				if(roomInfo[2].equals(arr[2])){
					//�����ϴ� �۾� ó��
					joinRoom(arr[1]);
				}
				else{
					guest.sendMsg("JoinRoom/��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				}
			}
			//��й�ȣ�� ���� �濡 �����ϴ� �۾�
			else{
				joinRoom(arr[1]);
			}
		}
		//�г��� ���� �۾�
		else if(arr[0].equals("ChangeAlias")){	// ChangeAlias/changedalias/roomname

			String[] allalias = getAllAlias().split("/");
			
			boolean isaliasexist = false;
			//�̹� ������ �ִ� �г������� üũ
			for(String s : allalias){
				if(s.equals(arr[1])){
					isaliasexist = true;
					break;
				}
			}
			if(isaliasexist){
				guest.sendMsg(arr[0] + "/�̹� ��ϵ� �г����Դϴ�.");
			}
			else{
				try{
					//���ǿ��� �г��� ���� �۾��� �̷����� �� ����
					if(arr[2].equals("WaitingRoomForm")){
						//������ ��ȭâ�� �޼��� ���
						server.broadcast("���Ǵ�ȭ/[" + guest.alias + "]���� �г����� [" + arr[1] + "]�� ����Ǿ����ϴ�.");
						guest.alias = arr[1];
						//���� ��������� ���� ��������Ʈ�� ���
						server.broadcastGuestlist();
					}
					//�� �ȿ��� �г��� ���� �۾��� �̷����� �� ����
					else{
						//���� ��ȭâ�� �޼��� ���
						server.broadcastRoom("���ȭ/[" + guest.alias + "]���� �г����� [" + arr[1] + "]�� ����Ǿ����ϴ�.", arr[2]);
						guest.alias = arr[1];
						//�濡 ������ ���� ����� �� ��������Ʈ�� ���
						server.broadcastRoomguestlist(arr[2]);
					}
					//Ŭ���̾�Ʈ�� �г��ӿ� ����� �г��� ����
					guest.sendMsg(arr[0] + "/�г����� ����Ǿ����ϴ�.");
				}catch(Exception e){
					System.out.println("�г��� ���� ����");
				}
			}
		}
		//���� ������ �۾� ����
		else if(arr[0].equals("����")){	// ����/msg/receiver
			//�ڽſ��� ���� ������ �� ����
			if(arr[2].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� ������ ���� �� �����ϴ�.");
			else
				//������ �������� ���� ������ �޼��� ����
				server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[2]);
		}
		//1:1��ȭ��û �۾� ����
		else if(arr[0].equals("1:1��ȭ��û")){	// 1:1��ȭ/receiver
			//�ڽſ��� ���� ������ �� ����
			if(arr[1].equals(guest.alias))
				guest.sendMsg("Error/�ڽſ��� 1:1��ȭ ��û�� �� �� �����ϴ�.");
			else
				//���õ� �������� 1:1 ��ȭ �������� ���� �޽��� ����
				server.broadcastonlysomeone(arr[0] + "/���� 1:1��ȭ�� ��û�ϼ̽��ϴ�. �����Ͻðڽ��ϱ�?/"  + guest.alias + "/" + arr[1]);
		}
		//1:1 ��ȭ���� �۾� ����
		else if(arr[0].equals("1:1��ȭ����")){	// 1:1��ȭ����/boolean/receiver/sender or 1:1��ȭ����/false/sender
			//1:1��ȭ ���� ���� �� ����
			if(Boolean.parseBoolean(arr[1]))
				server.broadcastonlysomeone(arr[0] + "/true/" + arr[2] + "/" + arr[3]);
			//1:1��ȭ �ź����� �� �ź� �޽��� ����
			else{
				server.broadcastonlysomeone(arr[0] + "/false/������ �����Ͽ����ϴ�./" + arr[2]);
			}
		}
		//1:1��ȭ �۾� ����
		else if(arr[0].equals("1:1��ȭ")){	// 1:1��ȭ/msg/receiver
			//�ڽſ��� �ڽ��� ���� �޽��� ���
			guest.sendMsg(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[2]);
			//���濡�� �ڽ��� ���� �޽��� ����
			server.broadcastonlysomeone(arr[0] + "/" + arr[1] + "/" + guest.alias + "/" + arr[2]);
		}
		//�濡�� ��ȭ�ϴ� �۾� ����
		else if(arr[0].equals("���ȭ")){		// ���ȭ/msg/fontName/fontSize/fontColor/toUser/roomname
			//�濡 ������ ��� �������� �޽��� ����
			if(arr[5].equals("��ο���")){
				server.broadcastRoom(arr[0] + "/" + "[" + guest.alias + "] " + arr[1] + "/" + arr[2] + "/" + 
						arr[3] + "/" + arr[4], arr[6]);
			}
			//�ڽſ��� �ӼӸ��� ���� �� ��
			else if(arr[5].equals(guest.alias)){
				guest.sendMsg("Error/�ڽſ��� �ӼӸ��� �� �� �����ϴ�.");
			}
			else
				//������ �������� �ӼӸ� ����
				server.broadcastRoomonlysomeone("��ӼӸ�/" + arr[1] + "/" + guest.alias + "/" + arr[5], arr[6]);
		}
		//�ʴ��ϱ� ���� ���� ���� ����Ʈ ��� �۾�
		else if(arr[0].equals("�����������")){
			server.guestListForInvite(guest, arr[1]);
		}
		//�ʴ����� ��� ���� ���� ���� �۾�
		else if(arr[0].equals("�ʴ�")){	// �ʴ�/roomname/selectedId
			server.broadcastonlysomeone(arr[0] + "/�ʴ밡 �Խ��ϴ�. �����Ͻðڽ��ϱ�?/" + arr[1] + "/" + arr[2]);
		}
		//�ʴ� �������� ��� �濡 �����ϴ� �۾� ����
		else if(arr[0].equals("�ʴ����")){	// �ʴ����/roomname/selectedId
			joinRoom(arr[1]);
		}
		//�濡�� �������� �� �ϴ� �۾�
		else if(arr[0].equals("Out")){ // out/roomname/null
			//������ ������ ������ ��� ����
			if(guest.isCaptain){
				//�մ����� ����
				guest.isCaptain = false;
				//�濡 ������ �����ִ� ��� �۾�
				if(server.getRoomguestNumber(arr[1]) > 1){
					//���� ���� ������ ����
					String nextcaptain = server.getNextGuest(arr[1]);
					//���� �������� ���� ���� �ο�
					server.giveGrant(nextcaptain, arr[1]);
					//���� ���  �������� �޽��� ����
					server.broadcastRoom("���ȭ/[" + nextcaptain + "]���� ������ �Ǿ����ϴ�.", arr[1]);
				}
			}
			//�ڽ��� �� ���� ��Ͽ��� ���� 
			server.removeRoomguest(guest, arr[1]);
			//���� �ִ� ������ ���� ��� ����
			if(server.getRoomguestNumber(arr[1]) == 0){
				//�� ��Ͽ��� �� ����
				server.removeRoom(arr[1]);
			}
			else{
				//������ ���� ���� ����
				String[] roomInfo = server.getRoomInfo(arr[1]);
				//����� ������ �����ϴ� �۾�
				server.updateRoomInfo(arr[1], roomInfo[0], roomInfo[1], server.getRoomguestNumber(arr[1]));
			}
			//���� ���� ��Ͽ� �ڽ� �߰�
			server.addGuest(guest);
			//���� ��� �������� ���� �޽��� ����
			server.broadcastRoom("������/" + guest.alias, arr[1]);
			//���� ��������Ʈ�� �� ���� ��� ���
			server.broadcastRoomguestlist(arr[1]);
			//���� ���� ����� ���� ��������Ʈ�� ���
			server.broadcastGuestlist();
			//�� ����� ���� �� ����Ʈ�� ���
			server.broadcastRoomlist();
			//������ �����ֱ����� Flag���� ����
			guest.sendMsg("������Ϸ�/");
		}
		//����ѱ�� �۾� ����
		else if(arr[0].equals("����ѱ��")){	// ����ѱ��/roomname/selectedId
			//������ ������ �ڽ��� ��� ����
			if(guest.alias.equals(arr[2])){
				guest.sendMsg("Error/�ڽſ��� ������ �ѱ� �� �����ϴ�.");
			}
			else{
				//������ �������� ���� ���� �ο�
				server.giveGrant(arr[2], arr[1]);
				//���� ��� �������� �޽��� ����
				server.broadcastRoom("���ȭ/[" + arr[2] + "]���� ������ �Ǿ����ϴ�.", arr[1]);
			}
		}
		//���� �۾� ����
		else if(arr[0].equals("����")){	// ����/roomname/selectedId
			//������ ������ �ڽ��� ��� ����
			if(guest.alias.equals(arr[2]))
				guest.sendMsg("Error/�ڽ��� ���� �� �� �����ϴ�.");
			else
				//������� �������� �޽��� ����
				server.broadcastRoomonlysomeone(arr[0] + "/����Ǿ����ϴ�./" + arr[1] + "/" + arr[2], arr[1]);
		}
		//���� �� ����Ʈ���� �濡 ������ ���� ���� ���� �۾�
		else if(arr[0].equals("������")){
			server.roomGuestList(guest, arr[1]);
		}
		//���α׷��� ������ ��� ������ ������ �۾�
		else if(arr[0].equals("��ü����")){ // ��ü����/Ÿ��
			//������ ��� ���� ����
			String s = server.getallconnectedguest();
			//�޽��� ����
			guest.sendMsg(msg + s);
		}
		//���� �����ϱ� ���� �۾�
		else if(arr[0].equals("��������")){	// ��������/selectedId
			String IPaddr = guest.s.getInetAddress().getHostAddress();
			server.broadcastonlysomeone("�������ۼ���/" + IPaddr + "/" + guest.alias + "/" + arr[1]);
		}
		else if(arr[0].equals("�������ۼ�������")){ // �������ۼ�������/false/sender/receiver
			server.broadcastonlysomeone("�������ۼ�������/" + arr[1] + "/" + arr[3] + "/" + arr[2]);
		}
	}
	
	//�濡 �����ϴ� �۾� ����
	private void joinRoom(String roomname){
		//�� ���� ��Ͽ� �ڽ��� �߰�, ���� ���� �ο��� �Ѿ��� ��� ����
		if(server.addRoomguest(guest, roomname)){
			return;
		}
		//���� ���� ��Ͽ��� �ڽ� ����
		server.removeGuest(guest);
		//������ ���� ���� ����
		String[] roomInfo = server.getRoomInfo(roomname);
		//�����ϴµ� �ʿ��� ���� ����
		guest.sendMsg("JoinRoom/" + roomInfo[0] + "/" + roomname + "/" + server.getRoomguestNumber(roomname) + "/" + roomInfo[1]); 
		//����� ������ �����ϴ� �۾�
		server.updateRoomInfo(roomname, roomInfo[0], roomInfo[1], server.getRoomguestNumber(roomname));
		//���� ��� �������� ���� �޽��� ���
		server.broadcastRoom("������/" + "[" + guest.alias + "]", roomname);
		//������ �� ����Ʈ�� �� ��� ���
		server.broadcastRoomlist();
		//�� ���� ����� �� ���� ����Ʈ�� ���
		server.broadcastRoomguestlist(roomname);
		//���� ���� ����� ���� ���� ����Ʈ�� ��
		server.broadcastGuestlist();
	}
}