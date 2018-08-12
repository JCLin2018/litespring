package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.litespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * class读取
 */
public class ClassReaderTest {

	private ClassReader reader;
	@Before
	public void startUp() throws IOException{
		ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreServiceImpl.class");
		reader = new ClassReader(resource.getInputStream());//ClassReader是ASM的读取.class类
	}

	@Test
	public void testGetClasMetaData() throws IOException {

		ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
		reader.accept(visitor, ClassReader.SKIP_DEBUG);//开始读取Class，当读取字段、方法后，调用visitField,visitMethod
		Assert.assertFalse(visitor.isAbstract());
		Assert.assertFalse(visitor.isInterface());
		Assert.assertFalse(visitor.isFinal());
		Assert.assertEquals("org.litespring.service.v4.PetStoreServiceImpl", visitor.getClassName());
		Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
		Assert.assertEquals(0, visitor.getInterfaceNames().length);
	}
	
	@Test
	public void testGetAnnonation() throws Exception{
		AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
		reader.accept(visitor, ClassReader.SKIP_DEBUG);
		String annotation = "org.litespring.stereotype.Component";
		Assert.assertTrue(visitor.hasAnnotation(annotation));
		AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
		Assert.assertEquals("petStore", attributes.get("value"));
	}
}
