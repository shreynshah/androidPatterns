package net.geekcoder.samples.secureipc;

import android.content.Context;
import android.os.Binder;

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

    public static void runAsSelf(IAction action) {
        long token = Binder.clearCallingIdentity();
        try {
            action.doWork();
        } finally {
            Binder.restoreCallingIdentity(token);
        }
    }

        public static <T> T runAndReturAsSelf(IActionWithResult<T> action) {
        long token = Binder.clearCallingIdentity();
        try {
            return action.doWork();
        } finally {
            Binder.restoreCallingIdentity(token);
        }
    }
}
