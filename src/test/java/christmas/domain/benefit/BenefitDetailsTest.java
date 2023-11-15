package christmas.domain.benefit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.domain.vo.Payment;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[도메인] 세부 할인 내역 ")
class BenefitDetailsTest {

    @Test
    void 할인_금액이_0원인_경우는_할인이_적용된_것이_아니다() {
        final var benefitDetails = BenefitDetails.create(new Payment(0))
                .merge(new LinkedHashMap<>() {{
                    this.put(Benefit.SPECIAL_DISCOUNT, new Payment(0));
                }}).getBenefitList();
        assertThat(benefitDetails.containsKey(Benefit.SPECIAL_DISCOUNT.getBenefitName())).isFalse();
    }

    @Test
    void 혜택_내역과_금액을_가져올_수_있습니다() {
        final var benefitDetails = BenefitDetails.create(new Payment(0))
                .merge(new LinkedHashMap<>() {{
                    this.put(Benefit.CHRISTMAS_DISCOUNT, new Payment(1000));
                    this.put(Benefit.SPECIAL_DISCOUNT, new Payment(2000));
                    this.put(Benefit.WEEKDAY_DISCOUNT, new Payment(3000));
                }});
        final var benefitList = benefitDetails.getBenefitList();

        assertAll(
                () -> assertThat(benefitList.get(Benefit.CHRISTMAS_DISCOUNT.getBenefitName())).isEqualTo(1000),
                () -> assertThat(benefitList.get(Benefit.SPECIAL_DISCOUNT.getBenefitName())).isEqualTo(2000),
                () -> assertThat(benefitList.get(Benefit.WEEKDAY_DISCOUNT.getBenefitName())).isEqualTo(3000)
        );
    }

    @Test
    void 총_혜택_금액을_계산할_수_있습니다() {
        final var benefitDetails = BenefitDetails.create(new Payment(0))
                .merge(new LinkedHashMap<>() {{
                    this.put(Benefit.CHRISTMAS_DISCOUNT, new Payment(1000));
                    this.put(Benefit.SPECIAL_DISCOUNT, new Payment(2000));
                    this.put(Benefit.WEEKDAY_DISCOUNT, new Payment(3000));
                    this.put(Benefit.GIVEAWAY_BENEFIT, new Payment(2000));
                }});

        assertThat(benefitDetails.calculateBenefitAmount()).isEqualTo(8000);
    }

    @Test
    void 할인_후_금액을_계산할_수_있습니다() {
        final var benefitDetails = BenefitDetails.create(new Payment(142_000))
                .merge(new LinkedHashMap<>() {{
                    this.put(Benefit.CHRISTMAS_DISCOUNT, new Payment(1200));
                    this.put(Benefit.SPECIAL_DISCOUNT, new Payment(1000));
                    this.put(Benefit.WEEKDAY_DISCOUNT, new Payment(4046));
                    this.put(Benefit.GIVEAWAY_BENEFIT, new Payment(25000));
                }});

        assertThat(benefitDetails.calculateAmountAfterDiscount()).isEqualTo(135_754);
    }

}