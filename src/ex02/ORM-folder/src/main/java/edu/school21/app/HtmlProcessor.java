package edu.school21.app;

import edu.school21.annotations.*;
import edu.school21.repositories.DataSourceConfig;
import edu.school21.repositories.RepositoryJdbcImpl;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SupportedAnnotationTypes("edu.school21.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        LinkedList<AnnotationMirror> annotationForm = new LinkedList<>();
        LinkedList<AnnotationMirror> annotationInput = new LinkedList<>();
        System.out.println("HtmlProcessor");

        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if (element.getAnnotation(OrmEntity.class) != null) {
                    annotationForm.addAll(element.getAnnotationMirrors());
                    System.out.println(annotationForm.addAll(element.getAnnotationMirrors()));
                } else {
                    annotationInput.addAll(element.getAnnotationMirrors());
                    System.out.println(annotationForm.addAll(element.getAnnotationMirrors()));
                }
            }
        }
        return false;
    }
}
