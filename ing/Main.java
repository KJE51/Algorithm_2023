import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int[][] arr;
	static boolean[] visited;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// 무조건 0번 인덱스의 사람은 존재한다고 생각하고 탐색
		// 1~N까지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
		arr = new int[n][n];
		visited = new boolean[n];
		visited[0] = true;
        

		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			for (int k = 0; k < 6; k++) {
				arr[i][k] = Integer.parseInt(temp[k]);
			}
		}

		dfs(1);
		System.out.println(ans);

	}

	static int dfs(int cnt) {
		if (ans == 0)
			return -1;

		if (cnt == n / 2) {
			cnt();
		} else {
			for(int i = 1; i < n; i++) {
				if(visited[i] == false) {
					visited[i] = true;
					dfs(cnt+1);
					visited[i] = false;
				}
			}
		}
		return 0;
	}

	static void cnt() {
		int team1 = 0;
		int team2 = 0;
		
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				if (i != k && visited[i] == visited[k]) {
					if(visited[i] == true)
						team1 += arr[i][k];
					else
						team2 += arr[i][k];
				}
			}
		}
		team1 = Math.abs(team1 - team2);
		ans = Math.min(ans, team1);
	}

}
