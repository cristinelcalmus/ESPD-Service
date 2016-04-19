package eu.europa.ec.grow.espd.domain.intf;

import java.math.BigDecimal;

/**
 * Created by ratoico on 1/5/16 at 2:47 PM.
 */
public interface MultipleAmountHolder {

    BigDecimal getAmount1();
    BigDecimal getAmount2();
    BigDecimal getAmount3();
    BigDecimal getAmount4();
    BigDecimal getAmount5();

    void setAmount1(BigDecimal amount1);
    void setAmount2(BigDecimal amount2);
    void setAmount3(BigDecimal amount3);
    void setAmount4(BigDecimal amount4);
    void setAmount5(BigDecimal amount5);

    String getCurrency1();
    String getCurrency2();
    String getCurrency3();
    String getCurrency4();
    String getCurrency5();

    void setCurrency1(String currency1);
    void setCurrency2(String currency2);
    void setCurrency3(String currency3);
    void setCurrency4(String currency4);
    void setCurrency5(String currency5);

}
