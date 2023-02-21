import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 2565 전깃줄

public class BOJ2565 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0])+1;
		
		//전깃줄 위치 주의
		int[] arr = new int[501];
		int maxIdx = 0;
		for(int i = 1; i < n; i++) {
			str = br.readLine().split(" ");
			int tmp = Integer.parseInt(str[0]);
			arr[tmp] = Integer.parseInt(str[1]);
			maxIdx = Math.max(maxIdx, tmp);
		}
		
		//i보다 앞에 있는 것들 중/ 겹치지 않는 것 중 max
		int max = 1;
		int[] dp = new int[501];
		for(int i = 1; i < maxIdx+1; i++) {
			if(arr[i] == 0)
				continue;
			
			int tmpMax =1;
			for(int k = i-1; k > -1; k--) {
				if(arr[k] != 0 && arr[k] < arr[i]) {
					tmpMax = Math.max(tmpMax, dp[k] + 1);
				}
			}
			dp[i] = tmpMax;
			max = Math.max(max, tmpMax);
		}
		System.out.println(n - max - 1);
	}

}
