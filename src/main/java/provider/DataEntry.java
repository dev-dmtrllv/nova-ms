package provider;
/**
 * @author Matze
 */
public interface DataEntry extends DataEntity {
    String getName();
    int getSize();
    int getChecksum();
    int getOffset();
}
