package edu.hw2;

public final class Task4 {
    private Task4() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    public static CallingInfo callinginfo() {
        Throwable t = new Throwable();
        var frameBeforeCallingInfo = t.getStackTrace()[1]; // t.getStackTrace[0] is callingInfo function
        String className = frameBeforeCallingInfo.getClassName();
        String methodName = frameBeforeCallingInfo.getMethodName();
        return new CallingInfo(className, methodName);
    }

    public record CallingInfo(String className, String methodName) {
    }
}
