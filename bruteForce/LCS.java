import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//9251
public class LCS {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str1 = bf.readLine();
		String str2 = bf.readLine();
		
		//A : 65 ~ Z : 90 / 26개
		int[][] arr = new int[str1.length()+1][str2.length()+1];

		for(int i = 1; i < str1.length()+1;i++) {
			for(int k = 1; k < str2.length()+1;k++) {
				
				if(str1.charAt(i-1) == str2.charAt(k-1)) {
					arr[i][k] = arr[i-1][k-1] + 1;
				}else {
					arr[i][k] = Math.max(arr[i-1][k], arr[i][k-1]);
				}
			}
		}
//		for(int i = 0; i < str1.length();i++) {
//			for(int k = 0; k < str2.length();k++) {
//				System.out.printf("%3d ", arr[i][k]);
//			}
//			System.out.println();}
		System.out.println(arr[str1.length()][str2.length()]);
	}
}
