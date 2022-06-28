package code;

import com.krest.employee.scheduled.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @author: krest
 * @date: 2021/5/23 23:07
 * @description:
 */
public class test {
    @Test
    public void test(){
        // 得到前一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        System.out.println(day);
    }
}
