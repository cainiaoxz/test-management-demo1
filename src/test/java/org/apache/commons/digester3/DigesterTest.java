package org.apache.commons.digester3;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class DigesterTest {

    private Students students;

    @Before
    public void setUp() throws Exception {
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.setNamespaceAware(false);

        digester.addObjectCreate("students", Students.class);
        digester.addObjectCreate("students/student", Student.class);
        digester.addBeanPropertySetter("students/student/id", "id");
        digester.addBeanPropertySetter("students/student/name", "name"); // 受注入缺陷影响
        digester.addBeanPropertySetter("students/student/age", "age");
        digester.addBeanPropertySetter("students/student/major", "major");
        digester.addSetNext("students/student", "addStudent");

        InputStream input = getClass().getClassLoader().getResourceAsStream("student.xml");
        assertNotNull("student.xml 文件未找到", input);

        students = digester.parse(input);
        assertNotNull("解析结果为空", students);
    }

    @Test
    public void testStudentCountShouldBeCorrect() {
        assertEquals("应解析出两个学生对象", 2, students.getStudentList().size());
    }

    @Test
    public void testEachFieldShouldBeAssigned() {
        for (Student student : students.getStudentList()) {
            assertBeanFieldsAssigned(student);
        }
    }

    @Test
    public void testAllStudentsAddedToList() {
        List<Student> list = students.getStudentList();
        assertNotNull("studentList 为空", list);
        assertFalse("studentList 不应为空", list.isEmpty());

        for (Object obj : list) {
            assertTrue("对象不是 Student 类型", obj instanceof Student);
        }
    }

    @Test
    public void testAgeFieldShouldBePositive() {
        for (Student student : students.getStudentList()) {
            assertTrue("年龄应为正数", student.getAge() > 0);
        }
    }

    @Test
    public void testToStringShouldContainFields() {
        for (Student student : students.getStudentList()) {
            String str = student.toString();
            assertTrue("toString 中缺少 id", str.contains("id"));
            assertTrue("toString 中缺少 major", str.contains("major"));
        }
    }

    @Test
    public void testNameFieldShouldNotBeNull() {
        for (Student student : students.getStudentList()) {
            assertNotNull("name 字段应有值，缺陷注入后为 null", student.getName());
        }
    }

    // 通用字段反射验证方法
    private void assertBeanFieldsAssigned(Object bean) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(bean);
                String name = field.getName();

                if (value == null) {
                    throw new AssertionError("字段 [" + name + "] 未被赋值（null）");
                }

                if (value instanceof String && ((String) value).trim().isEmpty()) {
                    throw new AssertionError("字段 [" + name + "] 为字符串但值为空");
                }

                if (value instanceof Number && ((Number) value).intValue() == 0) {
                    throw new AssertionError("字段 [" + name + "] 可能未被赋值（默认值 0）");
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException("无法访问字段: " + field.getName(), e);
            }
        }
    }
}
