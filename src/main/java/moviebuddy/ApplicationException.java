package moviebuddy;

/**
 * @author springrunner.kr@gmail.com
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class CommandNotFoundException extends ApplicationException {

        public CommandNotFoundException() {
            super("command not found.");
        }

    }

    public static class UndefinedCommandActionException extends ApplicationException {

        public UndefinedCommandActionException() {
            super("command action is undefined.");
        }

    }

    public static class InvalidCommandArgumentsException extends ApplicationException {

        public InvalidCommandArgumentsException() {
            super("input error, please try again!");
        }

        public InvalidCommandArgumentsException(String message) {
            super(message);
        }

        public InvalidCommandArgumentsException(Throwable cause) {
            super(String.format("%s: %s", cause.getClass().getSimpleName(), cause.getMessage()), cause);
        }

    }

}

// 이 클래스는 영화친구 애플리케이션이 동작하는 과정에서 문제가 있을 때, 즉 오류가 있을 때 이 오류를 예외 객체로 잡아서 외부에 전파하기 위해서 사용이 된다.
// 메시지를 담을 수도 있고, 메시지와 함께 그 상위에 발생된 예외를 담을 수 있도록 구성이 되어 있다.