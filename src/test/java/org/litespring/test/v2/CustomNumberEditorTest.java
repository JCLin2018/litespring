package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomNumberEditor;

/**
 * 简单类型转换器
 */
public class CustomNumberEditorTest {

    @Test
    public void testConvertString() {
        CustomNumberEditor intEditor = new CustomNumberEditor(Integer.class, true);
        intEditor.setAsText("3");
        Object intValue = intEditor.getValue();
        Assert.assertTrue(intValue instanceof Integer);
        Assert.assertEquals(3, ((Integer) intValue).intValue());

        CustomNumberEditor doubleEditor = new CustomNumberEditor(Double.class, true);
        doubleEditor.setAsText("3.1");
        Object doubleValue = doubleEditor.getValue();
        Assert.assertTrue(doubleValue instanceof Double);

        Assert.assertTrue(3.1 == ((Double) doubleValue).doubleValue());

        intEditor.setAsText("");
        Assert.assertTrue(intEditor.getValue() == null);
        try {
            intEditor.setAsText("3.1");
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }

}
