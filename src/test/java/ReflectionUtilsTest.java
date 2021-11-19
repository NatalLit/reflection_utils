import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReflectionUtilsTest {
    @Test
    public void testCreateObjectByDefaultConstructor() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Object testObject = ReflectionUtils.create(TestVo.class);
        assertNotNull(testObject);
        Assertions.assertEquals(TestVo.class, testObject.getClass());
        TestVo testVo = (TestVo) testObject;
        Assertions.assertEquals("Object was created by default constructor", testVo.getTest());
        Assertions.assertNotEquals("Object was created by constructor with parameter", testVo.getTest());


    }

    @Test
    public void testInvokeMethodsWithoutParameters() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Object testObject = ReflectionUtils.create(TestVo.class);
        ReflectionUtils.invokeMethods(testObject);
        TestVo testVo = (TestVo) testObject;
        Assertions.assertTrue(testVo.isTesting);
    }

    @Test
    public void testPrintSignaturesDisplayMethodsWithFinalModifier() {
        TestVo testVo = new TestVo();
        ReflectionUtils.printSignatures(testVo);
    }


    @Test
    public void testPrintMethodsDisplayNotPublicMethods() {
        ReflectionUtils.printMethods(TestVo.class);
    }

    @Test
    public void testPrintParentClassAndInterfacesIsWorkingCorrectly() {
        ReflectionUtils.printParentClassAndInterfaces(TestVo.class);
    }

    @Test
    public void testChangeAllPrivateFieldsToDefaultValues() throws IllegalAccessException {
        TestVo testVo = new TestVo();
        ReflectionUtils.changeFields(testVo);
        Assertions.assertEquals(0, testVo.getTestInt());
        Assertions.assertEquals('\u0000', testVo.getTestChar());
        Assertions.assertFalse(testVo.isTestBoolean());

    }


}
