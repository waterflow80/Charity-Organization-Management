package org.charityOrganization.organization;

import javafx.scene.Scene;

import java.util.Objects;

public class AppNavigator {
    private static Scene scene;

    private static boolean isInitialized() {
        return scene != null;
    }

    /**
     * This method should be called before using any of the other methods.
     */
    public static void init(Scene scene) {
        Objects.requireNonNull(scene, "Scene cannot be null");
        AppNavigator.scene = scene;
    }

    public static void navigateToLoginScreen() {
        throwExceptionIfNotInitialized();

    }

    public static void throwExceptionIfNotInitialized() {
        if (!isInitialized()) {
            throw new RuntimeException("AppNavigator is not initialized, call AppNavigator#init before using it.");
        }
    }
}
