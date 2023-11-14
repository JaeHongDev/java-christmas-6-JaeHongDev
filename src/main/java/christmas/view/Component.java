package christmas.view;

public interface Component {
    void render();

    default ComponentRenderResult invoke() {
        try {
            this.render();
            return ComponentRenderResult.success();
        } catch (IllegalArgumentException illegalArgumentException) {
            return ComponentRenderResult.error(illegalArgumentException.getMessage());
        }
    }


    record ComponentRenderResult(boolean isComplete, String errorMessage) {

        public static ComponentRenderResult success() {
            return new ComponentRenderResult(true, "");
        }

        public static ComponentRenderResult error(String errorMessage) {
            return new ComponentRenderResult(false, errorMessage);
        }

        public boolean isContinue() {
            return !isComplete;
        }
    }

    
}
