package sparrow;

import java.util.ArrayList;
import java.util.Random;

public class Doves {

	ArrayList<Dove> korea;
	ArrayList<Dove> america;
	ArrayList<Dove> china;
	ArrayList<Dove> europe;
	private Random random;
	
	public Doves() {
		korea = new ArrayList<Dove>();
		america = new ArrayList<Dove>();
		china = new ArrayList<Dove>();
		europe = new ArrayList<Dove>();
		
		for(int i = random.nextInt(10)+1; i>0 ;i--){
			korea.add(new Dove("�ѱ�", "����", "���浿"+i, "����"+i, "170904"));
		}
		
		for(int i =random.nextInt(10)+1; i>0;i--){
			america.add(new Dove("�̱�", "nine", "������"+i, "����"+i, "170804"));
		}
		
		for(int i = random.nextInt(10)+1; i>0;i--){
			china.add(new Dove("�߱�", "�ű�", "��â��"+i, "����"+i, "170704"));
		}
		
		for(int i = random.nextInt(10)+1; i>0;i--){
			europe.add(new Dove("����", "99", "������"+i, "����"+i, "170604"));
		}
	}
	
	public void randomSing(){
		for(int i = 0; i<4;i++){
			for(int j = 0; j< korea.size();j++){
				korea.get(j).getSound();
			}
			for(int j = 0; j< america.size();j++){
				korea.get(j).getSound();
			}
			for(int j = 0; j< europe.size();j++){
				korea.get(j).getSound();
			}
			for(int j = 0; j< china.size();j++){
				korea.get(j).getSound();
			}
		}
	}
}