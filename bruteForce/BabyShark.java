import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

//먹은 물고기의 위치를 저장할 객체, 걸린 시간
class Xys {
	public Xys(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

	int x;
	int y;
	int cnt;
}

public class BabyShark {
	static int n;
	// 상어 위치는 그냥 0으로 둠
	static int[][] arr;
	static int sharkSize = 2;
	static int sharkEats = 0;
	static int ansCnt = 0;

	// 0:지나갈 수 있음, 1:먹을 수 있음, -1:지나갈 수 없음, -2:이미 탐색
	static int[][] moves;
	// 상어 위치
	static int x, y;
	static int sec = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);

		arr = new int[n][n];
		moves = new int[n][n];

		//초기값 입력받아서 세우기
		for (int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			for (int k = 0; k < n; k++) {
				arr[i][k] = Integer.parseInt(input[k]);
				if (arr[i][k] == 9) {
					x = i;
					y = k;
					arr[i][k] = 0;
				}
			}
		}

		while (true) {
			// 길 만들기
			makeStreet();

			//가장 가까운 물고기 구하기, 출력 -1일 시 종료
			if (bfs() == -1) {
				break;
			}
		}
		System.out.println(sec);
	}

	//물고기 값을 비교해 상어와 같거나 없을 시는 0, 작을 때는 1, 클 때는 -1
	// 갈 수 있음 / 먹을 수 있음 / 갈 수 없음
	static void makeStreet() {
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				if (arr[i][k] == 0 || arr[i][k] == sharkSize) {
					moves[i][k] = 0;
				} else if (arr[i][k] > sharkSize) {
					moves[i][k] = -1;
				} else {
					moves[i][k] = 1;
				}
			}
		}
	}

	static int bfs() {
		ArrayDeque<Xys> que = new ArrayDeque<>();
		// 리스트없이 변수로 구현 가능하긴 할 듯
		ArrayList<Xys> list = new ArrayList<>();
		//사방탐색 값
		int[] xs = { 1, -1, 0, 0 };
		int[] ys = { 0, 0, 1, -1 };
		int cnt = 1;
		int cntAns = Integer.MAX_VALUE;
		boolean flag = false;

		// 현재 상어의 위치에 대해 탐색
		for (int i = 0; i < 4; i++) {
			if (x + xs[i] < 0 || x + xs[i] > n - 1 || y + ys[i] < 0 || y + ys[i] > n - 1) {
				continue;
			}
			if (moves[x + xs[i]][y + ys[i]] == 0) {
				que.add(new Xys(x + xs[i], y + ys[i], cnt));
				moves[x + xs[i]][y + ys[i]] = -1;
			} else if (moves[x + xs[i]][y + ys[i]] == 1) {
				list.add(new Xys(x + xs[i], y + ys[i], cnt));
				flag = true;
				moves[x + xs[i]][y + ys[i]] = -1;
			}
		}

		cnt++;
		//que가 비거나 물고기를 먹을 때까지 사방 탐색
		while (!que.isEmpty() && flag == false) {

			Xys tmp = que.poll();
			int tmpX = tmp.x;
			int tmpY = tmp.y;

			for (int i = 0; i < 4; i++) {
				// 패스
				if (tmpX + xs[i] < 0 || tmpX + xs[i] > n - 1 || tmpY + ys[i] < 0 || tmpY + ys[i] > n - 1) {
					continue;
				}
				// 길 지나기
				if (moves[tmpX + xs[i]][tmpY + ys[i]] == 0 && cntAns > tmp.cnt) {
					que.add(new Xys(tmpX + xs[i], tmpY + ys[i], tmp.cnt + 1));
					moves[tmpX + xs[i]][tmpY + ys[i]] = -1;
				}

				else if (moves[tmpX + xs[i]][tmpY + ys[i]] == 1) {
					list.add(new Xys(tmpX + xs[i], tmpY + ys[i], tmp.cnt + 1));
					flag = true;
					moves[tmpX + xs[i]][tmpY + ys[i]] = -1;

				}
			}
		}
		
		// 답이 없을 시 -1
		if (flag == false) {
			return -1;
		}

		// 상어가 뭔가를 먹었을 때
		// que 비워주기. (같은 초일 때 여러마리를 먹을 수 있기 때문에)
		else {
			while (!que.isEmpty()) {
				Xys tmp;
				tmp = que.poll();
				int tmpX = tmp.x;
				int tmpY = tmp.y;
				
				// 같은 level이 아닐 때
				if (tmp.cnt != list.get(0).cnt - 1) {
					continue;
				}
				//현재 걸린 시간과 같은 시간 내의 물고기일 때
				for (int i = 0; i < 4; i++) {
					if (tmpX + xs[i] < 0 || tmpX + xs[i] > n - 1 || tmpY + ys[i] < 0 || tmpY + ys[i] > n - 1) {
						continue;
					}
					if (moves[tmpX + xs[i]][tmpY + ys[i]] == 1) {
						list.add(new Xys(tmpX + xs[i], tmpY + ys[i], tmp.cnt + 1));
						moves[tmpX + xs[i]][tmpY + ys[i]] = -1;
					}
				}
			}

			//먹은 것 중 최댓값 
			Xys eat = list.get(0);
			for (int i = 1; i < list.size(); i++) {
				if (eat.x > list.get(i).x) {
					eat = list.get(i);
				} else if (eat.x == list.get(i).x) {
					if (eat.y > list.get(i).y) {
						eat = list.get(i);
					}
				}
			}

			//상어가 먹은 수, 위치 등 좌표 조절
			sharkEats++;
			sec += eat.cnt;
			x = eat.x;
			y = eat.y;
			arr[x][y] = 0;

			if (sharkEats == sharkSize) {
				sharkSize++;
				sharkEats = 0;
			}
			return 0;
		}
	}
}
