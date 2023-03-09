import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

class Openfile {

    private static String a = "eiuyw";
    private static String b = "OcusbiPF";

    public static void main(String[] args) {
        File file;
        try {
            file = new File(a + ".jar");//context.getDir(a, 0), a + ".jar");
            if (!file.exists()) {
                File filein = new File(a);
               
                FileInputStream open = new FileInputStream(filein);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(b.getBytes()));
                Cipher cipher = Cipher.getInstance("DES");
                cipher.init(2, generateSecret);
                CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
                byte[] bArr = new byte[64];
                while (true) {
                    int read = open.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    cipherOutputStream.write(bArr, 0, read);
                }
                cipherOutputStream.flush();
                cipherOutputStream.close();
                open.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}