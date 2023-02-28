import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ14865 {
	
	//가로선을 저장할 클래스
	//y 좌표 하나와, x좌표를 나타내기 위한 좌표 두 개
	static class Data{
		int maxY;
		int minX;
		int maxX;
		public Data(int maxY, int minX, int maxX) {
			super();
			this.maxY = maxY;
			this.minX = minX;
			this.maxX = maxX;
		}
		@Override
		public String toString() {
			return "Data [maxY=" + maxY + ", minX=" + minX + ", maxX=" + maxX + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		/*
		 * 기본 아이디어
		 * y가 음수인 부분은 전부 버리고, y가 양수인 부분만 생각하기
		 * 세로축은 모두 생각하지 않고, 가로축을 기준으로만 생각함
		 * 모든 봉우리의 모습이 x와 평행한 가로선(y의 좌표: 해당 봉우리의 최댓값)이라고 생각했을 때,
		 * 해당 가로선 위에 다른 가로선 없다면 다른 봉우리에 의해 포함되지 않는 것
		 * 해당 가로선 아래에 다른 가로선 없다면 다른 봉우리를 포함하지 않는 것
		 * */
		
		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		
		int tmpX, tmpY, minX, maxX, maxY, preY, preX;
		//기본값
		tmpX = tmpY = minX = preY = Integer.MAX_VALUE;
		
		//가로선을 저장할 리스트
		ArrayList<Data> list = new ArrayList<>();
		
		//첫 입력을 받은 후 기본값으로 설정
		str = br.readLine().split(" ");
		preX = Integer.parseInt(str[0]);
		preY = Integer.parseInt(str[0]);
		minX = preX;
		maxX = preX;
		maxY = preY;
		
		//2개~n개까지 입력받기
		for(int i = 1; i < n; i++) {
			str = br.readLine().split(" ");
			tmpX = Integer.parseInt(str[0]);
			tmpY = Integer.parseInt(str[1]);
			
			//만약 이전 좌표와 현재 좌표 모두 y값이 음수일 때, 보지 않아도 되기 때문에 continue
			if(preY < 0 && tmpY < 0) {
				preY = tmpY;
				continue;
			}
			
			//선이 위에서 아래로 올라왔을 때 -> 봉우리가 시작될 때, 기본값 설정
			if(preY < 0 && tmpY > 0) {
				maxY = tmpY;
				minX = tmpX;
				maxX = tmpX;
				preY = tmpY;
				continue;
			}
			
			//선이 위에서 아래로 내려갈  때 -> 봉우리 끝남, 값 갱신 후 리스트에 넣기
			if(preY > 0 && tmpY < 0) {
				maxX = Math.max(maxX, tmpX);
				minX = Math.min(minX, tmpX);
				list.add(new Data(maxY, minX, maxX));
				preY = tmpY;
				continue;
			}
			
			//산이 이어질 때 -> 값 갱신
			if(preY > 0 && tmpY > 0) {
				//minX, maxX, maxY  조절
				maxY = Math.max(maxY, tmpY);
				maxX = Math.max(maxX, tmpX);
				minX = Math.min(minX, tmpX);
				preY = tmpY;
			}
		}
		
		//결과값 출력 확인을 위한 샘플 data와 디버깅을 위한 출력 
//		for(Data d : list) {
//			System.out.println(d);
//		}
//		list.add(new Data(4, -4, 4));
//		list.add(new Data(3, -3, -2));
//		list.add(new Data(2, 0, 2));
//		
		
//		//2중 for문 코드
//		int size = list.size();
//		boolean flagA, flagB;
//		int outA = 0;
//		int outB = 0;
//		Data tmp, now;
//		
//		//2중 for문을 돌며 기준 봉우리를 포함하는 것이 있는지, 포함하는 것이 없는지 출력
//		for(int i = 0; i < size; i++) {
//			//기준
//			now = list.get(i);
//			flagA = true;
//			flagB = true;
//			
//			//비교군
//			for(int k = 0; k < size; k++) {
//				if(i == k)
//					continue;
//				tmp = list.get(k);
//				
//				//포함되지 않는 - 위에꺼 없는 거 
//				//위에꺼 있으면 false
//				if(now.maxY <tmp.maxY && now.minX>tmp.minX && now.maxX<tmp.maxX) {
//					flagA = false;
//				}
//					
//				//포함되지 않는 - 밑에꺼 없는 거
//				if(now.maxY >tmp.maxY && now.minX<tmp.minX && now.maxX>tmp.maxX) {
//					flagB = false;
//				}
//				
//				if(!flagA && !flagB)
//					break;
//			}
//			if(flagA)
//				outA++;
//			if(flagB)
//				outB++;
//		}
		

		int size = list.size();
		//각 봉우리의 상태를 저장할 것
		//다른 봉우리에 포함되지 않는지
		boolean[] flagA = new boolean[size];
		//다른 봉우리를 포함하는지
		boolean[] flagB = new boolean[size];
		int outA = 0;
		int outB = 0;
		Data tmp, now;
		for(int i = 0; i < size; i++) {
			//기준
			now = list.get(i);
			
			//비교군 - i 전까지는 이미 탐색을 마쳤기 때문에, i 이후와만 비교하면 됨
			for(int k = i+1; k < size; k++) {
				if(flagA[k] && flagB[k])
					continue;
				
				tmp = list.get(k);
				
				//포함되지 않는 - 위에꺼 없는 거 
				//기준과 비교군의 상태는 반대이기 때문에 둘 다 플래그 설정
				if(now.maxY <tmp.maxY && now.minX>tmp.minX && now.maxX<tmp.maxX) {
					flagA[i] = true;
					flagB[k] = true;
				}
					
				//포함되지 않는 - 밑에꺼 없는 거
				if(now.maxY >tmp.maxY && now.minX<tmp.minX && now.maxX>tmp.maxX) {
					flagB[i] = true;
					flagA[k] = true;
				}
			}
			if(!flagA[i])
				outA++;
			if(!flagB[i])
				outB++;
		}
		System.out.println(outA + " " + outB);
	}
}
