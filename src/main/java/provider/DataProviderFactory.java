package provider;

import provider.wz.WZFiles;
import provider.wz.XMLWZFile;

import java.nio.file.Path;

public class DataProviderFactory {
    private static DataProvider getWZ(Path in) {
        return new XMLWZFile(in);
    }

    public static DataProvider getDataProvider(WZFiles in) {
        return getWZ(in.getFile());
    }
}
