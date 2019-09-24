package net.geekcoder.samples.secureipc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class SecuredContentProvider extends ContentProvider {

    private TrustedContext mTrustedContext = new TrustedContext();

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        mTrustedContext.checkCallerIfNeeded(this.getContext());
        return TrustedContext.runAndReturAsSelf(() -> {
            return null;
        });
    }

    @Override
    public String getType(Uri uri) {
        mTrustedContext.checkCallerIfNeeded(this.getContext());
        return TrustedContext.runAndReturAsSelf(() -> {
            return null;
        });
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        mTrustedContext.checkCallerIfNeeded(this.getContext());
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        mTrustedContext.checkCallerIfNeeded(this.getContext());
        return TrustedContext.runAndReturAsSelf(() -> {
            return 0;
        });
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        mTrustedContext.checkCallerIfNeeded(this.getContext());
        return TrustedContext.runAndReturAsSelf(() -> {
            return 0;
        });
    }
}
