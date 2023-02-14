import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//백준 3190 뱀

public class Snake {

	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		str = br.readLine().split(" ");
		int k = Integer.parseInt(str[0]);
		
		//2 : apple, 1 : 뱀, 0 : 빈칸
		int[][] board = new int[n][n];
		board[0][0] = 1;
		//방향 - true : 오른쪽
		boolean[] directionChange;
		int[] sec;
		
		//입력
		for(int i = 0; i < k; i++) {
			//사과 입력
			str = br.readLine().split(" ");
			board[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1] = 2;
		}
		//시간별로 변하는 방향 저장
		str = br.readLine().split(" ");
		int l = Integer.parseInt(str[0]);
		sec = new int[l];
		directionChange = new boolean[l];
		for(int i = 0; i < l; i++) {
			str = br.readLine().split(" ");
			sec[i] = Integer.parseInt(str[0]);
			if(str[1].equals("D"))
				directionChange[i] = true;
		}
		
		int time = 1;
		// %4, 오른쪽(true)면 +1, 왼쪽이면 -1, / -1나오면 3으로 바꾸기 
		int direction = 0;
		//현재 뱀 위치 저장할 deque
		Deque<Integer> xs = new ArrayDeque<Integer>();
		Deque<Integer> ys = new ArrayDeque<Integer>();
		xs.add(0);
		ys.add(0);
		
		int newHeadX;
		int newHeadY;
		//오른쪽, 아래, 왼쪽, 위 - 0 1 2 3
		int directionIdx = 0;
		int[] arrX = {0, +1, 0, -1};
		int[] arrY = {+1, 0, -1, 0};
		//진행
		while(true) {
			//가장 상위값(머리) 위치 확인 후 인덱스 옮기기
			newHeadX = xs.peek() + arrX[direction];
			newHeadY = ys.peek() + arrY[direction];
			
			//인덱스 벗어나는지 확인(부딪히는지)
			if(newHeadX >=n || newHeadX < 0 ||newHeadY<0 || newHeadY>=n || board[newHeadX][newHeadY] ==1) {
				break;
			}
			
			//사과가 있다면 꼬리 꺼내지 않고 그대로 진행, 아니라면 꼬리 꺼내기
			if(board[newHeadX][newHeadY] != 2) {
				board[xs.peekLast()][ys.peekLast()] = 0;
				xs.pollLast();
				ys.pollLast();
			}
			
			//부딪히는지 - 새로운 머리쪽만 보면 됨
			//부딪히면 break
			if(board[newHeadX][newHeadY] ==1) {
				break;
			}
			//부딪히지 않으면 머리 추가
			else {
				xs.addFirst(newHeadX);
				ys.addFirst(newHeadY);
				board[newHeadX][newHeadY] = 1;
			}
			
			//방향 확인
			if(directionIdx < sec.length && sec[directionIdx] == time) {
				//오른쪽일 때, +1
				if(directionChange[directionIdx]) {
					direction = (direction + 1) % 4;
				}else {
					direction -= 1;
					if(direction == -1)
						direction = 3;
				}
				directionIdx++;
			}
			time++;
		}
		System.out.println(time);
	}

}
