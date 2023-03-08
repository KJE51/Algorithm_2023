import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17144 {

	// 미세먼지 map
	static int[][] arr;
	// 미세먼지 퍼지는 거 체크를 위한 배열
	static int[][] tmpArr;
	static int sizeI, sizeK, miK, miIup, miIdown, t;

	public static void main(String[] args) throws IOException {
		miIup = miIdown = miK = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		sizeI = Integer.parseInt(str[0]);
		sizeK = Integer.parseInt(str[1]);
		t = Integer.parseInt(str[2]);

		arr = new int[sizeI][sizeK];
		
		for (int i = 0; i < sizeI; i++) {
			str = br.readLine().split(" ");
			for (int k = 0; k < sizeK; k++) {
				arr[i][k] = Integer.parseInt(str[k]);
				if (arr[i][k] == -1) {
					if (miK == -1) {
						miK = k;
						miIup = i;
					} else
						miIdown = i;
				}
			}
		}
		while (t-- > 0) {
			diffusion();
			air(miIup, miIdown);
		}
		System.out.println(cal());

	}

	//배열 돌리기
	private static void air(int ai1, int ai2) {
		// 위 공기청정기
		// 왼쪽
		for (int i = ai1 - 1; i > 0; i--) {
			arr[i][0] = arr[i - 1][0];
		}
		// 위쪽
		for (int k = 0; k < sizeK - 1; k++) {
			arr[0][k] = arr[0][k + 1];
		}
		// 오른쪽
		for (int i = 0; i <ai1; i++) {
			arr[i][sizeK - 1] = arr[i + 1][sizeK - 1];
		}
		// 아래쪽
		for (int k = sizeK - 1; k > 1; k--) {
			arr[ai1][k] = arr[ai1][k - 1];
		}
		arr[ai1][1] = 0;

		// 아래 공기청정기
		// 왼쪽
		for (int i = ai2 + 1; i < sizeI - 1; i++) {
			arr[i][0] = arr[i + 1][0];
		}
		// 아래쪽
		for (int k = 0; k < sizeK - 1; k++) {
			arr[sizeI - 1][k] = arr[sizeI - 1][k + 1];
		}
		// 오른쪽
		for (int i = sizeI - 1; i > ai2; i--) {
			arr[i][sizeK - 1] = arr[i - 1][sizeK - 1];
		}
		// 위쪽
		for (int k = sizeK-1; k >1; k--) {
			arr[ai2][k] = arr[ai2][k - 1];
		}
		arr[ai2][1] = 0;
	}

	private static void diffusion() {
		int[] di = {0, 0, 1, -1};
		int[] dk = {1, -1, 0, 0};
		int tmp, cnt;
		tmpArr = new int[sizeI][sizeK];
		for(int i = 0; i < sizeI; i++) {
			for(int k = 0; k < sizeK; k++) {
				if(arr[i][k] == 0 && arr[i][k] == -1)
					continue;
				
				//사방탐색, 더하기
				cnt = 0;
				for(int d = 0; d < 4; d++) {
					//범위 내, -1 아님
					if(di[d]+i > -1 &&di[d]+i <sizeI&&dk[d]+k>-1&&dk[d]+k<sizeK&& arr[di[d]+i][dk[d]+k]!=-1) {
						cnt++;
					}
				}
				tmp = arr[i][k] / 5;
				arr[i][k] = arr[i][k] - cnt*tmp;
				
				for(int d = 0; d < 4; d++) {
					//범위 내, -1 아님
					if(di[d]+i > -1 &&di[d]+i <sizeI&&dk[d]+k>-1&&dk[d]+k<sizeK&& arr[di[d]+i][dk[d]+k]!=-1) {
						tmpArr[di[d]+i][k+dk[d]] += tmp;
					}
				}
			}
		}
		
		//tmp와 arr 합쳐주기
		for(int i = 0; i < sizeI; i++) {
			for(int k = 0; k < sizeK; k++) {
				arr[i][k] += tmpArr[i][k];
			}
		}
	}

	private static int cal() {
		int result = 0;
		for (int i = 0; i < sizeI; i++) {
			for (int k = 0; k < sizeK; k++) {
				result += arr[i][k];
			}
		}
		return result + 2;
	}

}
