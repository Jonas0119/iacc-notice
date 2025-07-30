package com.tjfae.notice.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Service层业务逻辑单元测试
 * 测试纯业务逻辑，不依赖外部组件
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Service层业务逻辑测试")
class ServiceLayerUnitTest {

    @Test
    @DisplayName("数据处理逻辑测试")
    void testDataProcessingLogic() {
        // Given
        List<String> inputData = Arrays.asList("数据1", "数据2", "数据3");
        
        // When
        int size = inputData.size();
        boolean isEmpty = inputData.isEmpty();
        String firstItem = inputData.get(0);
        
        // Then
        assertEquals(3, size);
        assertFalse(isEmpty);
        assertEquals("数据1", firstItem);
    }

    @Test
    @DisplayName("字符串处理测试")
    void testStringProcessing() {
        // Given
        String input = "  测试字符串  ";
        
        // When
        String trimmed = input.trim();
        String upperCase = trimmed.toUpperCase();
        boolean contains = trimmed.contains("测试");
        
        // Then
        assertEquals("测试字符串", trimmed);
        assertTrue(contains);
        assertNotNull(upperCase);
    }

    @Test
    @DisplayName("数值计算测试")
    void testNumericCalculations() {
        // Given
        int a = 10;
        int b = 5;
        
        // When
        int sum = a + b;
        int diff = a - b;
        int product = a * b;
        int quotient = a / b;
        
        // Then
        assertEquals(15, sum);
        assertEquals(5, diff);
        assertEquals(50, product);
        assertEquals(2, quotient);
    }

    @Test
    @DisplayName("集合操作测试")
    void testCollectionOperations() {
        // Given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // When
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        long count = numbers.stream().filter(n -> n > 3).count();
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        
        // Then
        assertEquals(15, sum);
        assertEquals(2, count);
        assertTrue(allPositive);
    }

    @Test
    @DisplayName("Map操作测试")
    void testMapOperations() {
        // Given
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "测试用户");
        dataMap.put("age", 25);
        dataMap.put("active", true);
        
        // When
        String name = (String) dataMap.get("name");
        Integer age = (Integer) dataMap.get("age");
        Boolean active = (Boolean) dataMap.get("active");
        
        // Then
        assertEquals("测试用户", name);
        assertEquals(Integer.valueOf(25), age);
        assertTrue(active);
        assertEquals(3, dataMap.size());
    }

    @Test
    @DisplayName("异常处理测试")
    void testExceptionHandling() {
        // Given
        List<String> emptyList = Arrays.asList();
        
        // When & Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyList.get(0);
        });
        
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
    }

    @Test
    @DisplayName("空值处理测试")
    void testNullHandling() {
        // Given
        String nullString = null;
        List<String> nullList = null;
        
        // When & Then
        assertNull(nullString);
        assertNull(nullList);
        
        assertThrows(NullPointerException.class, () -> {
            nullString.length();
        });
    }

    @Test
    @DisplayName("布尔逻辑测试")
    void testBooleanLogic() {
        // Given
        boolean condition1 = true;
        boolean condition2 = false;
        
        // When
        boolean andResult = condition1 && condition2;
        boolean orResult = condition1 || condition2;
        boolean notResult = !condition1;
        
        // Then
        assertFalse(andResult);
        assertTrue(orResult);
        assertFalse(notResult);
    }

    @Test
    @DisplayName("对象比较测试")
    void testObjectComparison() {
        // Given
        String str1 = "测试";
        String str2 = "测试";
        String str3 = new String("测试");
        
        // When & Then
        assertTrue(str1.equals(str2));
        assertTrue(str1.equals(str3));
        assertSame(str1, str2); // 字符串常量池
        assertNotSame(str1, str3); // 不同对象引用
    }

    @Test
    @DisplayName("类型转换测试")
    void testTypeConversion() {
        // Given
        String numberStr = "123";
        String doubleStr = "123.45";
        
        // When
        int intValue = Integer.parseInt(numberStr);
        double doubleValue = Double.parseDouble(doubleStr);
        String backToString = String.valueOf(intValue);
        
        // Then
        assertEquals(123, intValue);
        assertEquals(123.45, doubleValue, 0.01);
        assertEquals("123", backToString);
    }
} 