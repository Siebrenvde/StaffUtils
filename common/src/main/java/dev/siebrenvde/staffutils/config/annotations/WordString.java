package dev.siebrenvde.staffutils.config.annotations;

import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.ConfigFieldAnnotationProcessor;
import dev.siebrenvde.configlib.libs.quilt.config.api.metadata.MetadataContainerBuilder;
import dev.siebrenvde.configlib.libs.quilt.config.api.metadata.MetadataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface WordString {

    MetadataType<WordStringImpl, Builder> TYPE = MetadataType.create(Builder::new, true);

    class WordStringImpl {}

    final class Builder implements MetadataType.Builder<WordStringImpl> {
        public WordStringImpl build() { return new WordStringImpl(); }
    }

    final class Processor implements ConfigFieldAnnotationProcessor<WordString> {
        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Override
        public void process(WordString annotation, MetadataContainerBuilder<?> builder) {
            builder.metadata(TYPE, Builder::build);
        }
    }

}
