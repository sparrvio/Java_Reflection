package edu.school21.app;

import edu.school21.annotations.HtmlForm;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if (element.getAnnotation(HtmlForm.class) != null) {
                    annotationForm.addAll(element.getAnnotationMirrors());
                } else {
                    annotationInput.addAll(element.getAnnotationMirrors());
                }
            }
        }

        if (!annotationInput.isEmpty()) {
            writeInFileHtmlForm(annotationForm.pop().toString());
        }
        int count = 0;
        while (!annotationInput.isEmpty()) {
            count++;
            writeInFileHtmlInput(annotationInput.pop().toString(), count);
        }

        if (count == 3)
            writeHtmlFooter();
        return false;
    }

    private void writeHtmlFooter(){
        try {
            FileWriter fileWriter = new FileWriter("target/classes/user_form.html", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("<input type=\"submit\" value=\"Send\" onclick=\"displayMessage()\">\n" +
                    "</form>\n" +
                    "<script>\n" +
                    "function displayMessage() {event.preventDefault();\n" +
                    "  var firstName = document.getElementById(\"1\").value;\n" +
                    "  var lastName = document.getElementById(\"2\").value;\n" +
                    "  var password = document.getElementById(\"3\").value;\n" +
                    "  alert(\"First Name: \" + firstName + \"\\nLast Name: \" + lastName + \"\\nPassword: \" + password);\n" +
                    "}\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeInFileHtmlInput(String annotationInput, int count) {
        String type = null;
        String name = null;
        String placeholder = null;

        Pattern pattern = Pattern.compile("type=\"([^\"]*)\".*name=\"([^\"]*)\".*placeholder=\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(annotationInput);

        if (matcher.find()) {
            type = matcher.group(1);
            name = matcher.group(2);
            placeholder = matcher.group(3);
        }

        try {
            FileWriter fileWriter = new FileWriter("target/classes/user_form.html", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("<input type=\"" + type + "\" id=\"" + count + "\" name=\"" + name +"\" placeholder=\"" + placeholder +"\">");

            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeInFileHtmlForm(String annotationForm) {
        String filename = null;
        String action = null;
        String method = null;
        Pattern pattern = Pattern.compile("fileName=\"([^\"]*)\".*action=\"([^\"]*)\".*method=\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(annotationForm);

        if (matcher.find()) {
            filename = matcher.group(1);
            action = matcher.group(2);
            method = matcher.group(3);
        }

        try {
            FileWriter fileWriter = new FileWriter("target/classes/" + filename, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../../src/main/java/edu/school21/resources/style.css\">\n" +
                    "</head>\n" +
                    "<body>");
            bufferedWriter.write("<form action = \"" + action + "\" method = \"" + method + "\">");
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
