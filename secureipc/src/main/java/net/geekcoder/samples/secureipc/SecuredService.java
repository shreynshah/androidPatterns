package net.geekcoder.samples.secureipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicInteger;

public class SecuredService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        // Return a unique instance of the stub for each onBind
        return new ISecuredAPI.Stub() {
            private TrustedContext mTrustedContext = new TrustedContext();

            @Override
            public void specialFunction(int param1) throws RemoteException {
                // Each protected function should check the caller identity
                mTrustedContext.checkCallerIfNeeded(SecuredService.this);

                TrustedContext.runasSelf(() -> {
                    specialFunctionInternal(param1);
                });
            }
        };
    }

    private void specialFunctionInternal(int param1) {
        // TODO: Do work
    }
}
