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
			korea.add(new Dove("한국", "구구", "모충동"+i, "서원"+i, "170904"));
		}
		
		for(int i =random.nextInt(10)+1; i>0;i--){
			america.add(new Dove("미국", "nine", "사직동"+i, "서원"+i, "170804"));
		}
		
		for(int i = random.nextInt(10)+1; i>0;i--){
			china.add(new Dove("중국", "궈궈", "사창동"+i, "서원"+i, "170704"));
		}
		
		for(int i = random.nextInt(10)+1; i>0;i--){
			europe.add(new Dove("유럽", "99", "율량동"+i, "서원"+i, "170604"));
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
