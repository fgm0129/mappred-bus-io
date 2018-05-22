package mappred.bus.io.test;

/**
 * @author fgm
 * @date 2018/5/21
 * @description
 */
public class MapppredTest {

    public static void main(String[] args) {

        long i=2;
        long a= (i + 0xfffL) & ~0xfffL;
        long b=~0xfffL;
        long c=0xfffL;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);




    }

}
