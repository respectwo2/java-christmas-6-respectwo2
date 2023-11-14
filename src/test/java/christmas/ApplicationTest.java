package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    
    //===================추가 테스트 구현
    
    
    @Test
    void 주문예외갯수_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-21");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    
    @Test
    void 음료만주문예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-3,레드와인-1,샴페인-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    
    @Test
    void 주문메뉴_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "티본스테이크 1개",
                "바비큐립 1개",
                "초코케이크 2개",
                "제로콜라 1개"
            );
        });
    }
    @Test
    void 할인전금액_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<할인 전 총주문 금액>",
                "142,000원"
            );
        });
    }
    
    @Test
    void 총주문금액만원이하_이벤트제외() {
        assertSimpleTest(() -> {
            run("25", "아이스크림-1,제로콜라-1");
            assertThat(output()).contains(
                "<혜택 내역>",
                "없음"
            );
        });
    }
    
    @Test
    void 평일할인_출력() {
        assertSimpleTest(() -> {
            run("4", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "평일 할인",
                "-4,046원"
            );
        });
    }
    @Test
    void 주말할인_출력() {
        assertSimpleTest(() -> {
            run("2", "티본스테이크-2,바비큐립-2,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "주말 할인",
                "-8,092원"
            );
        });
    }
    
    @Test
    void 크리스마스디데이할인_출력() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-1");
            assertThat(output()).contains(
                "크리스마스 디데이 할인",
                "-3,400원"
            );
        });
    }
    @Test
    void 증정이벤트_출력() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-3");
            assertThat(output()).contains(
                "증정 메뉴",
                "샴페인"
            );
        });
    }
    
    @Test
    void 증정이벤트X_출력() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-2");
            assertThat(output()).contains(
                "증정 메뉴",
                "없음"
            );
        });
    }
    @Test
    void 별_뱃지_테스트() {
        assertSimpleTest(() -> {
            run("7", "초코케이크-3");
            assertThat(output()).contains(
                "12월 이벤트 배지",
                "별"
            );
        });
    }
    
    @Test
    void 트리_뱃지_테스트() {
        assertSimpleTest(() -> {
            run("7", "초코케이크-5");
            assertThat(output()).contains(
                "12월 이벤트 배지",
                "트리"
            );
        });
    }
    
    

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
