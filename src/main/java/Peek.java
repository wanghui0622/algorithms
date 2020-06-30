import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author wanghui email:wanghuiaf@yonyou.com
 * @create 2020-05-16 下午5:39
 */
public class Peek {
    public static void main(String[] args) throws Exception {
        BigDecimal b1 = BigDecimal.ONE;
        BigDecimal b2 = BigDecimal.ONE;
        BigDecimal b3 =  b1.add(b2);
        System.out.println(b1);
        System.out.println(b3);
    }
}
