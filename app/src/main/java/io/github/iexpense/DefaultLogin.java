package io.github.iexpense;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

// http://android-developers.blogspot.in/2011/03/identifying-app-installations.html
public class DefaultLogin {
    private static final String TAG = "DefaultLogin";
    private static String sID = null;
    private static final String INSTALLATION = "login";

    public synchronized static String id(Context context) {
        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists()) {
                    Log.d(TAG, "DefaultLogin: creating new installation file");
                    writeInstallationFile(installation);
                }

                sID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile file = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) file.length()];
        file.readFully(bytes);
        file.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }
}
