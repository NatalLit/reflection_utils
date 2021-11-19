public class TestVo extends TestParentClass implements TestInterface {
    private String test;
    private int testInt =10;
    public boolean isTesting;
    private double testDouble = 2.5d;
    private float testFloat = 1.5f;
    private char testChar = '\u0068';
    private boolean isTestBoolean = true;

    public char getTestChar() {
        return testChar;
    }

    public boolean isTestBoolean() {
        return isTestBoolean;
    }

    public int getTestInt() {
        return testInt;
    }

    public TestVo() {
        test = "Object was created by default constructor";
    }

    public TestVo(int testNumber) {
        test = "Object was created by constructor with parameter";
    }

    public String getTest() {
        isTesting = true;
        System.out.println("Method getTest is working");
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    private final static void printMethodWithParameter(int count) {
        System.out.println("Method printMethodWithParameter is working");
    }

    public String printMethod() {
        isTesting = true;
        return "Method printMethod is working";
    }
}
