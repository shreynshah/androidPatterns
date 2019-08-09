package net.geekcoder.samples.secureipc;

import java.util.HashMap;
import java.util.Map;

class Security {
    static {
        HashMap<String, String> signatures = new HashMap<>();
        signatures.put("signature1", "description 1");
        signatures.put("signature2", "description 2");

        trustedSignatures = new HashMap<String, String>(signatures);
    }

    private final static Map<String, String> trustedSignatures;

    public static boolean isTrusted(String signature) {
        return trustedSignatures.get(signature) != null;
    }
}
