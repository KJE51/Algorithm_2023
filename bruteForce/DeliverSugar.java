import java.util.Scanner;

public class DeliverSugar {
//2839
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
	
		//봉지의 갯수 저장
		int[] arr = new int[n+1];
		arr[3] = 1;
		arr[5] = 1;
		
		for(int i = 6; i < n+1; i++) {
			//math.min
			if(arr[i-3] == 0 && arr[i-5] == 0)
				arr[i] = 0;
			else if(arr[i-3] == 0)
				arr[i] = arr[i-5] +1;
			else if(arr[i-5] == 0)
				arr[i] = arr[i-3] + 1;
			else
				arr[i] = Math.min(arr[i-3], arr[i-5]) + 1;
		}
		if(arr[n] == 0) {
			System.out.println(-1);
		}else
			System.out.println(arr[n]);
	}

}
