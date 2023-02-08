import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Easy2048 {
static int[][] arr;
static int n;
static int max = -1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for(int k = 0; i < n; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
				max = Math.max(max, arr[i][k]);
			}
		}
		
		dfs(0, arr);
		System.out.println(max);
		
	}
	
	private static void dfs(int i, int[][] arr2) {
		int 
	//사방향 탐색
	//기저조건  : i가 5면 나가기
		
	}
	
	//왼쪽, dfs 보내기
	static void leftDfs(int i, int[][] arr2) {
		
		dfs(i, arr3);
	}

	
}
