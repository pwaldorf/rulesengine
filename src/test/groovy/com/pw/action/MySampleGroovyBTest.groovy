package com.pw.action

import org.junit.Test
import static org.junit.Assert.*

class MySampleGroovyBTest {

    @Test
    void testSetSystemId_WithValidGroupKey() {
        // Arrange
        MySampleGroovyB groovyScript = new MySampleGroovyB()
        Map<String, Object> context = new HashMap<>()
        context.put("GroupKey", "TEST123")

        // Act
        groovyScript.setSystemId(context)

        // Assert
        assertEquals("TEST123", context.get("SourceSystemId"))
    }

    @Test
    void testSetSystemId_WithNullGroupKey() {
        // Arrange
        MySampleGroovyB groovyScript = new MySampleGroovyB()
        Map<String, Object> context = new HashMap<>()
        context.put("GroupKey", null)

        // Act
        groovyScript.setSystemId(context)

        // Assert
        assertNull(context.get("SourceSystemId"))
    }

    @Test
    void testSetSystemId_WithoutGroupKey() {
        // Arrange
        MySampleGroovyB groovyScript = new MySampleGroovyB()
        Map<String, Object> context = new HashMap<>()

        // Act
        groovyScript.setSystemId(context)

        // Assert
        assertNull(context.get("SourceSystemId"))
    }
}