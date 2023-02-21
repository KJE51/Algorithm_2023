import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//백준 20058 마법사 상어

public class BOJ20058 {
	static int[][] arr;
	static int arrSize;

	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int q = Integer.parseInt(str[1]);
		n = (int) Math.pow(2, n);
		arrSize = n + 2;
		arr = new int[n + 2][n + 2];

		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < n; k++) {
				arr[i + 1][k + 1] = Integer.parseInt(str[k]);
			}
		}

		int turnSize, size;
		str = br.readLine().split(" ");
		for (int i = 0; i < q; i++) {
			turnSize = Integer.parseInt(str[i]);
			size = (int) Math.pow(2, turnSize);
			// 각 구역을 나눠서 한번씩 회전 연산을 돌림
			for (int k = 1; k <= n; k = k + size) {
				for (int t = 1; t <= n; t = t + size) {
					turn(k, t, size);
				}
			}
			// 회전 연산이 끝나면 얼음 지우기
			removeIce();
		}

		// 얼음 합과 덩어리 개수 출력
		boolean[][] visited = new boolean[n + 2][n + 2];
		int sum = 0;
		int max = 0; // 덩어리 수
		int[] di = { 0, 0, -1, +1 };
		int[] dk = { -1, +1, 0, 0 };

		// 칸을 하나씩 만나면서 덩어리를 만날 때마다 bfs
		for (int i = 1; i < n + 1; i++) {
			for (int k = 1; k < n + 1; k++) {
				if (arr[i][k] != 0 && visited[i][k] == false) {
					Queue<Integer> queI = new LinkedList<>();
					Queue<Integer> queK = new LinkedList<>();

					queI.add(i);
					queK.add(k);
					visited[i][k] = true;
					sum = sum + arr[i][k];
					// 덩어리 수 - 각 bfs마다 구하기
					int tmp = 1;

					while (!queI.isEmpty()) {
						int nowI = queI.poll();
						int nowK = queK.poll();

						for (int i1 = 0; i1 < 4; i1++) {
							// 얼음이 존재/방문한 적 없을 시
							if (arr[nowI + di[i1]][nowK + dk[i1]] != 0
									&& visited[nowI + di[i1]][nowK + dk[i1]] == false) {
								queI.add(nowI + di[i1]);
								queK.add(nowK + dk[i1]);
								sum = sum + arr[nowI + di[i1]][nowK + dk[i1]];
								tmp++;
								visited[nowI + di[i1]][nowK + dk[i1]] = true;
							}
						}
					}
					max = Math.max(max, tmp);
				}
			}
		}
		System.out.println(sum);
		System.out.println(max);

	}

	private static void removeIce() {
		// 없애야 할 얼음을 체크한 뒤 한번에 없애기
		boolean brr[][] = new boolean[arrSize][arrSize];
		int[] di = { 0, 0, -1, +1 };
		int[] dk = { -1, +1, 0, 0 };
		for (int i = 1; i < arrSize - 1; i++) {
			for (int k = 1; k < arrSize - 1; k++) {
				if (arr[i][k] == 0)
					continue;
				int tmp = 0;
				for (int idx = 0; idx < 4; idx++) {
					if (arr[i + di[idx]][k + dk[idx]] != 0)
						tmp++;
				}
				if (tmp < 3) {
					brr[i][k] = true;
				}
			}
		}

		for (int i = 1; i < arrSize - 1; i++) {
			for (int k = 1; k < arrSize - 1; k++) {
				if (brr[i][k] == true)
					arr[i][k] = arr[i][k] - 1;
			}
		}
	}

	private static void turn(int idxI, int idxK, int size) {
		if (size < 1) {
			return;
		}
		Queue<Integer> que = new LinkedList<>();

		// 아래
		for (int k = idxK; k < idxK + size - 1; k++) {
			que.add(arr[idxI + size - 1][k]);
		}

		// 오른쪽
		for (int i = idxI + size - 1; i > idxI; i--) {
			que.add(arr[i][idxK + size - 1]);
		}

		// 위
		for (int k = idxK + size - 1; k > idxK; k--) {
			que.add(arr[idxI][k]);
		}

		// 왼쪽
		for (int i = idxI; i < idxI + size - 1; i++) {
			que.add(arr[i][idxK]);
		}

		// 배열 채우기
		// 왼쪽
		for (int i = idxI; i < idxI + size - 1; i++) {
			arr[i][idxK] = que.poll();
		}
		// 아래
		for (int k = idxK; k < idxK + size - 1; k++) {
			arr[idxI + size - 1][k] = que.poll();
		}
		// 오른쪽
		for (int i = idxI + size - 1; i > idxI; i--) {
			arr[i][idxK + size - 1] = que.poll();
		}
		// 위
		for (int k = idxK + size - 1; k > idxK; k--) {
			arr[idxI][k] = que.poll();
		}
		turn(idxI + 1, idxK + 1, size - 2);
	}

}
