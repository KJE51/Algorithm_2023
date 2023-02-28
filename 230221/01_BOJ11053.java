import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 11053 - 가장 긴 증가하는 부분 수열

public class BOJ_11053 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		str = br.readLine().split(" ");
		
		int[] arr = new int[n+1];
		int[] ans = new int[n+1];
		int totalMax = 0;
		int tmpMax = -1;
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(str[i-1]);
		}
		
		//자기보다 앞에 있는 / 작은 수 중에 가장 큰 값 + 1 넣기
		for(int i = 1; i <= n; i++) {
			tmpMax = 0;
			for(int k = 0; k <= i; k++) {
				//
				if(arr[i] >arr[k]){
					tmpMax = Math.max(tmpMax, ans[k]);
				}
			}
			ans[i] = tmpMax + 1;
			totalMax =  Math.max(tmpMax+1, totalMax);
		}
		System.out.println(totalMax);
	}
}
