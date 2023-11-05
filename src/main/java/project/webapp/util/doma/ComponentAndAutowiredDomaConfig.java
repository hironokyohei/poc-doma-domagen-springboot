package project.webapp.util.doma;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AnnotateWith(annotations = {
        // 生成されたDao実装クラスに@Componentを付与する
        @Annotation(target = AnnotationTarget.CLASS, type = Component.class),
        // 生成されたDao実装クラスに@Componentを付与する
        @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class)
})
public @interface ComponentAndAutowiredDomaConfig {
}
