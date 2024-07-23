package hello.core.annotataion;

import org.springframework.beans.factory.annotation.Qualifier;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
    ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")

// 이런식으러 @Qualifier("mainDiscountPolicy")을 걸어두고 annotatation을 만들어주는거지!
public @interface MainDiscountPolicy {
}