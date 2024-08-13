/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author alexo
 */
public class FilesNotMatchException extends RuntimeException {
    public FilesNotMatchException(){
        super("Los archivos no tienen concordancia en el número de preguntas y el número de respuestas.");
    }
}
