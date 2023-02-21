import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 9251 LCS

public class BOJ9251 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		//긴 게 str1
		if(str1.length() < str2.length()) {
			String temp = str1;
			str1 = str2;
			str2 = temp;
		}
		
		int n = str1.length() + 1;
		int m = str2.length() + 1;
		int[][] dp = new int[n][m];
		
		//앞에서부터 하나씩 겹치는 거 / 
		for(int i = 1; i < n; i++) {
			for(int k = 1; k < m; k++) {
				if(str1.charAt(i-1) == str2.charAt(k-1)) {
					dp[i][k] = dp[i-1][k-1] + 1;
				}
				else {
					dp[i][k] = Math.max(dp[i-1][k], dp[i][k-1]);
				}
			}
		}
		
		System.out.println(dp[n-1][m-1]);
	}

}
