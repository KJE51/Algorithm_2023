import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ21609 {
	static class Data implements Comparable<Data> {
		int i;
		int k;
		int cnt;
		int mu;

		public Data(int i, int k, int cnt, int mu) {
			super();
			this.i = i;
			this.k = k;
			this.cnt = cnt;
			this.mu = mu;
		}

		@Override
		public int compareTo(Data o) {
			if (this.cnt == o.cnt) {
				if (this.mu == o.mu) {
					if (this.i == o.i) {
						return -(this.k - o.k);
					}
					return -(this.i - o.i);
				}
				return o.mu - this.mu;
			}
			return o.cnt - this.cnt;
		}
	}

	static int ans = 0;
	static int[][] arr;
	static int[][] brr;
	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		arr = new int[n][n];
		brr = new int[n][n];
		
		//배열 입력받기
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < n; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
			}
		}


		//수행할 수 없을 때까지 실행
		boolean flag = true;
		while (flag) {
			flag = grouping();
			if (flag) {

				gravity();
				rotate();
				gravity();
			}
		}

		System.out.println(ans);
	}
	
	//배열 돌리기
	private static void rotate() {
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				brr[i][k] = arr[i][k];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				arr[i][k] = brr[k][n - i - 1];
			}
		}
	}

	// 공백 : -2로 채우기
	// arr을 그대로 바꾸기
	private static void gravity() {
		ArrayList<Integer> list = new ArrayList<>();
		int idx;
		// 배열 복사
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				brr[i][k] = arr[i][k];
			}
		}
		
		for (int k = 0; k < n; k++) {
			list.clear();
			idx = n - 1;
			for (int i = n - 1; i > -1; i--) {
				//-1 만나면 
				if (brr[i][k] == -1) {
					for (int t = 0; t < list.size(); t++) {
						arr[idx - t][k] = list.get(t);
					}
					list.clear();
					idx = i - 1;
				} else if (brr[i][k] == -2) {

				} else {
					list.add(arr[i][k]);
					arr[i][k] = -2;
				}
			}
			for (int t = 0; t < list.size(); t++) {
				arr[idx - t][k] = list.get(t);
			}
		}
	}

	private static boolean grouping() {
		// 색 있는 거 visited (기준 블록 정하기를 위한)
		boolean[][] visited = new boolean[n][n];
		// bfs 돌때 visited
		boolean[][] visited0 = new boolean[n][n];
		// 가장 큰 그룹 뽑기
		PriorityQueue<Data> que = new PriorityQueue<>();
		// i, k값 저장
		Queue<Data> tmpQue = new LinkedList<Data>();
		int tmp, mu, nowColor, tmpI = 0, tmpK = 0;
		Data tmpData;
		int[] di = { 0, 0, -1, 1 };
		int[] dk = { -1, 1, 0, 0 };

		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				// 빈칸, 무지개, 검은 블럭일 때
				if (arr[i][k] == -1 || arr[i][k] == -2 || arr[i][k] == 0) {
					continue;
				}
				// 해당 색상이 탐색되지 않았을 때
				if (!visited[i][k]) {
					visited[i][k] = true;
					tmp = 1;
					tmpQue.add(new Data(i, k, 0, 0));
					nowColor = arr[i][k];
					visited0 = new boolean[n][n];
					visited0[i][k] = true;
					mu = 0;
					// bfs
					while (!tmpQue.isEmpty()) {
						tmpData = tmpQue.poll();
						for (int d = 0; d < 4; d++) {
							tmpI = tmpData.i + di[d];
							tmpK = tmpData.k + dk[d];

							// 범위 벗어나거나 이미 탐색했을 때
							if (tmpI < 0 || tmpI >= n || tmpK < 0 || tmpK >= n || visited[tmpI][tmpK]
									|| visited0[tmpI][tmpK]) {
								continue;
							}
							// 무지개 블럭
							if (arr[tmpI][tmpK] == 0) {
								tmp++;
								mu++;
								visited0[tmpI][tmpK] = true;
								tmpQue.add(new Data(tmpI, tmpK, 0, 0));
							//일반 블럭일 때
							} else if (arr[tmpI][tmpK] == nowColor) {
								tmp++;
								tmpQue.add(new Data(tmpI, tmpK, 0, 0));
								visited[tmpI][tmpK] = true;
								visited0[tmpI][tmpK] = true;
							}
						}
					}
					// 그룹핑이 된 블럭 수가 2 이상일 때 큐에 넣기
					if (tmp > 1) {
						que.add(new Data(i, k, tmp, mu));
					}
				}
			}
		}
		
		//그룹이 없을 때 
		if (que.isEmpty()) {
			return false;
		}

		//그룹이 있을 때 다시 bfs 후 배열 공백(-2)로 채우기
		tmpQue.clear();
		tmpData = que.peek();
		tmpQue.add(tmpData);
		nowColor = arr[tmpData.i][tmpData.k];
		que.clear();
		arr[tmpData.i][tmpData.k] = -2;
		ans = ans + tmpData.cnt * tmpData.cnt;
		visited0 = new boolean[n][n];
		visited0[tmpData.i][tmpData.k] = true;

		while (!tmpQue.isEmpty()) {
			tmpData = tmpQue.poll();
			for (int d = 0; d < 4; d++) {
				tmpI = tmpData.i + di[d];
				tmpK = tmpData.k + dk[d];

				// 범위 벗어나거나 이미 탐색했을 때
				if (tmpI < 0 || tmpI >= n || tmpK < 0 || tmpK >= n || visited0[tmpI][tmpK]) {
					continue;
				}
				// 그룹에 블럭 더하기
				if (arr[tmpI][tmpK] == 0 || arr[tmpI][tmpK] == nowColor) {
					visited0[tmpI][tmpK] = true;
					arr[tmpI][tmpK] = -2;
					tmpQue.add(new Data(tmpI, tmpK, 0, 0));
				}
			}
		}
		return true;
	}
}