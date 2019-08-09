package net.geekcoder.samples.secureipc;

import android.content.Context;

import java.util.concurrent.atomic.AtomicInteger;

class TrustedContext {
    // The stub should capture the result of the caller identity check
    private AtomicInteger mIsCallerTrusted = new AtomicInteger(0);

    public void checkCallerIfNeeded(Context context) {
        if (mIsCallerTrusted.get() == 0) {
            CallerIdentity.ensureTrustedPackageForUid(context);
            mIsCallerTrusted.set(1);
        }
    }
}
