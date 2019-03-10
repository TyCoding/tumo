package cn.tycoding.admin.dto;

import org.junit.Test;

/**
 * @auther TyCoding
 * @date 2018/10/24
 */
public class CastTest {

    @Test
    public void run(){
        double x = 7;
        double y = 6;
        Double d = Math.ceil(x / y);
        System.out.println(d.intValue());
        System.out.println(Math.ceil((double)7 / (double)6));
    }
}
