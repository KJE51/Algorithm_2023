import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1520 {
	static int sizeI, sizeK, arr[][], dp[][];
	static int[] di = { 0, 0, 1, -1 };
	static int[] dk = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		sizeI = Integer.parseInt(str[0]);
		sizeK = Integer.parseInt(str[1]);
		arr = new int[sizeI][sizeK];
		dp = new int[sizeI][sizeK];

		for (int i = 0; i < sizeI; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < sizeK; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
			}
		}
		boolean[][] visited = new boolean[sizeI][sizeK];
		visited[0][0] = true;
		dfs(0, 0, visited);
		
//		for(int i = 0; i < sizeI; i++) {
//			for(int k = 0; k < sizeK; k++) {
//				System.out.print(dp[i][k] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(dp[0][0]);
	}

	private static int dfs(int i, int k, boolean[][] visited) {
		int nextI, nextK, tmp = 0;
		//끝까지 도착했을 때
		if(i == sizeI-1 && k == sizeK-1) {
			return dp[i][k] = 1;
		}
		
		for (int d = 0; d < 4; d++) {
			nextI = i + di[d];
			nextK = k + dk[d];
			// 범위 벗어나거나 방문한 적 있을 때
			if (nextI < 0 ||nextK < 0 || nextI > sizeI - 1 || nextK > sizeK - 1 || visited[nextI][nextK]) {
				continue;
			}
			
			//이미 방문한 적 있을 때
			if(dp[nextI][nextK]!= 0) {
				dp[i][k] += dp[nextI][nextK];
				continue;
			}
			
			//내리막일 때만 방문 가능
			if (arr[nextI][nextK] < arr[i][k]) {
				visited[nextI][nextK] = true;
				dp[i][k] += dfs(nextI, nextK, visited);
				visited[nextI][nextK] = false;
			}
		}
		return dp[i][k];
	}

}
