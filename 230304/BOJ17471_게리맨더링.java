import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ17471 {
	static int min = Integer.MAX_VALUE;
	static int n;
	static int n1;
	static int[] nums;
	static boolean[][] graph;
	// 팀 정할때
	static boolean[] team;
	// 팀들 dfs 여부 판단
	static boolean[] visited1;
	static boolean[] visited2;
	static boolean flag1;
	static boolean flag2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		n1 = n + 1;
		// 인구수 저장
		nums = new int[n1];
		str = br.readLine().split(" ");
		for (int i = 1; i < n1; i++) {
			nums[i] = Integer.parseInt(str[i - 1]);
		}

		// 행렬로 입력받기
		graph = new boolean[n1][n1];
		int num, tmp;
		for (int i = 1; i < n1; i++) {
			str = br.readLine().split(" ");
			num = Integer.parseInt(str[0]) + 1;
			for (int k = 1; k < num; k++) {
				tmp = Integer.parseInt(str[k]);
				graph[i][tmp] = true;
				graph[tmp][i] = true;
			}
		}

		//1번은 무조건 팀이라고 생각하기
		team = new boolean[n1];
		team[1] = true;
		// 시작할 인덱스, 현재 팀원(true)
		makeTeam(2, 1);

		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	private static void makeTeam(int idx, int cnt) {
		//팀이 다 정해졌을 때
		if (idx == n + 1) {
			flag1 = false;
			flag2 = false;
			// dfs 두번 돌리기
			for (int i = 2; i < n1; i++) {
				if (!team[i]) {
					bfsF(i);
					break;
				}
			}
			bfsT(1);
			
			//그래프로 연결될 수 있을 때 값 갱신
			if (flag1 && flag2) {
				int sum1 = 0, sum2 = 0;
				for (int i = 1; i < n1; i++) {
					if (team[i])
						sum1 += nums[i];
					else
						sum2 += nums[i];
				}
				min = Math.min(Math.abs(sum1 - sum2), min);
			}
			return;
		}

		// 선택/선택 안함으로 팀 만들기
		makeTeam(idx + 1, cnt);
		team[idx] = true;
		makeTeam(idx + 1, cnt + 1);
		team[idx] = false;
	}

	//이렇게 안 나누고 그냥 boolean 매개변수로 두고 하나로 만들어도 됐을거같긴함,,,
	static void bfsF(int idx) {
		//bfs를 통해서 다 돌았는지 확인
		visited1 = new boolean[n1];
		visited1[idx] = true;
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(idx);
		int tmp;
		while (!que.isEmpty()) {
			tmp = que.poll();
			for (int i = 1; i < n1; i++) {
				if (graph[tmp][i] && !visited1[i] && !team[i]) {
					visited1[i] = true;
					que.add(i);
				}
			}
		}
		flag1 = true;
		for (int i = 1; i < n1; i++) {
			if (team[i] == false) {
				if (visited1[i] == false) {
					flag1 = false;
					break;
				}
			}
		}
	}

	static void bfsT(int idx) {
		visited1 = new boolean[n1];
		visited1[idx] = true;
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(idx);
		int tmp;
		while (!que.isEmpty()) {
			tmp = que.poll();
			for (int i = 1; i < n1; i++) {
				if (graph[tmp][i] && !visited1[i] && team[i]) {
					visited1[i] = true;
					que.add(i);
				}
			}
		}
		flag2 = true;
		for (int i = 1; i < n1; i++) {
			if (team[i] == true) {
				if (visited1[i] == false) {
					flag2 = false;
					break;
				}
			}
		}
	}
}