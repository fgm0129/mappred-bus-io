package mappred.bus.io.core;

/**
 * @author fgm
 * @date 2018/5/21
 * @description
 */
public interface MappedBusMessage {
    /**
     * Writes a message to the bus.
     *
     * @param mem an instance of the memory mapped file
     * @param pos the start of the current record
     */
     void write(MemoryMappedFile mem, long pos);

    /**
     * Reads a message from the bus.
     *
     * @param mem an instance of the memory mapped file
     * @param pos the start of the current record
     */
     void read(MemoryMappedFile mem, long pos);

    /**
     * Returns the message type.
     *
     * @return the message type
     */
     int type();
}
