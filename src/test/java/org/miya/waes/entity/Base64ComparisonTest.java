package org.miya.waes.entity;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests, for methods in {@link org.miya.waes.entity.Base64Comparison} class
 *
 * @author Yasin Kızılkaya
 */

class Base64ComparisonTest {

    @Test
    void testEntityHasDefaultConstructorExpectNotNullObject() {
        Base64Comparison base64Comparison = new Base64Comparison();
        assertNotNull(base64Comparison);
        assertNull(base64Comparison.getId());
        assertNull(base64Comparison.getLeftSide());
        assertNull(base64Comparison.getRightSide());
    }

    @Test
    void testEntityHasIdBasedConstructorExpectNotNullObject() {
        Base64Comparison base64Comparison = new Base64Comparison(123L);
        assertNotNull(base64Comparison);
        assertNotNull(base64Comparison.getId());
        assertNull(base64Comparison.getLeftSide());
        assertNull(base64Comparison.getRightSide());
    }

    @Test
    void testEntityHasGetterAndSetterForFields() {
        Base64Comparison base64Comparison = new Base64Comparison(456L);
        base64Comparison.setLeftSide("YmFzaWM=");
        base64Comparison.setRightSide("amFzaWM=");

        assertNotNull(base64Comparison);
        assertEquals(456L, base64Comparison.getId());
        assertEquals("YmFzaWM=", base64Comparison.getLeftSide());
        assertEquals("amFzaWM=", base64Comparison.getRightSide());
    }

    @Test
    void testEntityAnnotationsExpectOnlyEntityAnnotation() {
        Class base64Comparision = Base64Comparison.class;

        Annotation[] annotations = base64Comparision.getAnnotations();

        assertEquals(1, annotations.length);
        assertEquals("javax.persistence.Entity", annotations[0].annotationType().getName());
    }

}
