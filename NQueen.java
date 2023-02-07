import java.util.Scanner;
//N-Queen 9663
public class NQueen {
	//배열로 전달해서 풀기
	//arr의 인덱스가 세로, arr[i]가 가로? 였던듯?
	static int[] arr;
	static int n;
	static int ans = 0;

	public static void main(String[] args) {
		//입력
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		
		dfs(0);
		System.out.println(ans);
		
	}
	
	static void dfs(int i) {
		if(i == n) {
			ans++;
			return;
		}
		for(int k = 0; k < n;k++) {
			//세로 겹치거나 대각선일 때 불가능
			boolean flag = true;
			for(int t = 0; t < i; t++) {
				//세로로 겹칠 때
				if(arr[t] == k) {
					flag = false;
					break;
				}
				//대각선으로 겹칠 때
				if(Math.abs(arr[t]-k) == Math.abs(t-i)) {
					flag = false;
					break;
				}
			}
			//queen을 놓을 수 있을 때
			if(flag == true) {
				arr[i] = k;
				dfs(i + 1);
				arr[i] = 0;
			}
		}
	}
	

}
