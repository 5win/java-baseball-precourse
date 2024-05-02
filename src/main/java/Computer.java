import java.util.Set;
import java.util.stream.Collectors;

public class Computer {
    static final int NUM_LENGTH = 3;

    private String randNum;         // Random number
    private int strikeScore;
    private int ballScore;

    public Computer() {}

    /**
     * Random number 반환
     * @return Random Number
     */
    public String getRandNum() {
        return randNum;
    }

    /**
     * 스트라이크 점수 반환
     * @return 스트라이크 점수
     */
    public int getStrikeScore() {
        return strikeScore;
    }

    /**
     * 볼 점수 반환
     * @return 볼 점수
     */
    public int getBallScore() {
        return ballScore;
    }

    /**
     * Random number 생성하는 Setter
     */
    public void generateRandNum() {
        boolean[] numList = new boolean[10];
        int cnt = 0;
        int randNum = 0;

        while(cnt < 3) {
            int newNum = (int)(Math.random() * 10);
            if(numList[newNum]) {
                continue;
            }
            numList[newNum] = true;
            randNum = randNum * 10 + newNum;
            cnt++;
        }
        this.randNum = String.format("%03d", randNum);
    }

    /**
     * inputNum 유효성 검증
     * @param inputNum 입력된 수
     */
    public void inputValidationCheck(String inputNum) {
        isInputValid(inputNum);
    }

    /**
     * 각 유효 조건을 만족하는지 여부 확인
     * @param inputNum
     * @return 유효 조건 모두 만족 시 true 반환
     */
    private boolean isInputValid(String inputNum) {
        return inputLengthCheck(inputNum) && inputDuplicationCheck(inputNum) && inputDomainCheck(inputNum);
    }

    /**
     * 입력값 길이 검증
     * @param inputNum
     * @return NUM_LENGTH와 동일하면 true
     */
    private boolean inputLengthCheck(String inputNum) {
        return inputNum.length() == NUM_LENGTH;
    }

    /**
     * 입력값 중복 여부 검증
     * @param inputNum
     * @return 중복이 없으면 true
     */
    private boolean inputDuplicationCheck(String inputNum) {
        Set<Character> set = inputNum.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        return set.size() == NUM_LENGTH;
    }

    /**
     * 입력값이 모두 숫자인지 검증
     * @param inputNum
     * @return 모두 숫자이면 true
     */
    private boolean inputDomainCheck(String inputNum) {
        return inputNum.matches("^[0-9]+$");
    }

    /**
     * User의 inputNum에 대한 점수를 계산
     * @param inputNum User의 inputNum
     */
    public void calcScore(String inputNum) {
        strikeScore = ballScore = 0;
        for(int i = 0; i < inputNum.length(); i++) {
            if(isStrike(inputNum, i)) {
                strikeScore++;
            } else if(isBall(inputNum, i)) {
                ballScore++;
            }
        }
    }

    /**
     * 스트라이크인지 판단
     * @param inputNum 입력된 수
     * @param idx 확인할 숫자의 위치(인덱스)
     * @return 스트라이크라면 true를 반환
     */
    private boolean isStrike(String inputNum, int idx) {
        return inputNum.charAt(idx) == randNum.charAt(idx);
    }

    /**
     * 볼인지 판단
     * @param inputNum 입력된 수
     * @param idx 확인할 숫자의 위치(인덱스)
     * @return 볼이라면 true 반환
     */
    private boolean isBall(String inputNum, int idx) {
        for(int i = 0; i < inputNum.length(); i++) {
            if(idx != i && inputNum.charAt(idx) == randNum.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 추측 결과를 출력
     */
    public void printScore() {
        if(isNothing()) {
            System.out.println("낫싱");
            return;
        }
        if(ballScore > 0) {
            System.out.print(ballScore + "볼 ");
        }
        if(strikeScore > 0) {
            System.out.print(strikeScore + "스트라이크 ");
        }
        System.out.println();
    }

    /**
     * 볼과 스트라이크가 모두 해당되지 않는지 확인
     * @return 볼과 스트라이크 모두 없으면 true를 반환
     */
    private boolean isNothing() {
        return ballScore == 0 && strikeScore == 0;
    }

    public void printInputMessage() {
        System.out.print("숫자를 입력해 주세요 : ");
    }
}
