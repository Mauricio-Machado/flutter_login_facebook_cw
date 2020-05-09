package ru.innim.flutter_facebook_wrapper;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.HashMap;

public class Results {
    private enum LoginStatus {
        Success,
        Cancel,
        Error
    }

    public static HashMap<String, Object> loginSuccess(final LoginResult result) {
        return new HashMap<String, Object>() {{
            put("status", LoginStatus.Success.name());
            put("accessToken", accessToken(result.getAccessToken()));
        }};
    }

    public static HashMap<String, Object> loginCancel() {
        return new HashMap<String, Object>() {{
            put("status", LoginStatus.Cancel.name());
        }};
    }

    public static HashMap<String, Object> loginError() {
        return new HashMap<String, Object>() {{
            put("status", LoginStatus.Error.name());
        }};
    }

    public static HashMap<String, Object> accessToken(final AccessToken accessToken) {
        if (accessToken == null)
            return null;

        return new HashMap<String, Object>() {{
            put("token", accessToken.getToken());
            put("userId", accessToken.getUserId());
            put("expires", accessToken.getExpires().getTime());
            put("permissions", new ArrayList<>(accessToken.getPermissions()));
            put("declinedPermissions", new ArrayList<>(accessToken.getDeclinedPermissions()));
        }};
    }

    static HashMap<String, Object> userProfile(final Profile profile) {
        if (profile == null)
            return null;

        return new HashMap<String, Object>() {{
            put("userId", profile.getId());
            put("name", profile.getName());
            put("firstName", profile.getFirstName());
            put("middleName", profile.getMiddleName());
            put("lastName", profile.getLastName());
        }};
    }
}
