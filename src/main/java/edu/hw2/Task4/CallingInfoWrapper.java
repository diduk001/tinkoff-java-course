package edu.hw2.Task4;

public final class CallingInfoWrapper {
    private CallingInfoWrapper() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static CallingInfo callinginfo(Throwable thrown) {
        var frameBeforeCallingInfo = thrown.getStackTrace()[0];
        String className = frameBeforeCallingInfo.getClassName();
        String methodName = frameBeforeCallingInfo.getMethodName();
        return new CallingInfo(className, methodName);
    }

    public record CallingInfo(String className, String methodName) {
    }
}
