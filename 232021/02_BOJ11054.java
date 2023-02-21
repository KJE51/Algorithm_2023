import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11054 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		str = br.readLine().split(" ");
		
		int[] arr = new int[n+1];
		int[] ans = new int[n+1];
		int[] ans1 = new int[n+1];
		int totalMax = 0;
		int tmpMax = -1;
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(str[i-1]);
		}
		
		//앞에서부터 
		for(int i = 1; i <= n; i++) {
			tmpMax = 0;
			for(int k = 0; k <= i; k++) {
				//
				if(arr[i] >arr[k]){
					tmpMax = Math.max(tmpMax, ans[k]);
				}
			}
			ans[i] = tmpMax + 1;
		}
		
		//뒤에서부터
		for(int i = n; i >0; i--) {
			tmpMax = 0;
			for(int k = n; k >=i; k--) {
				if(arr[i] >arr[k]){
					tmpMax = Math.max(tmpMax, ans1[k]);
				}
			}
			ans1[i] = tmpMax + 1;
		}
		
		totalMax = 0;
		for(int i = 1; i <=n;i++) {
			totalMax = Math.max(totalMax, ans[i]+ans1[i]);
		}
		System.out.println(totalMax-1);
	}

}
