import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class SWEA5656 {
    // 벽돌을 담을 클래스
    static class Data {
        int i;
        int k;
        int size;
 
        public Data(int x, int y, int size) {
            super();
            this.i = x;
            this.k = y;
            this.size = size;
        }
    }
 
    static int n, w, h, max, tmp, bNums;
    // 벽돌 상태 저장
    static int[][] arr;
    // 새 배열
    static int[][] brr;
    // 구슬 위치 저장
    static int[] idxs;
    static Queue<Integer> que = new LinkedList<>();
    static Queue<Data> queD = new LinkedList<>();
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine()) + 1;
        String[] str;
        for (int tc = 1; tc < testCase; tc++) {
            // n, w, h 입력받기
            str = br.readLine().split(" ");
            n = Integer.parseInt(str[0]);
            w = Integer.parseInt(str[1]);
            h = Integer.parseInt(str[2]);
 
            // 벽돌 모양 입력받기
            // bNums : 전체 벽돌 값
            bNums = 0;
            int t;
            arr = new int[h][w];
            for (int i = 0; i < h; i++) {
                str = br.readLine().split(" ");
                for (int k = 0; k < w; k++) {
                    t = Integer.parseInt(str[k]);
                    arr[i][k] = t;
                    if (t > 0)
                        bNums++;
                }
            }
 
            // 초기값
            max = 0;
            idxs = new int[n];
            pick(0);
 
            System.out.println("#" + tc + " " + (bNums - max));
        }
    }
 
    // 벽돌 밑으로 내리기
    static void gravity() {
        que.clear();
        int i;
        for (int k = 0; k < w; k++) {
            // 한 줄씩 밑에서부터 탐색하며 빈 칸은 제외하고 que에 입력받기
            for (i = h - 1; i > -1; i--) {
                if (brr[i][k] != 0) {
                    que.add(brr[i][k]);
                    brr[i][k] = 0;
                }
            }
            // 한줄씩 밑에서부터 다시 채워넣기
            i = h - 1;
            while (!que.isEmpty()) {
                brr[i][k] = que.poll();
                i--;
            }
        }
    }
 
    // 중복순열을 이용해 떨어뜨릴 위치 정한 후 실행
    static void pick(int cnt) {
    	
    	//중간 return 하나만 넣어줘도 시간 반 이상 줄어듦
    	//벽돌이 다 깨진 적 있을 때 return
    	if(max == bNums)
    		return;
    	
        // n개의 위치 다 정했을 때
        if (cnt == n) {
            brr = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int k = 0; k < w; k++) {
                    brr[i][k] = arr[i][k];
                }
            }
 
            tmp = 0;
            // 벽돌을 떨어뜨린 후 max값 갱신(깨진 벽돌 수)
            for (int i = 0; i < n; i++) {
                down(idxs[i]);
                gravity();
            }
            max = Math.max(max, tmp);
            return;
        }
 
        //떨어뜨릴 위치 정하기
        for (int i = 0; i < w; i++) {
            idxs[cnt] = i;
            pick(cnt + 1);
        }
    }
 
    // 구슬 떨어뜨리기, 떨어진 구슬 수는 tmp에 더하기
    private static void down(int idx) {
        queD.clear();
        for (int i = 0; i < h; i++) {
            // 가장 처음 떨어지는 구슬 처리
            // 1 초과할 시 que에 넣기,
            if (brr[i][idx] != 0) {
                if (brr[i][idx] > 1)
                    queD.add(new Data(i, idx, brr[i][idx]));
                brr[i][idx] = 0;
                tmp++;
                break;
            }
        }
 
        Data tmpD;
        int[] di = { 0, 0, 1, -1 };
        int[] dk = { 1, -1, 0, 0 };
        int tmpI, tmpK, tmpSize;
        while (!queD.isEmpty()) {
            tmpD = queD.poll();
            tmpSize = tmpD.size;
            // 사방탐색
            for (int d = 0; d < 4; d++) {
                // 해당 방향으로 폭발의 범위만큼 가기
                for (int i = 1; i < tmpSize; i++) {
                    tmpI = tmpD.i + (di[d] * i);
                    tmpK = tmpD.k + (dk[d] * i);
 
                    // 범위 벗어날 때, 빈칸일 때
                    if (tmpI < 0 || tmpI > h - 1 || tmpK < 0 || tmpK > w - 1 || brr[tmpI][tmpK] == 0) {
                        continue;
                    }
 
                    if (brr[tmpI][tmpK] > 1) {
                        queD.add(new Data(tmpI, tmpK, brr[tmpI][tmpK]));
                    }
                    tmp++;
                    brr[tmpI][tmpK] = 0;
                }
            }
        }
    }
}