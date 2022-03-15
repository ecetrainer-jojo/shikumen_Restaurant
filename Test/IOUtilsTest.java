package Test;

import Utils.IOUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author ziangliang
 * @Date 2022-03-15
 * @Version 1.0
 */
class IOUtilsTest {
    @Test
    public void testReadMenuSelection(){
        System.out.println("please enter a number from 1 to 5");
        IOUtils.readMenuSelection();
    }

}