package net.geekcoder.samples.secureipc;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;

class CallerIdentity {
    public static void ensureTrustedPackageForUid(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            Signature[] signatures = pm.getPackageInfo(pm.getNameForUid(Binder.getCallingUid()), PackageManager.GET_SIGNATURES).signatures;
            for (Signature sig : signatures) {
                if (Security.isTrusted(sig.toCharsString())) {
                    return;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        throw new SecurityException();
    }
}
