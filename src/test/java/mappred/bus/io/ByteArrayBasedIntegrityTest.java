package mappred.bus.io;

import mappred.bus.io.core.MappedBusReader;
import mappred.bus.io.core.MappedBusWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author fgm
 * @date 2018/5/21
 * @description
 */
public class ByteArrayBasedIntegrityTest {

    public static final String FILE_NAME = "/tmp/bytearraybased-integrity-test";

    public static final long FILE_SIZE = 4000000L;

    public static final int NUM_WRITERS = 9;

    public static final int RECORD_LENGTH = 10;

    public static final int NUM_RECORDS = 10000;

    public static final int NUM_RUNS = 1000;

    @Before
    public void before() {
        new File(FILE_NAME).delete();
    }

    @After
    public void after() {
        new File(FILE_NAME).delete();
    }

    @Test
    public void test() throws Exception {
        for (int i = 0; i < NUM_RUNS; i++) {
            runTest();
        }
    }

    private void runTest() throws Exception {
        new File(FILE_NAME).delete();

        Writer[] writers = new Writer[NUM_WRITERS];
        for (int i = 0; i < writers.length; i++) {
            writers[i] = new Writer(i + 1);
        }
        for (int i = 0; i < writers.length; i++) {
            writers[i].start();
        }

        MappedBusReader reader = new MappedBusReader(FILE_NAME, FILE_SIZE, RECORD_LENGTH);
        reader.open();

        int records = 0;
        byte[] data = new byte[RECORD_LENGTH];
        while (true) {
            if (reader.next()) {
                int length = reader.readBuffer(data, 0);
                assertEquals(data[0], length);
                for (int i=0; i < length; i++) {
                    if (data[0] != data[i]) {
                        fail();
                        return;
                    }
                }
                records++;
                if (records >= NUM_RECORDS * NUM_WRITERS) {
                    break;
                }
            }
        }

        assertEquals(NUM_RECORDS * NUM_WRITERS, records);

        reader.close();
    }

    class Writer extends Thread {

        private final int id;

        public Writer(int id) {
            this.id = id;
        }

        public void run() {
            try {
                MappedBusWriter writer = new MappedBusWriter(ByteArrayBasedIntegrityTest.FILE_NAME, ByteArrayBasedIntegrityTest.FILE_SIZE, ByteArrayBasedIntegrityTest.RECORD_LENGTH, true);
                writer.open();

                byte[] data = new byte[ByteArrayBasedIntegrityTest.RECORD_LENGTH];
                Arrays.fill(data, (byte)id);

                for (int i=0; i < ByteArrayBasedIntegrityTest.NUM_RECORDS; i++) {
                    writer.write(data, 0, id);
                }
                writer.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}
