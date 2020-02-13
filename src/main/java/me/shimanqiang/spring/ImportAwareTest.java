package me.shimanqiang.spring;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author shimanqiang
 * @since 2020/1/19 18:13
 */
public class ImportAwareTest implements ImportAware {
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {

    }
}
