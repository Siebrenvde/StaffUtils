package dev.siebrenvde.staffchat.config.annotations;

import dev.siebrenvde.configlib.libs.quilt.config.api.annotations.ConfigFieldAnnotationProcessor;
import dev.siebrenvde.configlib.libs.quilt.config.api.metadata.MetadataContainerBuilder;
import dev.siebrenvde.configlib.metadata.SkipWrite;
import dev.siebrenvde.staffchat.StaffChat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequireProxy {

    final class Processor implements ConfigFieldAnnotationProcessor<RequireProxy> {
        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Override
        public void process(RequireProxy annotation, MetadataContainerBuilder<?> builder) {
            if(!StaffChat.getPlatform().isProxy()) {
                builder.metadata(SkipWrite.TYPE, SkipWrite.Builder::build);
            }
        }
    }

}
