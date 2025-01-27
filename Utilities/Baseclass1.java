package Utilities;
import java.io.FileInputStream;
import java.util.Properties;
public class Baseclass1 {


    public String getDecryptedUsername() throws Exception {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\configCred.properties");
        prop.load(file);

        String encryptedUsername = prop.getProperty("username");
        return EncryptionUtil.decrypt(encryptedUsername);
    }

    public String getDecryptedPassword() throws Exception {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\configCred.properties");
        prop.load(file);

        String encryptedPassword = prop.getProperty("password");
        return EncryptionUtil.decrypt(encryptedPassword);
    }
}
