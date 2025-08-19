package ConfigFiles;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFile {

    public static Properties getProperties() throws Exception {
   Properties prop=new Properties();
        FileInputStream fs = new FileInputStream("src/test/java/ConfigFiles/config.properties");
       prop.load(fs);
       return prop;
    }
}
