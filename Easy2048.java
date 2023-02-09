import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//12100
//2048 easy
public class Easy2048 {
	static int[][] arr;
	static int n;
	static int max = -1;
	//주석처리
	static final int FL = 0;
	static int allCount = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int k = 0; k < n; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
				max = Math.max(max, arr[i][k]);
			}
		}

		dfs(0, arr);
		System.out.println(max);

	}

	private static void dfs(int cnt, int[][] arr2) {
		// 기저조건 : i가 5면 나가기
		if (cnt == 5) {
			return;
		}
		// 사방향 탐색
		leftDfs(cnt, arr2);
		rightDfs(cnt, arr2);
		upDfs(cnt, arr2);
		downDfs(cnt, arr2);

	}

	// 왼쪽, dfs 보내기
	static void leftDfs(int cnt, int[][] arr2) {
		int[][] arr3 = new int[n][n];
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			// 각 줄마다 하나씩 넣기
			for (int k = 0; k < n; k++) {
				if (arr2[i][k] != 0) {
					que.add(arr2[i][k]);
				}
			}

			int idx = 0;
			while (!que.isEmpty()) {
				int tmp = que.poll();
				// 합쳐질 수 있을 때
				if (!que.isEmpty() && tmp == que.peek()) {
					arr3[i][idx] = tmp * 2;
					que.poll();
					idx++;
					max = Math.max(tmp * 2, max);
				} else {
					arr3[i][idx] = tmp;
					idx++;
				}
			}
		}
		
		if(FL == 1) {
			System.out.println(allCount);
			allCount++;
		System.out.println("이전 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr2[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println(" 왼쪽 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr3[i][k] + " ");
			}
			System.out.println();
		}
		
	}
		if(!arr2.equals(arr3))
			dfs(cnt + 1, arr3);
	}

	// 오른쪽, dfs 보내기
	static void rightDfs(int cnt, int[][] arr2) {
		int[][] arr3 = new int[n][n];
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			// 각 줄마다 하나씩 넣기
			for (int k = n - 1; k > -1; k--) {
				if (arr2[i][k] != 0) {
					que.add(arr2[i][k]);
				}
			}

			// 오른쪽부터 채우기
			int idx = n - 1;
			while (!que.isEmpty()) {
				int tmp = que.poll();
				// 합쳐질 수 있을 때
				if (!que.isEmpty() && tmp == que.peek()) {
					arr3[i][idx] = tmp * 2;
					que.poll();
					idx--;
					max = Math.max(tmp * 2, max);
				} else {
					arr3[i][idx] = tmp;
					idx--;
				}
			}
		}
		if(FL == 1) {
			System.out.println(allCount);
			allCount++;
		System.out.println("이전 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr2[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println(" 오른쪽 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr3[i][k] + " ");
			}
			System.out.println();
		}}
		if(!arr2.equals(arr3))
			dfs(cnt + 1, arr3);
	}

	//위
	static void upDfs(int cnt, int[][] arr2) {
		int[][] arr3 = new int[n][n];
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			// 각 줄마다 하나씩 넣기
			for (int k =0; k <n; k++) {
				if (arr2[k][i] != 0) {
					que.add(arr2[k][i]);
				}
			}

			// 위부터 채우기
			int idx = 0;
			while (!que.isEmpty()) {
				int tmp = que.poll();
				// 합쳐질 수 있을 때
				if (!que.isEmpty() && tmp == que.peek()) {
					arr3[idx][i] = tmp * 2;
					que.poll();
					idx++;
					max = Math.max(tmp * 2, max);
				} else {
					arr3[idx][i] = tmp;
					idx++;
				}
			}
		}
		if(FL == 1) {
			System.out.println(allCount);
			allCount++;
		System.out.println("이전 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr2[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println(" 위쪽 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr3[i][k] + " ");
			}
			System.out.println();
		}}
		if(!arr2.equals(arr3))
			dfs(cnt + 1, arr3);
	}

	//아래
	static void downDfs(int cnt, int[][] arr2) {
		int[][] arr3 = new int[n][n];
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			// 각 줄마다 하나씩 넣기
			for (int k =n-1; k >-1; k--) {
				if (arr2[k][i] != 0) {
					que.add(arr2[k][i]);
				}
			}

			// 아래부터 채우기
			int idx = n-1;
			while (!que.isEmpty()) {
				int tmp = que.poll();
				// 합쳐질 수 있을 때
				if (!que.isEmpty() && tmp == que.peek()) {
					arr3[idx][i] = tmp * 2;
					que.poll();
					idx--;
					max = Math.max(tmp * 2, max);
				} else {
					arr3[idx][i] = tmp;
					idx--;
				}
			}
		}
		if(FL == 1) {
			System.out.println(allCount);
			allCount++;
		System.out.println("이전 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr2[i][k] + " ");
			}
			System.out.println();
		}
		System.out.println("아래쪽 : ");
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				System.out.print(arr3[i][k] + " ");
			}
			System.out.println();
		}}
		if(!arr2.equals(arr3))
			dfs(cnt + 1, arr3);
	}
}
